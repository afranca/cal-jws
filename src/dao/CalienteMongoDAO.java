package dao;

import model.Caliente;

import org.bson.Document;
import org.codehaus.jettison.json.JSONArray;

import util.ToJSON;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;



public class CalienteMongoDAO extends DatasourceMongo {
	
	
	public int create(Caliente model) 	throws Exception {

		MongoDatabase db = null;
		
		try {

			db = getConnection();
			//DBCollection mongoCollection = db.getCollection("caliente");			
			MongoCollection<Document>  mongoCollection = db.getCollection("caliente");
			
			Document doc = createDBObject(model);
			
	        //create user
	        mongoCollection.insertOne(doc);
	   

		
		} catch(Exception e) {
			e.printStackTrace();
			return 500; //if a error occurs, return a 500
		}
		finally {
			if (db != null) closeConnection();
		}
		
		return 200;
	}	
	
	public int delete(int id) 	throws Exception {

		MongoDatabase db = null;
		
		try {

			db = getConnection();
			MongoCollection<Document>  mongoCollection = db.getCollection("caliente");
			
			
//			query = conn.prepareStatement("delete from CALIENTE where id = ? ");
//			
//			query.setInt(1,id); //protect against sql injection
//			
//			query.executeUpdate(); //note the new command for insert statement
		
		} catch(Exception e) {
			e.printStackTrace();
			return 500; //if a error occurs, return a 500
		}
		finally {
			if (db != null) closeConnection();
		}
		
		return 200;
	}	

	public int update(Caliente model) 	throws Exception {

		MongoDatabase db = null;
		
		try {

			db = getConnection();
//			query = conn.prepareStatement("update CALIENTE set NAME = ?");
//			
//			query.setString(1, model.getName());
//			
//			query.executeUpdate(); //note the new command for insert statement
		
		} catch(Exception e) {
			e.printStackTrace();
			return 500; //if a error occurs, return a 500
		}
		finally {
			if (db != null) closeConnection();
		}
		
		return 200;
	}		
	
	public JSONArray findAll() throws Exception {
		
		MongoDatabase db = null;
		
		ToJSON converter = new ToJSON();
		JSONArray jsonArray = new JSONArray();
		
		try {
			db = getConnection();			
			
			MongoCollection<Document> collection = db.getCollection("caliente");
			
	        //DBCursor cursor = mongoCollection.find();
			MongoCursor<Document> cursor = collection.find().iterator();
	        while (cursor.hasNext()) {
	        	Document obj = cursor.next();
	           jsonArray.put(obj);
	        }
 	        cursor.close();

		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
			//return jsonArray;
		}
		finally {
			if (db != null) closeConnection();
		}
		
		return jsonArray;
	}
	
	public JSONArray findByName(String name) throws Exception {
		
		MongoDatabase db = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			db = getConnection();
			
			/*
			query = conn.prepareStatement("select ID, NAME, BALANCE, PAID_IN, PAYMENT from CALIENTE where NAME = ?");
			
			query.setString(1, name); //protect against sql injection
			
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close(); //close connection
			*/
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (db != null) closeConnection();
		}
		
		return json;
	}
	
    private static Document createDBObject(Caliente model) {
    	/*
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
                                 
        docBuilder.append("_id", model.getId());
        docBuilder.append("name", model.getName());
        docBuilder.append("paidIn", model.getPaidIn());
        docBuilder.append("balance", model.getBalance());
        return docBuilder.get();
        */
    	
        Document doc = new Document("_id", model.getId())
        .append("name", model.getName())
        .append("paidIn", model.getId())
        .append("balance", model.getBalance());    
        
        return doc;
        
    }	
	

}
