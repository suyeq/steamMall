package com.example.steam.dao;

import com.example.steam.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 10:54
 */
@Repository
public interface UserDao {

    @Select("select * from user where email=#{email}")
    User findUserByEmail(@Param("email") String email);

    @Insert("insert into user(nickname,email,password,avatar,playtime,commentnum,buygames,isadmin) value(#{nickName},#{email},#{password}" +
            ",#{avatar},#{playTime},#{commentNum},#{buyGames},#{isAdmin})")
    int addUser(User user);

//    @Select("select * from user where id=#{id}")
//    User findUserById();

}
