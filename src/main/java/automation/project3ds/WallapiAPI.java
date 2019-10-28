package automation.project3ds;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.response.Response;

public class WallapiAPI {
	
	static String host = AnnotationPage.P2Endpoind;
	static String x_api_key = "V6HqrLtlRdQfVx3hV6HqrLtlRdQfVx3hCwkTmi5UvnChTugp4CwkTmi5UvnChTug";
	static String timestamp = AnnotationPage.timestamp();
	static String reference = "t"+timestamp;
	static String uid = "u"+timestamp;
	static String options = "[]";
	
	public static Response postPaymentToP2(String shortcode,String amount, String cu_code, String description, String version, String co_code) throws Exception {
		String query = host + "/api/v1/payments";
		System.out.println(query);
		ObjectNode node= JsonNodeFactory.instance.objectNode();
		node.put("reference", reference);
		node.put("shortname", shortcode);
		node.put("amount", amount);
		node.put("currency", cu_code);
		node.put("description", description);
		node.put("version", version);
		node.put("country", co_code);
		node.put("user_id", uid);
		node.put("options", options);
		ObjectMapper mapper = new ObjectMapper();
		String pretty = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
		System.out.println(pretty);
		Response responseBody = given().log().parameters()
				.header("Content-Type", "application/json")
				.header("x-api-key",x_api_key)
				.body(node.toString())
				.post(query);
		responseBody.prettyPrint();
		return responseBody;
	}

}
