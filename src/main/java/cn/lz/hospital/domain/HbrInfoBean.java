package cn.lz.hospital.domain;

/**
 * Author: hr
 * Date: 2019-10-29 16:19
 * Content:
 */
public class HbrInfoBean {

    /**
     * zybm : 住院号
     * name : 姓名
     * sex : 性别
     * age : 年龄
     * ks : 科室
     * rydoctor : 入院医生
     * gcdoctor : 管床医生
     * cw : 床号
     * card_no : 卡号
     */

    private String zybm;
    private String name;
    private String sex;
    private String age;
    private String ks;
    private String rydoctor;
    private String gcdoctor;
    private String cw;
    private String card_no;

    public String getZybm() {
        return zybm;
    }

    public void setZybm(String zybm) {
        this.zybm = zybm;
    }

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

    public String getCw() {
        return cw;
    }

    public void setCw(String cw) {
        this.cw = cw;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

}
