package com.example.steam.dao;

import com.example.steam.entity.GameType;
import com.example.steam.entity.Type;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 10:58
 */
@Repository
public interface TypeDao {

    @Select("select * from type where typename=#{typeName}")
    Type findTypeByTypeName(@Param("typeName") String typeName);

    @Select("select * from game_type where gameid=#{gameId}")
    List<GameType> findTypesByGameId(@Param("gameId") long gameId);

    @Select("select * from type where id=#{id}")
    Type findTypeById(@Param("id") long id);

    @Select("select typename from type where id=#{id}")
    String findTypeNameById(@Param("id") long id);

    @Select("select typename from type")
    List<String> findAllType();
}
