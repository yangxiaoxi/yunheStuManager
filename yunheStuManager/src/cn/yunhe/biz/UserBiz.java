package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.User;

public interface UserBiz {
	/**
	 * �����˺���������û���Ϣ
	 * @param user_name  �û���
	 * @param user_pwd   ����
	 * @return
	 */
	User checkLogin(String user_name,String user_pwd);
	
	
	

	/**
	 * ���ݵ�������¼��open id ��accessToken�����Ƿ���ڸ��û�
	 * @param openid 
	 * @param accessToken  
	 * @return
	 */
	User checkLoginThree(String openid);
	
	/**
	 * �����û�id�����û���Ϣ
	 * @param user_id  �û�id
	 * @return
	 */
	User  selectById(int user_id);
	
	/**
	 * ��ѯ���е��û�
	 * @return
	 */
	List<User> queryAllUser();
	
	/**
	 * ���Ȩ��
	 * @param p
	 * @return
	 */
	boolean addUser(User u);
	
	/**
	 * ��ӵ������û�
	 * @param p
	 * @return
	 */
	boolean addUserThree(User u);
	
	/**
	 * ɾ��Ȩ��
	 * @param id
	 * @return
	 */
	boolean deletUser(int id);
	
	/**
	 * �޸�Ȩ��
	 * @param permission_name
	 * @return
	 */
	boolean updateUser(User u);


}
