package dao;

import static com.mongodb.client.model.Filters.eq;
import model.Caliente;

import org.bson.Document;
import org.codehaus.jettison.json.JSONArray;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;



public class CalienteMongoDAO extends DatasourceMongo {
	
	
	public int create(Caliente model) 	throws Exception {

		MongoDatabase db = null;
		
		try {
			db = getConnection();
			MongoCollection<Document>  mongoCollection = db.getCollection("caliente");
			
			Document doc = createNewDBObject(model);
	        mongoCollection.insertOne(doc);
		} catch(Exception e) {
			e.printStackTrace();
			return 500; //if a error occurs, return a 500
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
			//if (db != null) closeConnection();
		}
		
		return 200;
	}	

	public int update(Caliente model) 	throws Exception {

		MongoDatabase db = null;
		
		try {

			db = getConnection();
			MongoCollection<Document> collection = db.getCollection("caliente");
			Document toUpdate = createUpdateDBObject(model);
			UpdateResult result = collection.updateOne(new Document("id",model.getId()), toUpdate );
			System.out.println("Modified:"+result.getModifiedCount());
		} catch(Exception e) {
			e.printStackTrace();
			return 500; //if a error occurs, return a 500
		}
		finally {
			//if (db != null) closeConnection();
		}
		
		return 200;
	}		
	
	public JSONArray findAll() throws Exception {
		
		MongoDatabase db = null;
		
		JSONArray jsonArray = new JSONArray();
		MongoCursor<Document> cursor = null;
		try {
			db = getConnection();			
			MongoCollection<Document> collection = db.getCollection("caliente");
			cursor = collection.find().iterator();
	        while (cursor.hasNext()) {
	        	Document obj = cursor.next();
	           jsonArray.put(obj);
	        }
 	        

		}
		catch(Exception e) {
			e.printStackTrace();
			throw new Exception(e);
			//return jsonArray;
		}
		finally {
			if (cursor != null) cursor.close();
			/* Closing connection cause connection stale exception */
			//if (db != null) closeConnection();
		}
		
		return jsonArray;
	}
	
	public JSONArray findById(String id) throws Exception {
		
		MongoDatabase db = null;		
		JSONArray jsonArray = new JSONArray();
		
		try {
			db = getConnection();
			
			MongoCollection<Document> collection = db.getCollection("caliente");
        	//Document obj = collection.find(eq("_id", id)).first();
			MongoCursor<Document> cursor = collection.find(new BasicDBObject("_id", id)).iterator();
        	System.out.println("cursor"+cursor);
			//jsonArray.put(cursor.ge);
		}
		catch(Exception e) {
			e.printStackTrace();
			return jsonArray;
		}
				
		return jsonArray;
	}
	
	public JSONArray findByName(String name) throws Exception {
		
		MongoDatabase db = null;
		
		JSONArray jsonArray = new JSONArray();
		try {
			db = getConnection();
			
			MongoCollection<Document> collection = db.getCollection("caliente");
        	Document obj = collection.find().first();
        	jsonArray.put(obj);
		}
		catch(Exception e) {
			e.printStackTrace();
			return jsonArray;
		}
				
		return jsonArray;
	}
	
    private static Document createUpdateDBObject(Caliente model) {
    	
        Document doc = new Document("_id", model.getId())
        .append("name", model.getName())
        .append("payment", model.getPayment())
        .append("balance", model.getBalance());    
        
        return doc;
        
    }	
    
    private static Document createNewDBObject(Caliente model) {
    	
        Document doc = new Document("name", model.getName())
        .append("payment", model.getPayment())
        .append("balance", model.getBalance());    
        
        return doc;
        
    }
	

}
