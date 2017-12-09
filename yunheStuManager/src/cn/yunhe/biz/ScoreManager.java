package cn.yunhe.biz;

import java.util.List;

import cn.yunhe.entity.Score;
import cn.yunhe.entity.Student;
import cn.yunhe.entity.Type;

public interface ScoreManager {
	 
	/**
	 * ��ҳ��ѯ����
	 * @param type  ��������
	 * @param time   ʱ��
	 * @param stu_id  ѧ��Ψһ��ʾ
	 * @param current  ���ݿ��м�¼��ʼ��λ��
	 * @param PageSize  ҳ�Ĵ�С
	 * @return
	 */
	List<Score> queryScorePageMySql(int class_id,int type,String time,int stu_id,int current,int PageSize);
	
	/**
	 * ���ݷ���id���ҷ���
	 * @param score_id  ������Ψһ��ʾ
	 * @return
	 */
	Score queryById(int score_id);
	
	/**
	 * ��ѯ���еķ�������
	 * @return
	 */
	List<Type> queryType();
	
	/**
	 * ɾ������
	 * @param score_id
	 * @return
	 */
	boolean delete(int score_id);
	
	/**
	 * ����µķ�����¼
	 * @param score
	 * @return
	 */
	boolean insert(Score score);
	
	/**
	 * �޸ķ�����¼
	 * @param score
	 * @param score_id
	 * @return
	 */
	boolean update(Score score,int score_id);
	
	/**
	 * ��������ϲ�ѯ����������
	 * @param score_type
	 * @param time
	 * @param stu_id
	 * @return
	 */
	int queryCount(int class_id,int score_type,String time,int stu_id);
	
}
