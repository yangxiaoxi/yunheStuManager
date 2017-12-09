package cn.yunhe.biz.impl;

import java.util.List;

import cn.yunhe.biz.ScoreManager;
import cn.yunhe.dao.ScoreDao;
import cn.yunhe.dao.impl.ScoreDaoImpl;
import cn.yunhe.entity.Score;
import cn.yunhe.entity.Type;

public class ScoreManagerImpl implements ScoreManager {
	private ScoreDao  scoreDao = new ScoreDaoImpl();

	@Override
	public List<Score> queryScorePageMySql(int class_id,int type, String time, int stu_id,
			int current, int PageSize) {
		 
		return scoreDao.queryScorePageMySql(class_id,type, time, stu_id, current, PageSize);
	}

	@Override
	public List<Type> queryType() {
		 
		return scoreDao.queryType();
	}

	@Override
	public boolean delete(int score_id) {
		 
		return scoreDao.delete(score_id);
	}

	@Override
	public boolean insert(Score score) {
		// TODO Auto-generated method stub
		return scoreDao.insert(score);
	}

	@Override
	public Score queryById(int score_id) {
		// TODO Auto-generated method stub
		return scoreDao.queryById(score_id);
	}

	@Override
	public boolean update(Score score, int score_id) {
		// TODO Auto-generated method stub
		return scoreDao.update(score, score_id);
	}

	@Override
	public int queryCount(int class_id,int score_type, String time, int stu_id) {
		// TODO Auto-generated method stub
		return scoreDao.queryCount(class_id,score_type, time, stu_id);
	}

}
