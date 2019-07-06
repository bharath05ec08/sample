package com.slate.api;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import com.google.gson.JsonObject;
import com.slate.common.ReadProperties;

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
	private static Header AUTH = new Header("Authorization",ReadProperties.ReadConfigProperties("AUTH_TOKEN"));
	private static Header CONTENT=new Header("Content-Type","application/json");
	private static String ENDPOINT = ReadProperties.ReadConfigProperties("ENDPOINT_URL");
	private static URI URL_ENDPOINT;
	private static Response RES;
	
	public static void createProject(String NAME) throws URISyntaxException
	{
		RestAssured.baseURI = ENDPOINT;
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
		RestAssured.baseURI = ENDPOINT;
		URL_ENDPOINT = new URI(ENDPOINT+"tasks");	
	
		do {  
			RES = REQ.header(AUTH).get(URL_ENDPOINT).peek();
			List<HashMap<String,String>> JSON = RES.jsonPath().getList("$");
			
				for(int i = 0; i<JSON.size(); i++)
					{
					  if(JSON.get(i).get("content").equals(TASK_NAME))
						  TASK_ID = String.valueOf(JSON.get(i).get("id"));
				  }
				if(TASK_ID.equals("no_id"))
					count++;
				else
					break;
				
		}while(count<5);
		 
		System.out.println("TASK_ID is "+TASK_ID);
		return TASK_ID;
	}
	
	public static void reopenTask(String TASK_ID) throws URISyntaxException
	{
		RestAssured.baseURI = ENDPOINT;
		URL_ENDPOINT = new URI(ENDPOINT+"tasks/"+TASK_ID+"/reopen");	
		RES = REQ.header(AUTH).post(URL_ENDPOINT).peek();
		
		if(RES.statusCode() != 204)
			throw new RuntimeException("Task reopen is failed with status code : "+RES.statusCode());
	}
}
