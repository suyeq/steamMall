package com.example.steam.dao;

import com.example.steam.entity.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-07
 * @time: 16:06
 */
@Repository
public interface CommentDao {

    @Delete("delete from comment where id=#{id}")
    int deleteComment(@Param("id")long id);

    @Select("select * from comment where id=#{id}")
    Comment findOneCommentById(@Param("id") long id);

    @Select("select count(*) from comment")
    int commentSum();

    @Select("select count(*) from comment where email=#{email}")
    int commentSumByEmail(@Param("email") String email);

    @Select("select max(id) from comment")
    long findLastCommentId();

    @Insert("insert into comment(content,commentdate,email,gameid,zannum,cainum,recommendstatu)" +
            "value(#{content},NOW(),#{email},#{gameId},0,0,#{recommendStatu})")
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    long addComment(Comment comment);

    @Update("update comment set zannum=#{zanNum},caiNum=#{caiNum},happy=#{happy},content=#{content} where id=#{id}")
    int updateComment(Comment comment);

    @Select("select * from comment where email=#{email} order by commentdate desc limit #{start},#{end}")
    List<Comment> findComentsByEmailOrderByTimeDesc(@Param("start")long start,@Param("end")long end,@Param("email") String email);
}
