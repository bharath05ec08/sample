package com.slate.common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadProperties {

	static String value="";
	
	public static String ReadConfigProperties(String key)
	{
		Properties config=new Properties();
		
				try
				{
					config.load(new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/resources/config.properties")));				
					value=config.getProperty(key);
				}catch(Exception e)
				{
					System.out.println("Error in reading file"+e.toString());
				}
		return value;
	}

}
