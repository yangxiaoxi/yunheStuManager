package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Score;
import cn.yunhe.entity.Student;
import cn.yunhe.entity.Type;

public interface ScoreDao {
	/**
	 * @param type  分数类型
	 * @param time  日期
	 * @param stu_id  学生id
	 * @param current  分页查询开始的位置
	 * @param PageSize 分页的大小
	 * @return
	 */
	List<Score> queryScorePageMySql(int class_id,int type_id,String time,int stu_id,int current,int PageSize);
	/**
	 * 条件查询记录数目
	 * @param name
	 * @param stu_profession
	 * @param stu_sex
	 * @return
	 */
	int queryCount(int class_id,int score_type,String time,int stu_id);
	/**
	 * 根据id 查询score
	 * @param score_id  分数id
	 * @return
	 */
	Score queryById(int score_id);
	/**
	 * 查找分数类型
	 * @return
	 */
	List<Type> queryType();
	
	/**
	 * 删除积分记录
	 * @param score_id
	 * @return
	 */
	boolean delete(int score_id);
	/**
	 * 插入积分记录
	 * @param 
	 * @return
	 */
	boolean insert(Score score);
	
	/**
	 * 根据分数id 修改分数 
	 * @param score
	 * @param score_id
	 * @return
	 */
	boolean update(Score score,int score_id);

}
