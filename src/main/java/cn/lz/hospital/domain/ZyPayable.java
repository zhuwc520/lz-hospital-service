package cn.lz.hospital.domain;

import java.math.BigDecimal;

public class ZyPayable {

    //住院编码
    private String zy_bm;

    //项目名称
    private String xm;

    //总金额
    private BigDecimal total;

    public String getZy_bm() {
        return zy_bm;
    }
    public void setZy_bm(String zy_bm) {
        this.zy_bm = zy_bm;
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
