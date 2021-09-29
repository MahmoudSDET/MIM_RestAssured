package mim.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;
	public String empID="23"; //Hard coded - Input for Get details of Single Employee & update employee
	
	
	public Logger logger;
	
	@BeforeSuite
	public void setup(){
		
		logger=Logger.getLogger("MIM_RestAPI");//added Logger
		PropertyConfigurator.configure("Log4j.properties"); //added logger
		logger.setLevel(Level.DEBUG);
		
		
		
	}
	


}
