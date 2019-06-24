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

    @Insert("insert into spikeshopcart(email,spikegameid) value(#{email},#{spikeGameId})")
    int addSpikeShopCart(SpikeShopCart spikeShopCart);

    @Select("select * from spikeshopcart where email=#{email} and spikegameid=#{spikeGameId}")
    SpikeShopCart findSpikeShopCart(@Param("email")String email,
                                    @Param("spikeGameId")long spikeGameId);

    @Select("select * from spikeshopcart where email=#{email}")
    SpikeShopCart findSpikeShopCartByUserEmail(@Param("email")String email);


    @Delete("delete from spikeshopcart where email=#{email}")
    int deleteSpikeShopCart(@Param("email")String email);
}
