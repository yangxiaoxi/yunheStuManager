package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Role;

public interface RoleManager {
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
	boolean addRolePermission(String role,List<Integer> listPemission);
	
	/**
	 * ɾ��Ȩ��
	 * @param id
	 * @return
	 */
	boolean deletRole(int id);
	
	/**
	 * �޸�Ȩ��
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
	boolean updateRolePemrission(String role_name,int role_id,List<Integer> listPermission);

}
