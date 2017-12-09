package cn.yunhe.biz.impl;

import java.util.List;

import cn.yunhe.biz.UserBiz;
import cn.yunhe.dao.UserDao;
import cn.yunhe.dao.impl.UserDaoImpl;
import cn.yunhe.entity.User;

public class UserBizImpl implements UserBiz{
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User checkLogin(String user_name, String user_pwd) {
		 
		return userDao.checkLogin(user_name, user_pwd);
	}

	@Override
	public User selectById(int user_id) {
		// TODO Auto-generated method stub
		return userDao.selectById(user_id);
	}

	@Override
	public List<User> queryAllUser() {
		// TODO Auto-generated method stub
		return userDao.queryAllUser();
	}

	@Override
	public boolean addUser(User u) {
		// TODO Auto-generated method stub
		return userDao.addUser(u);
	}

	@Override
	public boolean deletUser(int id) {
		// TODO Auto-generated method stub
		return userDao.deletUser(id);
	}

	@Override
	public boolean updateUser(User u) {
		return userDao.updateUser(u);
	}

	@Override
	public User checkLoginThree(String openid) {
		// TODO Auto-generated method stub
		return userDao.checkLoginThree(openid);
	}

	@Override
	public boolean addUserThree(User u) {
		// TODO Auto-generated method stub
		return userDao.addUserThree(u);
	}

}
