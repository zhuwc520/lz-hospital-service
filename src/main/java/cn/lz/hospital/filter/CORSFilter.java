package cn.lz.hospital.filter;

import org.springframework.http.HttpStatus;
import win.hupubao.common.utils.LoggerUtils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
* @Description:    拦截器添加跨域支持（如果是web.xml配置拦截器，请将@component删除）
* @Author:         zhuwc
* @CreateDate:     2019/1/16 16:12
* @Version:        1.0
*/
public class CORSFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		//跨域的header设置
		response.setHeader("Access-control-Allow-Origin",request.getHeader("Origin"));
		LoggerUtils.info( request.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization,chagoiToken");
		LoggerUtils.info(request.getHeader("Access-Control-Request-Headers"));
		//防止乱码，适用于传输JSON数据
		response.setHeader("Content-Type","application/json;charset=UTF-8");
		if(request.getMethod().equals("OPTIONS")){
			response.setStatus(200);
		}
		filterChain.doFilter( servletRequest, response );

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
