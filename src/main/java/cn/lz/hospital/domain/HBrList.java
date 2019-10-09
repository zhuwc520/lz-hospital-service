package cn.lz.hospital.domain;

import java.io.Serializable;

/**
 * @ClassName HBrList
 * @Description: TODO 查房患者列表
 * @Author zhuwc
 * @Date 2019/10/9
 * @Version V1.0
 **/
public class HBrList implements Serializable {
    //患者住院编号
    private String zybm;
    //患者 card_no
    private String card_no;
    //患者姓名
    private String name;
    //患者性别 1=男 0=女
    private Integer sex;
    //患者年龄
    private String age;
    //入院医生
    private String rydoctor;
    //管床医生
    private String gcdoctor;
    //病房
    private String room;
    //床号
    private String bed_no;
    //下次查房时间
    private String check_time;
    //共查房次数
    private Integer counts;
    //所在科室
    private String zyks;

    public String getZybm() {
        return zybm;
    }

    public void setZybm(String zybm) {
        this.zybm = zybm;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRydoctor() {
        return rydoctor;
    }

    public void setRydoctor(String rydoctor) {
        this.rydoctor = rydoctor;
    }

    public String getGcdoctor() {
        return gcdoctor;
    }

    public void setGcdoctor(String gcdoctor) {
        this.gcdoctor = gcdoctor;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBed_no() {
        return bed_no;
    }

    public void setBed_no(String bed_no) {
        this.bed_no = bed_no;
    }

    public String getCheck_time() {
        return check_time;
    }

    public void setCheck_time(String check_time) {
        this.check_time = check_time;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getZyks() {
        return zyks;
    }

    public void setZyks(String zyks) {
        this.zyks = zyks;
    }
}
