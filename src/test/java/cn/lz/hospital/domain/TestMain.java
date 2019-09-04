package cn.lz.hospital.domain;

import cn.lz.hospital.utils.HttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestMain {
    public static final String URL = "http://localhost:8080/sick/";
    public static void main(String [] arg){

    }
    @Test
    public void checkUniqueness(){
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("card_no","888888");
        String res = HttpUtil.sendPost("http://localhost:8080/sick/checkUniqueness",reqMap);
        System.out.println(res);
    }
    @Test
    public void getKsList(){
        String res = HttpUtil.sendPost("http://localhost:8080/sick/getKsList",null);
        System.out.println(res);
    }
    @Test
    public void getDoctorListByid(){
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("department_id","60");
        String res = HttpUtil.sendPost("http://localhost:8080/sick/getDoctorListByid",reqMap);
        System.out.println(res);
    }
    @Test
    public void getGhTypeList(){
        String res = HttpUtil.sendPost("http://localhost:8080/sick/getGhTypeList",null);
        System.out.println(res);
    }

    @Test
    public void ghInsert(){
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("name","张三");
        reqMap.put("sex",0);
        reqMap.put("age",18);
        reqMap.put("ghtype_id",01);
        reqMap.put("doctor_id",129);
        reqMap.put("department_id",60);
        reqMap.put("card_no","048273");
        reqMap.put("id_card","511023198805019849");
        String res = HttpUtil.sendPost("http://localhost:8080/sick/ghInsert",reqMap);
        System.out.println(res);
    }
    @Test
    public void getMzPayableList(){
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("card_no","1015578");
        String res = HttpUtil.sendPost("http://localhost:8080/sick/getMzPayableList",reqMap);
        System.out.println(res);
    }
    @Test
    public void getZyPayableList(){
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("card_no","1015578");
        String res = HttpUtil.sendPost("http://localhost:8080/sick/getZyPayableList",reqMap);
        System.out.println(res);
    }
    @Test
    public void insertZyPay(){
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("card_no","048273");
        reqMap.put("total",288.00);
        String res = HttpUtil.sendPost("http://localhost:8080/sick/insertZyPay",reqMap);
        System.out.println(res);
    }
    @Test
    public void insertZyPrePay(){
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("card_no","048273");
        reqMap.put("total",288.00);
        String res = HttpUtil.sendPost("http://localhost:8080/sick/insertZyPrePay",reqMap);
        System.out.println(res);
    }
    @Test
    public void hyBgDetail(){
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("tmh","919251102000");
        String res = HttpUtil.sendPost("http://localhost:8080/sick/hyBgDetail",reqMap);
        System.out.println(res);
    }
@Test
    public void insertMzPay(){
    Map<String,Object> reqMap = new HashMap<>();
    reqMap.put("card_no","048273");
    reqMap.put("total",288.00);

    String res = HttpUtil.sendPost("http://localhost:8080/sick/insertMzPay",reqMap);
    System.out.println(res);
    }
    @Test
    public void jchyBgInfo(){
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("tmh","919236101858");
        reqMap.put("type","化验");
        String res = HttpUtil.sendPost("http://localhost:8080/sick/jchyBgInfo",reqMap);
        System.out.println(res);
    }
    @Test
    public void jchyBgList(){
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("card_no","621553");
        String res = HttpUtil.sendPost("http://localhost:8080/sick/jchyBgList",reqMap);
        System.out.println(res);
    }


}
