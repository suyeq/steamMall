package com.example.steam.dao;

import com.example.steam.entity.SpikeGame;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

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

    @Update("update spikegame set gameid=#{gameId},postergame=#{posterGame}," +
            "spikeprice=#{spikePrice},stockcount=#{stockCount},starttime=#{startTime}," +
            "endtime=#{endTime},gameprice=#{gamePrice} where id=#{id}")
    int updateOneSpikeGame(SpikeGame spikeGame);

    @Select("select * from spikegame")
    List<SpikeGame> findAllSpikeGame();

    @Delete("delete from spikegame where id=#{spikeId}")
    int deleteSpikeGame(@Param("spikeId") long spikeId);

    @Insert("insert into spikegame(gameid,postergame,spikeprice,stockcount,starttime," +
            "endtime,gameprice) value(#{gameId},#{posterGame},#{spikePrice},#{stockCount}," +
            "#{startTime},#{endTime},#{gamePrice})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    long addSpikeGame(SpikeGame spikeGame);
}
