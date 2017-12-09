package cn.yunhe.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.log4j.Logger;


public class JDBCutil {
	private static Connection conn = null;
	private static ResultSet cursor = null;
	private static PreparedStatement prSate = null;
	private  static Logger logger =  Logger.getLogger(JDBCutil.class);

//	private static String URL = "";
//	private static String NAME = "";
//	private static String PWD = "";
//	private static String DRIVER = "";
	private static DataSource ds = null;
	
	static{
		
		
		Properties perProperties  = new Properties();
		try {
			perProperties.load(JDBCutil.class.getResourceAsStream("/yunhe.properties"));
			ds = BasicDataSourceFactory.createDataSource(perProperties);
//			URL = perProperties.getProperty("URL");
//			NAME = perProperties.getProperty("NAME");
//			PWD = perProperties.getProperty("PWD");
//			DRIVER = perProperties.getProperty("DRIVER");
			
		} catch (Exception e) {
			//e.printStackTrace();
			logger.info(e);
			logger.debug(e);
		}
	}
	

	/**
	 * 连接数据库
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void connection() throws ClassNotFoundException, SQLException {
//		Class.forName(DRIVER);
//		conn = DriverManager.getConnection(URL, NAME, PWD);
		
		conn = ds.getConnection();
		logger.debug("连接数据库");
	}

	 
	/**
	 * @param sql        外部传入需要执行的sql语句
	 * @param obj        传入sql语句中参数列表
	 * @return           返回影响行数
	 * @throws SQLException
	 */
	public static int update(String sql,Object[] obj) throws SQLException {
		prSate = conn.prepareStatement(sql);
		if(obj!=null){
			for (int i = 0; i < obj.length; i++) {
				prSate.setObject(i+1, obj[i]);
			}
		}
		
		int result = prSate.executeUpdate();
		return result;
	}
	 
	/** 
	 * @param sql 外部传入需要执行的sql语句
	 * @param obj   传入sql语句中参数列表
	 * @return    返回查询到的结果集
	 * @throws SQLException
	 */
	public static ResultSet query(String sql,Object[] obj) throws SQLException {
		prSate = conn.prepareStatement(sql);
		if(obj!=null){
			for (int i = 0; i < obj.length; i++) {
				prSate.setObject(i+1, obj[i]);
			}
		}
		cursor = prSate.executeQuery();
		return cursor;
	}

	/**
	 * 释放资源
	 * 
	 * @throws SQLException
	 */
	public static void closeSource() throws SQLException {
		
		
		if (cursor != null) {
			cursor.close();
			cursor = null;
		}
		
		if (prSate != null) {
			prSate.close();
			prSate = null;
		}
		
		if (conn != null) {
			conn.close();
			conn = null;
		}
		
	}

}
