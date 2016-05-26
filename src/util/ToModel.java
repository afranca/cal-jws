package util;

import model.Calentada;
import model.Caliente;
import model.Expense;
import model.ModelTemplate;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ToModel {
	
	public static final String CALIENTE = "CALIENTE";
	public static final String CALENTADA = "CALENTADA";
	public static final String EXPENSE = "EXPENSE";
	
	public static ModelTemplate covert(String incomingData, String modelClassName){
		
		JSONObject jsonIncomingData = null;
		try {
			 jsonIncomingData = new JSONObject(incomingData);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		if (modelClassName.equals(CALIENTE)){
			Caliente caliente = new Caliente();
			
			caliente.setId(jsonIncomingData.optInt("_id"));			
			caliente.setName(jsonIncomingData.optString("name"));
			caliente.setPayment(jsonIncomingData.optDouble("payment"));
			caliente.setBalance(jsonIncomingData.optDouble("balance"));
			return caliente;
			
		} else if (modelClassName.equals(EXPENSE)){
			
			return new Expense();
			
		} else if (modelClassName.equals(CALENTADA)){
			Calentada calentada  = new Calentada();
			calentada.setName(jsonIncomingData.optString("NAME"));
			calentada.setDescription(jsonIncomingData.optString("DESCRIPTION"));
			//calentada.setDate(partsData.optString("DATE"));
			return calentada;
						
		}
			
		return null;
		
		
	}
	
	

}
