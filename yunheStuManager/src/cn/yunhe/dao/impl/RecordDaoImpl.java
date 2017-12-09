package cn.yunhe.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.yunhe.dao.RecordDao;
import cn.yunhe.entity.Record;
import cn.yunhe.entity.Score;
import cn.yunhe.util.JDBCutil;

public class RecordDaoImpl  implements RecordDao{
	private  static Logger logger =  Logger.getLogger(RecordDaoImpl.class);

	@Override
	public List<Record> queryRecordPageMySql(int class_id,String date, int stu_id,
			int state,int current, int pageSize) {
		String sql = "select * from tb_record";
		List<Object> paramterLists = new ArrayList<Object>();
		if(class_id!=-1){
			sql+="  where stu_id in(select stu_id from tb_student where class_id = "+class_id+")  ";
		}else{
			sql=sql+" where 1=1 ";
		}
		if (!"".equals(date) || stu_id!=-1||state==-1||state==1||state==0) {
			if (!"".equals(date)) {
               sql=sql+" and date = ? ";
               paramterLists.add(date);
			}
			if (stu_id!=-1) {
				 sql=sql+" and stu_id =? ";
				paramterLists.add(stu_id);
			}
			if (state==-1||state==1||state==0) {
				 sql=sql+" and status =? ";
				paramterLists.add(state);
			}
		} 
		sql=sql+" order by date desc limit ?,?";
		System.out.println(sql);
		paramterLists.add(current);
		paramterLists.add(pageSize); 
		List<Record> lists = new ArrayList<Record>();
		try {
			JDBCutil.connection();
	        Object[] objs = paramterLists.toArray();
			ResultSet rs = JDBCutil.query(sql,objs);	
			while (rs.next()) {
				 Record record = new Record();
				 record.setDate(rs.getString("date"));
				 String end_time = rs.getString("end_time")==null?"":rs.getString("end_time");
				 record.setEnd_time(end_time);
				 record.setRecord_id(rs.getInt("record_id"));
				 String start_time = rs.getString("start_time")==null?"":rs.getString("start_time");
				 record.setStart_time(start_time);
				 int status = rs.getInt("status");
				 record.setStatus(status);
				 record.setStu_id(rs.getInt("stu_id"));
				 record.setStu_name(rs.getString("stu_name"));
				lists.add(record);
			}
		} catch (ClassNotFoundException e) {
			logger.info(e);
			logger.debug(e);
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.info(e);
				logger.debug(e);
			}
		}
		return lists;
	}

	@Override
	public int queryCount(int class_id,String date, int stu_id,int state) {
		String sql = "select * from tb_record";
		List<Object> paramterLists = new ArrayList<Object>();
		if(class_id!=-1){
			sql+="  where stu_id in(select stu_id from tb_student where class_id = "+class_id+")  ";
		}else{
			sql=sql+" where 1=1 ";
		}
		if (!"".equals(date) || stu_id!=-1||state==-1||state==1||state==0) {
			if (!"".equals(date)) {
               sql=sql+" and date = ? ";
               paramterLists.add(date);
			}
			if (stu_id!=-1) {
				 sql=sql+" and stu_id =? ";
				paramterLists.add(stu_id);
			}
			if (state==-1||state==1||state==0) {
				 sql=sql+" and status =? ";
				paramterLists.add(state);
			}
		} 
		List<Record> lists = new ArrayList<Record>();
		try {
			JDBCutil.connection();
	        Object[] objs = paramterLists.toArray();
			ResultSet rs = JDBCutil.query(sql,objs);	
			while (rs.next()) {
				 Record record = new Record();
				 record.setDate(rs.getString("date"));
				 String end_time = rs.getString("end_time")==null?"":rs.getString("end_time");
				 record.setEnd_time(end_time);
				 record.setRecord_id(rs.getInt("record_id"));
				 String start_time = rs.getString("start_time")==null?"":rs.getString("start_time");
				 record.setStart_time(start_time);
				 int status = rs.getInt("status");
				 record.setStatus(status);
				 record.setStu_id(rs.getInt("stu_id"));
				 record.setStu_name(rs.getString("stu_name"));
				lists.add(record);
			}
		} catch (ClassNotFoundException e) {
			logger.info(e);
			logger.debug(e);
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.info(e);
				logger.debug(e);
			}
		}
		return lists.size();
	}

	@Override
	public boolean delete(int record_id) {
		String sql = "delete from tb_record where record_id = ? ";
		Object[] objs = { record_id };
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			flag = true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	@Override
	public Record queryById(int record_id) {
		String sql ="select * from tb_record where record_id =?";
		Object[] objs = { record_id };
		 Record record = new Record();
		try {
			JDBCutil.connection();
			ResultSet rs = JDBCutil.query(sql, objs);
			if(rs.next()) {
				record.setDate(rs.getString("date"));
				String end_time = rs.getString("end_time")==null?"":rs.getString("end_time");
				 record.setEnd_time(end_time);
				record.setRecord_id(rs.getInt("record_id"));
				String start_time = rs.getString("start_time")==null?"":rs.getString("start_time");
				 record.setStart_time(start_time);
				int status = rs.getInt("status");
				 record.setStatus(status);
				 record.setStu_id(rs.getInt("stu_id"));
				 record.setStu_name(rs.getString("stu_name"));
			}
		} catch (ClassNotFoundException e) {
			logger.info(e);
			logger.debug(e);
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
		return record;
	}

	@Override
	public boolean update(Record record, int record_id) {
		String sql = "update tb_record set start_time = ?,end_time=?,status=?  where record_id = ?";
		Object[] objs = {record.getStart_time(),record.getEnd_time(),record.getStatus(),record_id};
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	 
	@Override
	public boolean updateStatus(String record_idsStr, int type) {
		List<Integer> objsList = new ArrayList<Integer>();
		String sql = "update tb_record set status=?  ";
		objsList.add(type);
		if(record_idsStr.startsWith("on")){
			record_idsStr=record_idsStr.substring(3);
		}
		String[] record_ids = record_idsStr.split(",");
		 
		for (int i = 0; i < record_ids.length; i++) {
			if(i == 0){
				sql = sql+" where record_id = ? ";
			}else{
				sql = sql+" or record_id = ?  ";
			}
			int record_id=Integer.parseInt(record_ids[i]);
			objsList.add(record_id);
		}
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objsList.toArray());
			flag = true;
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
		} catch (ClassNotFoundException e) {
			logger.info(e);
			logger.debug(e);
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
		return flag;
	}

}
