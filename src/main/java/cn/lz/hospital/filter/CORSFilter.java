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
		HttpServletRequest request=(HttpServletRequest)servletRequest;
		HttpServletResponse response=(HttpServletResponse)servletResponse;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		//过滤通行证
		filterChain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
