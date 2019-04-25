package com.example.steam.config;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * Created with IntelliJ IDEA.
 * 动态数据源事物管理
 * 在开启事务的时候，
 * 根据只读事务、读写事务
 * 选择操作是主库还是从库
 * @author: Suyeq
 * @date: 2019-04-25
 * @time: 10:38
 */
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {

    /**
     * 只读事务到读库，读写事务到写库
     * @param transaction
     * @param definition
     */
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
        //设置数据源
        boolean readOnly = definition.isReadOnly();
        System.out.println(readOnly);
        if(readOnly) {
            DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.SLAVE);
        } else {
            DynamicDataSourceHolder.putDataSource(DynamicDataSourceHolder.MASTER);
        }
        super.doBegin(transaction, definition);
    }

    /**
     * 清理本地线程的数据源
     * @param transaction
     */
    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        super.doCleanupAfterCompletion(transaction);
        DynamicDataSourceHolder.clearDataSource();
    }
}
