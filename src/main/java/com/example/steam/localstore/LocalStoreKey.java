package com.example.steam.localstore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-05-04
 * @time: 10:55
 */
public class LocalStoreKey {

    private String keyName;

    private long startTime;

    private long expiredTime;

    private Map<String,ExpiredTime> expiredTimeHashMap=new HashMap<>();

//    private static volatile LocalStoreKey FETURED_CAROUSEL_KEY=new LocalStoreKey("feturedCarousel",System.currentTimeMillis(),1000*30);
//
//    private static volatile LocalStoreKey SPECIAL_CAROUSEL_KEY=new LocalStoreKey("specialCarousel",System.currentTimeMillis(),1000*40);
//
//    private static volatile LocalStoreKey NEW_RELEASE_INDEX_KEY=new LocalStoreKey("specialCarousel",System.currentTimeMillis(),1000*50);
//
//    private static volatile LocalStoreKey HOT_SELL_INDEX_KEY=new LocalStoreKey("specialCarousel",System.currentTimeMillis(),1000*50);

    private static volatile LocalStoreKey FETURED_CAROUSEL_KEY;

    private static volatile LocalStoreKey SPECIAL_CAROUSEL_KEY;

    private static volatile LocalStoreKey NEW_RELEASE_INDEX_KEY;

    private static volatile LocalStoreKey HOT_SELL_INDEX_KEY;

    private static volatile LocalStoreKey UP_COMING_INDEX_KEY;

    private static volatile LocalStoreKey CLASS_CAROUSEL_KEY;

    private static volatile LocalStoreKey NEW_RELEASE_CLASS_KEY;

    private static volatile LocalStoreKey HOT_SELL_CLASS_KEY;

    private static volatile LocalStoreKey UP_COMING_CLASS_KEY;

    private LocalStoreKey(String keyName,long startTime,long expiredTime){
        this.keyName=keyName;
        this.startTime=startTime;
        this.expiredTime=expiredTime;
    }

    public static LocalStoreKey UP_COMING_CLASS_KEY(){
        if (UP_COMING_CLASS_KEY==null){
            synchronized (LocalStoreKey.class){
                if (UP_COMING_CLASS_KEY==null){
                    UP_COMING_CLASS_KEY=new LocalStoreKey("classUpComing",System.currentTimeMillis(),1000*50);
                }
                return UP_COMING_CLASS_KEY;
            }
        }
        return UP_COMING_CLASS_KEY;
    }

    public static LocalStoreKey HOT_SELL_CLASS_KEY(){
        if (HOT_SELL_CLASS_KEY==null){
            synchronized (LocalStoreKey.class){
                if (HOT_SELL_CLASS_KEY==null){
                    HOT_SELL_CLASS_KEY=new LocalStoreKey("classHotSell",System.currentTimeMillis(),1000*50);
                }
                return HOT_SELL_CLASS_KEY;
            }
        }
        return HOT_SELL_CLASS_KEY;
    }

    public static LocalStoreKey NEW_RELEASE_CLASS_KEY(){
        if (NEW_RELEASE_CLASS_KEY==null){
            synchronized (LocalStoreKey.class){
                if (NEW_RELEASE_CLASS_KEY==null){
                    NEW_RELEASE_CLASS_KEY=new LocalStoreKey("classNewRelease",System.currentTimeMillis(),1000*50);
                }
                return NEW_RELEASE_CLASS_KEY;
            }
        }
        return NEW_RELEASE_CLASS_KEY;
    }

    public static LocalStoreKey CLASS_CAROUSEL_KEY(){
        if (CLASS_CAROUSEL_KEY==null){
            synchronized (LocalStoreKey.class){
                if (CLASS_CAROUSEL_KEY==null){
                    CLASS_CAROUSEL_KEY=new LocalStoreKey("classCarousel",System.currentTimeMillis(),1000*30);
                }
                return CLASS_CAROUSEL_KEY;
            }
        }
        return CLASS_CAROUSEL_KEY;
    }

    public static LocalStoreKey FETURED_CAROUSEL_KEY(){
        if (FETURED_CAROUSEL_KEY==null){
            synchronized (LocalStoreKey.class){
                if (FETURED_CAROUSEL_KEY==null){
                    FETURED_CAROUSEL_KEY=new LocalStoreKey("feturedCarousel",System.currentTimeMillis(),1000*30);
                }
                return FETURED_CAROUSEL_KEY;
            }
        }
        return FETURED_CAROUSEL_KEY;
    }

    public static LocalStoreKey SPECIAL_CAROUSEL_KEY(){
        if (SPECIAL_CAROUSEL_KEY==null){
            synchronized (LocalStoreKey.class){
                if (SPECIAL_CAROUSEL_KEY==null){
                    SPECIAL_CAROUSEL_KEY=new LocalStoreKey("specialCarousel",System.currentTimeMillis(),1000*40);
                }
                return SPECIAL_CAROUSEL_KEY;
            }
        }
        return SPECIAL_CAROUSEL_KEY;
    }

    public static LocalStoreKey HOT_SELL_INDEX_KEY(){
        if (HOT_SELL_INDEX_KEY==null){
            synchronized (LocalStoreKey.class){
                if (HOT_SELL_INDEX_KEY==null){
                    HOT_SELL_INDEX_KEY=new LocalStoreKey("hotSell",System.currentTimeMillis(),1000*50);
                }
                return HOT_SELL_INDEX_KEY;
            }
        }
        return HOT_SELL_INDEX_KEY;
    }

    public static LocalStoreKey UP_COMING_INDEX_KEY(){
        if (UP_COMING_INDEX_KEY==null){
            synchronized (LocalStoreKey.class){
                if (UP_COMING_INDEX_KEY==null){
                    UP_COMING_INDEX_KEY=new LocalStoreKey("upComing",System.currentTimeMillis(),1000*50);
                }
                return UP_COMING_INDEX_KEY;
            }
        }
        return UP_COMING_INDEX_KEY;
    }

    public static LocalStoreKey NEW_RELEASE_INDEX_KEY(){
        if (NEW_RELEASE_INDEX_KEY==null){
            synchronized (LocalStoreKey.class){
                if (NEW_RELEASE_INDEX_KEY==null){
                    NEW_RELEASE_INDEX_KEY=new LocalStoreKey("newRelease",System.currentTimeMillis(),1000*50);
                }
                return NEW_RELEASE_INDEX_KEY;
            }
        }
        return NEW_RELEASE_INDEX_KEY;
    }

    @Override
    public int hashCode(){
        return keyName.hashCode();
    }

    @Override
    public boolean equals(Object o){
        LocalStoreKey storeKey=(LocalStoreKey)o;
        if (keyName.equals(storeKey.keyName)){
            return true;
        }
        return false;
    }

    public long getExpiredTime() {
        return expiredTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(){
        this.startTime=System.currentTimeMillis();
    }

    public Map<String, ExpiredTime> getExpiredTimeHashMap() {
        return expiredTimeHashMap;
    }

    public void setExpiredTime(String page){
//        if (){
//
//        }
//        long expiredTime=;
        ExpiredTime expiredTime=new ExpiredTime(System.currentTimeMillis(),40*1000);
        expiredTimeHashMap.put(page,expiredTime);
    }

    class ExpiredTime{
        long startTime;
        long expiredTime;

        ExpiredTime(long startTime,long expiredTime){
            this.startTime=startTime;
            this.expiredTime=expiredTime;
        }

        public long getStartTime(){
            return startTime;
        }

        public long getExpiredTime(){
            return expiredTime;
        }
    }
}
