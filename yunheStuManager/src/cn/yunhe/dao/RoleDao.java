package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Permission;
import cn.yunhe.entity.Role;

public interface RoleDao {
	
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
	boolean addRolePermission(String role_name,List<Integer> listPemission);
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	boolean deletRole(int id);
	
	/**
	 * 修改角色名
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
	boolean updateRolePemrission(int role_id,List<Integer> listPermission);


}
