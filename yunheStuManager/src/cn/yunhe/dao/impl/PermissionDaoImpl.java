package cn.yunhe.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.yunhe.dao.PermissionDao;
import cn.yunhe.entity.Clazz;
import cn.yunhe.entity.Permission;
import cn.yunhe.util.JDBCutil;

public class PermissionDaoImpl  implements PermissionDao{
	private  static Logger logger =  Logger.getLogger(JDBCutil.class);
	@Override
	public List<Permission> queryAllPermission() {
		String sql  ="select * from tb_permission";
		   List<Permission> lists = new ArrayList<Permission>();
		   try {
			  JDBCutil.connection();
			  ResultSet rs =  JDBCutil.query(sql, null);
			  while(rs.next()){
                 Permission permission = new Permission(rs.getInt("permission_id"), rs.getString("permission_des"));
				 lists.add(permission);
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
				logger.info(e);
				logger.debug(e);
			}
		}
			return lists;
	}

	@Override
	public boolean addPermission(Permission p) {
		String sql = "insert into tb_permission(permission_des) values(?)";
		Object[] objs = {p.getPermission_name()};
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
	public boolean deletePermission(int id) {
		String sql = "delete from tb_permission where permission_id = ? ";
		Object[] objs = { id };
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
	public boolean updatePermission(String permission_name, int permission_id) {
		String sql = "update tb_permission set permission_des=? where permission_id = ?";
		Object[] objs = { permission_name,permission_id };
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
	public List<Integer> queryByRole_id(int role_id) {
		String sql  ="select permission_id from tb_role_permission where role_id = ?";
		   List<Integer> lists = new ArrayList<Integer>();
		   try {
			  JDBCutil.connection();
			  ResultSet rs =  JDBCutil.query(sql, new Object[]{role_id});
			  while(rs.next()){
				 lists.add(rs.getInt(1));
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
				logger.info(e);
				logger.debug(e);
			}
		}
			return lists;
	}

}
