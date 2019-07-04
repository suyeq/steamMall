package com.example.steam.service;

import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.SensitiveKey;
import com.example.steam.utils.ResultMsg;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 敏感词
 * @author: 苍术
 * @date: 2019-07-04
 * @time: 19:58
 */
@Service
public class SensitiveWordService {

    @Autowired
    RedisService redisService;

    /**
     * 处理保存事件
     * @param words
     * @return
     */
    public ResultMsg handleSaveWord(String []words){
        List<String> wordList=getSensitiveWord();
        wordList.clear();
        for (int i=0;i<words.length;i++){
            wordList.add(words[i]);
        }
        redisService.set(SensitiveKey.WORD_LIST,SensitiveKey.SENSITIVE_KEY,wordList);
        return ResultMsg.SUCCESS;
    }

    /**
     * 从缓存中得到敏感词列表
     * @return
     */
    public List<String> getSensitiveWord(){
        List<String> sensitiveList=redisService.getList(SensitiveKey.WORD_LIST,SensitiveKey.SENSITIVE_KEY,String.class);
        return sensitiveList;
    }

    /**
     * 增加一个敏感词
     * @param word
     */
    public void addSensitiveWord(String word){
        List<String> sensitiveList=redisService.getList(SensitiveKey.WORD_LIST,SensitiveKey.SENSITIVE_KEY,String.class);
        if (sensitiveList == null){
            sensitiveList=new LinkedList<>();
        }
        sensitiveList.add(word);
        redisService.set(SensitiveKey.WORD_LIST,SensitiveKey.SENSITIVE_KEY,sensitiveList);
    }

    /**
     * 删除一个敏感词
     * @param word
     */
    public void deleteSensitiveWord(String word){
        List<String> sensitiveList=redisService.getList(SensitiveKey.WORD_LIST,SensitiveKey.SENSITIVE_KEY,String.class);
        sensitiveList.remove(word);
        redisService.set(SensitiveKey.WORD_LIST,SensitiveKey.SENSITIVE_KEY,sensitiveList);
    }

    /**
     * 转变
     * @return
     */
    public String sensitiveVo(){
        List<String> sensitiveList=redisService.getList(SensitiveKey.WORD_LIST,SensitiveKey.SENSITIVE_KEY,String.class);
        return sensitiveToVo(sensitiveList);
    }

    private String sensitiveToVo(List<String> sensitiviList){
        String sensitiveVo="";
        for (int i=0;i<sensitiviList.size();i++){
            if (i<sensitiviList.size()-1){
                sensitiveVo+=sensitiviList.get(i)+"|";
            }else {
                sensitiveVo+=sensitiviList.get(i);
            }
        }
        return sensitiveVo;
    }

}
