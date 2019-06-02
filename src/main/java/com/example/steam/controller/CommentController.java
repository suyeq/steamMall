package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.entity.Comment;
import com.example.steam.service.CommentService;
import com.example.steam.utils.ResultMsg;
import org.apache.ibatis.annotations.Param;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-07
 * @time: 20:40
 */
@Controller
public class CommentController{

    Logger log=LoggerFactory.getLogger(CommentController.class);

    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping("/comment/{id}")
    public String findOneCommentById(@PathVariable("id")long id){
        return JSON.toJSONString(ResultMsg.SUCCESS(commentService.findOneCommentById(id)));
    }

    @ResponseBody
    @RequestMapping("/commentDetail/{gameId}/time/{index}")
    public String findComentDetailByIndexTime(@PathVariable("gameId")long gameId,
                                          @PathVariable("index")long index){
        return JSON.toJSONString(ResultMsg.SUCCESS(commentService.findRangeCommentDetailByTime(index,gameId)));
    }

    @ResponseBody
    @RequestMapping("/commentDetail/{gameId}/zan/{index}")
    public String findComentDetailByIndexZan(@PathVariable("gameId")long gameId,
                                          @PathVariable("index")long index){
        return JSON.toJSONString(ResultMsg.SUCCESS(commentService.findRangeCommentDetailByZanNum(index,gameId)));
    }

    @ResponseBody
    @RequestMapping("/comment/add")
    public String addComment(@RequestParam("content")String content,
                             @RequestParam("email")String email,
                             @RequestParam("gameId")int gameId,
                             @RequestParam("recommendStatu")int recommendStatu){
        Comment comment=new Comment(content,email,gameId,recommendStatu);
        return JSON.toJSONString(commentService.addComment(comment));
    }


}
