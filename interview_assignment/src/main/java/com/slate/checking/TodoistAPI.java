package com.slate.checking;

import java.net.URI;
import java.net.URISyntaxException;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
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
	private static String REQ_DATA1="{\"name\": \"";
	private static String REQ_DATA2="\"}";
	
	public static void main(String args[]) throws Exception
	{
		REQ.header(AUTH);	
		URL_ENDPOINT = new URI(ENDPOINT+"projects");
		RES = REQ.get(URL_ENDPOINT);
		
		System.out.println("Response is "+RES.asString());
		RestAssured.baseURI="https://beta.todoist.com/API/v8/";
		createProject("Project_bharath1");
	}
	
	
	public static String createProject(String projectName) throws URISyntaxException
	{RestAssured.baseURI="https://beta.todoist.com/API/v8/";
		JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("name", "PROJECT1");
        REQ=RestAssured.given().contentType("").contentType("Content-Type,application/json").contentType(ContentType.JSON);
        System.out.println(loginCredentials.toString());
        
		REQ.header(AUTH).header(CONTENT).body(REQ_DATA1+projectName+REQ_DATA2);
		REQ.header(AUTH).header(CONTENT).body("{\r\n" + 
				"    \"name\": \"PROJECT2\"\r\n" + 
				"}");
		
		URL_ENDPOINT = new URI(ENDPOINT+"projects");
		RES = REQ.post(URL_ENDPOINT).peek();
		
		System.out.println("Project is "+RES.asString()+"\n status code"+RES.statusCode());
		return "";
	}
}
