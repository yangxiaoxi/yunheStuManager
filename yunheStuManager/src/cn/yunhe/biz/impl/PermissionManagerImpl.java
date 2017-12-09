package cn.yunhe.biz.impl;

import java.util.List;

import cn.yunhe.biz.PermissionManager;
import cn.yunhe.dao.PermissionDao;
import cn.yunhe.dao.impl.PermissionDaoImpl;
import cn.yunhe.entity.Permission;

public class PermissionManagerImpl implements PermissionManager {
   private PermissionDao  permissionDao  = new PermissionDaoImpl();
	@Override
	public List<Permission> queryAllPermission() {
		 
		return permissionDao.queryAllPermission();
	}
	@Override
	public boolean addPermission(Permission p) {
		// TODO Auto-generated method stub
		return permissionDao.addPermission(p);
	}
	@Override
	public boolean deletePermission(int id) {
		// TODO Auto-generated method stub
		return permissionDao.deletePermission(id);
	}
	@Override
	public boolean updatePermission(String permission_name, int permission_id) {
		// TODO Auto-generated method stub
		return permissionDao.updatePermission(permission_name, permission_id);
	}
	@Override
	public List<Integer> queryByRole_id(int Role) {
		// TODO Auto-generated method stub
		return permissionDao.queryByRole_id(Role);
	}

}
