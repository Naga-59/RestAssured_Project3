package api.test;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import  static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.*;
import java.io.*;
import com.github.javafaker.*;

import api.endpoints.UserEndPoints_Properties;
import api.payloads.User;
import io.restassured.response.Response;
public class UserTests_Properties {

	Faker faker;
	User userpayload;
	public Logger logger;
	@BeforeClass
	public void setUp() {
		 faker=new Faker();
		 userpayload=new User();
		 
		
		 userpayload.setId(faker.idNumber().hashCode());
		 userpayload.setUsername(faker.name().username());
		 userpayload.setFirstName(faker.name().firstName());
		 userpayload.setLastName(faker.name().lastName());
		 userpayload.setEmail(faker.internet().safeEmailAddress());
		 userpayload.setPassword(faker.internet().password(5,10));
		 userpayload.setPhone(faker.phoneNumber().cellPhone());
		 
		 //logs
		 logger=LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser(){
		
		logger.info("*** Post request will start ***");
		Response res1=UserEndPoints_Properties.createUser(userpayload);
		res1.then().log().all();
		
		Assert.assertEquals(res1.getStatusCode(), 200);
		logger.info("*** successfully the Post request has done ***");
	}
	
	@Test(priority=2)
	public void testGetUser() {
		
		logger.info("*** Get request will start ***");
		Response res2=UserEndPoints_Properties.getUser(this.userpayload.getUsername());
		res2.then().log().all();
		Assert.assertEquals(res2.statusCode(),200);
		logger.info("*** successfully the Get request has done ***");
	}
	
	@Test(priority=3)
	public void testUpdateUser(){
		 
		logger.info("*** Update request will start ***");
		 userpayload.setFirstName(faker.name().firstName());
		 userpayload.setLastName(faker.name().lastName());
		 userpayload.setEmail(faker.internet().safeEmailAddress());
		 
		Response res1=UserEndPoints_Properties.updateUser(this.userpayload.getUsername(), userpayload);
		res1.then().log().all();
		
		Assert.assertEquals(res1.getStatusCode(), 200);
		logger.info("*** successfully the Update request has done ***");
		
	}
	
	@Test(priority=4)
	public void testDeleteUser() {
		logger.info("*** Delete request will start ***");
		Response res2=UserEndPoints_Properties.deleteUser(this.userpayload.getUsername());
		res2.then().log().all();
		Assert.assertEquals(res2.statusCode(),200);
		logger.info("*** successfully the Delete request has done ***");
	}
}
