package cn.yunhe.biz.impl;

import java.util.List;

import cn.yunhe.biz.RoleManager;
import cn.yunhe.dao.RoleDao;
import cn.yunhe.dao.impl.RoleDaoImpl;
import cn.yunhe.entity.Role;

public class RoleManagerImpl  implements RoleManager{
 private RoleDao roleDao = new RoleDaoImpl();
	@Override
	public List<Role> queryAllRole() {
		return roleDao.queryAllRole();
	}
	 
	@Override
	public boolean deletRole(int id) {
		return roleDao.deletRole(id);
	}
	@Override
	public boolean updateRole(String role_name, int role_id) {
		return roleDao.updateRole(role_name, role_id);
	}
	@Override
	public boolean updateRolePemrission(String role_name,int role_id,
			List<Integer> listPermission) {
		boolean flag =  roleDao.updateRole(role_name, role_id);
		flag = roleDao.updateRolePemrission(role_id, listPermission);
		return flag;
	}
	@Override
	public boolean addRolePermission(String role, List<Integer> listPemission) {
		 boolean flag = roleDao.addRolePermission(role, listPemission);
		return flag;
	}

}
