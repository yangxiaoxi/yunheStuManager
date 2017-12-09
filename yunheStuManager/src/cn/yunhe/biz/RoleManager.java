package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Role;

public interface RoleManager {
	/**
	 * 查询所有的角色
	 * @return
	 */
	List<Role> queryAllRole();
	
	
	/**
	 * 添加角色权限
	 * @param listPemission
	 * @return
	 */
	boolean addRolePermission(String role,List<Integer> listPemission);
	
	/**
	 * 删除权限
	 * @param id
	 * @return
	 */
	boolean deletRole(int id);
	
	/**
	 * 修改权限
	 * @param permission_name
	 * @return
	 */
	boolean updateRole(String role_name,int role_id);
	/**
	 * 修改角色权限
	 * @param role_id
	 * @param listPermission
	 * @return
	 */
	boolean updateRolePemrission(String role_name,int role_id,List<Integer> listPermission);

}
