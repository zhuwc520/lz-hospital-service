
package cn.lz.hospital.utils.datasource.interceptor;
/**
* @Description:    
* @Author:         zhuwc
* @CreateDate:     2018/12/20 13:39
* @Version:        1.0
*/
import cn.lz.hospital.bean.DatasourceInfo;
import cn.lz.hospital.utils.datasource.DBOperationType;
import cn.lz.hospital.utils.datasource.holder.DynamicDataSourceHolder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Map;
import java.util.Properties;


@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        ),
        @Signature(
                type = Executor.class,
                method = "query",
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})

public class DynamicDataSourceInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();
        BoundSql boundSql = handler.getBoundSql();
        Object obj = boundSql.getParameterObject();

        if (obj instanceof DatasourceInfo) {

        } else if (obj instanceof Map) {

        }

        DynamicDataSourceHolder.setDbOperationType(DBOperationType.UPDATE);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}