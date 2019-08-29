package cn.lz.hospital.domain;

import java.math.BigDecimal;

/**
 * 缴费明细实体对象
 */
public class MzPayable {
    private BigDecimal Bm;

    private BigDecimal Xm;

    private BigDecimal Total;

    public BigDecimal getBm() {
        return Bm;
    }

    public void setBm(BigDecimal bm) {
        Bm = bm;
    }

    public BigDecimal getXm() {
        return Xm;
    }

    public void setXm(BigDecimal xm) {
        Xm = xm;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

}
