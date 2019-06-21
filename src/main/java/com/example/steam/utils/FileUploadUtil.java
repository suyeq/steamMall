package com.example.steam.utils;

import com.example.steam.entity.Image;
import com.example.steam.service.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * 文件上传工具
 * @author: 苍术
 * @date: 2019-06-21
 * @time: 11:12
 */
@Service
public class FileUploadUtil {

    @Value("${imageAddress}")
    private String imageServer;

    @Value("${imageUrl}")
    private String imageUrl;

    @Autowired
    ImageService imageService;

    public ResultMsg handleFileUpload(MultipartFile file){
        String origName=file.getOriginalFilename();
        File imgFile=new File(imageServer,origName);
        while (imgFile.exists()){
            origName=NamedDuplicateUtil.nameConversion(origName);
            imgFile=new File(imageServer,origName);
        }
        if (!imgFile.getParentFile().exists()){
            imgFile.getParentFile().mkdirs();
        }
        try{
            OutputStream outputStream=new FileOutputStream(imgFile);
            IOUtils.copy(file.getInputStream(),outputStream);
            outputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
        Image image=new Image(imageUrl+origName,"avatar","avatar");
        imageService.addImage(image);
        return ResultMsg.SUCCESS(image);
    }





}
