package com.mize.empdb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static String url;
	private static String pwd;
	private static String userid;
	private static String driverclass;
	private static Connection connection;
	private static FileInputStream input;
	private static Properties properties;
	private static boolean flag;
	public static boolean loadDriver(){
		flag=false;
		try {
			input=new FileInputStream("E:/Assesment/DbProperties.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		properties=new Properties();
		try {
			properties.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driverclass=properties.getProperty("driverclass");
		url=properties.getProperty("url");
		userid=properties.getProperty("userid");
		pwd=properties.getProperty("pwd");
		flag=true;
		
		return flag;
	}
	public static Connection getdbConnection(){
		if(loadDriver()){
		try {
			Class.forName(driverclass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	try {
		connection=DriverManager.getConnection(url,userid,pwd);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}else{
		System.out.println("Error in loading Properties file");
	}
		return connection;
	}
	public static void main(String arg[]){
		System.out.println(getdbConnection());
	}
}
