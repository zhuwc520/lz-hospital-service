package cn.lz.hospital.domain;

import java.io.Serializable;

/**
 * @ClassName HYzList
 * @Description: TODO 医嘱列表
 * @Author zhuwc
 * @Date 2019/10/9
 * @Version V1.0
 **/
public class HYzList implements Serializable {
    //序号
    private String xh;
    //住院号
    private String zyh;
    //开始时间
    private String begintime;
    //医嘱名称
    private String yzname;
    //分组标识
    private String fzbs;
    //用法
    private String yf;
    //组数
    private String zs;
    //开单医生
    private String kdys;
    //执行时间
    private String zxsj;
    //执行护士
    private String zxhs;
    //停止时间
    private String jssj;
    //停止医生
    private String tzys;
    //停止执行时间
    private String tzzxsj;
    //停止执行护士
    private String tzhs;
    //规格|数量|单位
    private String ggstr;

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getYzname() {
        return yzname;
    }

    public void setYzname(String yzname) {
        this.yzname = yzname;
    }

    public String getFzbs() {
        return fzbs;
    }

    public void setFzbs(String fzbs) {
        this.fzbs = fzbs;
    }

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getKdys() {
        return kdys;
    }

    public void setKdys(String kdys) {
        this.kdys = kdys;
    }

    public String getZxsj() {
        return zxsj;
    }

    public void setZxsj(String zxsj) {
        this.zxsj = zxsj;
    }

    public String getZxhs() {
        return zxhs;
    }

    public void setZxhs(String zxhs) {
        this.zxhs = zxhs;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getTzys() {
        return tzys;
    }

    public void setTzys(String tzys) {
        this.tzys = tzys;
    }

    public String getTzzxsj() {
        return tzzxsj;
    }

    public void setTzzxsj(String tzzxsj) {
        this.tzzxsj = tzzxsj;
    }

    public String getTzhs() {
        return tzhs;
    }

    public void setTzhs(String tzhs) {
        this.tzhs = tzhs;
    }

    public String getGgstr() {
        return ggstr;
    }

    public void setGgstr(String ggstr) {
        this.ggstr = ggstr;
    }
}
