package com.example.steam.service;

import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.LabelDao;
import com.example.steam.entity.GameLabel;
import com.example.steam.entity.Label;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.utils.HotComparator;
import com.example.steam.utils.ResultMsg;
import com.example.steam.vo.GameDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

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

    @Autowired
    RedisService redisService;

    @Autowired
    ApplicationContext applicationContext;

    Logger log= LoggerFactory.getLogger(LabelService.class);

    /**
     * 删除与该游戏相关的标签相关
     * @param gameId
     * @return
     */
    public int deleteGameLabelByGameId(long gameId){
        List<GameLabel> gameLabelList=labelDao.findLabelsByGameId(gameId);
        if (gameLabelList!=null){
            return labelDao.deleteGameLabelByGameId(gameId);
        }
        return -1;
    }

    /**
     * 通过标签名字找到标签
     * @param labelName
     * @param dataSource
     * @return
     */
    public Label findLabelByLabelName(String labelName, String dataSource){
        DynamicDataSourceHolder.putDataSource(dataSource);
        return labelDao.findLabelByLableName(labelName);
    }

    /**
     * 找到该游戏下的标签，label表的热度
     * @param gameId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<Label> findLabelsByGameId(long gameId){
        List<GameLabel> gameLabelList=labelDao.findLabelsByGameId(gameId);
        List<Label> labelList=new LinkedList<>();
        for (int i=0;i<gameLabelList.size();i++){
            GameLabel gameLabel=gameLabelList.get(i);
            labelList.add(labelDao.findLabelByLabelId(gameLabel.getLabelId()));
        }
        return labelList;
    }

    /**
     * 只获取该游戏下的标签名字
     * @param gameId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public List<String> findLabelNamesByGameId(long gameId){
        List<GameLabel> gameLabelList=labelDao.findLabelsByGameId(gameId);
        List<String> labelList=new LinkedList<>();
        for (int i=0;i<gameLabelList.size();i++){
            GameLabel gameLabel=gameLabelList.get(i);
            labelList.add(labelDao.findLabelNameById(gameLabel.getLabelId()));
        }
        return labelList;
    }

    /**
     * 找出该游戏下的标签以及热度，game_label表的热度
     * @param gameId
     * @return
     */
    public List<Label> findLabelInGame(long gameId){
        List<GameLabel> gameLabelList=labelDao.findLabelsByGameId(gameId);
        List<Label> labelList=new LinkedList<>();
        for(GameLabel gameLabel:gameLabelList){
            Label label=new Label(gameLabel.getLabelId(),labelDao.findLabelNameById(gameLabel.getLabelId()),gameLabel.getHotNum());
            labelList.add(label);
        }
        return labelList;
    }



    /**
     * 更新某个游戏标签的热度
     * @param gameId
     * @param labelId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultMsg updateHotNumByGameId(long gameId,long labelId){
        int result=labelDao.labelHotNumIncr(gameId,labelId);
        GameDetail gameDetail=redisService.get(GameKey.GAME_ID,gameId+"",GameDetail.class);
        List<Label> labelList=((LabelService)applicationContext.getBean("labelService")).findLabelInGame(gameId);
        PriorityQueue<Label> priorityQueue=new PriorityQueue<>(new HotComparator());
        for (Label label:labelList){
            priorityQueue.add(label);
        }
        List<String> labelNameList=gameDetail.getLabel();
        labelNameList.clear();
        Iterator<Label> iterator=priorityQueue.iterator();
        while (iterator.hasNext()){
            Label label=iterator.next();
            labelNameList.add(label.getName());
        }
        gameDetail.setLabel(labelNameList);
        redisService.set(GameKey.GAME_ID,gameId+"",gameDetail);
        return ResultMsg.SUCCESS(result);
    }

    /**
     * 增加一个游戏下的标签
     * @param gameId
     * @param labelName
     * @return 返回新增标签的id
     */
    public ResultMsg addLabelInGame(long gameId, String labelName){
        Label label1=labelDao.findLabelByLableName(labelName);
        GameDetail gameDetail=redisService.get(GameKey.GAME_ID,gameId+"",GameDetail.class);
        List<String> labelList=gameDetail.getLabel();
        labelList.add(labelName);
        gameDetail.setLabel(labelList);
        redisService.set(GameKey.GAME_ID,gameId+"",gameDetail);
        if (label1!=null){
            Label label2=labelDao.findLabelByLabelIdAndGameId(gameId,label1.getId());
            if (label2!=null){
                return ResultMsg.LABEL_PRESENCE;
            }else {
                GameLabel gameLabel=new GameLabel(0L,gameId,label1.getId(),0);
                labelDao.addLabelInGame(gameLabel);
            }
        }else {
            label1=new Label(0L,labelName,0);
            labelDao.addLabel(label1);
            GameLabel gameLabel=new GameLabel(0L,gameId,label1.getId(),0);
            labelDao.addLabelInGame(gameLabel);
        }
        return ResultMsg.SUCCESS(label1.getId());
    }
}
