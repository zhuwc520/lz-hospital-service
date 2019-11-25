package cn.lz.hospital.domain;

import java.io.Serializable;

/**
 * Author: hr
 * Date: 2019-11-25 09:44
 * Content:
 */
public class NurseBean implements Serializable {


    /**
     * ks : 内科
     * name : 刘娟
     */
    private Integer result;

    private String ks;
    private String xm;

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
