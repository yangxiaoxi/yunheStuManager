package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Score;
import cn.yunhe.entity.Student;
import cn.yunhe.entity.Type;

public interface ScoreManager {
	 
	/**
	 * 分页查询分数
	 * @param type  分数类型
	 * @param time   时间
	 * @param stu_id  学生唯一标示
	 * @param current  数据库中记录开始的位置
	 * @param PageSize  页的大小
	 * @return
	 */
	List<Score> queryScorePageMySql(int class_id,int type,String time,int stu_id,int current,int PageSize);
	
	/**
	 * 根据分数id查找分数
	 * @param score_id  分数的唯一标示
	 * @return
	 */
	Score queryById(int score_id);
	
	/**
	 * 查询所有的分数类型
	 * @return
	 */
	List<Type> queryType();
	
	/**
	 * 删除分数
	 * @param score_id
	 * @return
	 */
	boolean delete(int score_id);
	
	/**
	 * 添加新的分数记录
	 * @param score
	 * @return
	 */
	boolean insert(Score score);
	
	/**
	 * 修改分数记录
	 * @param score
	 * @param score_id
	 * @return
	 */
	boolean update(Score score,int score_id);
	
	/**
	 * 多条件组合查询分数的数量
	 * @param score_type
	 * @param time
	 * @param stu_id
	 * @return
	 */
	int queryCount(int class_id,int score_type,String time,int stu_id);
	
}
