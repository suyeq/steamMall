package com.example.steam.dao;

import com.example.steam.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-19
 * @time: 11:40
 */
@Repository
public interface ShoppingCartDao {

    @Select("select * from shoppingcart where userid=#{userId}")
    List<ShoppingCart> findCartByUserId(@Param("userId")long userId);

    @Insert("insert into shoppingcart(userid,posterimage,gameprice,gamename,gameid) value(#{userId},#{posterImage},#{gamePrice},#{gameName},#{gameId})")
    int addOneCart(ShoppingCart shoppingCart);

    @Delete("delete from shoppingcart where id=#{id}")
    int deleteOneGameById(@Param("id") long id);

    @Delete("delete from shoppingcart where userid=#{userId}")
    int deleteAllGameByUserId(@Param("userId")long userId);
}
