package cn.lz.hospital.utils.datasource.transaction;

import cn.lz.hospital.utils.datasource.DBOperationType;
import cn.lz.hospital.utils.datasource.holder.DynamicDataSourceHolder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
* @Description:    
* @Author:         zhuwc
* @CreateDate:     2018/12/20 15:22
* @Version:        1.0
*/
public class DynamicDataSourceTransactionManager extends DataSourceTransactionManager {
 
    /**
     * 只读事务到读库，写事务到写库
     * @param transaction
     * @param definition
     */
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) {
 
        //设置数据源
        boolean readOnly = definition.isReadOnly();
        if(readOnly) {
            DynamicDataSourceHolder.setDbOperationType(DBOperationType.READ);
        } else {
            DynamicDataSourceHolder.setDbOperationType(DBOperationType.UPDATE);
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
        DynamicDataSourceHolder.clearDataSourceType();
    }
}
