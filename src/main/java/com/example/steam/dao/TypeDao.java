package com.example.steam.dao;

import com.example.steam.entity.Type;
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

    @Select("select * from type")
    List<Type> findAllType();
}
