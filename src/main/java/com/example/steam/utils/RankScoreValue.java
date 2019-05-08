package com.example.steam.utils;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-04
 * @time: 15:25
 */
public class  RankScoreValue<T> {

    private T value;

    private long score;

    public RankScoreValue(){}

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
