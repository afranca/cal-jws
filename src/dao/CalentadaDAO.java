package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Calentada;

import org.codehaus.jettison.json.JSONArray;

import util.ToJSON;



public class CalentadaDAO extends Datasource {
	
	
	public int create(Calentada model) 	throws Exception {

		PreparedStatement query = null;
		Connection conn = null;
		
		try {

			conn = getConnection();
			query = conn.prepareStatement("insert into CALENTADA (NAME, DESCRIPTION) VALUES (?, ?)");
			
			query.setString(1, model.getName());
			query.setString(2, model.getDescription());
			//query.setString(3, model.getDate());
			
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
			query = conn.prepareStatement("delete from CALENTADA where id = ? ");
			
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

	public JSONArray findAll() throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = getConnection();
			query = conn.prepareStatement("select ID, NAME, DESCRIPTION, DATE from CALENTADA");
			
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
	
	public JSONArray findByName(String name) throws Exception {
		
		PreparedStatement query = null;
		Connection conn = null;
		
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		
		try {
			conn = getConnection();
			query = conn.prepareStatement("select ID, NAME, DESCRIPTION, DATE from CALENTADA where NAME = ?");
			
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
