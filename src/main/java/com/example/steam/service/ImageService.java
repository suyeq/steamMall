package com.example.steam.service;

import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.ImageDao;
import com.example.steam.entity.GameImage;
import com.example.steam.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Long addImage(Image image){
        imageDao.addImage(image);
        return image.getId();
    }

    public Image findImageById(long id){
        return imageDao.findImageById(id);
    }

    public String findImageUrlById(long id){
          return imageDao.findImageUrlById(id);
    }

    public String findImageUrlById(long id, String dataSource){
        DynamicDataSourceHolder.putDataSource(dataSource);
        return imageDao.findImageUrlById(id);
    }

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
