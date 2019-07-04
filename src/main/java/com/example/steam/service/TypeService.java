package com.example.steam.service;

import com.example.steam.dao.TypeDao;
import com.example.steam.entity.Game;
import com.example.steam.entity.GameType;
import com.example.steam.entity.Label;
import com.example.steam.entity.Type;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.GameKey;
import com.example.steam.vo.GameDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 11:01
 */
@Service
public class TypeService {

    @Autowired
    TypeDao typeDao;

    @Autowired
    RedisService redisService;

    /**
     * 增加一个类型
     * @param typeName
     * @return
     */
    public int addType(String typeName){
        Type type=new Type();
        type.setTypeName(typeName);
        return typeDao.addType(type);
    }


    @Transactional(rollbackFor = Exception.class)
    public int deleteGameTypeByGameId(long gameId){
        List<GameType> typeList=typeDao.findTypesByGameId(gameId);
        if (typeList != null){
            return typeDao.deleteGameTypeByGameId(gameId);
        }
        return -1;
    }

    /**
     * 为一个游戏增加分类
     * @param gameType
     * @return
     */
    public int addTypeToGame(GameType gameType){
        GameDetail gameDetail=redisService.get(GameKey.GAME_ID,gameType.getGameId()+"",GameDetail.class);
        if (gameDetail!=null){
            List<String> typeList=gameDetail.getType();
            if (typeList!=null){
                typeList.add(typeDao.findTypeNameById(gameType.getTypeId()));
            }else {
                typeList=new LinkedList<>();
                typeList.add(typeDao.findTypeNameById(gameType.getTypeId()));
            }
            gameDetail.setType(typeList);
            redisService.set(GameKey.GAME_ID,gameType.getGameId()+"",gameDetail);
        }
        return typeDao.addTypeToGame(gameType);
    }

    /**
     * 通过类型名字找到type
     * @param typeName
     * @return
     */
    public Type findTypeByTypeName(String typeName){
        return typeDao.findTypeByTypeName(typeName);
    }

    /**
     * 找到所有的类型
     */
    public List<String> findAllType(){
        return typeDao.findAllType();
    }

    /**
     * 找到所有的类型
     * @return
     */
    public List<Type> findAllTypes(){
        return typeDao.findAllTypes();
    }

    /**
     * 删除类型
     * @param typeId
     * @return
     */
    public int deleteTypeById(long typeId){
        return typeDao.deleteTypeById(typeId);
    }

    /**
     * 找到游戏id的游戏类型
     * @param gameId
     * @return
     */
    public List<String> findTypeNameByGameId(long gameId){
        List<GameType> gameTypeList=typeDao.findTypesByGameId(gameId);
        List<String> typeNameList=new LinkedList<>();
        for (int i=0;i<gameTypeList.size();i++){
            typeNameList.add(typeDao.findTypeNameById(gameTypeList.get(i).getTypeId()));
        }
        return typeNameList;
    }

    /**
     * 判断是否存在
     * @param types
     * @param typeName
     * @return
     */
    public boolean isExists(List<String> types,String typeName){
        for (int i=0;i<types.size();i++){
            if (types.get(i).equals(typeName)){
                return true;
            }
        }
        return false;
    }
}
