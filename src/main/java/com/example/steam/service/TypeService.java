package com.example.steam.service;

import com.example.steam.dao.TypeDao;
import com.example.steam.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional(readOnly = true)
    public List<Type> findAllTyp(){
        return typeDao.findAllType();
    }


    public int addType(Type type){
        return typeDao.addType(type);
    }

}
