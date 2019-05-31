package com.example.steam.service;

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

    @Autowired
    ImageDao imageDao;

    public Image findImageById(long id){
        return imageDao.findImageById(id);
    }

    public String findImageUrlById(long id){
          return imageDao.findImageUrlById(id);
    }

    public List<String> findGameImageUrlsByGameId(long gameId){
        GameImage gameImage=imageDao.findImagesByGameId(gameId);
        List<String> images=new LinkedList<>();
        images.add(imageDao.findImageUrlById(gameImage.getImage1()));
        images.add(imageDao.findImageUrlById(gameImage.getImage2()));
        images.add(imageDao.findImageUrlById(gameImage.getImage3()));
        images.add(imageDao.findImageUrlById(gameImage.getImage4()));
        images.add(imageDao.findImageUrlById(gameImage.getImage5()));
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
