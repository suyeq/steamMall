package com.example.steam.service;

import com.example.steam.dao.TypeDao;
import com.example.steam.entity.GameType;
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
 * @date: 2019-04-25
 * @time: 11:01
 */
@Service
public class TypeService {

    @Autowired
    TypeDao typeDao;

    @Transactional
    public List<String> findTypeNameByGameId(long gameId){
        List<GameType> gameTypeList=typeDao.findTypesByGameId(gameId);
        List<String> typeNameList=new LinkedList<>();
        for (int i=0;i<gameTypeList.size();i++){
            typeNameList.add(typeDao.findTypeNameById(gameTypeList.get(i).getTypeId()));
        }
        return typeNameList;
    }

//    public String find
//
//
    public boolean isExists(List<String> types,String typeName){
        for (int i=0;i<types.size();i++){
            if (types.get(i).equals(typeName)){
                return true;
            }
        }
        return false;
    }
}
