package cn.yunhe.entity;

public class User {
	
	private int user_id;
	private String user_name;
	private String user_pwd;
	private int user_type;
	private int user_role;
	private String role_name;
	private String user_nickname;
	private int class_id;
	private String class_name;
	
	private String open_id;
	private String acess_token;
	
	
	
	
	
	
	
	public String getOpen_id() {
		return open_id;
	}
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	public String getAcess_token() {
		return acess_token;
	}
	public void setAcess_token(String acess_token) {
		this.acess_token = acess_token;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pwd() {
		return user_pwd;
	}
	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	public int getUser_role() {
		return user_role;
	}
	public void setUser_role(int user_role) {
		this.user_role = user_role;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	
	
	public User(){}
	public User(int user_id, String user_name, String user_pwd, int user_type,
			int user_role, String role_name, String user_nickname, int class_id) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.user_type = user_type;
		this.user_role = user_role;
		this.role_name = role_name;
		this.user_nickname = user_nickname;
		this.class_id = class_id;
	}
	public User(int user_id, String user_name, String user_pwd, int user_type,
			int user_role, String role_name, String user_nickname,
			int class_id, String class_name) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pwd = user_pwd;
		this.user_type = user_type;
		this.user_role = user_role;
		this.role_name = role_name;
		this.user_nickname = user_nickname;
		this.class_id = class_id;
		this.class_name = class_name;
	}
	

}
