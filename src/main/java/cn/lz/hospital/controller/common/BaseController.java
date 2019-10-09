package cn.lz.hospital.controller.common;

import cn.lz.hospital.utils.AESUtil;
import cn.lz.hospital.utils.StaticConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import jodd.util.StringUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import win.hupubao.common.exception.BusinessException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 控制器基类
 * @Author: zhuwc
 * @CreateDate: 2018/12/7 18:27
 * @Version: 1.0
 */
public class BaseController {

    public static final String CODE = "chagoicode";


    /**
     * 获取request
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 创建Cookie
     *
     * @param name
     * @param value
     * @param domain
     * @param maxAge
     * @return
     */
    public static Cookie createCookie(String name, String value, String domain,
                                      Integer maxAge) {
        Cookie cookie = new Cookie(name, value);
        if (StringUtil.isNotBlank(domain)) {
            cookie.setDomain(domain);
        }
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        return cookie;
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        response.addCookie(createCookie(name, "", "", 0));
    }

    /**
     * 设置Session属性
     *
     * @param response
     * @param value
     */
    public static void setSessionAttr(HttpServletResponse response, String name,
                                      String value, String domain, Integer maxAge) {

        response.addCookie(createCookie(name, value, domain, maxAge));
    }

    @SuppressWarnings("unchecked")
    public static <T> T getSessionAttr(HttpServletRequest request,
                                       String attrKey, String attrName) {
        Map<String, Object> session = (Map<String, Object>) request
                .getAttribute(attrKey);
        if (session != null) {
            return (T) session.get(attrName);
        }
        return null;
    }

    /**
     * 获取Cookie中的值
     *
     * @param request
     * @param key
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 获取Cookie中的值
     *
     * @param request
     * @param key
     * @return
     */
    public static String getHeaderValue(HttpServletRequest request, String key) {
        String header = request.getHeader(key);
        return header;
    }


    /**
     * 获取outJSON
     *
     * @param object
     * @return
     * @throws IOException
     */
    public static void outJSON(HttpServletResponse response, Object object) {
        outJSONString(response, JSON.toJSONString(object));
    }

    public static void outJSONMsg(HttpServletResponse response, Object object) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = null;
        try {
            String text = JSON.toJSONString(object);
            out = response.getWriter();
            out.write(text);
        } catch (IOException e) {
            throw new BusinessException("输出信息错误:" + e.getMessage());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
                out = null;
            }
        }
    }

    /**
     * @param response
     * @param object
     * @param isCircular 当传入false时关闭循环检测
     */
    public static void outJSONMsg(HttpServletResponse response, Object object, Boolean isCircular) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = null;
        try {
            String text = "";
            if (!isCircular) {
                text = JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
            } else {
                text = JSON.toJSONString(object);
            }
            out = response.getWriter();
            out.write(text);
        } catch (IOException e) {
            throw new BusinessException("输出信息错误:" + e.getMessage());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
                out = null;
            }
        }
    }

    /**
     * 输出字符串
     *
     * @param response
     * @param text
     * @throws IOException
     */
    public static void outJSONString(HttpServletResponse response, String text) {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(text);
        } catch (IOException e) {
            throw new BusinessException("输出信息错误:" + e.getMessage());
        }
        out.flush();
        out.close();
    }

    /**
     * 分页
     *
     * @param amount
     * @param max
     * @return
     */
    public Integer getPage(Object amount, Integer max) {
        Integer a = Integer.valueOf(amount.toString());
        Integer count = a % max == 0 ? a / max : a / max + 1;
        if (count.intValue() == 0) {
            count = 1;
        }
        return count;
    }

    public String getCode(HttpServletRequest request) {
        String code = getCookieValue(request, CODE);
        return code;
    }

    public void removeCode(HttpServletResponse response) {
        removeCookie(response, CODE);
    }


    public String getString(String name, String defaultValue, Map<String, Object> paramMap) {
        String resultStr = getRequest().getParameter(name);
        if (resultStr == null || "".equals(resultStr) || "null".equals(resultStr) || "undefined".equals(resultStr)) {
            paramMap.put(name, defaultValue);
            return defaultValue;
        } else {
            paramMap.put(name, resultStr);
            return resultStr;
        }
    }

    public String getAESString(String name, String defaultValue, Map<String, Object> paramMap) {
        String resultStr = getRequest().getParameter(name);
        if (resultStr == null || "".equals(resultStr) || "null".equals(resultStr) || "undefined".equals(resultStr)) {
            paramMap.put(name, defaultValue);
            return defaultValue;
        } else {
            paramMap.put(name, AESUtil.Decrypt(resultStr, StaticConfig.AES_KEY));
            return resultStr;
        }
    }

    public Integer getInteger(String name, Integer defaultValue, Map<String, Object> paramMap) {
        String resultStr = getRequest().getParameter(name);
        if (resultStr == null || "".equals(resultStr) || "null".equals(resultStr) || "undefined".equals(resultStr)) {
            paramMap.put(name, defaultValue);
            return defaultValue;
        } else {
            paramMap.put(name, Integer.valueOf(resultStr));
            return Integer.valueOf(resultStr);
        }
    }

    public Integer getAESInteger(String name, Integer defaultValue, Map<String, Object> paramMap) {
        String resultStr = getRequest().getParameter(name);
        if (resultStr == null || "".equals(resultStr) || "null".equals(resultStr) || "undefined".equals(resultStr)) {
            paramMap.put(name, defaultValue);
            return defaultValue;
        } else {
            paramMap.put(name, Integer.valueOf(AESUtil.Decrypt(resultStr,StaticConfig.AES_KEY)));
            return Integer.valueOf(resultStr);
        }
    }

    public Boolean getBoolean(String name, Boolean defaultValue, Map<String, Object> paramMap) {
        String resultStr = getRequest().getParameter(name);
        if (resultStr == null || "".equals(resultStr) || "null".equals(resultStr) || "undefined".equals(resultStr)) {
            paramMap.put(name, defaultValue);
            return defaultValue;
        } else {
            paramMap.put(name, Boolean.valueOf(resultStr));
            return Boolean.valueOf(resultStr);
        }
    }

    public Boolean getAESBoolean(String name, Boolean defaultValue, Map<String, Object> paramMap) {
        String resultStr = getRequest().getParameter(name);
        if (resultStr == null || "".equals(resultStr) || "null".equals(resultStr) || "undefined".equals(resultStr)) {
            paramMap.put(name, defaultValue);
            return defaultValue;
        } else {
            paramMap.put(name, Boolean.valueOf(AESUtil.Decrypt(resultStr,StaticConfig.AES_KEY)));
            return Boolean.valueOf(resultStr);
        }
    }

    public String getString(String name) {
        String value = getString(name, null, new HashMap<>());
        return value;
    }

    /**
     * 转json 去掉引用对象
     *
     * @param response
     * @param object
     */
    public static void outJSONMsgFormat(HttpServletResponse response, Object object) {
        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = null;
        try {
            String text = JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
            out = response.getWriter();
            out.write(text);
        } catch (IOException e) {
            throw new BusinessException("输出信息错误:" + e.getMessage());
        } finally {
            if (out != null) {
                out.flush();
                out.close();
                out = null;
            }
        }
    }


    /**
     * 是否是Ajax请求
     *
     * @param request
     * @return
     * @author SHANHY
     * @create 2017年4月4日
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("x-requested-with");
        if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
            return true;
        } else {
            return false;
        }
    }
}
