package projeto.api.utils;

import io.restassured.response.Response;

public class BasePage {

	public static void takeScreenshot(Response response) {
	    System.out.println("\nSTATUS CODE -> " + response.getStatusCode() + "\n");
	    System.out.println("RESPONSE BODY -> \n" + response.getBody().asString() + "\n");
	    System.out.println("RESPONSE HEADERS -> \n" + response.getHeaders() + "\n");
	    System.out.println();
	    response.then()
	    		.log().status().log().body();
	}

}
