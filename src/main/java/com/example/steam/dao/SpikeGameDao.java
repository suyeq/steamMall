package com.example.steam.dao;

import com.example.steam.entity.SpikeGame;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-16
 * @time: 23:09
 */
@Repository
public interface SpikeGameDao {

    @Select("select * from spikegame where id=#{id}")
    SpikeGame findOneById(@Param("id") long id);

    @Select("select * from spikegame where gameid=#{gameid}")
    SpikeGame findOneByGameId(@Param("gameId")long gameId);
}
