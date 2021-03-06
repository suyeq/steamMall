package com.example.steam.service;

import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.ImageDao;
import com.example.steam.entity.GameImage;
import com.example.steam.entity.Image;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.vo.GameDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-27
 * @time: 14:27
 */
@Service
public class ImageService {

    private final static String DEFAULT_IMAGE="https://steamcdn-a.akamaihd.net/steamcommunity/public/images/avatars/95/959986934ff7a3782a0746a96d5f5df8524d9c2b_full.jpg";

    @Autowired
    ImageDao imageDao;

    @Autowired
    GameService gameService;

    @Autowired
    RedisService redisService;

    @Autowired
    ApplicationContext applicationContext;

    /**
     * 更新游戏介绍图
     * @param gameImage
     * @return
     */
    public long updateImageToGame(GameImage gameImage){
        long result=imageDao.updateImageToGame(gameImage);
        GameDetail gameDetail=gameService.findGameById(gameImage.getGameId());
        List<Image> imageList=((ImageService)applicationContext.getBean("imageService")).findGameImagesByGameId(gameImage.getGameId());
        gameDetail.setImageIntro1(imageList.get(0).getUrl());
        gameDetail.setImageIntro2(imageList.get(1).getUrl());
        gameDetail.setImageIntro3(imageList.get(2).getUrl());
        gameDetail.setImageIntro4(imageList.get(3).getUrl());
        gameDetail.setImageIntro5(imageList.get(4).getUrl());
        redisService.set(GameKey.GAME_ID,gameImage.getGameId()+"",gameDetail);
        return result;
    }

    /**
     * 增加介绍图片给游戏
     * 并更新缓存中的游戏介绍图
     * @param gameImage
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public long addImageToGame(GameImage gameImage){
        long result=imageDao.addImageToGame(gameImage);
        GameDetail gameDetail=gameService.findGameById(gameImage.getGameId());
        List<Image> imageList=((ImageService)applicationContext.getBean("imageService")).findGameImagesByGameId(gameImage.getGameId());
        gameDetail.setImageIntro1(imageList.get(0).getUrl());
        gameDetail.setImageIntro2(imageList.get(1).getUrl());
        gameDetail.setImageIntro3(imageList.get(2).getUrl());
        gameDetail.setImageIntro4(imageList.get(3).getUrl());
        gameDetail.setImageIntro5(imageList.get(4).getUrl());
        redisService.set(GameKey.GAME_ID,gameImage.getGameId()+"",gameDetail);
        return result;
    }

    /**
     * 得到一个gameimage
     * @param gameId
     * @return
     */
    public GameImage findGameImageByGameId(long gameId){
        return imageDao.findImagesByGameId(gameId);
    }

    /**
     * 增加一张图片，并返回新增id
     * @param image
     * @return
     */
    public Long addImage(Image image){
        imageDao.addImage(image);
        return image.getId();
    }

    /**
     * 通过id得到一个image
     * @param id
     * @return
     */
    public Image findImageById(long id){
        return imageDao.findImageById(id);
    }

    /**
     * 通过id得到image的url
     * @param id
     * @return
     */
    public String findImageUrlById(long id){
          return imageDao.findImageUrlById(id);
    }

    /**
     * 自定义数据库
     * @param id
     * @param dataSource
     * @return
     */
    public String findImageUrlById(long id, String dataSource){
        DynamicDataSourceHolder.putDataSource(dataSource);
        return imageDao.findImageUrlById(id);
    }

    /**
     * 通过游戏id找寻相关图片url
     * @param gameId
     * @return
     */
    public List<String> findGameImageUrlsByGameId(long gameId){
        GameImage gameImage=imageDao.findImagesByGameId(gameId);
        List<String> images=new LinkedList<>();
        if (gameImage ==null){
            images.add(DEFAULT_IMAGE);
            images.add(DEFAULT_IMAGE);
            images.add(DEFAULT_IMAGE);
            images.add(DEFAULT_IMAGE);
            images.add(DEFAULT_IMAGE);
            return images;
        }
        String image1=imageDao.findImageUrlById(gameImage.getImage1());
        String image2=imageDao.findImageUrlById(gameImage.getImage2());
        String image3=imageDao.findImageUrlById(gameImage.getImage3());
        String image4=imageDao.findImageUrlById(gameImage.getImage4());
        String image5=imageDao.findImageUrlById(gameImage.getImage5());
        images.add(image1);
        images.add(image2);
        images.add(image3);
        images.add(image4);
        images.add(image5);
        return images;
    }

    /**
     * 通过游戏id找寻相关图片
     * @param gameId
     * @return
     */
    public List<Image> findGameImagesByGameId(long gameId){
        GameImage gameImage=imageDao.findImagesByGameId(gameId);
        List<Image> images=new LinkedList<>();
        images.add(imageDao.findImageById(gameImage.getImage1()));
        images.add(imageDao.findImageById(gameImage.getImage2()));
        images.add(imageDao.findImageById(gameImage.getImage3()));
        images.add(imageDao.findImageById(gameImage.getImage4()));
        images.add(imageDao.findImageById(gameImage.getImage5()));
        return images;
    }
}
