package cn.lz.hospital.domain;

import java.io.Serializable;

public class JchyBgList implements Serializable {
    //条码号
    private String tmh;
    //类别
    private String type;
    //项目名称
    private String xmname;
    //检查部位
    private String checkbw;
    //报告时间
    private String bgdate;

    public String getTmh() {
        return tmh;
    }

    public void setTmh(String tmh) {
        this.tmh = tmh;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getXmname() {
        return xmname;
    }

    public void setXmname(String xmname) {
        this.xmname = xmname;
    }

    public String getCheckbw() {
        return checkbw;
    }

    public void setCheckbw(String checkbw) {
        this.checkbw = checkbw;
    }

    public String getBgdate() {
        return bgdate;
    }

    public void setBgdate(String bgdate) {
        this.bgdate = bgdate;
    }
}
