package cn.yunhe.entity;

public class Permission {
	private int permissin_id;
	private String permission_name;
	public int getPermissin_id() {
		return permissin_id;
	}
	public void setPermissin_id(int permissin_id) {
		this.permissin_id = permissin_id;
	}
	public String getPermission_name() {
		return permission_name;
	}
	public void setPermission_name(String permission_name) {
		this.permission_name = permission_name;
	}
	public Permission(){}
	public Permission(int permissin_id, String permission_name) {
		super();
		this.permissin_id = permissin_id;
		this.permission_name = permission_name;
	}
	
	

}
