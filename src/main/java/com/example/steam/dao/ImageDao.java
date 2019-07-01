package com.example.steam.dao;

import com.example.steam.entity.GameImage;
import com.example.steam.entity.Image;
import org.apache.ibatis.annotations.*;
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

    @Insert("insert into image(url,gamename,type) value(#{url},#{gameName},#{type})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    long addImage(Image image);

    @Insert("insert into game_image(gameid,image1,image2,image3,image4,image5) value(" +
            "#{gameId},#{image1},#{image2},#{image3},#{image4},#{image5})")
    long addImageToGame(GameImage gameImage);

    @Update("update game_image set image1=#{image1},image2=#{image2},image3=#{image3}," +
            "image4=#{image4},image5=#{image5} where gameid=#{gameId}")
    long updateImageToGame(GameImage gameImage);
}
