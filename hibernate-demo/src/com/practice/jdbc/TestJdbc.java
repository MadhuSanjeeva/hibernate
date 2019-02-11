package com.practice.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://192.168.1.21:3306/otsiqa";
		//String jdbcUrl = "jdbc:mysql://192.168.1.21:3306/hb_student_tracker?useSSL=false";
		String user = "demouser";
		String password = "demouser";
		try {
			System.out.println("Connecting to database..  "+jdbcUrl);
			Connection con = DriverManager.getConnection(jdbcUrl,user,password);
			System.out.println("Connection Successfull!!!!");
			Statement statement = con.createStatement();
			String query= "select * from emp";
			ResultSet rs = statement.executeQuery(query);
			while(rs.next())
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getInt(3));  
				con.close(); 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
