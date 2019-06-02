package com.example.steam.dao;

import com.example.steam.entity.UserGame;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 苍术
 * @date: 2019-06-02
 * @time: 21:25
 */
@Repository
public interface UserGameDao {

    @Select("select * from user_game where email=#{email}")
    List<UserGame> findGamesByEmail(@Param("email")String email);
}
