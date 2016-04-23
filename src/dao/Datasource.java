package dao;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class Datasource {
	
	private static MysqlDataSource  datasource = null;
	private static Context context = null;
	
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/calentada_db";
	   //  Database credentials
	   static final String USER = "resin";
	   static final String PASS = "r3sin";
	   
	public static DataSource MySQL308tubeConn() throws Exception{
		
		if (context!=null){
			return datasource;
		}
		
		try{			
			if (context==null){				
				context = new  InitialContext();				
			}			
			//MySQL308tube = (DataSource) context.lookup("dbc:mysql://localhost/");	
			Class.forName("com.mysql.jdbc.Driver");

			datasource = new MysqlDataSource();
			datasource.setURL(DB_URL);
			datasource.setUser(USER);
			datasource.setPassword(PASS);			
			
		} catch (Exception e){
				System.out.println("DB Connection Problem:"+e);
				System.exit(0);
			
		}
		
		return datasource;
	}
	
	protected static Connection getConnection(){
		
		Connection conn=null;
		
		try {
			conn = Datasource.MySQL308tubeConn().getConnection();
		}	catch(Exception e){
			e.printStackTrace();
		}	
		return conn;
	}
	


/*
	public static void main(String[] args) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL, USER, PASS);

	      //STEP 4: Execute a query
	      System.out.println("Creating database...");
	      stmt = conn.createStatement();
	      
	      String sql = "CREATE DATABASE STUDENTS";
	      stmt.executeUpdate(sql);
	      System.out.println("Database created successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}//end main
*/
}
