package cn.yunhe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.sun.xml.internal.ws.wsdl.writer.document.ParamType;

import cn.yunhe.biz.UserBiz;
import cn.yunhe.biz.impl.UserBizImpl;
import cn.yunhe.entity.User;
import cn.yunhe.util.JDBCutil;
import cn.yunhe.util.Parameter;
import cn.yunhe.util.VerifyCodeUtilByYock;

public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBiz userBiz = new UserBizImpl();
	private  static Logger logger =  Logger.getLogger(userServlet.class);

	public userServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");

		String method = request.getParameter("method");
		if ("checkLogin".equals(method)) {
			checkLog(request, response);
		}
		if ("exit".equals(method)) {
			exit(request, response);
		}
		
		 if("add".equals(method)){
			 addUser(request,response);
		 }
		 if("delete".equals(method)){
			 deletUser(request,response);
		 }
		 if("update".equals(method)){
			 updateUer(request,response);
		 } 
		 if ("checkLoginThree".equals(method)) {
			 checkLoginThree(request, response);
			}
	}

	private void updateUer(HttpServletRequest request,
			HttpServletResponse response) {
		
		
		User u = new User();
		String user_id = request.getParameter("user_id");
		u.setUser_id(Integer.parseInt(user_id));
		String user_type = request.getParameter("user_type");
		u.setUser_type(Integer.parseInt(user_type));
		if("1".equals(user_type)){
			String user_name = request.getParameter("user_name");
			u.setUser_name(user_name);
			String user_pwd = request.getParameter("user_pwd");
			u.setUser_pwd(user_pwd);
		}
		String user_nickname = request.getParameter("user_nickname");
		u.setUser_nickname(user_nickname);
		String user_role=request.getParameter("role_id");
		u.setUser_role(Integer.parseInt(user_role));
		String user_class_id = request.getParameter("class_id");
		u.setClass_id(Integer.parseInt(user_class_id));
		 boolean isYes =   userBiz.updateUser(u);
		    if(isYes){
		    	try {
					response.getWriter().print("<script type='text/javascript'>alert('修改成功');window.location.href='usermanager.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
		    }else{
		    	try { 
		    		response.getWriter().print("<script type='text/javascript'>alert('服务器开小差了');window.location.href='usermanager.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
		    }
	}

	private void deletUser(HttpServletRequest request,
			HttpServletResponse response) {
		 String user_id = request.getParameter("user_id");
			
		 boolean isYes =   userBiz.deletUser(Integer.parseInt(user_id));
		    if(isYes){
		    	try {
					response.getWriter().print("<script type='text/javascript'>alert('删除成功');window.location.href='usermanager.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
		    }else{
		    	try { 
		    		response.getWriter().print("<script type='text/javascript'>alert('服务器开小差了');window.location.href='usermanager.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
		    }
		
	}

	private void addUser(HttpServletRequest request,
			HttpServletResponse response) {
		 String user_name= request.getParameter("user_name");
		 String pwd = request.getParameter("user_pwd");
		 String user_nickname = request.getParameter("user_nickname");
		 String role_id = request.getParameter("role_id");
		 String class_id = request.getParameter("class_id");
		 User user = new User();
		 user.setUser_name(user_name);
		 user.setUser_pwd(pwd);
		 user.setUser_nickname(user_nickname);
		 user.setUser_role(Integer.parseInt(role_id));
		 user.setClass_id(Integer.parseInt(class_id));
		
		 boolean isYes =   userBiz.addUser(user);
		    if(isYes){
		    	try {
					response.getWriter().print("<script type='text/javascript'>alert('添加成功');window.location.href='usermanager.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
		    }else{
		    	try { 
		    		response.getWriter().print("<script type='text/javascript'>alert('服务器开小差了');window.location.href='usermanager.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
		    }
		
	}

	private void exit(HttpServletRequest request, HttpServletResponse response) {

		// 清除session
		request.getSession().invalidate();
		// 清除cookie,
		Cookie cookie = new Cookie(Parameter.USER_ID, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);

		try {
			response.getWriter()
					.println(
							"<script type='text/javascript'>window.location.href='login.jsp';</script>");
		} catch (IOException e) {
			logger.info(e);
			logger.debug(e);
		}
	}

	private void checkLog(HttpServletRequest request,
			HttpServletResponse response) {

		String verify = request.getParameter("j_captcha");// 获取输入的验证码
		String verifycode = (String) request.getSession().getAttribute(
				"verifycode");

		if (verify.equalsIgnoreCase(verifycode)) {// 如果验证码正确则验证账号密码
			String user_name = request.getParameter("email");
			String pwd = request.getParameter("password");
			String rember = request.getParameter("j_remember");
			User user = userBiz.checkLogin(user_name, pwd);
			
			if (user != null) {
				if ("true".equals(rember)) {
					// 如何选择框被选中，存cookie
					Cookie cookie = new Cookie(Parameter.USER_ID,
							user.getUser_id() + "");
					cookie.setMaxAge(7 * 24 * 60 * 60);
					response.addCookie(cookie);
				}
				try {
					response.getWriter()
							.println(
									"<script type='text/javascript'>alert('登陆成功');window.location.href='index.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
				request.getSession().setAttribute(Parameter.LOGIN_USER, user);// 保存session
				request.getSession().setMaxInactiveInterval(30*60);
			}else {
				try {
					response.getWriter()
							.println(
									"<script type='text/javascript'>alert('账号或密码错误');window.location.href='login.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
			}
		}else {//如果输入的验证码不匹配
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('验证码错误');window.location.href='login.jsp';</script>");
			} catch (IOException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
		
		// request.getSession().invalidate();

	}
	
	
	// 第三方登录验证
	private void checkLoginThree(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{

			String open_id = request.getParameter("open_id");
			System.out.println("open_id is"+open_id);
			String nike_name = request.getParameter("nick_name");
			User user = userBiz.checkLoginThree(open_id);
			System.out.println("user is"+user);
			JSONObject jo = new JSONObject();
			if (user != null) {
				// 如果用户存在 
				jo.put("status", 200);
//				try {
//					response.getWriter()
//							.println(
//									"<script type='text/javascript'>alert('登陆成功');window.location.href='index.jsp';</script>");
//				} catch (IOException e) {
//					logger.info(e);
//					logger.debug(e);
//				}
				request.getSession().setAttribute(Parameter.LOGIN_USER, user);// 保存session
				request.getSession().setMaxInactiveInterval(30*60);
			}else {//如果用户不存在,将用户存入数据库
				user = new User();
				user.setOpen_id(open_id);
				user.setUser_nickname(nike_name);
				userBiz.addUserThree(user);
				jo.put("status", 500); 
			}
		  response.getWriter().print(jo.toString());
		} 
}
