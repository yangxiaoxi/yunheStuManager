package cn.yunhe.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sun.org.apache.bcel.internal.generic.IINC;

import cn.yunhe.biz.ScoreManager;
import cn.yunhe.biz.StudentManager;
import cn.yunhe.biz.impl.ScoreManagerImpl;
import cn.yunhe.biz.impl.StudentManagerImpl;
import cn.yunhe.entity.Score;
import cn.yunhe.entity.Student;
import cn.yunhe.util.JDBCutil;
 
//@WebServlet("/scoreServlet")
public class scoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  static Logger logger =  Logger.getLogger(scoreServlet.class);
       
     
    public scoreServlet() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doPost(request, response);
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String  method = request.getParameter("method");
		
		if("add".equals(method)){
			addScore(request,response);
		}else if("update".equals(method)){
			updateScore(request, response);
		}else if("delete".equals(method)) {
			deleteScore(request, response);
		}else if("search".equals(method)) {
			searchScore(request, response);
		}
		else{
			response.getWriter().println("<script type='text/javascript'>alert('服务器开小差了');window.location.href='scoreinfomanager.jsp';</script>");
		}
	}

	/**
	 * 查找分数
	 * @param request
	 * @param response
	 */
	private void searchScore(HttpServletRequest request,
			HttpServletResponse response) {
		
		String type = request.getParameter("type_id");
		System.out.println("type_id is"+type);
		int type_id = "".equals(type)?-1:Integer.parseInt(type);
		String date = request.getParameter("time");
		System.out.println("date is"+date);
		 String time = "";
		if(date.contains("/")){
			 String[]	times = date.split("/");
			 time=times[2]+"-"+times[0]+"-"+times[1];
		}else{
			time=date;
		}
		 System.out.println("time is"+time);
		String stu_idStr = request.getParameter("stu_id");
		
		int stu_id = "".equals(stu_idStr)?-1:Integer.parseInt(stu_idStr);
		
		ScoreManager  scoreManager = new ScoreManagerImpl();
		try {
			response.getWriter().println("<script type='text/javascript'>"
					+ "window.location.href='scoremanager.jsp?currentPage="+1+"&type_id="+type_id+"&time="+time+"&stu_id="+stu_id+"';"
							+ "</script>");
		} catch (IOException e) {
			logger.info(e);
			logger.debug(e);
		}		
		
		
	}

	/**
	 * 删除分数
	 * @param request
	 * @param response
	 */
	private void deleteScore(HttpServletRequest request,
			HttpServletResponse response) {
		ScoreManager scoreManager = new ScoreManagerImpl();
		String stu_idStr = request.getParameter("score_id");
		int score_id = Integer.parseInt(stu_idStr);
		boolean isYes = scoreManager.delete(score_id);
		if (isYes) {
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('删除成功');window.location.href='scoremanager.jsp';</script>");
			} catch (IOException e) {
			
				logger.info(e);
				logger.debug(e);
			}
		}
		
	}

	/**
	 * 修改分数
	 * @param request
	 * @param response
	 */
	private void updateScore(HttpServletRequest request,
			HttpServletResponse response) {
		String score_typeStr = request.getParameter("status");
		int score_type = Integer.parseInt(score_typeStr);
	
		String score_idStr = request.getParameter("score_id");
		int score_id  = Integer.parseInt(score_idStr);
		
		String date = request.getParameter("datepicker");
		
		 String time = "";
			if(date.contains("/")){
				 String[]	times = date.split("/");
				 time=times[2]+"-"+times[0]+"-"+times[1];
			}else{
				time=date;
			}
		
		String stu_idStr = request.getParameter("stu_id");
		int stu_id = Integer.parseInt(stu_idStr);
		
		String score_contents = request.getParameter("score_contents");
		
		String score_valueStr = request.getParameter("score_value");
		double score_value = Double.parseDouble(score_valueStr);
		 
		 Score score = new Score();
		 score.setScore_id(score_id);
		 score.setScore_contents(score_contents);
		 score.setScore_type(score_type);
		 score.setScore_values(score_value);
		 score.setStu_id(stu_id);
		 score.setTime(time);
		
		ScoreManager scoreManager = new ScoreManagerImpl();
		 boolean isYes = scoreManager.update(score,score_id);
		 if(isYes){
			 try {
				response.getWriter().println("<script type='text/javascript'>window.location.href='scoremanager.jsp';alert('修改成功');</script>");
			} catch (IOException e) {
			 
				logger.info(e);
				logger.debug(e);
			}
		 }
		
	}

	/**
	 * 增加分数
	 * @param request
	 * @param response
	 */
	private void addScore(HttpServletRequest request,
			HttpServletResponse response) {
		String score_typeStr = request.getParameter("i_type_id");
		int score_type=Integer.parseInt(score_typeStr);
		 String  timeStr = request.getParameter("i_time");
	     
		 String[] times = timeStr.split("/");
		 String time = times[2]+"-"+times[0]+"-"+times[1] ;
		 String  stu_idStr = request.getParameter("i_stu_id");
		 int stu_id = Integer.parseInt(stu_idStr);
		 String score_contents = request.getParameter("i_score_contents");
		 String score_valueStr = request.getParameter("i_score_value");
		 double score_value = Double.parseDouble(score_valueStr);
		Score score  = new Score();
		score.setScore_type(score_type);
		score.setTime(time);
		score.setStu_id(stu_id);
		score.setScore_contents(score_contents);
		score.setScore_values(score_value);
	 
		ScoreManager scoreManager = new  ScoreManagerImpl();
		boolean isYes = scoreManager.insert(score);
		if(isYes){
			try {
				response.getWriter().println("<script type='text/javascript'>alert('添加成功');window.location.href='scoremanager.jsp';</script>");
			} catch (IOException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
	}
}