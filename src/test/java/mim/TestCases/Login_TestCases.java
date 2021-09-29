package mim.TestCases;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import mim.base.TestBase;
import utilities.ExcelReaderAndWriter;


public class Login_TestCases extends TestBase {
	
	
	
	
	
	
	ExcelReaderAndWriter ex=new ExcelReaderAndWriter();
	
	 @DataProvider(name = "Logindata")
	 public Object[][] ReaddataSoupServices() throws IOException{
		 
		 return ex.dataRestServices();
		 
		 
	 }
	
	public static String token;
	
	@Test(priority=1,dataProvider = "Logindata")
	
	void LoginWithInvalidData(Map<Object, Object> map) throws InterruptedException, IOException
	{
	
		FileInputStream fileinputstream=new FileInputStream(new File(".\\jsonfiles\\Login_TestCases.json"));
		String Jsonbody=org.apache.commons.io.IOUtils.toString(fileinputstream,"utf-8")
			.replace("EM",map.get("UserName").toString())
			.replace("Pass",map.get("Password").toString());
			//.replace("Email", map.get("Email").toString())
			//.replace("Programme",map.get("Programme").toString())
	
				;
	  RestAssured.baseURI="http://ppdevelopment-001-site48.gtempurl.com";
	      
	  httpRequest = RestAssured.given();
	  
	//  System.out.print(Jsonbody);
	  
	  
//	  httpRequest.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		httpRequest.body(Jsonbody);

		response = httpRequest.request(Method.POST, "/User/Authenticate");
		
		Map<String, String> userdata = response.jsonPath().getMap("data");
		
		 String dataType=map.get("Data_Type").toString();
	  if (dataType.equalsIgnoreCase("Valid")) {
		  
		  token=userdata.get("authenticationToken");
		  
		 System.out.println(token);
	  }
	  
	}
			
/*	@Test
	void checkResposeBody()
	{
		logger.info("***********  Checking Respose Body **********");
		
		String responseBody = response.getBody().asString();
		
		//token=
		logger.info("Response Body==>"+responseBody);
		
		Assert.assertTrue(responseBody!=null);
		
	}
		
	@Test
	void checkStatusCode()
	{
		logger.info("***********  Checking Status Code **********");
		
		int statusCode = response.getStatusCode(); // Gettng status code
		logger.info("Status Code is ==>" + statusCode); //200
		Assert.assertEquals(statusCode, 200);
		
	}
		
	@Test
	void checkResponseTime()
	{
		logger.info("***********  Checking Response Time **********");
		
		long responseTime = response.getTime(); // Getting status Line
		logger.info("Response Time is ==>" + responseTime);
		
		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		
		Assert.assertTrue(responseTime<10000);
		
			
	}
	
	@Test
	void checkstatusLine()
	{
		logger.info("***********  Checking Status Line **********");
		
		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	
		
	}
	
	
	@Test
	void checkContentType()
	{
		logger.info("***********  Checking Content Type **********");
		
		String contentType = response.header("Content-Type");
		logger.info("Content type is ==>" + contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
	}

	@Test
	void checkserverType()
	{
		logger.info("***********  Checking Server Type **********");
		
		String serverType = response.header("Server");
		logger.info("Server Type is =>" +serverType); 
		Assert.assertEquals(serverType, "nginx/1.14.1");
	
	}

	@Test
	void checkcontentEncoding()
	{
		logger.info("***********  Checking Content Encoding**********");
		
		String contentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is==>" +contentEncoding); 
		Assert.assertEquals(contentEncoding, "gzip");
		
		
	}

	@Test
	void checkContentLenght()
	{
		logger.info("***********  Checking Content Lenght**********");
		
		String contentLength = response.header("Content-Length");
		logger.info("Content Length is==>" +contentLength); 
		
		if(Integer.parseInt(contentLength)<100)
			logger.warn("Content Length is less than 100");
		
		Assert.assertTrue(Integer.parseInt(contentLength)>100);
		
	}
		
	@Test
	void checkCookies()
	{
		logger.info("***********  Checking Cookies **********");

		String cookie = response.getCookie("PHPSESSID");
		//Assert.assertEquals(cookie,"1esuvsfslcmiee2bfrsgnijtg0");
		
	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC001_Get_All_Employees **********");
	}
*/
}
