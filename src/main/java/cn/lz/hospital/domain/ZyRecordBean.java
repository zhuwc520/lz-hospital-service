package cn.lz.hospital.domain;

/**
 * Author: hr
 * Date: 2019-10-09 16:06
 * Content:
 */
public class ZyRecordBean {

    //名字
    private String xm;

    //住院编码
    private String zybm;

    //住院时间
    private String rydate;

    //目前状态
    private String zt;

    //卡号
    private String cardno;

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getZybm() {
        return zybm;
    }

    public void setZybm(String zybm) {
        this.zybm = zybm;
    }

    public String getRydate() {
        return rydate;
    }

    public void setRydate(String rydate) {
        this.rydate = rydate;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
}
