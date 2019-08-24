package cn.lz.hospital.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class GhType implements Serializable {
    /**
     * 类别编码
     */
    private String reg_bm;
    /**
     * 类别名称
     */
    private String reg_name;
    /**
     * 类别金额
     */
    private BigDecimal reg_money;

    public String getReg_bm() {
        return reg_bm;
    }

    public void setReg_bm(String reg_bm) {
        this.reg_bm = reg_bm;
    }

    public String getReg_name() {
        return reg_name;
    }

    public void setReg_name(String reg_name) {
        this.reg_name = reg_name;
    }

    public BigDecimal getReg_money() {
        return reg_money;
    }

    public void setReg_money(BigDecimal reg_money) {
        this.reg_money = reg_money;
    }
}

