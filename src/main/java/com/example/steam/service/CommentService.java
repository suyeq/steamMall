package com.example.steam.service;

import com.example.steam.dao.CommentDao;
import com.example.steam.entity.Comment;
import com.example.steam.entity.User;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.CommentKey;
import com.example.steam.utils.CommentRank;
import com.example.steam.utils.RankScoreValue;
import com.example.steam.vo.CommentDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-07
 * @time: 16:06
 */
@Service
public class CommentService implements InitializingBean{

    private final static long RECENT_CONMENT_SIZE=6;

    private final static long POPULAR_COMMENT_SIZE=5;

    Logger log= LoggerFactory.getLogger(CommentService.class);

    @Autowired
    CommentDao commentDao;
    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;
    @Autowired
    ImageService imageService;
    @Autowired
    ApplicationContext applicationContext;

    /**
     * 从数据库中查询或者从缓存层查询
     * @param id
     * @return
     */
    public Comment findOneCommentById(long id){
        Comment comment=redisService.get(CommentKey.COMMENT_ID,id+"",Comment.class);
        if (comment!=null){
            return comment;
        }
        comment=commentDao.findOneCommentById(id);
        redisService.set(CommentKey.COMMENT_ID,id+"",comment);
        return comment;
    }

    /**
     * 根据id查询该评论的详情信息
     * @param id
     * @return
     */
    public CommentDetail findCommentDetailById(long id){
        Comment comment=((CommentService)applicationContext.getBean("commentService")).findOneCommentById(id);
        User user=userService.findByEmail(comment.getEmail());
        CommentDetail commentDetail=new CommentDetail();
        commentDetail.setAvatar(imageService.findImageUrlById(user.getAvatar()));
        commentDetail.setBuyGames(user.getBuyGames());
        commentDetail.setCaiNum(comment.getCaiNum());
        commentDetail.setZanNum(comment.getZanNum());
        commentDetail.setCommentDate(comment.getCommentDate());
        commentDetail.setCommmentNum(user.getCommmentNum());
        commentDetail.setContent(comment.getContent());
        commentDetail.setNickName(user.getNickName());
        commentDetail.setEmail(user.getEmail());
        commentDetail.setPlayTime(user.getPlayTime());
        commentDetail.setRecommendStatu(comment.getRecommendStatu());
        commentDetail.setId(comment.getId());
        commentDetail.setUserId(user.getId());
        return commentDetail;
    }

    /**
     * 根据页号查询该游戏下最近的评论（每页6条评论）
     * @param page
     * @param gameId
     * @return
     */
    public List<CommentDetail> findRangeCommentDetailByTime(long page,long gameId){
        long count=0;
        long cursor=0;
        List<CommentDetail> commentDetailList=new LinkedList<>();
        int sum=((CommentService)applicationContext.getBean("commentService")).findCommentSum();
        while (cursor<sum){
            Set<CommentRank> commentRankSet=redisService.zrange(CommentKey.COMMENT_RANK_TIME,CommentKey.COMMENT_RANK_TIME_KEY,cursor,cursor+100,CommentRank.class);
            Iterator<CommentRank> iterator=commentRankSet.iterator();
            while (iterator.hasNext()){
                CommentRank commentRank=iterator.next();
                if ( commentRank.getGameId()==gameId && commentDetailList.size()<RECENT_CONMENT_SIZE ){
                    if (count>=page*RECENT_CONMENT_SIZE && count<page*RECENT_CONMENT_SIZE+RECENT_CONMENT_SIZE){
                        CommentDetail commentDetail=((CommentService)applicationContext.getBean("commentService")).findCommentDetailById(commentRank.getId());
                        commentDetailList.add(commentDetail);
                    }
                    count++;
                }
            }
            cursor+=100;
        }
        return commentDetailList;
    }

    /**
     * 根据页号查询该游戏下点赞最多的评论（每页5条评论）
     * @param page
     * @param gameId
     * @return
     */
    public List<CommentDetail> findRangeCommentDetailByZanNum(long page,long gameId){
        long count=0;
        long cursor=0;
        List<CommentDetail> commentDetailList=new LinkedList<>();
        int sum=((CommentService)applicationContext.getBean("commentService")).findCommentSum();
        while (cursor<sum){
            Set<CommentRank> commentRankSet=redisService.zrange(CommentKey.COMMENT_RANK_ZANNUM,CommentKey.COMMENT_RANK_ZANNUM_KEY,cursor,cursor+100,CommentRank.class);
            Iterator<CommentRank> iterator=commentRankSet.iterator();
            while (iterator.hasNext()){
                CommentRank commentRank=iterator.next();
                if ( commentRank.getGameId()==gameId && commentDetailList.size()<POPULAR_COMMENT_SIZE){
                    if (count>=page*POPULAR_COMMENT_SIZE && count<page*POPULAR_COMMENT_SIZE+POPULAR_COMMENT_SIZE){
                        CommentDetail commentDetail=((CommentService)applicationContext.getBean("commentService")).findCommentDetailById(commentRank.getId());
                        commentDetailList.add(commentDetail);
                    }
                    count++;
                }
            }
            cursor+=100;
        }
        return commentDetailList;
    }

    public int findCommentSum(){
        Integer sum=redisService.get(CommentKey.COMMENT_SUM,CommentKey.COMMENT_SUM_KEY,Integer.class);
        if (sum!=null){
            return sum;
        }
        sum=commentDao.commentSum();
        redisService.set(CommentKey.COMMENT_SUM,CommentKey.COMMENT_SUM_KEY,sum);
        return sum;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
//        int sum=((CommentService)applicationContext.getBean("commentService")).findCommentSum();
//        log.error(sum+"");
//        for (int i=0;i<sum;i++){
//            Comment comment=((CommentService)applicationContext.getBean("commentService")).findOneCommentById(i+1);
//            CommentRank commentRank=new CommentRank();
//            commentRank.setId(comment.getId());
//            commentRank.setGameId(comment.getGameId());
//
//            RankScoreValue zanRank=new RankScoreValue();
//            zanRank.setScore(comment.getZanNum());
//            zanRank.setValue(commentRank);
//
//            RankScoreValue timeRank=new RankScoreValue();
//            timeRank.setScore(comment.getCommentDate().getTime());
//            timeRank.setValue(commentRank);
//            redisService.zadd(CommentKey.COMMENT_RANK_ZANNUM,CommentKey.COMMENT_RANK_ZANNUM_KEY,zanRank);
//            redisService.zadd(CommentKey.COMMENT_RANK_TIME,CommentKey.COMMENT_RANK_TIME_KEY,timeRank);
//        }
    }
}
