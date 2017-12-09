package cn.yunhe.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.yunhe.dao.ClazzDao;
import cn.yunhe.entity.Clazz;
import cn.yunhe.util.JDBCutil;

public class ClazzDaoImpl  implements ClazzDao{
	private  static Logger logger =  Logger.getLogger(ClazzDaoImpl.class);

	@Override
	public List<Clazz> query() {
		String sql  ="select * from tb_class";
	   List<Clazz> lists = new ArrayList<Clazz>();
	   try {
		  JDBCutil.connection();
		  ResultSet rs =  JDBCutil.query(sql, null);
		  while(rs.next()){
			 Clazz clazz = new Clazz();
			 clazz.setClazz_id(rs.getInt("class_id"));
			 clazz.setClazz_name(rs.getString("class_name"));
			 lists.add(clazz);
		  }
	} catch (ClassNotFoundException e) {
		logger.info(e);
		logger.debug(e);
	} catch (SQLException e) {
		logger.info(e);
		logger.debug(e);
	}finally{
		try {
			JDBCutil.closeSource();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.info(e);
			logger.debug(e);
		}
	}
		return lists;
	}

	@Override
	public Clazz queryClassById(int class_id) {
		String sql  ="select * from tb_class where class_id =?";
		 Clazz clazz = new Clazz();
		   try {
			  JDBCutil.connection();
			  ResultSet rs =  JDBCutil.query(sql, new Object[]{class_id});
			 
			  while(rs.next()){
				 clazz.setClazz_id(rs.getInt("class_id"));
				 clazz.setClazz_name(rs.getString("class_name"));
	
			  }
		} catch (ClassNotFoundException e) {
			logger.info(e);
			logger.debug(e);
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
		}finally{
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.info(e);
				logger.debug(e);
			}
		}
			return clazz;
	}

	@Override
	public boolean update(Clazz clazz, int clazz_id) {
		String sql = "update tb_class set class_name=? where class_id = ?";
		Object[] objs = { clazz.getClazz_name(),clazz_id };
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			flag = true;
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
		} catch (ClassNotFoundException e) {
			logger.info(e);
			logger.debug(e);
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
		return flag;
	}

	@Override
	public boolean insert(Clazz clazz) {
		String sql = "insert into tb_class(class_name) values(?)";
		Object[] objs = {clazz.getClazz_name()};
		boolean isAdd = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			isAdd = true;
		} catch (ClassNotFoundException e1) {
			logger.info(e1);
			logger.debug(e1);
		} catch (SQLException e1) {
			logger.info(e1);
			logger.debug(e1);
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
		return isAdd;
	}

	@Override
	public boolean delete(int class_id) {
		String sql = "delete from tb_class where class_id = ? ";
		Object[] objs = { class_id };
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			flag = true;
		} catch (ClassNotFoundException | SQLException e) {
			logger.info(e);
			logger.debug(e);
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
		return flag;
	}

	@Override
	public int queryCount() {
		List<Clazz> clazzs = query();
		return clazzs.size();
	}

}
