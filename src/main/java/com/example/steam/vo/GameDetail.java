package com.example.steam.vo;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-29
 * @time: 19:08
 */
public class GameDetail extends SpecialGame {

    private String imageIntro5;

    private String gameIntroduction;

    private String gameAbout;

    private Date issuedDate;

    private Integer sellNum;

    private List<String> label;

    private List<String> type;

    private Long lowestSystem;

    private Long recommendSystem;

    public GameDetail(){}


    public String getImageIntro5() {
        return imageIntro5;
    }

    public void setImageIntro5(String imageIntro5) {
        this.imageIntro5 = imageIntro5;
    }

    public String getGameIntroduction() {
        return gameIntroduction;
    }

    public void setGameIntroduction(String gameIntroduction) {
        this.gameIntroduction = gameIntroduction;
    }

    public String getGameAbout() {
        return gameAbout;
    }

    public void setGameAbout(String gameAbout) {
        this.gameAbout = gameAbout;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Integer getSellNum() {
        return sellNum;
    }

    public void setSellNum(Integer sellNum) {
        this.sellNum = sellNum;
    }

    public List<String> getLabel() {
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }


    public Long getLowestSystem() {
        return lowestSystem;
    }

    public void setLowestSystem(Long lowestSystem) {
        this.lowestSystem = lowestSystem;
    }

    public Long getRecommendSystem() {
        return recommendSystem;
    }

    public void setRecommendSystem(Long recommendSystem) {
        this.recommendSystem = recommendSystem;
    }

}
