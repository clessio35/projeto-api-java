package projeto.api.pages;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONObject;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

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
	
	public void validateResponseUserList() {
		System.out.println("VALIDATE RESPONSE");
		response.then().log().status().statusCode(200).log().body();
		List<Map<String, Object>> data = response.jsonPath().getList("data");
		for (Map<String, Object> user : data) {
			String firstName = (String) user.get("first_name");
			assertTrue("The 'first_name' can't be null", firstName != null && !firstName.isEmpty());
			System.out.println("RESULT -> " + firstName);
		}
	}

	public void validateResponseUserPage(String page) {
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
	}

	public void validateResponseSpecificUser(String user) {
		System.out.println("Validate Specific User -> " + user);
		response.then()
			.log().status().statusCode(200)
			.body("data.id", Matchers.equalTo(Integer.parseInt(user)))
			.body("data.email", Matchers.not(Matchers.emptyOrNullString()))
			.body("data.first_name", Matchers.not(Matchers.emptyOrNullString()))
			.body("data.last_name", Matchers.not(Matchers.emptyOrNullString())).log().body();
		String responseBody = response.getBody().asString();
	    System.out.println("Body user validate: " + responseBody);
	}

	public void validateResponseInexistanceUser(String status) {
		System.out.println("Validate Inexistance User");
		int statusCode =  Integer.parseInt(status);
		response.then()
			.log().body().log().status().statusCode(statusCode);
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

	public void requestPOSTMethod(String endpoint) {
		System.out.println("SEND REQUEST POST METHOD -> Endpoint: " + endpoint);
		response = RestAssured.given()
					.log().all().contentType(ContentType.JSON)
					.body(payload().toString())
					.when().post(endpoint);
	}
	
	public void validateResponseUserCreated(String status) {
		System.out.println("Validate response user created.");
		int statusCode =  Integer.parseInt(status);
		response.then()
			.log().body().log().status().statusCode(statusCode)
			.body("id", Matchers.not(Matchers.emptyOrNullString()))
			.body("name", Matchers.not(Matchers.emptyOrNullString()))
			.body("job", Matchers.not(Matchers.emptyOrNullString()));
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

	public void validateReturnResponseInvalidMethod(String statusCode, String name, String job) {
		System.out.println("Validate Return Response With Variation user");
		int sc = Integer.parseInt(statusCode);
		String nm = String.format(name);
		response.then()
			.log().body().statusCode(sc)
			.body("name", Matchers.equalTo(nm))
			.body("job", Matchers.equalTo(job));
	}

	public void requestPUTMethod(String endpoint) {
		System.out.println("Update Method");
		response = RestAssured.given()
					.log().all().contentType(ContentType.JSON)
					.body(payload().toString())
					.when().put(endpoint);
	}

	public void validateResponseUpdateUser() {
		System.out.println("Validate Update Method");
		response.then()
			.log().body().statusCode(200)
			.body("name", Matchers.not(Matchers.emptyOrNullString()))
			.body("job", Matchers.not(Matchers.emptyOrNullString()));
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

	public void validateResponseUpdateWithoutInformation() {
		System.out.println("Validate Update Method");
		response.then()
			.log().body().statusCode(200)
			.body("name", Matchers.not(Matchers.emptyOrNullString()))
			.body("job", Matchers.equalTo(""));
	}

	
}
