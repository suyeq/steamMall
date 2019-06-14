package com.example.steam.dao;

import com.example.steam.entity.SpikeShopCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: 苍术
 * @date: 2019-06-06
 * @time: 15:54
 */
@Repository
public interface SpikeShopCartDao {

    @Insert("insert into spikeshopcart(userid,spikegameid) value(#{userId},#{spikeGameId})")
    int addSpikeShopCart(SpikeShopCart spikeShopCart);

    @Select("select * from spikeshopcart where userid=#{userId} and spikegameid=#{spikeGameId}")
    SpikeShopCart findSpikeShopCart(@Param("userId")long userId,
                                    @Param("spikeGameId")long spikeGameId);

    @Select("select * from spikeshopcart where userid=#{userId}")
    SpikeShopCart findSpikeShopCartByUserId(@Param("userId")long userId);


    @Delete("delete spikeshopcart where userid=#{userId}")
    int deleteSpikeShopCart(@Param("userId")long userId);
}
