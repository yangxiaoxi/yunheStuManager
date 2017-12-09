package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Clazz;

public interface ClazzManager {
	/**
	 * ≤È—Ø∞‡º∂
	 * @return
	 */
	List<Clazz> query();
	Clazz queryClassById(int class_id);
	boolean  update(Clazz clazz,int clazz_id);
	
	boolean insert(Clazz clazz);
	
	boolean delete(int class_id);
	int queryCount();
}

