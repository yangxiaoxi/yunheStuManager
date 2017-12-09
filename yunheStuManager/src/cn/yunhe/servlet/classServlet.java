package cn.yunhe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.yunhe.biz.ClazzManager;
import cn.yunhe.biz.impl.ClazzManagerImpl;
import cn.yunhe.entity.Clazz;
import cn.yunhe.util.JDBCutil;

//@WebServlet("/classServlet")
public class classServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  static Logger logger =  Logger.getLogger(classServlet.class);
    
    public classServlet() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String method = request.getParameter("method");
		
		if("add".equals(method)){
			addClass(request, response);
		}else if("update".equals(method)){
			updateClass(request, response);
		}else if("delete".equals(method)){
			deleteClass(request, response);
		}else {
			response.getWriter().println("<script type='text/javascript'>alert('服务器开小差了');window.location.href='stuinfomanager.jsp';</script>");
		}
	}
	
	/**
	 *  修改班级的控制
	 * @param request
	 * @param response
	 */
	private void addClass(HttpServletRequest request, HttpServletResponse response){
		String class_name = request.getParameter("class_name");
		 
		Clazz clazz = new Clazz();
		clazz.setClazz_name(class_name);
		ClazzManager clazzManager = new ClazzManagerImpl();
		boolean isYes = clazzManager.insert(clazz);
		if(isYes){
			try {
				response.getWriter().println("<script type='text/javascript'>alert('添加成功');window.location.href='classmanager.jsp';</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.debug(e);
				logger.error(e);
			}
		}
		
	}
	/**
	 *  修改班级的控制
	 * @param request
	 * @param response
	 */
	private void updateClass(HttpServletRequest request, HttpServletResponse response){
		String class_idStr = request.getParameter("class_id");
		int class_id = Integer.parseInt(class_idStr);
		String class_name = request.getParameter("class_name");
		
		Clazz clazz = new Clazz();
		clazz.setClazz_id(class_id);
		clazz.setClazz_name(class_name);
		ClazzManager clazzManager = new ClazzManagerImpl();
		 boolean isYes = clazzManager.update(clazz, class_id);
		 if(isYes){
			 try {
				response.getWriter().println("<script type='text/javascript'>window.location.href='classmanager.jsp';alert('修改成功');</script>");
			} catch (IOException e) {
				//e.printStackTrace();
				logger.debug(e);
				logger.error(e);
			}
		 }
		
	}
	/**
	 * 删除班级的控制
	 * @param request
	 * @param response
	 */
	private void deleteClass(HttpServletRequest request, HttpServletResponse response){
		ClazzManager clazzManager = new ClazzManagerImpl();
		String clazz_idStr = request.getParameter("class_id");
		int clazz_id = Integer.parseInt(clazz_idStr);
		boolean isYes = clazzManager.delete(clazz_id);
		if (isYes) {
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('删除成功');window.location.href='classmanager.jsp';</script>");
			} catch (IOException e) {
				logger.debug(e);
				logger.error(e);
			}
		}
	}

}
