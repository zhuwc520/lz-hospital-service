package cn.lz.hospital.interceptor;

import cn.lz.hospital.bean.sys.OutMsgBean;
import com.alibaba.fastjson.JSON;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import win.hupubao.common.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhuwc 系统拦截
 * @ClassName SystemInterceptor
 * @Description TODO
 * @Date 2019/1/18 17:35
 * @Version 1.0
 **/
public class SystemInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (!request.getMethod().equals("POST")) {
            OutMsgBean outMsgBean = new OutMsgBean(-101,"请求方式错误");
            outJSONMsg(response,outMsgBean);
            return false;
        }
        return true;
    }

    public static void outJSONMsg(HttpServletResponse response, Object object) {
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
}
