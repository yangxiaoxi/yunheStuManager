package cn.yunhe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/otherServlet")
public class OtherServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		 doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	     System.out.println("第三方登陆进入");
          resp.getWriter().print("<script type='text/javascript' src='http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js' charset='utf-8' data-callback='true'></script>");
	}

}
