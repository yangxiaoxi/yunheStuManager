package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Clazz;
import cn.yunhe.entity.Student;

public interface ClazzDao {
	
	/**
	 * ��ѯ�༶���а༶
	 * @return
	 */
	List<Clazz> query();
	
	/**
	 * ���ݰ༶id���Ұ༶
	 * @param class_id
	 * @return
	 */
	Clazz queryClassById(int class_id);
	
	/**
	 * �޸İ༶
	 * @param clazz
	 * @param clazz_id
	 * @return
	 */
	boolean  update(Clazz clazz,int clazz_id);
	
	/**
	 * ��Ӱ༶
	 * @param clazz
	 * @return
	 */
	boolean insert(Clazz clazz);
	
	/**
	 * ɾ���༶
	 * @param class_id
	 * @return
	 */
	boolean delete(int class_id);
	
	/**
	 * ��ѯ�༶����
	 * @return
	 */
	int queryCount();

}
