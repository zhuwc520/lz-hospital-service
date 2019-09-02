package cn.lz.hospital.domain;

import java.io.Serializable;

public class HyBgDetail implements Serializable {
    //代码
    private String sx;
    //名称
    private String mc;
    //结果
    private String jg;
    //提示
    private String ts;
    //单位
    private String dw;
    //参考值
    private String ckz;

    public String getSx() {
        return sx;
    }

    public void setSx(String sx) {
        this.sx = sx;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getCkz() {
        return ckz;
    }

    public void setCkz(String ckz) {
        this.ckz = ckz;
    }
}
