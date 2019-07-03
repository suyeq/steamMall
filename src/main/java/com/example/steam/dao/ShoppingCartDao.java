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

    @Select("select * from shoppingcart where email=#{email}")
    List<ShoppingCart> findCartByUserEmail(@Param("email")String email);

    @Insert("insert into shoppingcart(email,posterimage,gameprice,gamename,gameid) value(#{email},#{posterImage},#{gamePrice},#{gameName},#{gameId})")
    int addOneCart(ShoppingCart shoppingCart);

    @Delete("delete from shoppingcart where id=#{id}")
    int deleteOneGameById(@Param("id") long id);

    @Delete("delete from shoppingcart where email=#{email}")
    int deleteAllGameByUserEmail(@Param("email")String email);

    @Select("select * from shoppingcart")
    List<ShoppingCart> findAllCart();
}
