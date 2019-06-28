package com.example.steam.controller;

import com.alibaba.fastjson.JSON;
import com.example.steam.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: 苍术
 * @date: 2019-06-21
 * @time: 11:09
 */
@Controller
public class FileController {

    @Autowired
    FileUploadUtil fileUploadUtil;

    @ResponseBody
    @RequestMapping("/file/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        return JSON.toJSONString(fileUploadUtil.handleFileUpload(file));
    }




}
