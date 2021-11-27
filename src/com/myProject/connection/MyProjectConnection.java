/**
 * 
 */
package com.myProject.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author pnlmm075
 *
 */
public class MyProjectConnection {
	
	static String DRIVER= "oracle.jdbc.OracleDriver";
	static String DATABASE_URL= "jdbc:oracle:thin:@//hostname:1528/xe";
	static String USER= "username";
	static String PASSWORD= "pwd";
	
	public static Connection connectionMethod() throws Exception{
		  
		Connection conn = null;
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
		return conn;
	}
}
