package cn.lz.hospital.domain;

import java.math.BigDecimal;

/**
 * 缴费明细实体对象
 */
public class MzPayable {
    private BigDecimal bm;

    private String xm;

    private BigDecimal total;

    public BigDecimal getBm() {
        return bm;
    }

    public void setBm(BigDecimal bm) {
        this.bm = bm;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
