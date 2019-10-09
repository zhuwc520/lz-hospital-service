package cn.lz.hospital.domain;

import java.math.BigDecimal;

/**
 * Author: hr
 * Date: 2019-10-09 16:11
 * Content:入院清单
 */
public class FyDetail {

    /**
     * xm : 葡萄糖注射液
     * gg : 250ml
     * dw : 瓶
     * sl : 1
     * dj : 4.67
     * je : 4.67
     * fsdate : 2019-08-19 09:53:56
     * lb : 西药费
     * ys : 马永彬
     */

    private String xm;
    private String gg;
    private String dw;
    private BigDecimal sl;
    private BigDecimal dj;
    private BigDecimal je;
    private String fsdate;
    private String lb;
    private String ys;

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public BigDecimal getSl() {
        return sl;
    }

    public void setSl(BigDecimal sl) {
        this.sl = sl;
    }

    public BigDecimal getDj() {
        return dj;
    }

    public void setDj(BigDecimal dj) {
        this.dj = dj;
    }

    public BigDecimal getJe() {
        return je;
    }

    public void setJe(BigDecimal je) {
        this.je = je;
    }

    public String getFsdate() {
        return fsdate;
    }

    public void setFsdate(String fsdate) {
        this.fsdate = fsdate;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }
}
