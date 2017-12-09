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
	
	
	
	

	//1,导出excle表
	private void  ExcleOut(HttpServletRequest req, HttpServletResponse response)throws ServletException, IOException  {
		
		 OutputStream os = response.getOutputStream();// 取得输出流    
		 response.reset();// 清空输出流    
		 String sheetName =System.currentTimeMillis()+"学生信息";//表名
         response.setHeader("Content-disposition", "attachment; filename="+new String(sheetName.getBytes("GB2312"),"8859_1")+".xls");// 设定输出文件头    
         response.setContentType("application/msexcel");// 定义输出类型  
         
         WritableWorkbook wwb = Workbook.createWorkbook(os);//创建excle文件
         WritableSheet ws = wwb.createSheet("stuinfo", 10);
         
         //    设置单元格的文字格式  
         WritableFont wf = new WritableFont(WritableFont.ARIAL,10,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLUE);  
         WritableCellFormat wcf = new WritableCellFormat(wf);  
         try {
        	   wcf.setVerticalAlignment(VerticalAlignment.CENTRE);  
			   wcf.setAlignment(Alignment.CENTRE);
			   ws.setRowView(0, 500);  
			   //    填充数据的内容  
	            //    填充表头  
	            Label no = new Label(0,0,"学号");  
	            Label name = new Label(1,0,"姓名");  
	            Label sex = new Label(2,0,"性别");
	            Label profession = new Label(3,0,"专业");
	            Label icno = new Label(4,0,"身份证");
	            Label phone = new Label(5,0,"电话");
	            Label birth = new Label(6,0,"生日");
	            Label qq = new Label(7,0,"qq");
	            Label clazz = new Label(8,0,"班级");
	            ws.addCell(no);  
	            ws.addCell(name);  
	            ws.addCell(sex);  
	            ws.addCell(profession);  
	            ws.addCell(icno);  
	            ws.addCell(phone);  
	            ws.addCell(birth);  
	            ws.addCell(qq);  
	            ws.addCell(clazz);  
	            //填充数据  
	           List<Student> stuList = (List<Student>) req.getSession().getAttribute("stuLists");//获取学生信息集合
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
	
	//2,导入excle表
	private void excleIn(HttpServletRequest req, HttpServletResponse response)
			throws ServletException, IOException  {
		 File file = null;  
	        response.setContentType("text/html;charset=UTF-8");  
	        try  
	        {  
	        	DiskFileItemFactory factory = new DiskFileItemFactory();
	            // 文件上传核心工具类  
	            ServletFileUpload upload = new ServletFileUpload(factory);  
	            upload.setFileSizeMax(10*1024*1024);    // 单个文件大小限制  
	            upload.setSizeMax(50*1024*1024);        // 总文件大小限制  
	            upload.setHeaderEncoding("UTF-8");      // 对中文文件编码处理  
	              
	            if (ServletFileUpload.isMultipartContent(req))   
	            {  
	                // 3. 把请求数据转换为list集合  
	            	System.out.println("jinru");
	                List<FileItem> list = upload.parseRequest(req); 
	                // 遍历  
	                for (FileItem item : list)  
	                {  
	                    // 判断：普通文本数据  
	                    if (!item.isFormField())  
	                    {  
	                    	 // a. 获取文件名称  
	                        String name = item.getName();  
	                        // b. 得到上传目录  
	                        String basePath = getServletContext().getRealPath("/excel"); 
	                        // c. 创建要上传的文件对象  
	                        file = new File(basePath,name);
	                        // d. 上传  
	                        item.write(file); 
	                       // item.delete();  // 删除组件运行时产生的临时文件  
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
		response.getWriter().print("<script type='text/javascript'>alert('导入成功');location.href='index.jsp';</script>");
	}
}
