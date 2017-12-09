package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Clazz;
import cn.yunhe.entity.Student;

public interface ClazzDao {
	
	/**
	 * 查询班级所有班级
	 * @return
	 */
	List<Clazz> query();
	
	/**
	 * 根据班级id查找班级
	 * @param class_id
	 * @return
	 */
	Clazz queryClassById(int class_id);
	
	/**
	 * 修改班级
	 * @param clazz
	 * @param clazz_id
	 * @return
	 */
	boolean  update(Clazz clazz,int clazz_id);
	
	/**
	 * 添加班级
	 * @param clazz
	 * @return
	 */
	boolean insert(Clazz clazz);
	
	/**
	 * 删除班级
	 * @param class_id
	 * @return
	 */
	boolean delete(int class_id);
	
	/**
	 * 查询班级数量
	 * @return
	 */
	int queryCount();

}
