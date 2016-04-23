package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Calentada;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import util.ToModel;
import dao.CalentadaDAO;

@Path("/v1/inventory")
public class Inventory {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllCalentada()	throws Exception {
				
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			CalentadaDAO dao = new CalentadaDAO();
			
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
	public Response findCalentadaByName(@PathParam("name") String name)	throws Exception {
				
		String returnString = null;
		JSONArray json = new JSONArray();
		
		try {
			
			//return a error is brand is missing from the url string
			if(name == null) {
				return Response.status(400).entity("Error: please specify name for this search").build();
			}
			
			CalentadaDAO dao = new CalentadaDAO();
			
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
	public Response createCalentada(String incomingData) throws Exception {
				
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		CalentadaDAO dao = new CalentadaDAO();
		
		try {
			Calentada model = (Calentada) ToModel.covert(incomingData, ToModel.CALENTADA);
			
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
	
	@Path("/{calentada_id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteCalentada(@QueryParam("calentada_id") int calentada_id) throws Exception {
				
		String returnString = null;
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();
		CalentadaDAO dao = new CalentadaDAO();
		
		try {
			
			//JSONObject partsData = new JSONObject(calentada_id);
			System.out.println( "calentada_id: " + calentada_id );
			
			int http_code = dao.delete(calentada_id);			
			
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
	
	
}
