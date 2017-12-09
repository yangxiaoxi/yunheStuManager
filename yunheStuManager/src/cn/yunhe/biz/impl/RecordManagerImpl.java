package cn.yunhe.biz.impl;

import java.util.List;

import cn.yunhe.biz.RecordManager;
import cn.yunhe.dao.RecordDao;
import cn.yunhe.dao.impl.RecordDaoImpl;
import cn.yunhe.entity.Record;

public class RecordManagerImpl implements RecordManager{
	private RecordDao recordDao = new RecordDaoImpl();

	@Override
	public List<Record> queryRecordPageMySql(int class_id,String date, int stu_id,
			int status, int current, int PageSize) {
		// TODO Auto-generated method stub
		return recordDao.queryRecordPageMySql(class_id,date, stu_id, status, current, PageSize);
	}

	@Override
	public int queryCount(int class_id,String date, int stu_id,int state) {
		// TODO Auto-generated method stub
		return recordDao.queryCount(class_id,date, stu_id,state);
	}

	@Override
	public boolean delete(int record_id) {
		// TODO Auto-generated method stub
		return recordDao.delete(record_id);
	}

	@Override
	public Record queryById(int record_id) {
		// TODO Auto-generated method stub
		return recordDao.queryById(record_id);
	}

	@Override
	public boolean update(Record record, int record_id) {
		// TODO Auto-generated method stub
		return recordDao.update(record, record_id);
	}

	@Override
	public boolean updateStatus(String record_ids, int type) {
		// TODO Auto-generated method stub
		return recordDao.updateStatus(record_ids, type);
	}

}
