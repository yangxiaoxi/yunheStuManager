package cn.yunhe.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import cn.yunhe.entity.Student;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;

public class ImportExcelUtil {
    private static List<Student> list = new LinkedList<Student>();  
    public static List<Student> importExcel(File file)  
    {  
        try  
        {  
            list.clear();  
           FileInputStream fs = new FileInputStream(file);  
            Workbook wb = Workbook.getWorkbook(fs);  
            Sheet sheet = wb.getSheet(0);  
            //获取表的行数  
            int row = sheet.getRows();  
            //循环获取值  
            for(int i = 0;i<row;i++)  
            {  
            	Student stu = new Student();
                String no = sheet.getCell(0,i).getContents(); 
                String name =sheet.getCell(1,i).getContents();;  
                String sex = sheet.getCell(2,i).getContents();
                String profession = sheet.getCell(3,i).getContents();
                String icno =sheet.getCell(4,i).getContents();
                String phone = sheet.getCell(5,i).getContents();
                String birth = sheet.getCell(6,i).getContents();
                String qq =sheet.getCell(7,i).getContents();
                String clazz = sheet.getCell(8,i).getContents();
                stu.setStu_no(no);
                stu.setStu_name(name);
                stu.setStu_sex(sex);
                stu.setStu_profession(profession);
                stu.setStu_icno(icno);
                stu.setStu_phone(phone);
                stu.setStu_birth(birth);
                stu.setStu_qq(qq);
                stu.setClass_name(clazz);
                list.add(stu);  
            }  
            //关闭流，否则后面文件删除会失败  
            fs.close();  
            file.delete();  
        } catch (BiffException e)  
        {  
            e.printStackTrace();  
        } catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
        return list;  
    }  

}
