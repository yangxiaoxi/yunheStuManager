package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Permission;
import cn.yunhe.entity.Role;

public interface RoleDao {
	
	/**
	 * ��ѯ���еĽ�ɫ
	 * @return
	 */
	List<Role> queryAllRole();
	

	
	/**
	 * ��ӽ�ɫȨ��
	 * @param listPemission
	 * @return
	 */
	boolean addRolePermission(String role_name,List<Integer> listPemission);
	
	/**
	 * ɾ����ɫ
	 * @param id
	 * @return
	 */
	boolean deletRole(int id);
	
	/**
	 * �޸Ľ�ɫ��
	 * @param permission_name
	 * @return
	 */
	boolean updateRole(String role_name,int role_id);
	
	/**
	 * �޸Ľ�ɫȨ��
	 * @param role_id
	 * @param listPermission
	 * @return
	 */
	boolean updateRolePemrission(int role_id,List<Integer> listPermission);


}
