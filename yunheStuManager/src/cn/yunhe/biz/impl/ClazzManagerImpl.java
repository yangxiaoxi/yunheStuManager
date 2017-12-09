package cn.yunhe.biz.impl;

import java.util.List;

import cn.yunhe.biz.ClazzManager;
import cn.yunhe.dao.ClazzDao;
import cn.yunhe.dao.impl.ClazzDaoImpl;
import cn.yunhe.entity.Clazz;

public class ClazzManagerImpl  implements ClazzManager{

	private ClazzDao clazzDao = new ClazzDaoImpl();
	@Override
	public List<Clazz> query() {
	
		return clazzDao.query();
	}
	@Override
	public Clazz queryClassById(int class_id) {
		 
		return clazzDao.queryClassById(class_id);
	}
	@Override
	public boolean update(Clazz clazz, int clazz_id) {
	 
		return clazzDao.update(clazz, clazz_id);
	}
	@Override
	public boolean insert(Clazz clazz) {
		 
		return clazzDao.insert(clazz);
	}
	@Override
	public boolean delete(int class_id) {
		// TODO Auto-generated method stub
		return clazzDao.delete(class_id);
	}
	@Override
	public int queryCount() {
		// TODO Auto-generated method stub
		return clazzDao.queryCount();
	}

}
