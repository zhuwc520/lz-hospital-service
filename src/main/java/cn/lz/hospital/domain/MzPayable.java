package cn.lz.hospital.domain;

import java.math.BigDecimal;

/**
 * 缴费明细实体对象
 */
public class MzPayable {
    private BigDecimal Bm;

    private String Xm;

    private BigDecimal Total;

    public BigDecimal getBm() {
        return Bm;
    }

    public void setBm(BigDecimal bm) {
        Bm = bm;
    }

    public String getXm() {
        return Xm;
    }

    public void setXm(String xm) {
        Xm = xm;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

}
