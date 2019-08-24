package cn.lz.hospital.domain;

import java.io.Serializable;

/**
 * 科室实体对象
 */
public class KS implements Serializable {
    /**
     * 科室id
     */
    private Integer id;
    /**
     * 科室名称
     */
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
