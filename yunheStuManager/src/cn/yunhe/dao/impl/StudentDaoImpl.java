package cn.yunhe.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.yunhe.dao.StudentDao;
import cn.yunhe.entity.Student;
import cn.yunhe.util.JDBCutil;

public class StudentDaoImpl implements StudentDao {
	private  static Logger logger =  Logger.getLogger(StudentDaoImpl.class);

	@Override
	public Student querystuBystuid(int stu_id) {

		String sql = "select t1.* ,t1.class_id,class_name from tb_student t1 "
				+ "inner join tb_class t2 on t1.class_id = t2.class_id "
				+ "where stu_id = ?";
		Object[] objs = { stu_id };
		Student stu = new Student();
		try {
			JDBCutil.connection();
			ResultSet rs = JDBCutil.query(sql, objs);
			while (rs.next()) {
				stu.setStu_imag(rs.getString("stu_img"));
				stu.setClass_id(rs.getInt("class_id"));
				stu.setStu_id(rs.getInt("stu_id"));
				stu.setStu_no(rs.getString("stu_no"));
				stu.setStu_name(rs.getString("stu_name"));
				stu.setStu_sex(rs.getString("stu_sex"));
				stu.setStu_profession(rs.getString("stu_profession"));
				stu.setStu_icno(rs.getString("stu_icno"));
				stu.setStu_phone(rs.getString("stu_phone"));
				stu.setStu_birth(rs.getString("stu_birth"));
				stu.setStu_qq(rs.getString("stu_qq"));
				stu.setClass_name(rs.getString("class_name"));
				stu.setClass_id(rs.getInt("class_id"));
				stu.setStu_school(rs.getString("stu_school"));
				stu.setStu_edu(rs.getString("stu_edu"));
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
		return stu;
	}

	@Override
	public boolean update(Student stu, int stu_id) {
		String sql = "update tb_student set stu_img = ?,stu_name = ?,stu_sex=?,stu_profession=?,stu_icno=?,stu_birth=?,stu_edu=?,stu_school=?,stu_qq = ? where stu_id = ?";
		Object[] objs = { stu.getStu_imag(),stu.getStu_name(), stu.getStu_sex(),
				stu.getStu_profession(), stu.getStu_icno(), stu.getStu_birth(),
				stu.getStu_edu(), stu.getStu_school(), stu.getStu_qq(), stu_id };
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			flag = true;
		} catch (SQLException e) {
			logger.info(e);
			logger.debug(e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
	public boolean insert(Student stu) {
		String sql = "insert into tb_student(stu_img,class_id,stu_no,stu_name,stu_sex,stu_profession,stu_icno,stu_phone,stu_birth,stu_qq,stu_edu,stu_school) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		Object[] objs = {stu.getStu_imag(), stu.getClass_id(), stu.getStu_no(),
				stu.getStu_name(), stu.getStu_sex(), stu.getStu_profession(),
				stu.getStu_icno(), stu.getStu_phone(), stu.getStu_birth(),
				stu.getStu_qq(), stu.getStu_edu(), stu.getStu_school() };
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
	public boolean delete(int stu_id) {
		String sql = "delete from tb_student where stu_id = ? ";
		Object[] objs = { stu_id };
		boolean flag = false;
		try {
			JDBCutil.connection();
			JDBCutil.update(sql, objs);
			flag = true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
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
	public List<Student> queryStuPageMySql(int class_id,String name, String stu_profession,
			String stu_sex, int current, int pageSize) {
		String sql = "select * ,t1.class_id,class_name from tb_student t1 "
				+ "inner join tb_class t2 on t1.class_id = t2.class_id ";
		if(class_id!=-1){
			sql+="where t1.class_id = "+class_id;
		}else{
			sql=sql+" where 1=1 ";
		}
		List<Object> paramterLists = new ArrayList<Object>();
		if (!"".equals(name) || !"".equals(stu_profession) || !"".equals(stu_sex)) {
			 
			if (!"".equals(name)) {
               sql=sql+" and stu_name like ?";
               paramterLists.add("%"+name+"%");
			}
			if (!"".equals(stu_profession)) {
				 sql=sql+" and stu_profession like ? ";
				paramterLists.add("%"+stu_profession+"%");
			}
			if (!"".equals(stu_sex)) {
				sql=sql+" and stu_sex =?";
				paramterLists.add(stu_sex);
			}	
		} 
		sql=sql+" order by stu_id desc limit ?,?";
		paramterLists.add(current);
		paramterLists.add(pageSize); 
		List<Student> lists = new ArrayList<Student>();
		try {
			JDBCutil.connection();
	       Object[] objs = paramterLists.toArray();
			ResultSet rs = JDBCutil.query(sql,objs);	
			while (rs.next()) {
				Student stu = new Student();
				stu.setClass_id(rs.getInt("class_id"));
				stu.setStu_id(rs.getInt("stu_id"));
				stu.setStu_no(rs.getString("stu_no"));
				stu.setStu_name(rs.getString("stu_name"));
				stu.setStu_sex(rs.getString("stu_sex"));
				stu.setStu_profession(rs.getString("stu_profession"));
				stu.setStu_icno(rs.getString("stu_icno"));
				stu.setStu_phone(rs.getString("stu_phone"));
				stu.setStu_birth(rs.getString("stu_birth"));
				stu.setStu_qq(rs.getString("stu_qq"));
				stu.setClass_name(rs.getString("class_name"));
				stu.setClass_id(rs.getInt("class_id"));
				lists.add(stu);
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
		return lists;
	}

	@Override
	public int queryCount(int class_id,String name, String stu_profession, String stu_sex) {
		String sql = "select * ,t1.class_id,class_name from tb_student t1 "
				+ "inner join tb_class t2 on t1.class_id = t2.class_id ";
		if(class_id!=-1){
			sql+="where  t1.class_id = "+class_id;
		}else{
			sql=sql+" where 1=1 ";
		}
		List<Object> paramterLists = new ArrayList<Object>();
		if (!"".equals(name) || !"".equals(stu_profession) || !"".equals(stu_sex)) {
			if (!"".equals(name)) {
               sql=sql+" and stu_name like ?";
               paramterLists.add("%"+name+"%");
			}
			if (!"".equals(stu_profession)) {
				 sql=sql+" and stu_profession like ?";
				 paramterLists.add("%"+stu_profession+"%");
			}
			if (!"".equals(stu_sex)) {
				sql=sql+" and stu_sex =?";
				paramterLists.add(stu_sex);
			}	
		} 
		List<Student> lists = new ArrayList<Student>();
		try {
			JDBCutil.connection();
			Object[] objs = paramterLists.toArray();
			ResultSet rs = JDBCutil.query(sql,objs);
			while (rs.next()) {
				Student stu = new Student();
				stu.setClass_id(rs.getInt("class_id"));
				stu.setStu_id(rs.getInt("stu_id"));
				stu.setStu_no(rs.getString("stu_no"));
				stu.setStu_name(rs.getString("stu_name"));
				stu.setStu_sex(rs.getString("stu_sex"));
				stu.setStu_profession(rs.getString("stu_profession"));
				stu.setStu_icno(rs.getString("stu_icno"));
				stu.setStu_phone(rs.getString("stu_phone"));
				stu.setStu_birth(rs.getString("stu_birth"));
				stu.setStu_qq(rs.getString("stu_qq"));
				stu.setClass_name(rs.getString("class_name"));
				stu.setClass_id(rs.getInt("class_id"));
				lists.add(stu);
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
				e.printStackTrace();
			}
		}
		return lists.size();
	}

	@Override
	public List<Student> queryAllstu() {
			String sql = "select * from tb_student";
				List<Student> lists = new ArrayList<Student>();
			try {
				JDBCutil.connection();
				ResultSet rs = JDBCutil.query(sql,null);	
				while (rs.next()) {
					Student stu = new Student();
					stu.setStu_id(rs.getInt("stu_id"));
					stu.setStu_no(rs.getString("stu_no"));
					stu.setStu_name(rs.getString("stu_name"));
					lists.add(stu);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					JDBCutil.closeSource();
				} catch (SQLException e) {
					logger.info(e);
					logger.debug(e);
				}
			}
			return lists;
	}
	}
