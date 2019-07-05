package com.slate.checking;

import java.net.URI;
import java.net.URISyntaxException;

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

	private static RequestSpecification REQ=RestAssured.given();
	private static Header AUTH=new Header("Authorization","Bearer 5218e851ebb0f561785972e5244d5112b97eb7a0");
	private static Header CONTENT=new Header("Content-Type","application/json");
	private static String ENDPOINT="https://beta.todoist.com/API/v8/";
	private static URI URL_ENDPOINT;
	private static Response RES;
	
	public static void main(String args[]) throws Exception
	{
		REQ.header(AUTH);	
		URL_ENDPOINT = new URI(ENDPOINT+"projects");
		RES = REQ.get(URL_ENDPOINT);
		
		System.out.println("Response is "+RES.asString());
		RestAssured.baseURI="https://beta.todoist.com/API/v8/";
		createProject("Project_bharath1");
	}
	
	
	public static void createProject(String NAME) throws URISyntaxException
	{
		RestAssured.baseURI="https://beta.todoist.com/API/v8/";
		URL_ENDPOINT = new URI(ENDPOINT+"projects");
		JsonObject PROJECT_NAME = new JsonObject();
		PROJECT_NAME.addProperty("name", NAME);  
		 
		/*There is an issue with Rest assured for post request
		This is the reference for older version. How ever the same issue is exists in newer version as well
		 I will work on this issue at the time of closing the project.
		https://groups.google.com/forum/#!topic/rest-assured/WKkOdjrboSA
		*/
		REQ.header(AUTH).header(CONTENT).body(PROJECT_NAME.toString());		
		RES = REQ.post(URL_ENDPOINT).peek();
	}
	
	public static void verifyTask(String TASK_ID)
	{
		
	}
}
