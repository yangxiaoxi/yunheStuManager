package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Student;

public interface StudentDao {


	/**
	 * ����ѧ��id�ҳ�ѧ��
	 * @param stu_id  ѧ��id
	 * @return
	 */
	Student querystuBystuid(int stu_id);
	/**
	 * ����ѧ��id�޸�ѧ��
	 * @param stu
	 * @param stu_id
	 * @return
	 */
	boolean update(Student stu,int stu_id);
	
	
	/**
	 * ����ѧ��
	 * @param stu
	 * @return
	 */
	boolean insert(Student stu);
	
	/**
	 * ����ѧ��idɾ��ѧ��
	 * @param stu_id
	 * @return
	 */
	boolean delete(int stu_id);
	
	
	/**
	 * ��������ҳ��ѯ  
	 * @param start  ��¼��ʼλ��
	 * @param count  ÿҳ��¼������
	 * @return
	 */
	List<Student> queryStuPageMySql(int class_id,String name,String stu_profession,String stu_sex,int currentPage,int PageSize);
	
	
	/**
	 * ��ѯ��¼������
	 * @return
	 */
	int queryCount(int class_id,String name,String stu_profession,String stu_sex);
	
	
	
	/**
	 * �������е�ѧ��
	 * @return
	 */
	List<Student> queryAllstu();
	
}
