package cn.lz.hospital.utils.datasource;

import cn.lz.hospital.utils.datasource.holder.DynamicDataSourceHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import win.hupubao.common.utils.LoggerUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
* @Description:    
* @Author:         zhuwc
* @CreateDate:     2018/12/20 15:23
* @Version:        1.0
*/
public class DynamicDataSource extends AbstractRoutingDataSource
{

    private static final String MASTER = "master";

    private Object masterDataSource;
    private List<Object> slaveDataSourceList;

    private Map<Object, Object> allDataSources;
    private List<Object> readDataSourceKeyList;

    public void setMasterDataSource(Object masterDataSource) {
        this.masterDataSource = masterDataSource;
    }

    public void setSlaveDataSourceList(List<Object> slaveDataSourceList) {
        this.slaveDataSourceList = slaveDataSourceList;
    }


    @Override
    public void afterPropertiesSet() {

        //参数检查
        if (null == masterDataSource) {
            throw new IllegalArgumentException("Property 'masterDataSource' is required");
        }
        if (null == slaveDataSourceList) {
            throw new IllegalArgumentException("Property 'slaveDataSourceList' is required");
        }

        allDataSources = new HashMap<>();
        readDataSourceKeyList = new ArrayList<>(slaveDataSourceList.size());


        //添加主库
        allDataSources.put(MASTER, masterDataSource);

        //添加从库
        for (int i = 0; i < slaveDataSourceList.size(); i++) {
            String dataSourceKey = "slave" + i;
            allDataSources.put(dataSourceKey, slaveDataSourceList.get(i));
            readDataSourceKeyList.add(dataSourceKey);
        }

        //设置父类的targetDataSource属性
        super.setDefaultTargetDataSource(masterDataSource);
        super.setTargetDataSources(allDataSources);


        super.afterPropertiesSet();
    }

    //获取数据源对应的key
    @Override
    protected Object determineCurrentLookupKey()
    {
        DBOperationType dBOperationType = DynamicDataSourceHolder.getDbOperationType();

        if (DBOperationType.READ == dBOperationType) {

            //如果读操作，读数据源中随机获取一个数据源
            String dataSourceKey = getRandomBackupDatasource();
            LoggerUtils.debug("read,dataSourceKey: {}", dataSourceKey);
            return dataSourceKey;
        } else {
            //如果更新操作，返回主库
            LoggerUtils.debug("update,dataSourceKey:{} ", MASTER);
            return MASTER;
        }
    }

    private String getRandomBackupDatasource() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, readDataSourceKeyList.size());
        return (String) readDataSourceKeyList.get(randomNum);
    }

}