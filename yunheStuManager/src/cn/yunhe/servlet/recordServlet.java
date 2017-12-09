package cn.yunhe.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.yunhe.biz.RecordManager;
import cn.yunhe.biz.ScoreManager;
import cn.yunhe.biz.StudentManager;
import cn.yunhe.biz.impl.RecordManagerImpl;
import cn.yunhe.biz.impl.ScoreManagerImpl;
import cn.yunhe.biz.impl.StudentManagerImpl;
import cn.yunhe.entity.Record;
import cn.yunhe.util.JDBCutil;

//@WebServlet("/recordServlet")
public class recordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  static Logger logger =  Logger.getLogger(recordServlet.class);

	public recordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		if ("update".equals(method)) {
			updateRecord(request, response);
		} else if ("delete".equals(method)) {
			deleteRecord(request, response);
		} else if ("search".equals(method)) {
			searchRecord(request, response);
		} else if ("updateStatus".equals(method)) {
			updateStatus(request, response);
		} else {
			response.getWriter()
					.println(
							"<script type='text/javascript'>alert('服务器开小差了');window.location.href='scoreinfomanager.jsp';</script>");
		}
	}

	private void updateStatus(HttpServletRequest request,
			HttpServletResponse response) {

		String update_statusStr = request.getParameter("update_status");
		int update_status = Integer.parseInt(update_statusStr);
		String record_idsStr = request.getParameter("record_ids");
		RecordManager recordManager = new RecordManagerImpl();
		boolean isYes = false;
		isYes =recordManager.updateStatus(record_idsStr, update_status);
		if(isYes){
		try {
			response.getWriter()
					.println(
							"<script type='text/javascript'>alert('标记成功');window.location.href='checkingmanager.jsp';</script>");
		} catch (IOException e) {

			logger.info(e);
			logger.debug(e);
		}
	}
		
	}
	
	/**
	 * 删除打卡记录
	 * 
	 * @param request
	 * @param response
	 */
	private void deleteRecord(HttpServletRequest request,
			HttpServletResponse response) {
		RecordManager recordManager = new RecordManagerImpl();
		String record_idStr = request.getParameter("record_id");
		int record_id = Integer.parseInt(record_idStr);
		boolean isYes = recordManager.delete(record_id);
		if (isYes) {
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('删除成功');window.location.href='checkingmanager.jsp';</script>");
			} catch (IOException e) {

				logger.info(e);
				logger.debug(e);
			}
		}

	}

	/**
	 * 查找打卡记录
	 * 
	 * @param request
	 * @param response
	 */
	private void searchRecord(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入记录查询servlet");

		String dateStr = request.getParameter("date");
		String time = "";
		if (dateStr.contains("/")) {
			String[] times = dateStr.split("/");
			time = times[2] + "-" + times[0] + "-" + times[1];
		} else {
			time = dateStr;
		}
		String stu_idStr = request.getParameter("stu_id");
		int stu_id = "".equals(stu_idStr) ? -1 : Integer.parseInt(stu_idStr);
		String statusStr = request.getParameter("status");
		int status = statusStr == null ? 5 : Integer.parseInt(statusStr);

		int currentPage = 1;

		try {
			response.getWriter()
					.println(
							"<script type='text/javascript'>"
									+ "window.location.href='checkingmanager.jsp?currentPage="
									+ currentPage + "&date=" + time
									+ "&stu_id=" + stu_id + "&status=" + status
									+ "';" + "</script>");
		} catch (IOException e) {
			logger.info(e);
			logger.debug(e);
		}

	}

	/**
	 * 修改打卡记录
	 * 
	 * @param request
	 * @param response
	 */
	private void updateRecord(HttpServletRequest request,
			HttpServletResponse response) {
		String record_idStr = request.getParameter("record_id");
		int recore_id = Integer.parseInt(record_idStr);
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String statusStr = request.getParameter("status");
		int status = Integer.parseInt(statusStr);

		Record record = new Record();
		record.setStart_time(start_time);
		record.setEnd_time(end_time);
		record.setStatus(status);

		RecordManager recordManager = new RecordManagerImpl();
		boolean isYes = recordManager.update(record, recore_id);

		if (isYes) {
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('修改成功');window.location.href='checkingmanager.jsp';</script>");
			} catch (IOException e) {

				logger.info(e);
				logger.debug(e);
			}
		}

	}

}
