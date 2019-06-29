package com.example.steam.utils;

import com.example.steam.entity.GameImage;
import com.example.steam.entity.Image;
import com.example.steam.service.GameService;
import com.example.steam.service.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @Autowired
    GameService gameService;

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
        String imageId=handleMultipartFile(file);
        return ResultMsg.SUCCESS(imageId);
    }

    /**
     * 多文件属性上传
     * 第一次上传则插入图片
     * 否则就修改图片
     * @param request
     * @return
     */
    public ResultMsg handleMultipleAttributrUpload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
        MultipartFile file=multipartHttpServletRequest.getFile("file");
        String gameId=multipartHttpServletRequest.getParameter("gameId");
        String imageUrl=handleMultipartFile(file);
        handleImageDao(imageUrl,Long.parseLong(gameId));
        return ResultMsg.SUCCESS;
    }

    /**
     * 处理文件流，并新增图片到数据库里
     * 返回图片的id
     * @param file
     * @return
     */
    private String handleMultipartFile(MultipartFile file){
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
        return imageUrl+origName;
    }

    /**
     * 处理数据库增加
     * @param imageUrl
     */
    private void handleImageDao(String imageUrl,long gameId){
        Image image=new Image(imageUrl,"","");
        long newImageId=imageService.addImage(image);
        GameImage gameImage=imageService.findGameImageByGameId(gameId);
        if (gameImage == null){
            /**
             *  1.加入集合存入
             *  2. 当集合中满了6条的时候
             *  3. 取第一条做海报，其它插入做介绍
             */
           List<Image> imageList=ImageHolderUtil.getImageHolder();
           if (imageList == null){
               imageList=new LinkedList<>();
               imageList.add(image);
               ImageHolderUtil.setImageHolder(imageList);
           }else {
               imageList.add(image);
               ImageHolderUtil.setImageHolder(imageList);
               if (imageList.size() == 6){
                   /**
                    * 插入数据库
                    */
                    GameImage newGameImage=new GameImage(gameId,imageList.get(1).getId(),imageList.get(2).getId(),
                            imageList.get(3).getId(),imageList.get(4).getId(),imageList.get(5).getId());
                    imageService.addImageToGame(newGameImage);
                    gameService.updateGamePosterImage(gameId,newImageId);
                    ImageHolderUtil.setImageHolder(null);
               }
           }
        }else {

        }
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
