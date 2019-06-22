# 项目介绍
这个项目是主要设计缓存的一个练手项目，所以后台没有实现，但是前台流程是通了的，获取后面会把从登陆到购买以及秒杀。目前主要模块有四个：用户模块，游戏模块，评论模块以及秒杀模块。

# 项目的运行
在数据库中创建steam库，然后导入sql文件，修改相应的的配置后，运行起项目，然后需要把启动器类里面的`afterPropertiesSet`方法里面的代码注释掉，这是第一次启动加载缓存的代码。

# 架构以及详细实现
## 1.登录注册
* 两次md5,对密码加密
* 分布式session，将用户的登录信息缓存在redis中
* 权限检查，每次操作在权限允许下才能进行
* 注册，需要邮箱验证码，邮箱验证码会在缓存中存在90s的时间

## 2.页面数据
* 首页及其他页面因为读多写少，利用Mysql主从复制实现读写分离，写入在主Mysql下进行，读取在从Mysql进行
* 关于数据的不一致性，可以在写入的时候先写入缓存，读取的时候也先在缓存中读取，这样就可以避免数据的不一致性
* 缓存利用Redis，内存满的情况下，键的删除策略采用volatile-lru
* 热卖榜以及排序功能依据Redis的zset实现，缓存时间为30s
* 为了减少网络时延的影响，引入了redis的管道技术，实行批处理
* 为了更好地加快性能，在redis缓存层之上加了加了一层本地的缓存

## 3.秒杀功能
原理：尽量减少Mysql的访问
* 将先将需要秒杀的数据缓存在Redis中，在秒杀接口里做预减少库存
* 判断秒杀订单里有无对应的信息，有则秒杀重复
* 将user信息与秒杀资源信息加入消息队列中
* 消息接受者减少库存，新增秒杀订单
* 客户端不断轮询缓存，查询到对应的秒杀订单，则秒杀成功

## 4.页面的渲染
* 页面的渲染采取了jquery加ajax技术的字符串拼接以及thymleaf模板的渲染
* 大部分是用的字符串拼接，只有少部分采用的模板渲染


## 5. 主从配置
继承`AbstractRoutingDataSource`类，重写`determineCurrentLookupKey`方法，改变DataSource路由中介，在运行当中根据自定义KEY值动态切换真正的DataSource：
```java
public class DynamicDataSource extends AbstractRoutingDataSource {
    
    ......
    
    @Override
    protected Object determineCurrentLookupKey() {
        Object key = "";
        //主库
        if (DynamicDataSourceHolder.isMaster()) {
            key = DynamicDataSourceHolder.MASTER;
        } else {
            //从库
            key = getSlaveKey();
            //key=DynamicDataSourceHolder.SLAVE;
        }
        logger.info("==> select datasource key [{}]", key);
        return key;
    }

    public void setSlaveDataSources(List<Object> slaveDataSources) {
        this.slaveDataSources = slaveDataSources;
    }

    ......
}
```
再利用AOP技术动态的改变数据源键值，就可以达到动态的分发数据请求了：
```java
/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 15:23
 */
@Component
@Aspect
public class DynamicDataSourceAop {


    Logger log= LoggerFactory.getLogger(DynamicDataSourceAop.class);
    /**
     * 切面service包下的所有以add形式开头的方法
     */
    @Before("execution(* com.example.steam.service.*.add*(..))")
    public void dynamicDataSourceAddMethod(){
        log.info("设置为主节点");
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.MASTER);
    }

    @Before("execution(* com.example.steam.service.*.update*(..))")
    public void dynamicDataSourceUpdateMethod(){
        log.info("设置为主节点");
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.MASTER);
    }

    @Before("execution(* com.example.steam.service.*.delete*(..))")
    public void dynamicDataSourceDeleteMethod(){
        log.info("设置为主节点");
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.MASTER);
    }

    @Before("execution(* com.example.steam.service.*.find*(..))")
    public void dynamicDataSourceFindMethod(){
        log.info("设置为从节点");
        DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.SLAVE);
    }

}

```
## 6.消息队列
在项目里，利用redis的list来实现了一个简单的消息队列，用来异步处理邮件任务以及秒杀任务，在线程池中提交一个不断循环从list中取出事件的任务，调用对应的消息处理器处理：
```java

        ......
        
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Event event=redisService.rpop(MQKey.MQ,Event.EVENT_KEY,Event.class);
                    if (event==null){
                        continue;
                    }
                    if (!eventMap.keySet().contains(event.getEventType())){
                        log.error("未知的事件类型");
                        continue;
                    }
                    for (EventHandle eventHandle:eventMap.get(event.getEventType())){
                        threadPoolExecutor.execute(new EventHanleThread(event,eventHandle,applicationContext));
                    }
                    /**
                     * 每隔500毫秒取一次
                     */
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
```

