package cn.yunhe.entity;

import java.math.BigDecimal;

public class Score {
	private int score_id;
	private String  time;//日期
	private String stu_name;//学生姓名
	private int stu_id;
	private String score_contents;//分数描述
	private double score_values;//分数
	private int score_type;
	private String type_value;
	
	
	
	
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public int getScore_id() {
		return score_id;
	}
	public void setScore_id(int score_id) {
		this.score_id = score_id;
	}
	public String getType_value() {
		return type_value;
	}
	public void setType_value(String type_value) {
		this.type_value = type_value;
	}
	public int getScore_type() {
		return score_type;
	}
	public void setScore_type(int score_type) {
		this.score_type = score_type;
	}
 
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getScore_contents() {
		return score_contents;
	}
	public void setScore_contents(String score_contents) {
		this.score_contents = score_contents;
	}

	
	public double getScore_values() {
		return score_values;
	}
	public void setScore_values(double score_values) {
		this.score_values = score_values;
	}
	public  Score(){}
	public Score(String time, String stu_name, String score_contents,
			double score_values) {
		super();
		this.time = time;
		this.stu_name = stu_name;
		this.score_contents = score_contents;
		this.score_values = score_values;
	}
	@Override
	public String toString() {
		return "Score [time=" + time + ", stu_name=" + stu_name
				+ ", score_contents=" + score_contents + ", score_values="
				+ score_values + "]";
	}
	 
	
	
   

}
