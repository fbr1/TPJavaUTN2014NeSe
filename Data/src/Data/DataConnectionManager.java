package Data;

import java.sql.*;

public class DataConnectionManager {
	
	private static DataConnectionManager instancia;	
	private static String dbUrl="jdbc:mysql://localhost:3306/electrodomesticos";
	private static String dbUser="root";
	private static String dbPassword="root";
	private Connection conn;	
	
	public static DataConnectionManager getInstancia(){
		if(instancia==null){
			instancia=new DataConnectionManager();
		}
		return instancia;
	}
		
	public Connection getConn(){
		try {
			if(conn==null || !conn.isValid(3)){
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn=DriverManager.getConnection(dbUrl,dbUser,dbPassword);	
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	public void CloseConn(){
		try {
			if(conn!=null && !conn.isClosed()){
				conn.close();
				conn=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
