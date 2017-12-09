package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Record;

/**
 * @author Administrator
 *
 */
public interface RecordManager {

	/**
	 * 多条件分页查询
	 * @param date  日期
	 * @param stu_id  学生唯一标示
	 * @param current  数据库记录开始的位置
	 * @param PageSize  页的大小
	 * @return
	 */
	List<Record> queryRecordPageMySql(int class_id,String date,int stu_id,int status, int current,int PageSize);
	
	
	/**
	 * 多条件查询记录的条数
	 * @param date
	 * @param stu_id
	 * @return
	 */
	int queryCount(int class_id,String date,int stu_id,int state);
	
	/**
	 * 根据打卡记录id查询打卡记录
	 * @param record_id
	 * @return
	 */
	Record queryById(int record_id);
	
	
	/**
	 * 删除某条打卡记录
	 * @param record_id
	 * @return
	 */
	boolean delete(int record_id);
	
	/**
	 * 修改某条打卡记录
	 * @param record
	 * @param record_id
	 * @return
	 */
	boolean update(Record record,int record_id);
	
	 
	
	/**
	 * 根据打卡记录的唯一标示修改打卡记录的状态
	 * @param record_ids
	 * @param type
	 * @return
	 */
	boolean updateStatus(String record_ids,int type);
}
