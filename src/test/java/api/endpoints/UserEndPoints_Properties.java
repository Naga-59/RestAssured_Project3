package api.endpoints;
import org.testng.annotations.Test;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.util.*;
import org.json.*;
import java.io.*;
public class UserEndPoints_Properties {

	//private static ResourceBundle getURL;

	


	public static Response createUser(User payload)
	{

		//properties file
	String post_urls= UserEndPoints.getURL().getString("post_url");

		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				
				.post(post_urls);

		return res;
	}

	public static Response getUser(String username) {
		
		String get_urls= UserEndPoints.getURL().getString("get_url");
		Response res=given()
				.pathParam("username", username)


				.when()
				.get(get_urls);

		return res;

	}

	public static Response updateUser(String username,User payload){

		String put_urls= UserEndPoints.getURL().getString("put_url");
		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)

				.when()
				.put(put_urls);

		return res;
	}

	public static Response deleteUser(String username) {
		
		String delete_urls= UserEndPoints.getURL().getString("delete_url");
		Response res=given()
				.pathParam("username", username)

				.when()
				.delete(delete_urls);

		return res;

	}
}
