package com.example.steam.utils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-08
 * @time: 16:17
 */
public class CommentRank {

    private Long id;

    private Long gameId;

    public CommentRank(){}

    public CommentRank(long id,long gameId){
        this.id=id;
        this.gameId=gameId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }
}
