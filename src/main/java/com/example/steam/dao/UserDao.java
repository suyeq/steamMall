package com.example.steam.dao;

import com.example.steam.entity.User;
import org.apache.ibatis.annotations.*;
import org.hibernate.validator.constraints.EAN;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Insert("insert into user(nickname,salt,email,password,avatar,playtime,commentnum,buygames,isadmin,introduction,province) value(#{nickName},#{salt},#{email},#{password}" +
            ",#{avatar},#{playTime},#{commentNum},#{buyGames},#{isAdmin},#{introduction},#{province})")
    int addUser(User user);

    @Update("update user set nickname=#{nickName},salt=#{salt},email=#{email},password=#{password}," +
            "playtime=#{playTime},commentnum=#{commentNum},buygames=#{buyGames},isadmin=#{isAdmin}, " +
            "avatar=#{avatar},country=#{country},province=#{province},introduction=#{introduction} where id=#{id}")
    int updateUser(User user);

    @Select("select * from user")
    List<User> findAllUser();

    @Delete("delete from user where email=#{email}")
    int deleteUser(@Param("email") String email);

//    @Select("select * from user where id=#{id}")
//    User findUserById();

}
