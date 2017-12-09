package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Permission;

public interface PermissionManager {
	
	/**
	 * 查询所有的权限
	 * @return
	 */
	List<Permission> queryAllPermission();
	
	/**
	 * 添加权限
	 * @param p
	 * @return
	 */
	boolean addPermission(Permission p);
	
	/**
	 * 删除权限
	 * @param id
	 * @return
	 */
	boolean deletePermission(int id);
	
	/**
	 * 修改权限
	 * @param permission_name
	 * @return
	 */
	boolean updatePermission(String permission_name,int permission_id);
	
	/**
	 * 根据角色id查找该角色拥有的权限
	 * @param Role
	 * @return
	 */
	List<Integer> queryByRole_id(int Role);


}
