package cn.yunhe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sun.xml.internal.bind.v2.TODO;

import cn.yunhe.biz.PermissionManager;
import cn.yunhe.biz.impl.PermissionManagerImpl;
import cn.yunhe.entity.Permission;

 
public class PermissionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  static Logger logger =  Logger.getLogger(PermissionServlet.class);
    
	PermissionManager  pManager = new PermissionManagerImpl();
    public PermissionServlet() {
        super();
       
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
		 response.setCharacterEncoding("utf-8");
		 response.setContentType("text/html;charset= utf-8 ");
		 String method = request.getParameter("method");
		 if("add".equals(method)){
			 addPermission(request,response);
		 }else if("delete".equals(method)){
			 delete(request,response);
		 }else if("update".equals(method)){
			 update(request,response);
		 }else{
			 
		 }
		
 
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		 String permission_id = request.getParameter("permission_id");
		 String name = request.getParameter("permission_name");
			
		 boolean isYes =   pManager.updatePermission(name, Integer.parseInt(permission_id));
		    if(isYes){
		    	try {
					response.getWriter().print("<script type='text/javascript'>alert('修改成功');window.location.href='permissionmanager.jsp';</script>");
				} catch (IOException e) {
					//e.printStackTrace();
					logger.debug(e);
					logger.error(e);
				}
		    }else{
		    	try { 
		    		response.getWriter().print("<script type='text/javascript'>alert('服务器开小差了');window.location.href='permissionmanager.jsp';</script>");
				} catch (IOException e) {
					//e.printStackTrace();
					logger.debug(e);
					logger.error(e);
				}
		    }
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		 String permission_id = request.getParameter("permission_id");
		
		 boolean isYes =   pManager.deletePermission(Integer.parseInt(permission_id));
		    if(isYes){
		    	try {
					response.getWriter().print("<script type='text/javascript'>alert('删除成功');window.location.href='permissionmanager.jsp';</script>");
				} catch (IOException e) {
					//e.printStackTrace();
					logger.debug(e);
					logger.error(e);
				}
		    }else{
		    	try { 
		    		response.getWriter().print("<script type='text/javascript'>alert('服务器开小差了');window.location.href='permissionmanager.jsp';</script>");
				} catch (IOException e) {
					//e.printStackTrace();
					logger.debug(e);
					logger.error(e);
				}
		    }
	}

	private void addPermission(HttpServletRequest request,
			HttpServletResponse response) {
		String pName = request.getParameter("class_name");
		Permission permission = new Permission();
		permission.setPermission_name(pName);
	    boolean	isYes =  pManager.addPermission(permission);
	    if(isYes){
	    	try {
				response.getWriter().print("<script type='text/javascript'>alert('添加成功');window.location.href='permissionmanager.jsp';</script>");
			} catch (IOException e) {
				//e.printStackTrace();
				logger.debug(e);
				logger.error(e);
			}
	    }else{
	    	try { 
	    		response.getWriter().print("<script type='text/javascript'>alert('服务器开小差了');window.location.href='permissionmanager.jsp';</script>");
			} catch (IOException e) {
				//e.printStackTrace();
				logger.debug(e);
				logger.error(e);
			}
	    }
	}
}
