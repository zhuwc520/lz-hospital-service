package cn.lz.hospital.utils.datasource.holder;


import cn.lz.hospital.utils.datasource.DBOperationType;

/**
* @Description:    
* @Author:         zhuwc
* @CreateDate:     2018/12/20 15:45
* @Version:        1.0
*/
public class DynamicDataSourceHolder {

    private static ThreadLocal<DBOperationType> dbOperationTypeThreadLocal = new ThreadLocal<>();

    public static void setDbOperationType(DBOperationType dbOperationType) {
        dbOperationTypeThreadLocal.set(dbOperationType);
    }

    public static DBOperationType getDbOperationType() {
        DBOperationType dbOperationType = dbOperationTypeThreadLocal.get();

        if (null == dbOperationType) {
            dbOperationType = DBOperationType.UPDATE;
        }

        return dbOperationType;
    }

    public static void clearDataSourceType() {
        dbOperationTypeThreadLocal.remove();
    }

}