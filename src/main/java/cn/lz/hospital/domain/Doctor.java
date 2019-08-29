package cn.lz.hospital.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 医生实体
 */
public class Doctor implements Serializable {
    /**
     * 医生主键ID
     */
    private Integer id;
    /**
     * 医生头像
     */
    private String avatar;
    /**
     * 医生名称
     */
    private String name;
    /**
     * 医生标题
     */
    private String title;
    /**
     * 医生科室id
     */
    private Integer department;
    /**
     * 描述
     */
    private String descript;
    /**
     * 挂号费
     */
    private BigDecimal price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
