package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Record;

/**
 * @author Administrator
 *
 */
public interface RecordManager {

	/**
	 * ��������ҳ��ѯ
	 * @param date  ����
	 * @param stu_id  ѧ��Ψһ��ʾ
	 * @param current  ���ݿ��¼��ʼ��λ��
	 * @param PageSize  ҳ�Ĵ�С
	 * @return
	 */
	List<Record> queryRecordPageMySql(int class_id,String date,int stu_id,int status, int current,int PageSize);
	
	
	/**
	 * ��������ѯ��¼������
	 * @param date
	 * @param stu_id
	 * @return
	 */
	int queryCount(int class_id,String date,int stu_id,int state);
	
	/**
	 * ���ݴ򿨼�¼id��ѯ�򿨼�¼
	 * @param record_id
	 * @return
	 */
	Record queryById(int record_id);
	
	
	/**
	 * ɾ��ĳ���򿨼�¼
	 * @param record_id
	 * @return
	 */
	boolean delete(int record_id);
	
	/**
	 * �޸�ĳ���򿨼�¼
	 * @param record
	 * @param record_id
	 * @return
	 */
	boolean update(Record record,int record_id);
	
	 
	
	/**
	 * ���ݴ򿨼�¼��Ψһ��ʾ�޸Ĵ򿨼�¼��״̬
	 * @param record_ids
	 * @param type
	 * @return
	 */
	boolean updateStatus(String record_ids,int type);
}
