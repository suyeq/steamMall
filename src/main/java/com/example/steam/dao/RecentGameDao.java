package com.example.steam.dao;

import com.example.steam.entity.RecentGame;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * -----------
 * 穷则独善其身
 * 达则兼济天下
 * -----------
 *
 * @author: 苍术
 * @date: 2019-06-20
 * @time: 10:53
 */
@Repository
public interface RecentGameDao {

    @Select("select * from recentgame where email=#{email} order by lastplay desc")
    List<RecentGame> findRecentGameListByEmail(@Param("email") String email);
}
