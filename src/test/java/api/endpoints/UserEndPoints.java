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
public class UserEndPoints {
	public static ResourceBundle getURL()
	{

		ResourceBundle route=ResourceBundle.getBundle("routes");
		return route;
	}

	public static Response createUser(User payload){

		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when()
				.post(Routes.post_url);

		return res;
	}

	public static Response getUser(String username) {
		Response res=given()
				.pathParam("username", username)
				

				.when()
				.get(Routes.get_url);

		return res;

	}
	
	public static Response updateUser(String username,User payload){

		Response res=given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("username", username)
				.body(payload)

				.when()
				.put(Routes.put_url);

		return res;
	}
	
	public static Response deleteUser(String username) {
		Response res=given()
				.pathParam("username", username)

				.when()
				.delete(Routes.delete_url);

		return res;

	}
}
