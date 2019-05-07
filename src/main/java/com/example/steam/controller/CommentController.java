package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.service.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @Autowired
    CommentService commentService;

    @ResponseBody
    @RequestMapping("/comment/{id}")
    public String findOneCommentById(@PathVariable("id")long id){
        return JSON.toJSONString(commentService.findOneCommentById(id));
    }


}
