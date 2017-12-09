package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Clazz;
import cn.yunhe.entity.Student;

public interface StudentManager {
	/**
	 *����ѧ����Ψһ��ʾ����ĳ��ѧ��
	 * @param stu_id
	 * @return
	 */
	Student querystuBystuid(int stu_id);
	
	/**
	 * ����ѧ��Ψһһ��ʾ �޸�ѧ��
     * @param stu
	 * @param stu_id
	 * @return
	 */
	boolean update(Student stu,int stu_id);
	
	
	/**
	 * �����ͬѧ
	 * @param stu
	 * @return
	 */
	boolean insert(Student stu);
	
	/**
	 * ����ѧ��Ψһһ��ʾɾ��ѧ��
	 * @param stu_id
	 * @return
	 */
	boolean delete(int stu_id);
	
	 
	/**
	 * ��������ҳ��ѯѧ������Ϣ
	 * @param name ѧ������
	 * @param stu_profession  ѧ��רҵ
	 * @param stu_sex  ѧ���Ա�
	 * @param current  mysql���ݿ��¼��ʼ��λ��
	 * @param PageSize  ҳ�Ĵ�С
	 * @return
	 */
	List<Student> queryStuPageMySql(int class_id,String name,String stu_profession,String stu_sex,int current,int PageSize);
	
	/**
	 * @param name ѧ������
	 * @param stu_profession  ѧ��רҵ
	 * @param stu_sex  ѧ���Ա�
	 * ��������ϲ�ѯѧ������
	 * @return
	 */
	int queryCount(int class_id,String name,String stu_profession,String stu_sex);
	
	
	/**
	 * ��ѯȫ��ѧ��
	 * @return
	 */
	List<Student> queryAllstu();
	

}
