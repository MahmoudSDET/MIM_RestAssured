package mim.TestCases;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import utilities.ExcelReaderAndWriter;

public class DDTWithRestAssured {
	
	
	
	
	ExcelReaderAndWriter ex=new ExcelReaderAndWriter();
	
	 @DataProvider(name = "Restdata")
	 public Object[][] ReaddataSoupServices() throws IOException{
		 
		 return ex.dataRestServices();
		 
		 
	 }
	
	//How to call Post Method using Json file in RestAssured API
		@Test(priority=3,dataProvider = "Restdata")
	    public void PostDataByJsonFile(Map<Object, Object> map) throws IOException{

		FileInputStream fileinputstream=new FileInputStream(new File(".\\jsonfiles\\Student.json"));
		String Jsonbody=org.apache.commons.io.IOUtils.toString(fileinputstream,"utf-8")
			.replace("fn",map.get("").toString() )
			.replace("Ln",map.get("").toString())
			//.replace("Email", map.get("Email").toString())
			//.replace("Programme",map.get("Programme").toString())
				
				;
	      RestAssured.baseURI="http://localhost:8085";
	      System.out.print(Jsonbody);
	     
	      given().contentType("application/json")
	      .and()
	      .body(Jsonbody)
	      .when()
	      .post("/student")
	      .then()
	    
	      .log().all().statusCode(201)
	      
	      ;
	      
	     

	    }
	
	
      

}
