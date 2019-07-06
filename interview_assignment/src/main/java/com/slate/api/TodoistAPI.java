package com.slate.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.Header;

/*
 * Author Bharath Mohankumar
 * Date 04-07-2019
 * 
*/

public class TodoistAPI {

	private static RequestSpecification REQ  = RestAssured.given();
	private static Header AUTH = new Header("Authorization","Bearer 5218e851ebb0f561785972e5244d5112b97eb7a0");
	private static Header CONTENT=new Header("Content-Type","application/json");
	private static String ENDPOINT = "https://beta.todoist.com/API/v8/";
	private static URI URL_ENDPOINT;
	private static Response RES;
	
	public static void createProject(String NAME) throws URISyntaxException
	{
		RestAssured.baseURI = "https://beta.todoist.com/API/v8/";
		URL_ENDPOINT = new URI(ENDPOINT+"projects");
		JsonObject PROJECT_NAME = new JsonObject();
		PROJECT_NAME.addProperty("name", NAME);  
		 
		/*There is an issue with Rest assured for post request
		This is the reference for older version. How ever the same issue is exists in newer version as well
		 I will work on this issue at the time of completing the project.
		https://groups.google.com/forum/#!topic/rest-assured/WKkOdjrboSA
		*/
		//Following line will be uncommented once the post issue resolved
		REQ.header(AUTH).header(CONTENT).body(PROJECT_NAME.toString());		
//		RES = REQ.post(URL_ENDPOINT).peek();
	}
	
	public static String verifyTask(String TASK_NAME) throws URISyntaxException
	{
		String TASK_ID = "no_id";
		int count = 0;
		RestAssured.baseURI = "https://beta.todoist.com/API/v8/";
		URL_ENDPOINT = new URI(ENDPOINT+"tasks");	
	
		do {  
			RES = REQ.header(AUTH).get(URL_ENDPOINT).peek();
			List<HashMap<String,String>> JSON = RES.jsonPath().getList("$");
			
				for(int i = 0; i<JSON.size(); i++)
					{
					  if(JSON.get(i).get("content").equals(TASK_NAME))
						  TASK_ID = String.valueOf(JSON.get(i).get("id"));
				  }
				System.out.println("TASK_ID inside "+TASK_ID);
				if(TASK_ID.equals("no_id"))
					count++;
				else
					break;
				
		}while(count<3);
		  
		return TASK_ID;
	}
	
	public static void reopenTask(String TASK_ID) throws URISyntaxException
	{
		RestAssured.baseURI = "https://beta.todoist.com/API/v8/";
		URL_ENDPOINT = new URI(ENDPOINT+"tasks/"+TASK_ID+"/reopen");	
		RES = REQ.header(AUTH).post(URL_ENDPOINT).peek();
		
		if(RES.statusCode() != 204)
			throw new RuntimeException("Task reopen is failed with status code : "+RES.statusCode());
	}
}
