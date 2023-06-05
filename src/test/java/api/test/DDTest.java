package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.utilities.DataProviderUtilities;
import io.restassured.response.Response;
import api.endpoints.UserEndPoints;
import api.payloads.User;
public class DDTest {

	@Test(priority=1,dataProvider="data", dataProviderClass=DataProviderUtilities.class)
	public void testPostUsers(String userid, String username, String fname, String lname, String usermail, String pwd, String pnumber) {
		User userpayload=new User();
		
		userpayload.setId(Integer.parseInt(userid));
		 userpayload.setUsername(username);
		 userpayload.setFirstName(fname);
		 userpayload.setLastName(lname);
		 userpayload.setEmail(usermail);
		 userpayload.setPassword(pwd);
		 userpayload.setPhone(pnumber);
		 
		Response ress= UserEndPoints.createUser(userpayload);
		ress.then().log().all();
		Assert.assertEquals(ress.getStatusCode(), 200);
	}
	
	@Test(priority=2, dataProvider="UserNames",dataProviderClass=DataProviderUtilities.class)
	public void testDeleteusers(String username) {
		Response res=UserEndPoints.deleteUser(username);
		res.then().log().all();
		Assert.assertEquals(res.statusCode(),200);
	}
}
