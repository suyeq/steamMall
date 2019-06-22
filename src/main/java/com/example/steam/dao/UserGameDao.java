package com.example.steam.dao;

import com.example.steam.entity.UserGame;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

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

    @Insert("insert into user_game(email,gameid,playtime) value(#{email},#{gameId},0)")
    int addGameToUser(UserGame userGame);

    @Select("select * from user_game where email=#{email} order by lastplay desc")
    List<UserGame> findGamesByEmailOrderByLastPlayDesc(@Param("email")String email);

    @Select("select * from user_game where email=#{email} and gameid=#{gameId}")
    UserGame findGameByEmailAndGameId(@Param("email")String email, @Param("gameId") long gameId);


}
