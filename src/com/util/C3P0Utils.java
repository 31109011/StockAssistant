package com.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0Utils {
	  private static ComboPooledDataSource ds = new ComboPooledDataSource();
	  
	    static {
	        try {
	            Class.forName("org.sqlite.JDBC");
	            //String path = C3P0Utils.class.getResource("/").getPath();
	            ds.setJdbcUrl("jdbc:sqlite:test.db");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	 
	    /**
	     * 打开数据库连接
	     */
	    public static Connection openConnection() {
	        Connection conn = null;
	        try {
	            conn = ds.getConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return conn;
	    }
	 
	    /**
	     * 关闭数据库
	     */
	    public static void closeAll(Connection con, Statement stmt, ResultSet rs) {
	        try {
	            if (con != null) {
	                con.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (rs != null) {
	                rs.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
}
