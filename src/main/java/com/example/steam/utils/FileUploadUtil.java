package com.example.steam.utils;

import com.example.steam.entity.Image;
import com.example.steam.service.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger log= LoggerFactory.getLogger(FileUploadUtil.class);

    @Value("${imageAddress}")
    private String imageServer;

    @Value("${imageUrl}")
    private String imageUrl;

    private final static int MAX_IMAGE_SIZE=1024*1024*10;

    private final static String[] allImageType=new String[]{"jpg","png","jpeg","gif"};

    @Autowired
    ImageService imageService;

    /**
     * 处理图片的上传
     * @param file
     * @return
     */
    public ResultMsg handleFileUpload(MultipartFile file){
        log.info("图片大小："+file.getSize());
        log.info("图片类型"+file.getContentType());
        if (file.getSize()>MAX_IMAGE_SIZE){
            return ResultMsg.IMAGE_OVERSIZE;
        }
        if (!isImageType(file.getContentType())){
            return ResultMsg.IMAGE_TYPE_ERROR;
        }
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
        Image image=new Image(imageUrl+origName,"","");
        imageService.addImage(image);
        return ResultMsg.SUCCESS(image);
    }

    /**
     * 是否是图片
     * @param imageType
     * @return
     */
    private static boolean isImageType(String imageType){
        for (int i=0;i<allImageType.length;i++){
            if (imageType.contains(allImageType[i])){
                return true;
            }
        }
        return false;
    }

//    public static void main(String []args){
//        System.out.println(isImageType("application/octet-stream"));
//    }



}
