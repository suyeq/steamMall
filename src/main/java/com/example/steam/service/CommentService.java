package com.example.steam.service;

import com.example.steam.dao.CommentDao;
import com.example.steam.entity.Comment;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.CommentKey;
import com.example.steam.utils.RankScoreValue;
import com.example.steam.vo.CommentDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-07
 * @time: 16:06
 */
@Service
public class CommentService implements InitializingBean{

    Logger log= LoggerFactory.getLogger(CommentService.class);

    @Autowired
    CommentDao commentDao;
    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;
    @Autowired
    ApplicationContext applicationContext;

    public Comment findOneCommentById(long id){
        Comment comment=redisService.get(CommentKey.COMMENT_ID,id+"",Comment.class);
        if (comment!=null){
            return comment;
        }
        comment=commentDao.findOneCommentById(id);
        redisService.set(CommentKey.COMMENT_ID,id+"",comment);
        return comment;
    }

    public List<CommentDetail> findRangeCommentDetail(long start,long gameId){
        return null;
    }

    public int findCommentSum(){
        return commentDao.commentSum();
    }


    @Override
    public void afterPropertiesSet() throws Exception {
//        int sum=((CommentService)applicationContext.getBean("commentService")).findCommentSum();
//        log.error(sum+"");
//        for (int i=0;i<sum;i++){
//            Comment comment=((CommentService)applicationContext.getBean("commentService")).findOneCommentById(i+1);
//            RankScoreValue zanRank=new RankScoreValue();
//            zanRank.setScore(comment.getZanNum());
//            zanRank.setId(comment.getId());
//            RankScoreValue timeRank=new RankScoreValue();
//            timeRank.setScore(comment.getCommentDate().getTime());
//            timeRank.setId(comment.getId());
//            redisService.zadd(CommentKey.COMMENT_RANK_ZANNUM,CommentKey.COMMENT_RANK_ZANNUM_KEY,zanRank);
//            redisService.zadd(CommentKey.COMMENT_RANK_TIME,CommentKey.COMMENT_RANK_TIME_KEY,timeRank);
//        }
    }
}
