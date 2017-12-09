package cn.yunhe.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.yunhe.biz.RoleManager;
import cn.yunhe.biz.impl.RoleManagerImpl;
import cn.yunhe.entity.Permission;
import cn.yunhe.entity.Role;
import cn.yunhe.util.JDBCutil;

 
 
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private RoleManager roleManger = new RoleManagerImpl();
     private  static Logger logger =  Logger.getLogger(RoleServlet.class);
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 response.setCharacterEncoding("utf-8");
		 response.setContentType("text/html;charset= utf-8 ");
		 String method = request.getParameter("method");
		 if("add".equals(method)){
			 add(request,response);
		 }else if("delete".equals(method)){
			 delete(request,response);
		 }else if("update".equals(method)){
			 System.out.println("进入servet");
			 update(request,response);
		 }else{
			 
		 }
		
	}


	private void update(HttpServletRequest request, HttpServletResponse response) {
		 String role_id = request.getParameter("role_id");
		 String role_name = request.getParameter("role_name");
		 String[] permission = request.getParameterValues("permission");
		 List<Integer> listPermission = new ArrayList<Integer>();
		 for(String str:permission){
			 listPermission.add(Integer.parseInt(str));
		 }
		 boolean isYes = roleManger.updateRolePemrission(role_name, Integer.parseInt(role_id), listPermission);
		    if(isYes){
		    	try {
					response.getWriter().print("<script type='text/javascript'>alert('修改成功');window.location.href='rolemanager.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
		    }else{
		    	try { 
		    		response.getWriter().print("<script type='text/javascript'>alert('服务器开小差了');window.location.href='rolemanager.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
		    }
		
		
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) {
		 String role_id = request.getParameter("role_id");
			
		 boolean isYes =   roleManger.deletRole(Integer.parseInt(role_id));
		    if(isYes){
		    	try {
					response.getWriter().print("<script type='text/javascript'>alert('删除成功');window.location.href='rolemanager.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
		    }else{
		    	try { 
		    		response.getWriter().print("<script type='text/javascript'>alert('服务器开小差了');window.location.href='rolemanager.jsp';</script>");
				} catch (IOException e) {
					logger.info(e);
					logger.debug(e);
				}
		    }
		
	}


	private void add(HttpServletRequest request,
			HttpServletResponse response) {
		String role_name = request.getParameter("role_name");
	    String[] permission = request.getParameterValues("permission");
		 List<Integer> listPermission = new ArrayList<Integer>();
		 for(String str:permission){
			 listPermission.add(Integer.parseInt(str));
		 }
	    boolean	isYes =  roleManger.addRolePermission(role_name, listPermission);
	    if(isYes){
	    	try {
				response.getWriter().print("<script type='text/javascript'>alert('添加成功');window.location.href='rolemanager.jsp';</script>");
			} catch (IOException e) {
				e.printStackTrace();
				logger.info(e);
				logger.debug(e);
			}
	    }else{
	    	try { 
	    		response.getWriter().print("<script type='text/javascript'>alert('服务器开小差了');window.location.href='rolemanager.jsp';</script>");
			} catch (IOException e) {
				logger.info(e);
				logger.debug(e);
			}
	    }
	}

}
