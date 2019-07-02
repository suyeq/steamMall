package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.service.UserService;
import com.example.steam.utils.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: 苍术
 * @date: 2019-06-20
 * @time: 15:01
 */
@Controller
public class UserController {

    Logger log=LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/user/{email}")
    public String findUserByEmail(@PathVariable("email") String email){
        return JSON.toJSONString(ResultMsg.SUCCESS(userService.findUserVoByEmail(email)));
    }

    @ResponseBody
    @RequestMapping("/user/edit")
    public String updateUserInfo(@RequestParam("email")String email,
                                 @RequestParam("nickName")String nickName,
                                 @RequestParam("introduction")String introduction,
                                 @RequestParam("country")String country,
                                 @RequestParam("province")String province,
                                 @RequestParam("avatar")String avatar,
                                 @RequestParam("avatarAddress")String avatarAddress){
        if (avatar.equals("")){
            return JSON.toJSONString(ResultMsg.SUCCESS(userService.updateNickNameAndCountryAndProvinceAndAvatarAndIntroduction(email, nickName, country, province, 0, null, introduction)));
        }
        return JSON.toJSONString(ResultMsg.SUCCESS(userService.updateNickNameAndCountryAndProvinceAndAvatarAndIntroduction(email, nickName, country, province, Long.parseLong(avatar), avatarAddress, introduction)));
    }

    /**
     * 找到所有的用户
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/alluser")
    public String findAllUser(){
        return JSON.toJSONString(ResultMsg.SUCCESS(userService.findAllUser()));
    }

    /**
     * 新增一个用户
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/add")
    public String addUser(HttpServletRequest request){
        return JSON.toJSONString(userService.handleAddNewUser(request));
    }


    @ResponseBody
    @RequestMapping("/user/updateAdmin/{email}")
    public String updateAdminStatu(@PathVariable("email")String email){
        return JSON.toJSONString(userService.updateAdminStatu(email));
    }

    @ResponseBody
    @RequestMapping("/user/delete/{email}")
    public String handleDeleteUser(@PathVariable("email") String email){
        return JSON.toJSONString(userService.handleDeleteUser(email));
    }

    @ResponseBody
    @RequestMapping("/user/edit/{email}")
    public String handleEditUser(@PathVariable("email") String email,
                                 HttpServletRequest request){
        return JSON.toJSONString(userService.handleEditUser(email,request));
    }

    @ResponseBody
    @RequestMapping("/user/editpass/{email}")
    public String handleEditPass(@PathVariable("email") String email,
                                 @RequestParam("newPass")String newPass,
                                 @RequestParam("confimPass")String confimPass){
        return JSON.toJSONString(userService.handleEditPass(email,newPass,confimPass));
    }
}
