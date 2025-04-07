package projeto.api.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;

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
		List<Map<String, Object>> data = response.jsonPath().getList("data");
		for (Map<String, Object> user : data) {
			String firstName = (String) user.get("first_name");
			assertTrue("The 'first_name' can't be null", firstName != null && !firstName.isEmpty());
			System.out.println("RESULT -> " + firstName);
		}
	}

	public void validateResponseUserPage(String page) {
		System.out.println("VALIDATE RESPONSE WITH USER PAGE -> " + page);
		response.then().body("page", Matchers.equalTo(Integer.parseInt(page)));
		List<Map<String, Object>> data = response.jsonPath().getList("data");
		for(Map<String, Object> user : data) {
			String email = user.get("email").toString();
			String firstName = user.get("first_name").toString();
			assertTrue(!email.isEmpty() &&  !firstName.isEmpty());
			System.out.println("RESULT -> FirstName: " + firstName + ", email: " + email);
		}
	}
}
