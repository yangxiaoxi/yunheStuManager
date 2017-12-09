package cn.yunhe.biz.impl;

import java.util.List;

import cn.yunhe.biz.StudentManager;
import cn.yunhe.dao.StudentDao;
import cn.yunhe.dao.impl.StudentDaoImpl;
import cn.yunhe.entity.Student;

public class StudentManagerImpl  implements StudentManager{
	private StudentDao stuDao = new StudentDaoImpl();

	@Override
	public Student querystuBystuid(int stu_id) {
		 
		return stuDao.querystuBystuid(stu_id);
	}

	@Override
	public boolean update(Student stu, int stu_id) {
		 
		return stuDao.update(stu, stu_id);
	}

	@Override
	public boolean insert(Student stu) {
		 
		return stuDao.insert(stu);
	}

	@Override
	public boolean delete(int stu_id) {
		return stuDao.delete(stu_id);
	}
 

	@Override
	public List<Student> queryStuPageMySql(int class_id,String name, String stu_profession,
			String stu_sex, int current, int PageSize) {
		// TODO Auto-generated method stub
		return stuDao.queryStuPageMySql(class_id,name,stu_profession, stu_sex, current, PageSize);
	}

	@Override
	public int queryCount(int class_id,String name, String stu_profession, String stu_sex) {
	
		return stuDao.queryCount(class_id,name, stu_profession, stu_sex);
	}

	@Override
	public List<Student> queryAllstu() {
		// TODO Auto-generated method stub
		return stuDao.queryAllstu();
	}

}
