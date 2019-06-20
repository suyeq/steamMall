package com.example.steam.service;

import com.example.steam.dao.UserDao;
import com.example.steam.entity.User;
import com.example.steam.mq.Event;
import com.example.steam.mq.EventType;
import com.example.steam.mq.MQProducer;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.CookieKey;
import com.example.steam.redis.key.EmailKey;
import com.example.steam.redis.key.UserKey;
import com.example.steam.utils.*;
import com.example.steam.vo.LoginUser;
import com.example.steam.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 16:58
 */
@Service
public class UserService {

    Logger log= LoggerFactory.getLogger(UserService.class);

    @Value("${server.servlet.session.cookie.max-age}")
    int cookieMaxAge;

    @Autowired
    UserDao userDao;

    @Autowired
    RedisService redisService;

    @Autowired
    ImageService imageService;

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MQProducer mqProducer;

    /**
     * 找回密码，加入消息队列发送邮件
     * @param email
     * @return
     */
    public ResultMsg updateFindPassword(String email){
        User user=redisService.get(UserKey.USER_ID,email,User.class);
        if (user==null){
            return ResultMsg.USER_NO;
        }
        String newPassword=UUIDUntil.randomUUID().substring(0,7);
        String finalPassword=Md5PassUtil.md5Conver(newPassword,user.getSalt());
        user.setPassword(finalPassword);
        redisService.set(UserKey.USER_ID,email,user);
        ((UserService)applicationContext.getBean("userService")).updateUser(user);
        mqProducer.productEvent(new Event(EventType.FIND_PASSWORD).setEtrMsg(Event.EMAIL,email).setEtrMsg(Event.NEW_PASSWORD,newPassword));
        return ResultMsg.SUCCESS;
    }


    /**
     * 更新用户购买游戏的数量
     * @param email
     * @return
     */
    public int updateBuyGames(String email){
        User user=redisService.get(UserKey.USER_ID,email,User.class);
        user.setBuyGames(user.getBuyGames()+1);
        redisService.set(UserKey.USER_ID,email,user);
        return ((UserService)applicationContext.getBean("userService")).updateUser(user);
    }

    /**
     * 更新用户的评论数量
     * @param email
     * @return
     */
    public int updateCommnetNum(String email){
        User user=redisService.get(UserKey.USER_ID,email,User.class);
        user.setCommentNum(user.getCommentNum()+1);
        redisService.set(UserKey.USER_ID,email,user);
        return ((UserService)applicationContext.getBean("userService")).updateUser(user);
    }

    /**
     * 更新一个用户
     * @param user
     * @return
     */
    public int updateUser(User user){
        return userDao.updateUser(user);
    }

    /**
     * 通过邮件找到用户
     * @param email
     * @return
     */
    public User findByEmail(String email){
        User user=redisService.get(UserKey.USER_ID,email,User.class);
        if (user!=null){
            return user;
        }
        user=userDao.findUserByEmail(email);
        if (user!=null){
            redisService.set(UserKey.USER_ID,email,user);
        }
        return user;
    }

    /**
     * 返回一个uservo
     * @param email
     * @return
     */
    public UserVo findUserVoByEmail(String email){
        User user=findByEmail(email);
        UserVo userVo=new UserVo();
        userVo.setId(user.getId());
        userVo.setAvatarImage(imageService.findImageUrlById(user.getAvatar()));
        userVo.setLv(user.getLv());
        userVo.setCountry(user.getCountry());
        userVo.setProvince(user.getProvince());
        userVo.setNickName(user.getNickName());
        userVo.setIntroduction(user.getIntroduction());
        return userVo;
    }


    /**
     * 增加一个用户
     * @param user
     * @return
     */
    public int addUser(User user){
        redisService.set(UserKey.USER_ID,user.getEmail(),user);
        return userDao.addUser(user);
    }

    /**
     * 通过token从缓存中获取user对象
     * @param response
     * @param cookieToken
     * @return
     */
    public LoginUser getUserByToken(HttpServletResponse response, String cookieToken) {
        if (cookieToken == null){
            return null;
        }
        LoginUser loginUser=redisService.get(UserKey.COOKIE_ID,cookieToken,LoginUser.class);
        //log.error(loginUser.toString()+" "+2);
        if (loginUser != null){
            addCookie(response,cookieToken,null,loginUser);
        }
        return loginUser;
    }

    /**
     * 处理登录信息
     * @param email
     * @param password
     * @param request
     * @param response
     * @return
     */
    public ResultMsg handleLogin(String email, String password,HttpServletRequest request,HttpServletResponse response) {
        log.error(email+" "+password);
        String cookieId=redisService.get(CookieKey.EMAIL,email,String.class);
        Cookie cookie=findCookie(request);
        if (StringUtils.isNotEmpty(cookieId) && cookie == null){
            return ResultMsg.HAD_Login;
        }
        /**
         * 此处必须有外部调用，内部调用不会走代理路线
         * 即AOP会失效
         */
        User user=((UserService)applicationContext.getBean("userService")).findByEmail(email);
        if (user == null){
            return ResultMsg.NO_EMAIL;
        }
        String finalPass= Md5PassUtil.secondMd5(password,user.getSalt());
        if (!finalPass.equals(user.getPassword())){
            return ResultMsg.PASS_ERROR;
        }
        cookieIsNullAndCreate(request,response,user);
        return ResultMsg.LOGIN_SUCCESS;
    }

    /**
     * 处理注册用户
     * @param email
     * @param password
     * @param code 验证码
     * @return
     */
    public ResultMsg handleRegister(String email,String password,String code){
        String verificationCode=redisService.get(EmailKey.VERIFICATION_CODE,email,String.class);
        if (verificationCode==null){
            return ResultMsg.CODE_INVALID;
        }
        User user=((UserService)applicationContext.getBean("userService")).findByEmail(email);
        if (user!=null){
            return ResultMsg.HAD_REGISTER;
        }
        if (!code.equals(verificationCode)){
            return ResultMsg.CODE_ERROR;
        }
        /**
         * 随机昵称，salt值，两次MD5密码加密
         */
        String nickName=UUIDUntil.randomUUID().substring(0,5);
        String salt=UUIDUntil.randomUUID().substring(0,6);
        String finalPass=Md5PassUtil.md5Conver(password,salt);
        User newUser=new User(nickName,email,salt,finalPass);
        ((UserService) applicationContext.getBean("userService")).addUser(newUser);
        return ResultMsg.REGISTER_SUCCESS;
    }

    /**
     * 删除登录信息
     * @param email
     * @return
     */
    public ResultMsg handleLogout(String email){
        String cookieId=redisService.get(CookieKey.EMAIL,email,String.class);
        redisService.del(CookieKey.EMAIL,email);
        redisService.del(UserKey.COOKIE_ID,cookieId);
        log.info("已注销"+cookieId);
        return ResultMsg.SUCCESS(cookieId);
    }

    private void cookieIsNullAndCreate(HttpServletRequest request, HttpServletResponse response, User user){
        Cookie cookie=findCookie(request);
        String cookieId;
        if (cookie == null || StringUtils.isEmpty(cookie.getValue())){
            cookieId=UUIDUntil.randomUUID();
        }else {
            cookieId=cookie.getValue();
        }
        addCookie(response,cookieId,user,null);
    }

    private void addCookie(HttpServletResponse response,String cookieId,User user,LoginUser loginUr){
        Cookie cookie=new Cookie(StaticField.COOKIE_KEY,cookieId);
        cookie.setMaxAge(cookieMaxAge);
        /**
         * 不同路径下cookie不同
         * 要设置相同的路径，否则会出现一样的cookie
         */
        cookie.setPath("/");
        log.info(cookieMaxAge+"");
        response.addCookie(cookie);
        //UserKey.COOKIE_ID.setExpiredTime(cookieMaxAge);
        LoginUser loginUser;
        if (loginUr!=null){
            loginUser=loginUr;
        }else {
            loginUser=new LoginUser();
            loginUser.setId(user.getId());
            loginUser.setEmail(user.getEmail());
            loginUser.setIsAdmin(user.getIsAdmin());
            loginUser.setNickName(user.getNickName());
            loginUser.setAvatar(imageService.findImageUrlById(user.getAvatar()));
        }
        redisService.set(UserKey.COOKIE_ID,cookieId,loginUser);
        //CookieKey.EMAIL.setExpiredTime(cookieMaxAge);
        redisService.set(CookieKey.EMAIL,loginUser.getEmail(),cookieId);
    }

    private Cookie findCookie(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if (cookies==null){
            return null;
        }
        for (int i=0;i<cookies.length;i++){
            if (cookies[i].getName().equals(StaticField.COOKIE_KEY)){
                return cookies[i];
            }
        }
        return null;
    }

    public LoginUser converViewLoginUser(User user) {
        if (user != null){
            return new LoginUser(user,imageService.findImageById(user.getAvatar()));
        }
        return null;
    }
}
