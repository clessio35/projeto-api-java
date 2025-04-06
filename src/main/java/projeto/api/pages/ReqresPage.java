package projeto.api.pages;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ReqresPage {
	
	private Response response;
	
	public void accessApi(String url) {
		RestAssured.baseURI = url;
	}
	
	public void requestGETMethod(String endpoint) {
		response = RestAssured.given()
					.log().all()
					.contentType(ContentType.JSON)
					.when()
					.get(endpoint);
	}
	
	public void validateResponseUserList() {
		List<Map<String, Object>> data = response.jsonPath().getList("data");
		for (Map<String, Object> user : data) {
			String firstName = (String) user.get("first_name");
			assertTrue("O 'first_name' não pode ser nulo ou vazio", firstName != null && !firstName.isEmpty());
		}
	}
}
