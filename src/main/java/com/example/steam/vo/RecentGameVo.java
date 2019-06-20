package com.example.steam.vo;

import com.example.steam.dao.RecentGameDao;
import com.example.steam.entity.RecentGame;

/**
 * -----------
 * 穷则独善其身
 * 达则兼济天下
 * -----------
 *
 * @author: 苍术
 * @date: 2019-06-20
 * @time: 11:02
 */
public class RecentGameVo extends RecentGame {

    private String posterImage;

    private String gameName;

   public RecentGameVo(){
       super();
   }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }
}
