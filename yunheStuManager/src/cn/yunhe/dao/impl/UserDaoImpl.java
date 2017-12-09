package cn.yunhe.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.yunhe.dao.UserDao;
import cn.yunhe.entity.Student;
import cn.yunhe.entity.User;
import cn.yunhe.util.JDBCutil;

public class UserDaoImpl implements UserDao {
	private  static Logger logger =  Logger.getLogger(UserDaoImpl.class);
	@Override
	public User checkLogin(String userName, String psw) {
		 
		String sql = "select t1.*,role_name from tb_user t1"
				+ " inner join tb_role t2 on t1.user_role = t2.role_id "
				+ " where user_name = ? and user_pwd= ?";
		 User user = null;
		try {
			JDBCutil.connection();
			 ResultSet rs =  JDBCutil.query(sql, new Object[]{userName,psw});
			 if(rs.next()){
				 int user_id = rs.getInt("user_id");
				 String user_name = rs.getString("user_name");
				 String user_pwd = rs.getString("user_pwd");
				 int user_type = rs.getInt("user_type");
				 int user_role = rs.getInt("user_role");
				 String user_nickname  = rs.getString("user_nickname");
				 String role_name = rs.getString("role_name");
				 int class_id =  rs.getInt("class_id");
				 user=  new User(user_id, user_name, user_pwd, user_type, user_role, role_name, user_nickname, class_id);
			 }
		} catch (ClassNotFoundException | SQLException e) {
			logger.info(e);
			logger.debug(e);
		}finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public User selectById(int userId) {
		String sql = "select t1.*,role_name from tb_user t1 inner join tb_role t2 on t1.user_role = t2.role_id where t1.user_id = ?";
		 User user = null;
		try {
			JDBCutil.connection();
			 ResultSet rs =  JDBCutil.query(sql, new Object[]{userId});
			 if(rs.next()){
				 int user_id = rs.getInt("user_id");
				 String user_name = rs.getString("user_name");
				 String user_pwd = rs.getString("user_pwd");
				 int user_type = rs.getInt("user_type");
				 int user_role = rs.getInt("user_role");
				 String user_nickname  = rs.getString("user_nickname");
				 String role_name = rs.getString("role_name");
				 int class_id =  rs.getInt("class_id");
				 user=  new User(user_id, user_name, user_pwd, user_type, user_role, role_name, user_nickname, class_id);
			 }
		} catch (ClassNotFoundException | SQLException e) {
			logger.info(e);
			logger.debug(e);
		}finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public List<User> queryAllUser() {
		String sql = "select t1.*,class_name,role_name from tb_user t1 "
				+ "left join tb_class t2 on t1.class_id = t2.class_id "
				+ "left join tb_role t3 on t1.user_role = t3.role_id";
		List<User> lists = new ArrayList<User>();
	try {
		JDBCutil.connection();
		ResultSet rs = JDBCutil.query(sql,null);	
		while (rs.next()) {
			 User user = new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("user_pwd"),
					 rs.getInt("user_type"),rs.getInt("user_role"), rs.getString("role_name"), rs.getString("user_nickname"), 
					 rs.getInt("class_id"), rs.getString("class_name"));
			lists.add(user);
		}
	} catch (ClassNotFoundException e) {
		logger.info(e);
		logger.debug(e);
	} catch (SQLException e) {
		logger.info(e);
		logger.debug(e);
	} finally {
		try {
			JDBCutil.closeSource();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return lists;
	}

	@Override
	public boolean addUser(User u) {
		String sql = "insert into tb_user"
				+ "(user_type,user_name,user_pwd,user_role,user_nickname,class_id) "
				+ "values(1,?,?,?,?,?)";
		Object[] objs = {u.getUser_name(),u.getUser_pwd(),u.getUser_role(),u.getUser_nickname(),u.getClass_id()};
		boolean isAdd = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			isAdd = true;
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
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
	public boolean deletUser(int id) {
		String sql = "delete from tb_user where user_id = ? ";
		Object[] objs = { id };
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			flag = true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public boolean updateUser(User u) {
		String sql = "update tb_user "
				+ "set user_role=?,user_nickname=?,class_id = ?";
		List<Object> listParam = new ArrayList<Object>();
		listParam.add( u.getUser_role());
		listParam.add(u.getUser_nickname());
		listParam.add(u.getClass_id());
		if(u.getUser_type()==1){
			sql+=",user_name=?,user_pwd=? ";
			listParam.add(u.getUser_name());
			listParam.add(u.getUser_pwd());
		}
		sql+="  where user_id = ?";
		listParam.add(u.getUser_id());
		System.out.println("sql is"+sql);
		
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, listParam.toArray());
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
	public User checkLoginThree(String openid) {
		String sql = "select t1.*,role_name from tb_user t1"
				+ " inner join tb_role t2 on t1.user_role = t2.role_id "
				+ " where user_openid =? and user_type=2";
		 User user = null;
		try {
			JDBCutil.connection();
			 ResultSet rs =  JDBCutil.query(sql,new Object[]{openid});
			 if(rs.next()){
				 int user_id = rs.getInt("user_id");
				 String user_name = rs.getString("user_name");
				 String user_pwd = rs.getString("user_pwd");
				 int user_type = rs.getInt("user_type");
				 int user_role = rs.getInt("user_role");
				 String user_nickname  = rs.getString("user_nickname");
				 String role_name = rs.getString("role_name");
				 int class_id =  rs.getInt("class_id");
				 user=  new User(user_id, user_name, user_pwd, user_type, user_role, role_name, user_nickname, class_id);
			 }
		} catch (ClassNotFoundException | SQLException e) {
			logger.info(e);
			logger.debug(e);
		}finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public boolean addUserThree(User u) {
		String sql = "insert into tb_user"
				+ "(user_type,user_nickname,user_openid) "
				+ "values(2,?,?)";
		Object[] objs = {u.getUser_nickname(),u.getOpen_id()};
		boolean isAdd = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			isAdd = true;
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
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

}
