package com.example.steam.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 10:25
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public final static Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    /**
     * 从数据源
     */
    private List<Object> slaveDataSources = new ArrayList<Object>();
    /**
     * 轮询计数
     */
    private AtomicInteger squence = new AtomicInteger(0);

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
    }

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
        logger.error("==> select datasource key [{}]", key);
        return key;
    }

    public void setSlaveDataSources(List<Object> slaveDataSources) {
        this.slaveDataSources = slaveDataSources;
    }

    /**
     * 轮询获取从库
     * 在有多从数据库的情况下
     * 轮训算法详解: https://github.com/wtstengshen/blog-page/wiki/Round-Robin
     * Netflix ribbon: https://www.oschina.net/p/ribbon
     * @return
     */
    public Object getSlaveKey() {
        if (squence.intValue() == Integer.MAX_VALUE) {
            synchronized (squence) {
                if (squence.intValue() == Integer.MAX_VALUE) {
                    squence = new AtomicInteger(0);
                }
            }
        }
        int idx = squence.getAndIncrement() % slaveDataSources.size();
        return slaveDataSources.get(idx);
    }
}
