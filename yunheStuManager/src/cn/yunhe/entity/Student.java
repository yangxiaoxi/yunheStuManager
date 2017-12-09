package cn.yunhe.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Student  implements Serializable{
//名次	学号	姓名	纪律考核分	技术考核分	职业能力考核分	综合分
   
	private String stu_no;//学号
	private String stu_name;//姓名
	private String stu_sex;
	private String stu_profession;
	private String stu_icno;
	private String stu_phone;
	private String stu_birth;
	private String stu_qq;
	private int class_id;
	private String class_name;
	private int stu_id;
	private String stu_school;
	private String stu_edu;
	private String stu_imag;
	
	
	
	
	
	 
	public String getStu_imag() {
		return stu_imag;
	}
	public void setStu_imag(String stu_imag) {
		this.stu_imag = stu_imag;
	}
	public String getStu_school() {
		return stu_school;
	}
	public void setStu_school(String stu_school) {
		this.stu_school = stu_school;
	}
	public String getStu_edu() {
		return stu_edu;
	}
	public void setStu_edu(String stu_edu) {
		this.stu_edu = stu_edu;
	}
	public String getStu_icno() {
		return stu_icno;
	}
	public void setStu_icno(String stu_icno) {
		this.stu_icno = stu_icno;
	}
	public String getStu_phone() {
		return stu_phone;
	}
	public void setStu_phone(String stu_phone) {
		this.stu_phone = stu_phone;
	}
	public String getStu_birth() {
		return stu_birth;
	}
	public void setStu_birth(String stu_birth) {
		this.stu_birth = stu_birth;
	}
	public String getStu_qq() {
		return stu_qq;
	}
	public void setStu_qq(String stu_qq) {
		this.stu_qq = stu_qq;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getStu_profession() {
		return stu_profession;
	}
	public void setStu_profession(String stu_profession) {
		this.stu_profession = stu_profession;
	}
	public String getStu_sex() {
		return stu_sex;
	}
	public void setStu_sex(String stu_sex) {
		this.stu_sex = stu_sex;
	}
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	 
	public String getStu_no() {
		return stu_no;
	}
	public void setStu_no(String stu_no) {
		this.stu_no = stu_no;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	@Override
	public String toString() {
		return "Student [stu_no=" + stu_no + ", stu_name=" + stu_name
				+ ", stu_sex=" + stu_sex + ", stu_profession=" + stu_profession
				+ ", stu_icno=" + stu_icno + ", stu_phone=" + stu_phone
				+ ", stu_birth=" + stu_birth + ", stu_qq=" + stu_qq
				+ ", class_id=" + class_id + ", class_name=" + class_name
				+ ", stu_id=" + stu_id + ", stu_school=" + stu_school
				+ ", stu_edu=" + stu_edu + ", stu_imag=" + stu_imag + "]";
	}
 
	 
}
