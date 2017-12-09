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
		System.out.println("����");
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
							"<script type='text/javascript'>alert('��������С����');window.location.href='stuinfomanager.jsp';</script>");
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
	 * ���ѧ������
	 * 
	 * @param request
	 * @param response
	 */
	private void addStudent(HttpServletRequest request,
			HttpServletResponse response) {

		if (ServletFileUpload.isMultipartContent(request)) {// �ж��ǲ������ļ�����ʽ�ύ��
			String path = request.getServletContext().getRealPath("/stuimgs");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);// ͨ������ģʽ����ServletFileUpload
			List<String> paramterPutong = new ArrayList<String>();// ���������ͨ���ֶε�valueֵ;
			List<String> fileParamter = new ArrayList<String>();// ����ļ���
			try {
				List<FileItem> listFiles = fileUpload.parseRequest(request);// ȡ�����еĵ��ļ�Ԫ��
				Iterator<FileItem> it = listFiles.iterator();

				while (it.hasNext()) {
					FileItem fileItem = (FileItem) it.next();
					if (fileItem.isFormField()) {// �������ͨ���ֶ�
						// String fileName =
						// fileItem.getFieldName();//��ȡ��ͨ���ֶε�name���Ե�ֵ
						String fileValue = fileItem.getString("utf-8");// ��ȡ��ͨ���ֶε�ֵ
						paramterPutong.add(fileValue);

					} else {// ������ļ����ֶ�
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
										+ formName;// ��ʱ������ļ���ΪͼƬ�ı�������
								fileParamter.add(stuimgsName);
								fileItem.write(new File(path, stuimgsName));
							} else {
								response.getWriter()
										.println(
												"<script type='text/javascript'>alert('ͼƬ̫����,�뱣����800*800����֮��');</script>");
							}
						}else{
							response.getWriter()
									.println(
											"<script type='text/javascript'>alert('�ϴ����ļ���ʽ����ȷ�����ϴ� jpg,jpeg,png,bmp��ʽ���ļ�');</script>");
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
										"<script type='text/javascript'>alert('��ӳɹ�');window.location.href='stuinfomanager.jsp';</script>");
					} catch (IOException e) {
						logger.info(e);
						logger.debug(e);
					}
				} else {
					response.getWriter()
							.println(
									"<script type='text/javascript'>alert('���ʧ��');history.go(-1);</script>");
				}

			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {// ����������ļ�����ʽ�ύ��
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('����֧���ϴ��ļ�');</script>");
			} catch (IOException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
	}

	/**
	 * �޸�ѧ������
	 * 
	 * @param request
	 * @param response
	 */
	private void updateStudent(HttpServletRequest request,
			HttpServletResponse response) {
		int stu_id = 0;
		if (ServletFileUpload.isMultipartContent(request)) {// �ж��ǲ������ļ�����ʽ�ύ��
			String path = request.getServletContext().getRealPath("/stuimgs");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload fileUpload = new ServletFileUpload(factory);// ͨ������ģʽ����ServletFileUpload
			List<String> paramterPutong = new ArrayList<String>();// ���������ͨ���ֶε�valueֵ;
			List<String> fileParamter = new ArrayList<String>();// ����ļ���
			
			try {
				List<FileItem> listFiles = fileUpload.parseRequest(request);// ȡ�����еĵ��ļ�Ԫ��
				Iterator<FileItem> it = listFiles.iterator();

				while (it.hasNext()) {
					FileItem fileItem = (FileItem) it.next();
					if (fileItem.isFormField()) {// �������ͨ���ֶ�
						// String fileName =
						// fileItem.getFieldName();//��ȡ��ͨ���ֶε�name���Ե�ֵ
						String fileValue = fileItem.getString("utf-8");// ��ȡ��ͨ���ֶε�ֵ
						if(fileItem.getFieldName().equals("stu_id")){//�ҵ�ѧ��id
							stu_id = Integer.parseInt(fileValue);
						}
						paramterPutong.add(fileValue);

					} else {// ������ļ����ֶ�
						String formName = fileItem.getName();
						String stuimgsName = null;
						if(formName.equals("")){
							stuimgsName="-1.jpg";
							fileParamter.add(stuimgsName);
						}else{
						if (formName.endsWith(".jpg")|| formName.endsWith(".jpeg")|| formName.endsWith(".png")|| formName.endsWith(".bmp")) {
							if (fileItem.getSize() <= 800 * 800) {
								stuimgsName = System.currentTimeMillis()
										+ formName;// ��ʱ������ļ���ΪͼƬ�ı�������    
								fileParamter.add(stuimgsName);
								fileItem.write(new File(path,stuimgsName));
							} else {
								response.getWriter()
										.println(
												"<script type='text/javascript'>alert('ͼƬ̫����,�뱣����800*800����֮��');</script>");
							}
						}else{
							response.getWriter()
									.println(
											"<script type='text/javascript'>alert('�ϴ����ļ���ʽ����ȷ�����ϴ� jpg,jpeg,png,bmp��ʽ���ļ�');</script>");
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
										"<script type='text/javascript'>alert('�޸ĳɹ�');window.location.href='stuinfomanager.jsp';</script>");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					response.getWriter()
							.println(
									"<script type='text/javascript'>alert('�޸�ʧ��');history.go(-1);</script>");
				}
			} catch (FileUploadException e) {
				logger.info(e);
				logger.debug(e);
			} catch (Exception e) {
				logger.info(e);
				logger.debug(e);
			}
		} else {// ����������ļ�����ʽ�ύ��
			try {
				response.getWriter()
						.println(
								"<script type='text/javascript'>alert('����֧���ϴ��ļ�');</script>");
			} catch (IOException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
	}

	/**
	 * ɾ��ѧ������
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
								"<script type='text/javascript'>alert('ɾ���ɹ�');window.location.href='stuinfomanager.jsp';</script>");
			} catch (IOException e) {

				logger.info(e);
				logger.debug(e);
			}
		}
	}
}
