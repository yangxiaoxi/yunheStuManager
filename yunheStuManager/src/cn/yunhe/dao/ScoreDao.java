package cn.yunhe.dao;

import java.util.List;

import cn.yunhe.entity.Score;
import cn.yunhe.entity.Student;
import cn.yunhe.entity.Type;

public interface ScoreDao {
	/**
	 * @param type  ��������
	 * @param time  ����
	 * @param stu_id  ѧ��id
	 * @param current  ��ҳ��ѯ��ʼ��λ��
	 * @param PageSize ��ҳ�Ĵ�С
	 * @return
	 */
	List<Score> queryScorePageMySql(int class_id,int type_id,String time,int stu_id,int current,int PageSize);
	/**
	 * ������ѯ��¼��Ŀ
	 * @param name
	 * @param stu_profession
	 * @param stu_sex
	 * @return
	 */
	int queryCount(int class_id,int score_type,String time,int stu_id);
	/**
	 * ����id ��ѯscore
	 * @param score_id  ����id
	 * @return
	 */
	Score queryById(int score_id);
	/**
	 * ���ҷ�������
	 * @return
	 */
	List<Type> queryType();
	
	/**
	 * ɾ�����ּ�¼
	 * @param score_id
	 * @return
	 */
	boolean delete(int score_id);
	/**
	 * ������ּ�¼
	 * @param 
	 * @return
	 */
	boolean insert(Score score);
	
	/**
	 * ���ݷ���id �޸ķ��� 
	 * @param score
	 * @param score_id
	 * @return
	 */
	boolean update(Score score,int score_id);

}
