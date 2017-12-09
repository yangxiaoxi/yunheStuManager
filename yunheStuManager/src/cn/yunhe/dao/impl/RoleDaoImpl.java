package cn.yunhe.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.yunhe.dao.RoleDao;
import cn.yunhe.entity.Permission;
import cn.yunhe.entity.Role;
import cn.yunhe.util.JDBCutil;

public class RoleDaoImpl  implements RoleDao{
	private  static Logger logger =  Logger.getLogger(RoleDaoImpl.class);

	@Override
	public List<Role> queryAllRole() {
		String sql  ="select * from tb_role";
		   List<Role> lists = new ArrayList<Role>();
		   try {
			  JDBCutil.connection();
			  ResultSet rs =  JDBCutil.query(sql, null);
			  while(rs.next()){
              Role role = new Role(rs.getInt("role_id"), rs.getString("role_name"));
			  lists.add(role);
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
	public boolean addRolePermission(String role_name,List<Integer> listPemission) {
		 boolean  flag = false;
	try{

		JDBCutil.connection();
		String sql1 = "insert into tb_role(role_name) values(?)";
		Object[] objs = {role_name};
		JDBCutil.update(sql1, objs);
		String sql = "select last_insert_id()";
	    ResultSet rs = 	JDBCutil.query(sql, null);
	  int id = 0;
	  if(rs.next()){
		  id = rs.getInt(1);
	  }
		System.out.println("last_insert_id() is "+id);
		 StringBuffer insertSql =  new StringBuffer("insert into tb_role_permission (role_id,permission_id) values");
		
		 List<Integer> list_permission = new ArrayList<Integer>();
		
		 for(int i=0;i<listPemission.size();i++){
			 insertSql.append("(?,?) ");
			 if(i!=listPemission.size()-1){
				 insertSql.append(",");
			 }
			 list_permission.add(id);
			 list_permission.add(listPemission.get(i));
		 }
		 JDBCutil.update(insertSql.toString(), list_permission.toArray());
		 flag = true;
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
			 flag =false;
		} catch (ClassNotFoundException e) {
			logger.info(e);
			logger.debug(e);
		}finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		 return flag;
	}
	
	
	
	@Override
	public boolean deletRole(int id) {
		String sql = "delete from tb_role where role_id = ? ";
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
	public boolean updateRole(String role_name, int role_id) {
		String sql = "update tb_role set role_name=? where role_id = ?";
		Object[] objs = { role_name,role_id };
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
	public boolean updateRolePemrission(int role_id,List<Integer> listPermission) {
		boolean flag = false;
		 //1.删除角色权限
		  String deleteSql = "delete from tb_role_permission where role_id=?";
			try {
			   JDBCutil.connection();
			   JDBCutil.update(deleteSql, new Object[]{role_id});
			 flag =true;
			//2.添加角色权限
			 StringBuffer insertSql =  new StringBuffer("insert into tb_role_permission (role_id,permission_id) values");
			 List<Integer> list_permission = new ArrayList<Integer>();
			 for(int i=0;i<listPermission.size();i++){
				 insertSql.append("(?,?) ");
				 if(i!=listPermission.size()-1){
					 insertSql.append(",");
				 }
				 list_permission.add(role_id);
				 list_permission.add(listPermission.get(i));
			 }
			 JDBCutil.update(insertSql.toString(), list_permission.toArray());
			 flag = true;
			} catch (ClassNotFoundException e) {
				 flag =false;
				 logger.info(e);
					logger.debug(e);
			} catch (SQLException e) {
				logger.info(e);
				logger.debug(e);
				 flag =false;
			}finally {
				try {
					JDBCutil.closeSource();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		
		return flag;
	}

	


}
