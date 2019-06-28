package com.example.steam.dao;

import com.example.steam.entity.SystemNeed;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-06
 * @time: 22:41
 */
@Repository
public interface SystemNeedDao {

    @Select("select * from systemneed where id=#{id}")
    SystemNeed findSystemNeedById(@Param("id") long id);

    @Insert("insert into systemneed(operatingsystem,cpu,ram,graphicscard,directx,network," +
            "rom,soundcard) value(#{operatingSystem},#{cpu},#{ram},#{graphicsCard}," +
            "#{directx},#{network},#{rom},#{soundCard})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    long addSystemNeed(SystemNeed systemNeed);
}
