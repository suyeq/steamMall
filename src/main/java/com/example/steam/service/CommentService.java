package com.example.steam.service;

import com.alibaba.fastjson.JSON;
import com.example.steam.config.DynamicDataSourceHolder;
import com.example.steam.dao.CommentDao;
import com.example.steam.entity.Comment;
import com.example.steam.entity.User;
import com.example.steam.redis.RedisService;
import com.example.steam.redis.key.CommentKey;
import com.example.steam.utils.CommentRank;
import com.example.steam.utils.RankScoreValue;
import com.example.steam.utils.ResultMsg;
import com.example.steam.vo.CommentDetail;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.org.apache.xerces.internal.xs.LSInputList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 每页显示的最近评论数
     */
    private final static long RECENT_CONMENT_SIZE=6;
    /**
     * 每页显示的最受欢迎评论数
     */
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
     * 通过游戏id得到该游戏的评论id集合
     * @param gameId 游戏id
     * @return
     */
    public List<Long> findCommentListNumberByGameId(long gameId){
        //long count=0;
        long cursor=0;
        List<CommentDetail> commentDetailList=new LinkedList<>();
        List<Long> commentIdList=new LinkedList<>();
        int sum=((CommentService)applicationContext.getBean("commentService")).findCommentSum();
        while (cursor<sum){
            Set<CommentRank> commentRankSet=redisService.zrange(CommentKey.COMMENT_RANK_TIME,CommentKey.COMMENT_RANK_TIME_KEY,cursor,cursor+100,CommentRank.class);
            Iterator<CommentRank> iterator=commentRankSet.iterator();
            while (iterator.hasNext()){
                CommentRank commentRank=iterator.next();
                if (commentRank.getGameId()==gameId){
                    //count++;
                    commentIdList.add(commentRank.getId());
                }
            }
            cursor+=100;
        }
        return commentIdList;
    }

    /**
     * 通过游戏id找到该游戏评论下的推荐
     * 与不推荐评论数
     * @param gameId
     * @return
     */
    public List<Long> findCommentDescriptionNumberByGameId(long gameId){
        long goodCount=0;
        long unGoodCount=0;
        List<String> list=new LinkedList<>();
        List<Long> result=new LinkedList<>();
        List<Long> commentIdList=((CommentService)applicationContext.getBean("commentService")).findCommentListNumberByGameId(gameId);
        for (Long id:commentIdList){
            list.add(id+"");
        }
        List<Comment> commentList=redisService.getPipelineBatch(CommentKey.COMMENT_ID,list,Comment.class);
        for (Comment comment:commentList){
            if (comment.getRecommendStatu()==1){
                goodCount++;
            }else {
                unGoodCount++;
            }
        }
        result.add(goodCount);
        result.add(unGoodCount);
        return result;
    }

    /**
     * 从数据库中查询或者从缓存层查询评论
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
        commentDetail.setCommmentNum(user.getCommentNum());
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
    public ResultMsg findRangeCommentDetailByTime(long page,long gameId){
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
        return ResultMsg.SUCCESS(commentDetailList);
    }

    /**
     * 根据页号查询该游戏下点赞最多的评论（每页5条评论）
     * @param page
     * @param gameId
     * @return
     */
    public ResultMsg findRangeCommentDetailByZanNum(long page,long gameId){
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
        return ResultMsg.SUCCESS(commentDetailList);
    }

    /**
     * 获得评论总数
     * @return
     */
    public int findCommentSum(){
        Integer sum=redisService.get(CommentKey.COMMENT_SUM,CommentKey.COMMENT_SUM_KEY,Integer.class);
        if (sum!=null){
            return sum;
        }
        sum=commentDao.commentSum();
        redisService.set(CommentKey.COMMENT_SUM,CommentKey.COMMENT_SUM_KEY,sum);
        return sum;
    }

    /**
     * 从主库中查询最后一条评论的id
     * @return
     */
    public long findLastCommentId(){
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.MASTER);
        return commentDao.findLastCommentId();
    }

    /**
     * 增加一个评论
     * @param comment
     * @return
     */
    public long addComment(Comment comment){
        commentDao.addComment(comment);
        /**
         * 加入缓存，以及排名
         */
        redisService.set(CommentKey.COMMENT_ID,comment.getId()+"",comment);
        redisService.incKey(CommentKey.COMMENT_SUM,CommentKey.COMMENT_SUM_KEY);
        CommentRank commentRank=new CommentRank();
        commentRank.setId(comment.getId());
        commentRank.setGameId(comment.getGameId());
        RankScoreValue zanRank=new RankScoreValue();
        zanRank.setScore(comment.getZanNum());
        zanRank.setValue(commentRank);
        RankScoreValue timeRank=new RankScoreValue();
        timeRank.setScore(comment.getCommentDate().getTime());
        timeRank.setValue(commentRank);
        redisService.zadd(CommentKey.COMMENT_RANK_ZANNUM,CommentKey.COMMENT_RANK_ZANNUM_KEY,zanRank);
        redisService.zadd(CommentKey.COMMENT_RANK_TIME,CommentKey.COMMENT_RANK_TIME_KEY,timeRank);
        return comment.getId();
    }

    public String getCommentStatu(long goodCommentNum,long unGoodCommentNum){
        double statu=(double)goodCommentNum/(double)(goodCommentNum+unGoodCommentNum);
        if (goodCommentNum==0 && unGoodCommentNum==0){
            return "无评测";
        }
        if(statu>=0.8){
            return  "好评如潮";
        }else if (statu>=0.6 && statu <0.8){
            return "好评较多";
        }else if (statu>=0.4 && statu <0.6){
            return "褒贬不一";
        }else {
            return "差评较多";
        }
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
