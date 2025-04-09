package projeto.api.pages;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import projeto.api.utils.BasePage;

public class ReqresPage {
	
	private Response response;
	
	public void accessApi(String url) {
		System.out.println("ACCESS API -> " + url);
		RestAssured.baseURI = url;
	}
	
	public void requestGETMethod(String endpoint) {
		System.out.println("GET METHOD -> " + endpoint);
		response = RestAssured.given()
					.log().all()
					.contentType(ContentType.JSON)
					.when()
					.get(endpoint);
	}
	
	public void validateResponseUserList() throws IOException {
		System.out.println("VALIDATE RESPONSE");
		response.then().log().status().statusCode(200).log().body();
		List<Map<String, Object>> data = response.jsonPath().getList("data");
		for (Map<String, Object> user : data) {
			String firstName = (String) user.get("first_name");
			assertTrue("The 'first_name' can't be null", firstName != null && !firstName.isEmpty());
			System.out.println("RESULT -> " + firstName);
		}
		BasePage.takeScreenshot(response, "Validate Response User List");
	}

	public void validateResponseUserPage(String page) throws IOException {
		System.out.println("VALIDATE RESPONSE WITH USER PAGE -> " + page);
		response.then().log().status().statusCode(200)
		.body("page", Matchers.equalTo(Integer.parseInt(page))).log().body();
		List<Map<String, Object>> data = response.jsonPath().getList("data");
		for(Map<String, Object> user : data) {
			String email = user.get("email").toString();
			String firstName = user.get("first_name").toString();
			assertTrue(!email.isEmpty() &&  !firstName.isEmpty());
			System.out.println("RESULT -> FirstName: " + firstName + ", email: " + email);
		}
		BasePage.takeScreenshot(response, "Response user page");
	}

	public void validateResponseSpecificUser(String user) throws IOException {
		System.out.println("Validate Specific User -> " + user);
		response.then()
			.log().status().statusCode(200)
			.body("data.id", Matchers.equalTo(Integer.parseInt(user)))
			.body("data.email", Matchers.not(Matchers.emptyOrNullString()))
			.body("data.first_name", Matchers.not(Matchers.emptyOrNullString()))
			.body("data.last_name", Matchers.not(Matchers.emptyOrNullString())).log().body();
		String responseBody = response.getBody().asString();
	    System.out.println("Body user validate: " + responseBody);
	    BasePage.takeScreenshot(response, "Specific User");
	}

	public void validateResponseInexistanceUser(String status) throws IOException {
		System.out.println("Validate Inexistant User");
		int statusCode =  Integer.parseInt(status);
		response.then()
			.log().body().log().status().statusCode(statusCode);
		BasePage.takeScreenshot(response, "Response Inexistant");
	}

	public JSONObject payload() {
		System.out.println("DATA FAKE");
		Faker fake = new Faker();
		HashMap<String, Object> user = new HashMap<String, Object>();
		user.put("name", fake.name().fullName());
		user.put("job", fake.job().title());
		JSONObject json = new JSONObject(user);
		return json;
	}
	public JSONObject login() {
		System.out.println("DATA FAKE");
		Faker fake = new Faker();
		HashMap<String, Object> user = new HashMap<String, Object>();
		user.put("email", fake.internet().emailAddress());
		user.put("password", fake.internet().password(10, 12));
		JSONObject json = new JSONObject(user);
		return json;
	}

	public void requestPOSTMethod(String endpoint) {
		System.out.println("SEND REQUEST POST METHOD -> Endpoint: " + endpoint);
		if(endpoint.trim().equals("/login")) {
			response = RestAssured.given()
					.log().all().contentType(ContentType.JSON)
					.body(login().toString())
					.when().post(endpoint);		
		}else {
			response = RestAssured.given()
					.log().all().contentType(ContentType.JSON)
					.body(payload().toString())
					.when().post(endpoint);			
		}
	}
	
	public void validateResponseUserCreated(String status) throws IOException {
		System.out.println("Validate response user created.");
		int statusCode =  Integer.parseInt(status);
		response.then()
			.log().body().log().status().statusCode(statusCode)
			.body("id", Matchers.not(Matchers.emptyOrNullString()))
			.body("name", Matchers.not(Matchers.emptyOrNullString()))
			.body("job", Matchers.not(Matchers.emptyOrNullString()));
		BasePage.takeScreenshot(response, "User created");
	}
	
	public void requestPOSTMethodInvalidMethod(String endpoint, String name, String job) {
		System.out.println("SEND REQUEST POST METHOD -> Endpoint: " + endpoint);
		HashMap<String, Object> user = new HashMap<String, Object>();
		user.put("name", name);
		user.put("job", job);
		JSONObject json = new JSONObject(user);
		response = RestAssured.given()
					.log().all().contentType(ContentType.JSON)
					.body(json.toString())
					.when().post(endpoint);
	}

	public void validateReturnResponseInvalidMethod(String statusCode, String name, String job) throws IOException {
		System.out.println("Validate Return Response With Variation user");
		int sc = Integer.parseInt(statusCode);
		String nm = String.format(name);
		response.then()
			.log().body().statusCode(sc)
			.body("name", Matchers.equalTo(nm))
			.body("job", Matchers.equalTo(job));
		BasePage.takeScreenshot(response, "Response Invalid method");
	}

	public void requestPUTMethod(String endpoint) {
		System.out.println("Update Method");
		response = RestAssured.given()
					.log().all().contentType(ContentType.JSON)
					.body(payload().toString())
					.when().put(endpoint);
	}

	public void validateResponseUpdateUser() throws IOException {
		System.out.println("Validate Update Method");
		response.then()
			.log().body().statusCode(200)
			.body("name", Matchers.not(Matchers.emptyOrNullString()))
			.body("job", Matchers.not(Matchers.emptyOrNullString()));
		BasePage.takeScreenshot(response, "Update User");
	}
	
	public void requestPUTMethodWithoutInformation(String endpoint) {
		System.out.println("Update Method without job");
		Faker fake = new Faker();
		HashMap<String, Object> user = new HashMap<String, Object>();
		user.put("name", fake.name().fullName());
		user.put("job", "");
		JSONObject json = new JSONObject(user);
		response = RestAssured.given()
					.log().all().contentType(ContentType.JSON)
					.body(json.toString())
					.when().put(endpoint);
	}

	public void validateResponseUpdateWithoutInformation() throws IOException {
		System.out.println("Validate Update Method");
		response.then()
			.log().body().statusCode(200)
			.body("name", Matchers.not(Matchers.emptyOrNullString()))
			.body("job", Matchers.equalTo(""));
		BasePage.takeScreenshot(response, "Update Without information");
	}

	public void requestDELETEMethod(String endpoint) {
		System.out.println("Request DELETE Method");
		response = RestAssured.given()
			.log().all().contentType(ContentType.JSON)
			.when().delete(endpoint);
	}

	public void validateReturnRequestDelete(String status) throws IOException {
		System.out.println("Validate DELETE Method");
		int sc = Integer.parseInt(status);
		response.then()
			.log().body().statusCode(sc);
		BasePage.takeScreenshot(response, "Delete");
	}

	public void validateResponseLoginUnsuccessfull(String status, String msg) throws IOException {
		System.out.println("Validate Login access");
		response.then()
			.log().body().statusCode(400);
		String error = response.jsonPath().getString("error");
		System.out.println("Error captured: " + error);
		Assert.assertEquals(error, msg);
		BasePage.takeScreenshot(response, "Login unsuccessfull");
	}

	public void requestPOSTMethodLoginData(String endpoint) {
		System.out.println("Request POST Login data");
		HashMap<String, Object> login = new HashMap<String, Object>();
		login.put("email", "eve.holt@reqres.in");
		login.put("password", "cityslicka");
		JSONObject json = new JSONObject(login);
		response = RestAssured.given()
			.log().all().when().contentType(ContentType.JSON)
			.body(json.toString()).post(endpoint);
		}

	public void validateResponseLoginAccess() throws IOException {
		System.out.println("Validate Login Access");
		String token = response.jsonPath().getString("token");
		System.out.println("TOKEN: " + token);
		response.then().log().status().statusCode(200)
			.body("token", Matchers.equalTo(token));
		BasePage.takeScreenshot(response, "Login access");
	}
	

	
}
