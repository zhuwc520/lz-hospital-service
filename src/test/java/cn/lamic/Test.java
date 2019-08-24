package cn.lamic;

import com.alibaba.fastjson.JSONObject;
import org.jsoup.Connection;
import win.hupubao.common.http.Page;
import win.hupubao.common.utils.Md5Utils;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    private static final String key = "fde9ed389b2d4aec92309eb1a1e060c5";
    @org.junit.Test
    public void test() {

    }

    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1; i ++) {
            service.submit(Test::test1);
        }

        begin.countDown();
        service.shutdown();
    }

//    @org.junit.Test
    public static void test1() {


        JSONObject params = new JSONObject();
        JSONObject data = new JSONObject();
        params.put("action", "auth");
        params.put("method", "active");
        params.put("signType", "MD5");

        data.put("activeCode", "123");
        params.put("data", data);
        params.put("sign", Md5Utils.sign(key, data.toJavaObject(Map.class)));

        System.out.println(params);
        System.out.println(Page.create().request("http://localhost:8500/api", params, Connection.Method.POST));
    }
}
