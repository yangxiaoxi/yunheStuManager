
package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Record;
import cn.yunhe.entity.Score;

public interface RecordDao {

	/**
	 * ��������ѯ��¼
	 * @param date  ����
	 * @param stu_id ѧ��id
	 * @param current  ��ǰλ��
	 * @param PageSize ҳ�Ĵ�С
	 * @return
	 */
	List<Record> queryRecordPageMySql(int class_id,String date,int stu_id,int status,int current,int PageSize);
	
	/**
	 * ��ѯ��¼������
	 * @return
	 */
	int queryCount(int class_id,String date,int stu_id,int status);
	
	/**
	 * ���ݴ�id���Ҵ򿨼�¼
	 * @param record_id
	 * @return
	 */
	Record queryById(int record_id);
	
	/**
	 * ɾ���򿨼�¼
	 * @param record_id
	 * @return
	 */
	boolean delete(int record_id);
	
	/**
	 * ���´򿨼�¼
	 * @param record
	 * @param record_id
	 * @return
	 */
	boolean update(Record record,int record_id);
	
	
	/**
	 * �޸ļ�¼��״̬
	 * @param record_ids
	 * @param record_id
	 * @return
	 */
	boolean updateStatus(String record_ids,int record_id);
}
