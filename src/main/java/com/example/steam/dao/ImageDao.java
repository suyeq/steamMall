package com.example.steam.dao;

import com.example.steam.entity.GameImage;
import com.example.steam.entity.Image;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-27
 * @time: 14:24
 */
@Repository
public interface ImageDao {

    @Select("select * from image where id=#{id}")
    Image findImageById(@Param("id") long id);

    @Select("select * from game_image where gameid=#{gameId}")
    GameImage findImagesByGameId(@Param("gameId") long gameId);

    @Select("select url from image where id=#{id}")
    String findImageUrlById(@Param("id") long id);
}
