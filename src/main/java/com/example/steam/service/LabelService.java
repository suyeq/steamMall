package com.example.steam.service;

import com.example.steam.dao.LabelDao;
import com.example.steam.entity.GameLabel;
import com.example.steam.entity.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-29
 * @time: 20:37
 */
@Service
public class LabelService {
    @Autowired
    LabelDao labelDao;

    @Transactional
    public List<Label> findLabelsByGameId(long gameId){
        List<GameLabel> gameLabelList=labelDao.findLabelsByGameId(gameId);
        List<Label> labelList=new LinkedList<>();
        for (int i=0;i<gameLabelList.size();i++){
            GameLabel gameLabel=gameLabelList.get(i);
            labelList.add(labelDao.findLabelByLabelId(gameLabel.getLabelId()));
        }
        return labelList;
    }

    @Transactional
    public List<String> findLabelNamesByGameId(long gameId){
        List<GameLabel> gameLabelList=labelDao.findLabelsByGameId(gameId);
        List<String> labelList=new LinkedList<>();
        for (int i=0;i<gameLabelList.size();i++){
            GameLabel gameLabel=gameLabelList.get(i);
            labelList.add(labelDao.findLabelNameById(gameLabel.getLabelId()));
        }
        return labelList;
    }
}
