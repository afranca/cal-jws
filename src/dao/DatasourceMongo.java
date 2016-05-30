package dao;

import javax.naming.Context;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class DatasourceMongo {
	
	private static Context context = null;
	private static MongoClient mongoClient = null;
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "localhost";
	   static final String DB_PORT = "27017";
	   static final String DB_NAME = "calentada_db";
	   
	public static MongoClient MongoConn() throws Exception{
		 
		if (mongoClient!=null){
			return mongoClient;
		}
		
		try{
			mongoClient = new MongoClient( "localhost" , 27017 ); 
			
		} catch (Exception e){
				System.out.println("DB Connection Problem:"+e);
				System.exit(0);
		}
		
		return mongoClient;
	}
	
	protected static DB getConnection(){
		
		DB db = null;
		
		try {
			db = DatasourceMongo.MongoConn().getDB(DB_NAME);
		}	catch(Exception e){
			e.printStackTrace();
		}	
		return db;
	}
	
	protected static void closeConnection(){
		try {
			DatasourceMongo.MongoConn().close();
		}	catch(Exception e){
			e.printStackTrace();
		}	
	}
}