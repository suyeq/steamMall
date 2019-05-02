package com.example.steam.dao;

import com.example.steam.entity.GameLabel;
import com.example.steam.entity.Label;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("select name from label where id=#{id}")
    String findLabelNameById(@Param("id") long id);

    @Select("select * from game_label where gameid=#{gameId}")
    List<GameLabel> findLabelsByGameId(@Param("gameId") long gameId);

    @Select("select * from label where id=#{labelId}")
    Label findLabelByLabelId(@Param("labelId") long labelId);
}
