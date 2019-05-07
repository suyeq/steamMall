package com.example.steam.redis.key;

import com.example.steam.redis.BaseKey;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-07
 * @time: 16:30
 */
public class CommentKey extends BaseKey {

    public static final String COMMENT_RANK_ZANNUM_KEY="rankZan";

    public final static String COMMENT_RANK_TIME_KEY="rankTime";

    public CommentKey(int expiredTime) {
        super(expiredTime);
    }

    public static CommentKey COMMENT_ID=new CommentKey(0);

    public static CommentKey COMMENT_RANK_ZANNUM=new CommentKey(0);

    public static CommentKey COMMENT_RANK_TIME=new CommentKey(0);
}
