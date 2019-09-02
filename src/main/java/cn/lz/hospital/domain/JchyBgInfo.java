package cn.lz.hospital.domain;

import java.io.Serializable;

public class JchyBgInfo implements Serializable {
    //姓名
    private String name;
    //性别
    private String sex;
    //年龄
    private String age;
    //科室
    private String ks;
    //医生
    private String ys;
    //就诊号
    private String jybm;
    //申请时间
    private String sqdate;
    //来源
    private String ly;
    //检查部位
    private String checkbw;
    //床号
    private String cw;
    //报告医生
    private String bgys;
    //审核医生
    private String shys;
    //报告时间
    private String bgdate;
    //影像描述
    private String yyms;
    //诊断意见
    private String zdyj;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }

    public String getJybm() {
        return jybm;
    }

    public void setJybm(String jybm) {
        this.jybm = jybm;
    }

    public String getSqdate() {
        return sqdate;
    }

    public void setSqdate(String sqdate) {
        this.sqdate = sqdate;
    }

    public String getLy() {
        return ly;
    }

    public void setLy(String ly) {
        this.ly = ly;
    }

    public String getCheckbw() {
        return checkbw;
    }

    public void setCheckbw(String checkbw) {
        this.checkbw = checkbw;
    }

    public String getCw() {
        return cw;
    }

    public void setCw(String cw) {
        this.cw = cw;
    }

    public String getBgys() {
        return bgys;
    }

    public void setBgys(String bgys) {
        this.bgys = bgys;
    }

    public String getShys() {
        return shys;
    }

    public void setShys(String shys) {
        this.shys = shys;
    }

    public String getBgdate() {
        return bgdate;
    }

    public void setBgdate(String bgdate) {
        this.bgdate = bgdate;
    }

    public String getYyms() {
        return yyms;
    }

    public void setYyms(String yyms) {
        this.yyms = yyms;
    }

    public String getZdyj() {
        return zdyj;
    }

    public void setZdyj(String zdyj) {
        this.zdyj = zdyj;
    }
}
