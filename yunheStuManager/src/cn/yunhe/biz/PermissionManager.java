package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Permission;

public interface PermissionManager {
	
	/**
	 * ��ѯ���е�Ȩ��
	 * @return
	 */
	List<Permission> queryAllPermission();
	
	/**
	 * ���Ȩ��
	 * @param p
	 * @return
	 */
	boolean addPermission(Permission p);
	
	/**
	 * ɾ��Ȩ��
	 * @param id
	 * @return
	 */
	boolean deletePermission(int id);
	
	/**
	 * �޸�Ȩ��
	 * @param permission_name
	 * @return
	 */
	boolean updatePermission(String permission_name,int permission_id);
	
	/**
	 * ���ݽ�ɫid���Ҹý�ɫӵ�е�Ȩ��
	 * @param Role
	 * @return
	 */
	List<Integer> queryByRole_id(int Role);


}
