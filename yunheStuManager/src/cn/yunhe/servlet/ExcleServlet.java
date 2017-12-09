package cn.yunhe.servlet;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.yunhe.biz.StudentManager;
import cn.yunhe.biz.impl.StudentManagerImpl;
import cn.yunhe.entity.Student;
import cn.yunhe.util.ImportExcelUtil;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

@WebServlet("/excle")
public class ExcleServlet  extends HttpServlet{
	private StudentManager stuManger = new StudentManagerImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("excleOut".equals(method)){
			ExcleOut(req,response);
			
		}
		
		if("excleIn".equals(method)){
			excleIn(req,response);
		}
	}
	
	
	
	

	//1,����excle��
	private void  ExcleOut(HttpServletRequest req, HttpServletResponse response)throws ServletException, IOException  {
		
		 OutputStream os = response.getOutputStream();// ȡ�������    
		 response.reset();// ��������    
		 String sheetName =System.currentTimeMillis()+"ѧ����Ϣ";//����
         response.setHeader("Content-disposition", "attachment; filename="+new String(sheetName.getBytes("GB2312"),"8859_1")+".xls");// �趨����ļ�ͷ    
         response.setContentType("application/msexcel");// �����������  
         
         WritableWorkbook wwb = Workbook.createWorkbook(os);//����excle�ļ�
         WritableSheet ws = wwb.createSheet("stuinfo", 10);
         
         //    ���õ�Ԫ������ָ�ʽ  
         WritableFont wf = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLUE);  
         WritableCellFormat wcf = new WritableCellFormat(wf);  
         try {
        	   wcf.setVerticalAlignment(VerticalAlignment.CENTRE);  
			   wcf.setAlignment(Alignment.CENTRE);
			   ws.setRowView(0, 500);  
			   //    ������ݵ�����  
	            //    ����ͷ  
	            Label no = new Label(0,0,"ѧ��");  
	            Label name = new Label(1,0,"����");  
	            Label sex = new Label(2,0,"�Ա�");
	            Label profession = new Label(3,0,"רҵ");
	            Label icno = new Label(4,0,"���֤");
	            Label phone = new Label(5,0,"�绰");
	            Label birth = new Label(6,0,"����");
	            Label qq = new Label(7,0,"qq");
	            Label clazz = new Label(8,0,"�༶");
	            ws.addCell(no);  
	            ws.addCell(name);  
	            ws.addCell(sex);  
	            ws.addCell(profession);  
	            ws.addCell(icno);  
	            ws.addCell(phone);  
	            ws.addCell(birth);  
	            ws.addCell(qq);  
	            ws.addCell(clazz);  
	            //�������  
	           List<Student> stuList = (List<Student>) req.getSession().getAttribute("stuLists");//��ȡѧ����Ϣ����
	            for(int i=0;i<stuList.size();i++){
	            	Label noValue = new Label(0,i+1,stuList.get(i).getStu_no());
	            	Label nameValue = new Label(1,i+1,stuList.get(i).getStu_name());
	            	Label sexValue = new Label(2,i+1,stuList.get(i).getStu_sex());
	            	Label professionValue = new Label(3,i+1,stuList.get(i).getStu_profession());
	            	Label icnoValue = new Label(4,i+1,stuList.get(i).getStu_icno());
	            	Label phoneValue = new Label(5,i+1,stuList.get(i).getStu_phone());
	            	Label birthValue = new Label(6,i+1,stuList.get(i).getStu_birth());
	            	Label qqValue = new Label(7,i+1,stuList.get(i).getStu_qq());
		            Label clazzValue = new Label(8,i+1,stuList.get(i).getClass_name());  
		            ws.addCell(noValue); 
		            ws.addCell(nameValue); 
		            ws.addCell(sexValue); 
		            ws.addCell(professionValue); 
		            ws.addCell(icnoValue); 
		            ws.addCell(phoneValue); 
		            ws.addCell(birthValue); 
		            ws.addCell(qqValue); 
		            ws.addCell(clazzValue); 
	            }
	            req.getSession().removeAttribute("stuLists");
	            wwb.write();  
	            wwb.close();  
		} catch (WriteException e) {
			e.printStackTrace();
		}  
      
		 
		
	}
	
	//2,����excle��
	private void excleIn(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException  {
		 File file = null;  
	        response.setContentType("text/html;charset=UTF-8");  
	        try  
	        {  
	        	DiskFileItemFactory factory = new DiskFileItemFactory();
	            // �ļ��ϴ����Ĺ�����  
	            ServletFileUpload upload = new ServletFileUpload(factory);  
	            upload.setFileSizeMax(10*1024*1024);    // �����ļ���С����  
	            upload.setSizeMax(50*1024*1024);        // ���ļ���С����  
	            upload.setHeaderEncoding("UTF-8");      // �������ļ����봦��  
	              
	            if (ServletFileUpload.isMultipartContent(req))   
	            {  
	                // 3. ����������ת��Ϊlist����  
	            	System.out.println("jinru");
	                List<FileItem> list = upload.parseRequest(req); 
	                // ����  
	                for (FileItem item : list)  
	                {  
	                    // �жϣ���ͨ�ı�����  
	                    if (!item.isFormField())  
	                    {  
	                    	 // a. ��ȡ�ļ�����  
	                        String name = item.getName();  
	                        // b. �õ��ϴ�Ŀ¼  
	                        String basePath = getServletContext().getRealPath("/excel"); 
	                        // c. ����Ҫ�ϴ����ļ�����  
	                        file = new File(basePath,name);
	                        // d. �ϴ�  
	                        item.write(file); 
	                       // item.delete();  // ɾ���������ʱ��������ʱ�ļ�  
	                    }   
	                }  
	            }  
	        List<Student> list = ImportExcelUtil.importExcel(file);
	       
	        for (Student student : list) {
	        	 stuManger.insert(student);
			}
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
		response.getWriter().print("<script type='text/javascript'>alert('����ɹ�');location.href='index.jsp';</script>");
	}
}
