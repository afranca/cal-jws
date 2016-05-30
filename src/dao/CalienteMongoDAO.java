package dao;

import model.Caliente;

import org.codehaus.jettison.json.JSONArray;

import util.ToJSON;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;



public class CalienteMongoDAO extends DatasourceMongo {
	
	
	public int create(Caliente model) 	throws Exception {

		DB db = null;
		
		try {

			db = getConnection();
			DBCollection mongoCollection = db.getCollection("caliente");
			
			DBObject doc = createDBObject(model);
			
	        //create user
	        WriteResult result = mongoCollection.insert(doc);
	        /*
	        System.out.println(result.getUpsertedId());
	        System.out.println(result.getN());
	        System.out.println(result.isUpdateOfExisting());
	        System.out.println(result.getLastConcern());			
			*/

		
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

		DB db = null;
		
		try {

			db = getConnection();
			DBCollection col = db.getCollection("caliente");
			
			
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

		DB db = null;
		
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
		
		DB db = null;
		
		ToJSON converter = new ToJSON();
		JSONArray jsonArray = new JSONArray();
		
		try {
			db = getConnection();			
			
			DBCollection mongoCollection = db.getCollection("caliente");
			
			//DBObject doc = createDBObject(model);
	        DBCursor cursor = mongoCollection.find();
	        while (cursor.hasNext()) {
	           DBObject obj = cursor.next();
	           jsonArray.put(obj);
	        }
			

		}
		catch(Exception e) {
			e.printStackTrace();
			return jsonArray;
		}
		finally {
			if (db != null) closeConnection();
		}
		
		return jsonArray;
	}
	
	public JSONArray findByName(String name) throws Exception {
		
		DB db = null;
		
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
	
    private static DBObject createDBObject(Caliente model) {
        BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
                                 
        docBuilder.append("_id", model.getId());
        docBuilder.append("name", model.getName());
        docBuilder.append("paidIn", model.getPaidIn());
        docBuilder.append("balance", model.getBalance());
        return docBuilder.get();
    }	
	

}
