package com.example.steam.utils;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-08
 * @time: 15:07
 */
public class GameRank {

    private Long id;

    private List<String> type;

    public GameRank(){}

    public GameRank(long id,List<String> type){
        this.id=id;
        this.type=type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }
}
