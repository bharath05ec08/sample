package com.slate.checking;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.entity.mime.Header;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/*
 * Author Bharath Mohankumar
 * Date 04-07-2019
 * 
*/

public class TodoistAPI {

	
	public static void main(String args[]) throws Exception
	{
		RequestSpecification req=RestAssured.given();
		Header auth=new Header();
		;
	req.header(new io.restassured.http.Header("Authorization","Bearer 5218e851ebb0f561785972e5244d5112b97eb7a0"));
	
		URI fromSession=new URI("https://beta.todoist.com/API/v8/projects");
		Response res=req.get(fromSession);
		
		System.out.println("Response is "+res.asString());
	}
}
