package com.example.steam.dao;

import com.example.steam.entity.GameLabel;
import com.example.steam.entity.Label;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-29
 * @time: 20:26
 */
@Repository
public interface LabelDao {

    @Delete("delete from game_label where gameid=#{gameId}")
    int deleteGameLabelByGameId(@Param("gameId")long gameId);

    @Select("select name from label where id=#{id}")
    String findLabelNameById(@Param("id") long id);

    @Select("select * from game_label where gameid=#{gameId}")
    List<GameLabel> findLabelsByGameId(@Param("gameId") long gameId);

    @Select("select * from label where id=#{labelId}")
    Label findLabelByLabelId(@Param("labelId") long labelId);

    @Select("select * from label where name =#{labelName}")
    Label findLabelByLableName(@Param("labelName") String labelName);

    @Select("select * from game_label where gameid=#{gameId} and labelid=#{labelId}")
    Label findLabelByLabelIdAndGameId(@Param("gameId")long gameId,@Param("labelId")long labelId);

    @Update("update game_label set hotnum=hotnum+1 where gameid=#{gameId} and labelid=#{labelId}")
    int labelHotNumIncr(@Param("gameId") long gameId,@Param("labelId") long labelId);

    @Insert("insert into label(name,hotnum) value(#{name},#{hotNum})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int addLabel(Label label);

    @Insert("insert into game_label(gameid,labelid,hotnum) value(#{gameId},#{labelId},#{hotNum})")
    int addLabelInGame(GameLabel gameLabel);

    @Select("select * from label")
    List<Label> findAllLabel();

    @Delete("delete from label where id=#{labelId}")
    int deleteLabelByLabelId(@Param("labelId") long labelId);



}
