package cn.yunhe.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.yunhe.biz.UserBiz;
import cn.yunhe.biz.impl.UserBizImpl;
import cn.yunhe.entity.User;
import cn.yunhe.util.Parameter;

public class LoginFilter implements Filter {
	private UserBiz userBiz = new UserBizImpl();
	public LoginFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String[] url = httpRequest.getRequestURI().split("/");
		String resourse = url[url.length - 1];

		// 如果访问的资源是以.jsp结尾的
		if (resourse.endsWith(".jsp") || resourse.endsWith("yunheStuManager")) {

			if (resourse.equals("login.jsp")) {// 如果访问的资源是以login.jsp ,直接放行
				chain.doFilter(request, response);
			}else {// 如果访问的资源不是login.jsp,
					 //检查是否存session
					User user = (User) httpRequest.getSession().getAttribute(
							Parameter.LOGIN_USER);
					if (user != null) {// 如果seesion不为空，放行
							chain.doFilter(request, response);// 如果cookie不为空，放行	
					}else {// 如果session为空，检查cookie
						Cookie[] cookies = httpRequest.getCookies();
						int haveCookie = 0;
						if (cookies != null) { // 如果cookie不为空，则放行
							for (Cookie cookie : cookies) {
								if (Parameter.USER_ID.equals(cookie.getName())&&cookie.getValue()!=null) {
										chain.doFilter(request, response);// 如果cookie不为空，放行	
									break;
								}else {
									haveCookie++;
								}
							}
							if (haveCookie == cookies.length) {
								//httpResponse.sendRedirect("login.jsp");
								httpRequest.getRequestDispatcher("login.jsp").forward(httpRequest, httpResponse);
							}
						}else{// 如果cookie也为空，则跳转至登陆页面
							//httpResponse.sendRedirect("login.jsp");
							httpRequest.getRequestDispatcher("login.jsp").forward(httpRequest, httpResponse);
						}
					}
			}
		}else {// 如果访问的资源不是以.jsp结尾的 ，则放行
			chain.doFilter(request, response);
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
