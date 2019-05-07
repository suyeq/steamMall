package com.example.steam.dao;

import com.example.steam.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-07
 * @time: 16:06
 */
@Repository
public interface CommentDao {

    @Select("select * from comment where id=#{id}")
    Comment findOneCommentById(@Param("id") long id);

    @Select("select count(*) from comment")
    int commentSum();
}
