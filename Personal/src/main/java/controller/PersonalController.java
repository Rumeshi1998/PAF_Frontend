package controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.apache.tomcat.util.json.JSONParser;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
import com.google.gson.*;
import model.PersonalModel;

import org.json.JSONException;
import org.json.simple.*;

@Path("/personal")
public class PersonalController {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String add(String app_text)
	{
		PersonalModel model =new PersonalModel();
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();

		model.addPersonal(app.get("name").getAsString(),app.get("nic").getAsString(),app.get("address").getAsString(),app.get("phone").getAsString(),app.get("email").getAsString(),app.get("area").getAsString(),app.get("quantity").getAsInt(),app.get("solar_panel").getAsString());
		
		JSONObject json = new JSONObject();
		json.put("success", model.getSuccess());
		
		return json.toString();
		
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String edit(String app_text)
	{
		PersonalModel model =new PersonalModel();
		
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();

		model.editPersonal(Integer.parseInt(app.get("id").getAsString()),app.get("name").getAsString(),app.get("nic").getAsString(),app.get("address").getAsString(),app.get("phone").getAsString(),app.get("email").getAsString(),app.get("area").getAsString(),app.get("quantity").getAsInt(),app.get("solar_panel").getAsString());
		
		JSONObject json = new JSONObject();
		json.put("successfully Updated!!", model.getSuccess());
		
		return json.toString();

	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(String app_text)
	{

		PersonalModel model =new PersonalModel();
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();

		model.deletePersonal(Integer.parseInt(app.get("id").getAsString()));
		
		JSONObject json = new JSONObject();
		json.put("successfully Deleted!!", model.getSuccess());
		
		return json.toString();

	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String view(String app_text)
	{
		PersonalModel model =new PersonalModel();
		return model.getPersonal();
	}
	
	
	@POST
	@Path("/get")
	@Produces(MediaType.TEXT_HTML)
	public String viewOne(String app_text) throws NumberFormatException, JSONException
	{
		JsonObject app = new JsonParser().parse(app_text).getAsJsonObject();
		PersonalModel model =new PersonalModel();
		
		return model.getOneRequest(Integer.parseInt(app.get("id").getAsString())).toString();
	}
	
}

//http://localhost:8082/Personal/api/personal
