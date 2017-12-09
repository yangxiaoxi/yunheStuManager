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

		// ������ʵ���Դ����.jsp��β��
		if (resourse.endsWith(".jsp") || resourse.endsWith("yunheStuManager")) {

			if (resourse.equals("login.jsp")) {// ������ʵ���Դ����login.jsp ,ֱ�ӷ���
				chain.doFilter(request, response);
			}else {// ������ʵ���Դ����login.jsp,
					 //����Ƿ��session
					User user = (User) httpRequest.getSession().getAttribute(
							Parameter.LOGIN_USER);
					if (user != null) {// ���seesion��Ϊ�գ�����
							chain.doFilter(request, response);// ���cookie��Ϊ�գ�����	
					}else {// ���sessionΪ�գ����cookie
						Cookie[] cookies = httpRequest.getCookies();
						int haveCookie = 0;
						if (cookies != null) { // ���cookie��Ϊ�գ������
							for (Cookie cookie : cookies) {
								if (Parameter.USER_ID.equals(cookie.getName())&&cookie.getValue()!=null) {
										chain.doFilter(request, response);// ���cookie��Ϊ�գ�����	
									break;
								}else {
									haveCookie++;
								}
							}
							if (haveCookie == cookies.length) {
								//httpResponse.sendRedirect("login.jsp");
								httpRequest.getRequestDispatcher("login.jsp").forward(httpRequest, httpResponse);
							}
						}else{// ���cookieҲΪ�գ�����ת����½ҳ��
							//httpResponse.sendRedirect("login.jsp");
							httpRequest.getRequestDispatcher("login.jsp").forward(httpRequest, httpResponse);
						}
					}
			}
		}else {// ������ʵ���Դ������.jsp��β�� �������
			chain.doFilter(request, response);
		}
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
