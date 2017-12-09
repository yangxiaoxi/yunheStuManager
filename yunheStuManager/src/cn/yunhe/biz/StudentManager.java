package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Clazz;
import cn.yunhe.entity.Student;

public interface StudentManager {
	/**
	 *根据学生的唯一标示查找某个学生
	 * @param stu_id
	 * @return
	 */
	Student querystuBystuid(int stu_id);
	
	/**
	 * 根据学生唯一一标示 修改学生
     * @param stu
	 * @param stu_id
	 * @return
	 */
	boolean update(Student stu,int stu_id);
	
	
	/**
	 * 添加新同学
	 * @param stu
	 * @return
	 */
	boolean insert(Student stu);
	
	/**
	 * 根据学生唯一一标示删除学生
	 * @param stu_id
	 * @return
	 */
	boolean delete(int stu_id);
	
	 
	/**
	 * 多条件分页查询学生新消息
	 * @param name 学生姓名
	 * @param stu_profession  学生专业
	 * @param stu_sex  学生性别
	 * @param current  mysql数据库记录开始的位置
	 * @param PageSize  页的大小
	 * @return
	 */
	List<Student> queryStuPageMySql(int class_id,String name,String stu_profession,String stu_sex,int current,int PageSize);
	
	/**
	 * @param name 学生姓名
	 * @param stu_profession  学生专业
	 * @param stu_sex  学生性别
	 * 多条件组合查询学生数量
	 * @return
	 */
	int queryCount(int class_id,String name,String stu_profession,String stu_sex);
	
	
	/**
	 * 查询全部学生
	 * @return
	 */
	List<Student> queryAllstu();
	

}
