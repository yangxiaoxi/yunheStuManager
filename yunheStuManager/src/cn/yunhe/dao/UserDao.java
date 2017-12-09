package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Permission;
import cn.yunhe.entity.User;

public interface UserDao {
	
	/**
	 * 根据第三方登录的open id 和accessToken查找是否存在该用户
	 * @param openid 
	 * @param accessToken  
	 * @return
	 */
	User checkLoginThree(String openid);
	
	/**
	 * 根据账号密码查找用户信息
	 * @param user_name  用户名
	 * @param user_pwd   密码
	 * @return
	 */
	User checkLogin(String user_name,String user_pwd);
	
	/**
	 * 根据用户id查找用户信息
	 * @param user_id  用户id
	 * @return
	 */
	User  selectById(int user_id);
	
	/**
	 * 查询所有的用户
	 * @return
	 */
	List<User> queryAllUser();
	
	
	/**
	 * 添加用户
	 * @param p
	 * @return
	 */
	boolean addUser(User u);
	/**
	 * 添加第三方用户
	 * @param p
	 * @return
	 */
	boolean addUserThree(User u);
	
	
	
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	boolean deletUser(int id);
	
	/**
	 * 修改用户
	 * @param permission_name
	 * @return
	 */
	boolean updateUser(User u);


}
