package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Student;

public interface StudentDao {


	/**
	 * 根据学生id找出学生
	 * @param stu_id  学生id
	 * @return
	 */
	Student querystuBystuid(int stu_id);
	/**
	 * 根据学生id修改学生
	 * @param stu
	 * @param stu_id
	 * @return
	 */
	boolean update(Student stu,int stu_id);
	
	
	/**
	 * 增加学生
	 * @param stu
	 * @return
	 */
	boolean insert(Student stu);
	
	/**
	 * 根据学生id删除学生
	 * @param stu_id
	 * @return
	 */
	boolean delete(int stu_id);
	
	
	/**
	 * 多条件分页查询  
	 * @param start  记录开始位置
	 * @param count  每页记录的数量
	 * @return
	 */
	List<Student> queryStuPageMySql(int class_id,String name,String stu_profession,String stu_sex,int currentPage,int PageSize);
	
	
	/**
	 * 查询记录的条数
	 * @return
	 */
	int queryCount(int class_id,String name,String stu_profession,String stu_sex);
	
	
	
	/**
	 * 查找所有的学生
	 * @return
	 */
	List<Student> queryAllstu();
	
}
