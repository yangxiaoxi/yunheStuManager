package cn.yunhe.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import cn.yunhe.biz.StudentManager;
import cn.yunhe.biz.impl.StudentManagerImpl;
import cn.yunhe.entity.Student;
import cn.yunhe.util.JDBCutil;

//@WebServlet("/studentServlet")
public class studentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  static Logger logger =  Logger.getLogger(studentServlet.class);

	public studentServlet() {
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
		System.out.println("进入");
		String method = request.getParameter("method");

		if ("add".equals(method)) {
			addStudent(request, response);
		} else if ("update".equals(method)) {
			updateStudent(request, response);
		} else if ("delete".equals(method)) {
			deleteStudent(request, response);
		} else if ("search".equals(method)) {
			searchStudent(request, response);
		} else {
			response.getWriter()
					.println(
							"<script type='text/javascript'>alert('服务器开小差了');window.location.href='stuinfomanager.jsp';</script>");
		}
	}

	private void searchStudent(HttpServletRequest request,
			HttpServletResponse response) {
		String stu_nameStr = request.getParameter("stu_name");
		String stu_professionStr = request.getParameter("stu_profession");
		String stu_sexStr = request.getParameter("stu_sex");

		String stu_name = stu_nameStr == null ? "" : stu_nameStr;
		String stu_profession = stu_professionStr == null ? ""
				: stu_professionStr;
		String stu_sex = stu_sexStr == null ? "" : stu_sexStr;

		int currentPage = 1;
		StudentManager stuManager = new StudentManagerImpl();
		try {
			response.getWriter()
					.println(
							"<script type='text/javascript'>"
									+ "window.location.href='stuinfomanager.jsp?currentPage="
									+ currentPage + "&stu_name=" + stu_name
									+ "&stu_sex=" + stu_sex
									+ "&stu_profession=" + stu_profession
									+ "';" + "</script>");
		} catch (IOException e) {
			logger.info(e);
			logger.debug(e);
		}
	}

	/**
	 * 添加学生控制
	 * 
	 * @param request
	 * @param response
	 */
	private void addStudent(HttpServletRequest request,
			HttpServletResponse response) {

		if (ServletFileUpload.isMultipartContent(request)) {// 判断是不是以文件的形式提交表单
			String path = request.getServletContext().getRealPath("/stuimgs");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);// 通过工厂模式创建ServletFileUpload
			List<String> paramterPutong = new ArrayList<String>();// 用来存放普通表单字段的value值;
			List<String> fileParamter = new ArrayList<String>();// 存放文件名
			try {
				List<FileItem> listFiles = fileUpload.parseRequest(request);// 取出所有的的文件元素
				Iterator<FileItem> it = listFiles.iterator();

				while (it.hasNext()) {
					FileItem fileItem = (FileItem) it.next();
					if (fileItem.isFormField()) {// 如果是普通表单字段
						// String fileName =
						// fileItem.getFieldName();//获取普通表单字段的name属性的值
						String fileValue = fileItem.getString("utf-8");// 获取普通表单字段的值
						paramterPutong.add(fileValue);

					} else {// 如果是文件表单字段
						String formName = fileItem.getName();
						String stuimgsName = null;
						if(formName.equals("")){
							stuimgsName="-1.jpg";
							fileParamter.add(stuimgsName);
						}else{
						if (formName.endsWith(".jpg")
								|| formName.endsWith(".jpeg")
								|| formName.endsWith(".png")
								|| formName.endsWith(".bmp")) {
							if (fileItem.getSize() <= 800 * 800) {
								stuimgsName = System.currentTimeMillis()
										+ formName;// 以时间戳加文件名为图片的保存名称
								fileParamter.add(stuimgsName);
								fileItem.write(new File(path, stuimgsName));
							} else {
								response.getWriter()
										.println(
												"<script type='text/javascript'>alert('图片太大了,请保持在800*800像素之内');</script>");
							}
						}else{
							response.getWriter()
									.println(
											"<script type='text/javascript'>alert('上传的文件格式不正确，请上传 jpg,jpeg,png,bmp格式的文件');</script>");
						}
					}
					}
				}

				Student stu = new Student();
				stu.setStu_imag((fileParamter.get(0)));
				stu.setClass_id(Integer.parseInt(paramterPutong.get(0)));
				stu.setStu_no(paramterPutong.get(1));
				stu.setStu_name(paramterPutong.get(2));
				stu.setStu_sex(paramterPutong.get(3));
				stu.setStu_profession(paramterPutong.get(4));
				stu.setStu_icno(paramterPutong.get(5));
				stu.setStu_phone(paramterPutong.get(6));
				stu.setStu_birth(paramterPutong.get(7));
				stu.setStu_qq(paramterPutong.get(8));
				stu.setStu_edu(paramterPutong.get(9));
				stu.setStu_school(paramterPutong.get(10));
				StudentManager stuManager = new StudentManagerImpl();
				boolean isYes = stuManager.insert(stu);
				if (isYes) {
					try {
						response.getWriter()
								.println(
										"<script type='text/javascript'>alert('添加成功');window.location.href='stuinfomanager.jsp';</script>");
					} catch (IOException e) {
						logger.info(e);
						logger.debug(e);
					}
				} else {
					response.getWriter()
							.println(
									"<script type='text/javascript'>alert('添加失败');history.go(-1);</script>");
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {// 如果不是以文件的形式提交表单
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('表单不支持上传文件');</script>");
			} catch (IOException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
	}

	/**
	 * 修改学生控制
	 * 
	 * @param request
	 * @param response
	 */
	private void updateStudent(HttpServletRequest request,
			HttpServletResponse response) {
		int stu_id = 0;
		if (ServletFileUpload.isMultipartContent(request)) {// 判断是不是以文件的形式提交表单
			String path = request.getServletContext().getRealPath("/stuimgs");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);// 通过工厂模式创建ServletFileUpload
			List<String> paramterPutong = new ArrayList<String>();// 用来存放普通表单字段的value值;
			List<String> fileParamter = new ArrayList<String>();// 存放文件名
			
			try {
				List<FileItem> listFiles = fileUpload.parseRequest(request);// 取出所有的的文件元素
				Iterator<FileItem> it = listFiles.iterator();

				while (it.hasNext()) {
					FileItem fileItem = (FileItem) it.next();
					if (fileItem.isFormField()) {// 如果是普通表单字段
						// String fileName =
						// fileItem.getFieldName();//获取普通表单字段的name属性的值
						String fileValue = fileItem.getString("utf-8");// 获取普通表单字段的值
						if(fileItem.getFieldName().equals("stu_id")){//找到学生id
							stu_id = Integer.parseInt(fileValue);
						}
						paramterPutong.add(fileValue);

					} else {// 如果是文件表单字段
						String formName = fileItem.getName();
						String stuimgsName = null;
						if(formName.equals("")){
							stuimgsName="-1.jpg";
							fileParamter.add(stuimgsName);
						}else{
						if (formName.endsWith(".jpg")|| formName.endsWith(".jpeg")|| formName.endsWith(".png")|| formName.endsWith(".bmp")) {
							if (fileItem.getSize() <= 800 * 800) {
								stuimgsName = System.currentTimeMillis()
										+ formName;// 以时间戳加文件名为图片的保存名称    
								fileParamter.add(stuimgsName);
								fileItem.write(new File(path,stuimgsName));
							} else {
								response.getWriter()
										.println(
												"<script type='text/javascript'>alert('图片太大了,请保持在800*800像素之内');</script>");
							}
						}else{
							response.getWriter()
									.println(
											"<script type='text/javascript'>alert('上传的文件格式不正确，请上传 jpg,jpeg,png,bmp格式的文件');</script>");
						}
					}
					}
				}

				Student stu = new Student();
				stu.setStu_imag((fileParamter.get(0)));
				stu.setStu_name(paramterPutong.get(1));
				stu.setStu_sex(paramterPutong.get(2));
				stu.setStu_profession(paramterPutong.get(3));
				stu.setStu_icno(paramterPutong.get(4));
				stu.setStu_phone(paramterPutong.get(5));
				stu.setStu_birth(paramterPutong.get(6));
				stu.setStu_qq(paramterPutong.get(7));
				stu.setStu_edu(paramterPutong.get(8));
				stu.setStu_school(paramterPutong.get(9));
				StudentManager stuManager = new StudentManagerImpl();
				boolean isYes = stuManager.update(stu, stu_id);
				if (isYes) {
					try {
						response.getWriter()
								.println(
										"<script type='text/javascript'>alert('修改成功');window.location.href='stuinfomanager.jsp';</script>");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					response.getWriter()
							.println(
									"<script type='text/javascript'>alert('修改失败');history.go(-1);</script>");
				}
			} catch (FileUploadException e) {
				logger.info(e);
				logger.debug(e);
			} catch (Exception e) {
				logger.info(e);
				logger.debug(e);
			}
		} else {// 如果不是以文件的形式提交表单
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('表单不支持上传文件');</script>");
			} catch (IOException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
	}

	/**
	 * 删除学生控制
	 * 
	 * @param request
	 * @param response
	 */
	private void deleteStudent(HttpServletRequest request,
			HttpServletResponse response) {
		StudentManager stuManger = new StudentManagerImpl();
		String stu_idStr = request.getParameter("stu_id");
		int stu_id = Integer.parseInt(stu_idStr);
		boolean isYes = stuManger.delete(stu_id);
		if (isYes) {
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('删除成功');window.location.href='stuinfomanager.jsp';</script>");
			} catch (IOException e) {

				logger.info(e);
				logger.debug(e);
			}
		}
	}
}
