
package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Record;
import cn.yunhe.entity.Score;

public interface RecordDao {

	/**
	 * 多条件查询记录
	 * @param date  日期
	 * @param stu_id 学生id
	 * @param current  当前位置
	 * @param PageSize 页的大小
	 * @return
	 */
	List<Record> queryRecordPageMySql(int class_id,String date,int stu_id,int status,int current,int PageSize);
	
	/**
	 * 查询记录的条数
	 * @return
	 */
	int queryCount(int class_id,String date,int stu_id,int status);
	
	/**
	 * 根据打卡id查找打卡记录
	 * @param record_id
	 * @return
	 */
	Record queryById(int record_id);
	
	/**
	 * 删除打卡记录
	 * @param record_id
	 * @return
	 */
	boolean delete(int record_id);
	
	/**
	 * 更新打卡记录
	 * @param record
	 * @param record_id
	 * @return
	 */
	boolean update(Record record,int record_id);
	
	
	/**
	 * 修改记录的状态
	 * @param record_ids
	 * @param record_id
	 * @return
	 */
	boolean updateStatus(String record_ids,int record_id);
}
