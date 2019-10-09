package cn.lz.hospital.domain;

import java.io.Serializable;

/**
 * @ClassName HHsList
 * @Description: TODO 护士列表
 * @Author zhuwc
 * @Date 2019/10/9
 * @Version V1.0
 **/
public class HHsList implements Serializable {
    //护士id
    private Integer id;
    //护士编号
    private String number;
    //护士头像地址
    private String avatar;
    //护士姓名
    private String name;
    //护士所属科室
    private String ks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }
}
