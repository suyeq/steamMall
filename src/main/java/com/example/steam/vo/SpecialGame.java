package com.example.steam.vo;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-27
 * @time: 18:42
 */
public class SpecialGame {

    private Long id;

    private String gameName;

    private String posterImage;

    private Integer discount;

    private Integer gamePrice;

    private Integer issuedStatu;

    private String imageIntro1;

    private String imageIntro2;

    private String imageIntro3;

    private String imageIntro4;

    public SpecialGame(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getIssuedStatu() {
        return issuedStatu;
    }

    public void setIssuedStatu(Integer issuedStatu) {
        this.issuedStatu = issuedStatu;
    }

    public String getImageIntro1() {
        return imageIntro1;
    }

    public void setImageIntro1(String imageIntro1) {
        this.imageIntro1 = imageIntro1;
    }

    public String getImageIntro2() {
        return imageIntro2;
    }

    public void setImageIntro2(String imageIntro2) {
        this.imageIntro2 = imageIntro2;
    }

    public String getImageIntro3() {
        return imageIntro3;
    }

    public void setImageIntro3(String imageIntro3) {
        this.imageIntro3 = imageIntro3;
    }

    public Integer getGamePrice() {
        return gamePrice;
    }

    public void setGamePrice(Integer gamePrice) {
        this.gamePrice = gamePrice;
    }

    public String getImageIntro4() {
        return imageIntro4;
    }

    public void setImageIntro4(String imageIntro4) {
        this.imageIntro4 = imageIntro4;
    }
}
