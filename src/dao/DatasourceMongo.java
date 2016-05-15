package dao;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DatasourceMongo {
	
	private static MysqlDataSource  datasource = null;
	private static Context context = null;
	
	   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "localhost";
	   static final String DB_PORT = "27017";
	   static final String DB_NAME = "calentada_db";
	   
	public static MongoClient MongoConn() throws Exception{
		MongoClient mongoClient = null;
		if (context!=null){
			return mongoClient;
		}
		
		try{			
			if (mongoClient==null){				
				context = new  InitialContext();				
			}			
			
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