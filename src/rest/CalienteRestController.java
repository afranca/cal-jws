package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Caliente;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import util.ToModel;
import dao.CalienteDAO;

@Path("/v1/caliente")
public class CalienteRestController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllCaliente()	throws Exception {
				
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			CalienteDAO dao = new CalienteDAO();
			
			json = dao.findAll();
			returnString = json.toString();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}	
	
	@Path("/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCalienteByName(@PathParam("name") String name)	throws Exception {
				
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			//return a error is brand is missing from the url string
			if(name == null) {
				return Response.status(400).entity("Error: please specify name for this search").build();
			}
			
			CalienteDAO dao = new CalienteDAO();
			
			json = dao.findByName(name);
			returnString = json.toString();
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}		
	
	
	
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCaliente(String incomingData) throws Exception {
				
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		CalienteDAO dao = new CalienteDAO();
		
		try {
			Caliente model = (Caliente) ToModel.covert(incomingData, ToModel.CALIENTE);
			
			int http_code = dao.create(model );			
			
			if( http_code == 200 ) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been entered successfully");
		
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to enter Item").build();
			}
			
			System.out.println( "returnString: " + returnString );
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}	
	
	@Path("/{caliente_id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCaliente(@PathParam("caliente_id") int caliente_id) throws Exception {
				
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		CalienteDAO dao = new CalienteDAO();
		
		try {
			
			//JSONObject partsData = new JSONObject(calentada_id);
			System.out.println( "Caliente_id: " + caliente_id );
			
			int http_code = dao.delete(caliente_id);			
			
			if( http_code == 200 ) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been deleted successfully");
		
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to delete Item").build();
			}
			
			System.out.println( "returnString: " + returnString );
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}

	@PUT
	@Path("/{caliente_id}")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCaliente(@PathParam("caliente_id") int caliente_id ,String incomingData) throws Exception {
				
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		CalienteDAO dao = new CalienteDAO();
		
		try {
			Caliente model = (Caliente) ToModel.covert(incomingData, ToModel.CALIENTE);
			
			int http_code = dao.update(model );			
			
			if( http_code == 200 ) {
				jsonObject.put("HTTP_CODE", "200");
				jsonObject.put("MSG", "Item has been updated successfully");
		
				returnString = jsonArray.put(jsonObject).toString();
			} else {
				return Response.status(500).entity("Unable to enter Item").build();
			}
			
			System.out.println( "returnString: " + returnString );
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}		
	
}
