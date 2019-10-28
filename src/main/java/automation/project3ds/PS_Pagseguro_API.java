package automation.project3ds;

import org.openqa.selenium.By;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PS_Pagseguro_API {
	
	static String token = "ED39055B0AA449399A3CC04656923819";
	
	public static void setStatus(String transactionCode, String status) {
		String query = "https://sandbox.api.pagseguro.com/digital-payments/v1/transactions/"+transactionCode+"/status";
		System.out.println(query);
		Response responseBody = given().log().headers().log().body()
				.header("Authorization", token)
				.header("Content-Type","application/json")
				.body("{ \"status_to\": \""+status+"\" }").patch(query);
		responseBody.prettyPrint();
	}
	
}
