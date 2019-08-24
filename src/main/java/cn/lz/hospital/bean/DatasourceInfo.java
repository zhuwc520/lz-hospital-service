package cn.lz.hospital.bean;

import java.io.Serializable;

/**
 *
 * @author Moses
 * @date 2018-12-18 14:55:22
 * @copyright Copyright by www.lamic.cn
 * 数据源信息
 *
 */

public class DatasourceInfo implements Serializable {


    private static final long serialVersionUID = 171525333292698706L;

    private String databaseUrl;
    private String databaseName;
    private String databaseUser;
    private String databasePwd;


    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    public String getDatabasePwd() {
        return databasePwd;
    }

    public void setDatabasePwd(String databasePwd) {
        this.databasePwd = databasePwd;
    }
}
