package cn.yunhe.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.yunhe.dao.ScoreDao;
import cn.yunhe.entity.Score;
import cn.yunhe.entity.Student;
import cn.yunhe.entity.Type;
import cn.yunhe.util.JDBCutil;

public class ScoreDaoImpl  implements ScoreDao{
	private  static Logger logger =  Logger.getLogger(ScoreDaoImpl.class);

	@Override
	public List<Score> queryScorePageMySql(int class_id,int type, String time, int stu_id,
			int current, int pageSize) {
		String sql = "select t1.*,type_value,stu_name from tb_score t1"
				+ " inner join tb_type t2 on t1.score_type= t2.type_id "
				+ " inner join tb_student t3 on t1.stu_id = t3.stu_id";
		if(class_id!=-1){
			sql+="where  t1.class_id = "+class_id;
		}else{
			sql=sql+" where 1=1 ";
		}
		List<Object> paramterLists = new ArrayList<Object>();
		if (type!=-1|| !"".equals(time) || stu_id!=-1) {
			if (type!=-1) {
               sql=sql+" and score_type = ? ";
               paramterLists.add(type);
			}
			if ( !"".equals(time)) {
				 sql=sql+" and time =? ";
				paramterLists.add(time);
			}
			if ( stu_id!=-1) {
				sql=sql+" and t1.stu_id =?";
				paramterLists.add(stu_id);
			}	
		} 
		sql=sql+" limit ?,?";
		System.out.println(sql);
		paramterLists.add(current);
		paramterLists.add(pageSize); 
		List<Score> lists = new ArrayList<Score>();
		try {
			JDBCutil.connection();
	       Object[] objs = paramterLists.toArray();
			ResultSet rs = JDBCutil.query(sql,objs);	
			while (rs.next()) {
				 Score score = new Score();
				 score.setScore_id(rs.getInt("score_id"));
				 score.setScore_type(rs.getInt("score_type"));
				 score.setScore_values(rs.getDouble("score_value"));
				 score.setStu_id(rs.getInt("stu_id"));
				 score.setScore_contents(rs.getString("score_contents"));
				 score.setTime(rs.getString("time"));
				 score.setType_value(rs.getString("type_value"));
				 score.setStu_name(rs.getString("stu_name"));
				 lists.add(score);
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
	public List<Type> queryType() {
		String sql = "select * from tb_type";
       List<Type> lists = new ArrayList<Type>();
		try {
			JDBCutil.connection();
			ResultSet rs = JDBCutil.query(sql,null);
			while (rs.next()) {
				Type type = new Type();
				 type.setType_id(rs.getInt("type_id"));
				 type.setType_value(rs.getString("type_value"));
				lists.add(type);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}

	@Override
	public boolean delete(int score_id) {
		String sql = "delete from tb_score where score_id = ? ";
		Object[] objs = { score_id };
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			flag = true;
		} catch (ClassNotFoundException | SQLException e) {
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

	@Override
	public boolean insert(Score score) {
		String sql = "insert into tb_score(score_type,time,stu_id,score_contents,score_value) values(?,?,?,?,?)";
		Object[] objs = {score.getScore_type(),score.getTime(),score.getStu_id(),score.getScore_contents(),score.getScore_values()};
		System.out.println("sql is"+sql);
		boolean isAdd = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			isAdd = true;
		} catch (ClassNotFoundException e1) {
			logger.info(e1);
			logger.debug(e1);
		} catch (SQLException e1) {
			logger.info(e1);
			logger.debug(e1);
		} finally {
			try {
				JDBCutil.closeSource();
			} catch (SQLException e) {
				logger.info(e);
				logger.debug(e);
			}
		}
		return isAdd;
	}

	@Override
	public boolean update(Score score, int score_id) {
		String sql = "update tb_score set score_type = ?,time=?,stu_id=?,score_contents=?,score_value=? where score_id = ?";
		Object[] objs = {score.getScore_type(),score.getTime(),score.getStu_id(),score.getScore_contents(),score.getScore_values(),score_id};
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
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

	@Override
	public Score queryById(int score_id) {
		String sql ="select t1.*,stu_name,type_value from tb_score  t1"
				+ " inner join tb_type t2 on t1.score_type=t2.type_id "
				+ " inner join tb_student t3 on t1.stu_id=t3.stu_id "
				+ " where score_id =?";
		Object[] objs = { score_id };
		Score score = new Score();
		try {
			JDBCutil.connection();
			ResultSet rs = JDBCutil.query(sql, objs);
			if(rs.next()) {
				 score.setScore_id(rs.getInt("score_id"));
				 score.setScore_type(rs.getInt("score_type"));
				 score.setType_value(rs.getString("type_value"));
				 score.setTime(rs.getString("time"));
				 score.setStu_id(rs.getInt("stu_id"));
				 score.setStu_name(rs.getString("stu_name"));
				 score.setScore_contents(rs.getString("score_contents"));
				 score.setScore_values(rs.getDouble("score_value"));
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
		return score;
	}

	@Override
	public int queryCount(int class_id,int score_type, String time, int stu_id) {
		String sql = "select t1.*,type_value,stu_name from tb_score t1"
				+ " inner join tb_type t2 on t1.score_type= t2.type_id "
				+ " inner join tb_student t3 on t1.stu_id = t3.stu_id";
		if(class_id!=-1){
			sql+="where  t1.class_id = "+class_id;
		}else{
			sql=sql+" where 1=1 ";
		}
		List<Object> paramterLists = new ArrayList<Object>();
		if (score_type!=-1|| !"".equals(time) || stu_id!=-1) {
			if (score_type!=-1) {
               sql=sql+" and score_type = ? ";
               paramterLists.add(score_type);
			}
			if ( !"".equals(time)) {
				 sql=sql+" and time =? ";
				paramterLists.add(time);
			}
			if ( stu_id!=-1) {
				sql=sql+" and t1.stu_id =?";
				paramterLists.add(stu_id);
			}	
		} 
		List<Score> lists = new ArrayList<Score>();
		try {
			JDBCutil.connection();
	       Object[] objs = paramterLists.toArray();
			ResultSet rs = JDBCutil.query(sql,objs);	
			while (rs.next()) {
				 Score score = new Score();
				 score.setScore_id(rs.getInt("score_id"));
				 score.setScore_type(rs.getInt("score_type"));
				 score.setScore_values(rs.getDouble("score_value"));
				 score.setStu_id(rs.getInt("stu_id"));
				 score.setScore_contents(rs.getString("score_contents"));
				 score.setTime(rs.getString("time"));
				 score.setType_value(rs.getString("type_value"));
				 score.setStu_name(rs.getString("stu_name"));
				 lists.add(score);
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

}
