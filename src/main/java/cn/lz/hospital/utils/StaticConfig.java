package cn.lz.hospital.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author zhuwc
 * @ClassName StaticConfig
 * @Description TODO
 * @Date 2019/8/29 14:38
 * @Version 1.0
 **/
public class StaticConfig {
    public static String AES_KEY = "SjknB953RPSyFXS4";

    static {
        Properties properties = new Properties();
        InputStream in = StaticConfig.class.getClassLoader().getResourceAsStream("static-config.properties");

        try {
            properties.load(in);
            AES_KEY = properties.getProperty("aes_key");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
