package com.example.steam.utils;

import com.example.steam.vo.GameDetail;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-03
 * @time: 19:41
 */
public class GamePriorityQueue<E> extends PriorityQueue<E> {

    private Set<E> set=new HashSet<>();

    public GamePriorityQueue(Comparator<? super E> comparator){
        super(comparator);
    }

    @Override
    public boolean add(E e){
        if (set.add(e)){
            return super.add(e);
        }
        return false;
    }
}
