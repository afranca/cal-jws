package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Caliente;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import util.ToJSON;



public class CalienteDAO extends Datasource {
	
	
	public int create(Caliente model) 	throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		
		try {

			conn = getConnection();
			query = conn.prepareStatement("insert into CALIENTE (NAME) VALUES (?)");
			
			query.setString(1, model.getName());
			
			query.executeUpdate(); //note the new command for insert statement
		
		} catch(Exception e) {
			e.printStackTrace();
			return 500; //if a error occurs, return a 500
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return 200;
	}	
	
	public int delete(int id) 	throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		
		try {

			conn = getConnection();
			query = conn.prepareStatement("delete from CALIENTE where id = ? ");
			
			query.setInt(1,id); //protect against sql injection
			
			query.executeUpdate(); //note the new command for insert statement
		
		} catch(Exception e) {
			e.printStackTrace();
			return 500; //if a error occurs, return a 500
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return 200;
	}	

	public int update(Caliente model) 	throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		
		try {

			conn = getConnection();
			query = conn.prepareStatement("update CALIENTE set NAME = ?");
			
			query.setString(1, model.getName());
			
			query.executeUpdate(); //note the new command for insert statement
		
		} catch(Exception e) {
			e.printStackTrace();
			return 500; //if a error occurs, return a 500
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return 200;
	}		
	
	public JSONArray findAll() throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = getConnection();
			//query = conn.prepareStatement("select ID, NAME, BALANCE, PAID_IN, PAYMENT from CALIENTE");
			query = conn.prepareStatement("select ID, NAME, BALANCE, PAYMENT from CALIENTE");
			
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}
	
	public JSONObject findById(String id) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONObject jsonObj = new JSONObject();
		
		try {
			conn = getConnection();
			//query = conn.prepareStatement("select ID, NAME, BALANCE, PAID_IN, PAYMENT from CALIENTE where NAME = ?");
			query = conn.prepareStatement("select ID, NAME, BALANCE, PAYMENT from CALIENTE where ID = ?");
			
			query.setInt(1, Integer.parseInt(id)); //protect against sql injection
			
			ResultSet rs = query.executeQuery();
			
			jsonObj = converter.toJSONObject(rs); 
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return jsonObj;
		}
		catch(Exception e) {
			e.printStackTrace();
			return jsonObj;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return jsonObj;
	}
	
	
	public JSONArray findByName(String name) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = getConnection();
			//query = conn.prepareStatement("select ID, NAME, BALANCE, PAID_IN, PAYMENT from CALIENTE where NAME = ?");
			query = conn.prepareStatement("select ID, NAME, BALANCE, PAYMENT from CALIENTE where NAME = ?");
			
			query.setString(1, name); //protect against sql injection
			
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}
		catch(SQLException sqlError) {
			sqlError.printStackTrace();
			return json;
		}
		catch(Exception e) {
			e.printStackTrace();
			return json;
		}
		finally {
			if (conn != null) conn.close();
		}
		
		return json;
	}

}
