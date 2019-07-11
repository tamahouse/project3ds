package automation.project3ds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Z2 {

	private static Statement getStatement() throws Exception {
		MyTunnel tunnel = new MyTunnel("wallapi");
		Statement statement = tunnel.getStatement("z2");
		return statement;
	}

	public static ResponseLookup getLookupResponse(String t_id) throws Exception {
		String query = "select pl_response from processor_log where t_id = '" + t_id
				+ "' and pl_response like '%ErrorNo%' limit 1";
		ResultSet resultSet = getStatement().executeQuery(query);
		resultSet.next();
		ResponseLookup response = null;
		try {
			String result = resultSet.getString("pl_response");
			String printResult = PrettyPrint.formatXML(result);
			try {
			ExtentTestManager.getTest().info("lookup_response: " + printResult);
			}catch(Exception ignore) {
			}
			XmlMapper xmlMapper = new XmlMapper();
			response = xmlMapper.readValue(result, ResponseLookup.class);
		} catch (SQLException ignore) {
		} catch(Exception e) {
			throw e;
		}
		return response;
	}

	public static ResponseAuth getAuthResponse(String t_id) throws Exception {
		ResultSet resultSet = getStatement().executeQuery("select pl_response from processor_log where t_id = '" + t_id
				+ "' and pl_response like '%ErrorNumber%' limit 1");
		resultSet.next();
		ResponseAuth response = null;
		try {
			String result = resultSet.getString("pl_response");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(result);
			String printResult = PrettyPrint.formatJson(jsonNode);
			try {
			ExtentTestManager.getTest().info("auth_response:<br />" + printResult);
			}catch(Exception ignore) {
				
			}
			jsonNode = jsonNode.get("Payload");
			response = mapper.convertValue(jsonNode, ResponseAuth.class);
		} catch (SQLException ignore) {
		} catch(Exception e) {
			throw e;
		}
		return response;
	}

	public static RequestAuth getAuthRequest(String t_id) throws Exception {
		String query = "select pl_request from processor_log where t_id ='" + t_id
				+ "' and pl_request like '%\"method\":\"AUTH\"%' limit 1";
		ResultSet resultSet = getStatement().executeQuery(query);
		resultSet.next();
		RequestAuth response = null;
		try {
			String result = resultSet.getString("pl_request");
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonNode = mapper.readTree(result);
			String printResult = PrettyPrint.formatJson(jsonNode);
			try {
			ExtentTestManager.getTest().info("auth_quest:<br />" + printResult);
			}catch(Exception ignore) {
				
			}
			jsonNode = jsonNode.path("request").get("ecomReqData");
			System.out.println(jsonNode.toString());
			response = mapper.convertValue(jsonNode, RequestAuth.class);
			;
		} catch (SQLException ignore) {
		} catch(Exception e) {
			throw e;
		}
		return response;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ResponseLookup {
		public String Enrolled;
		public String PAResStatus;
		public String SignatureVerification;
		public String Cavv;
		public String EciFlag;
		public String ACSUrl;
		public String Payload;
		public String ErrorNo;
		public String ErrorDesc;
		public String WhiteListStatusSource = "null";
		public String WhiteListStatus = "null";

		public String getEnrolled() {
			return this.Enrolled;
		}

		public String getPAResStatus() {
			return this.PAResStatus;
		}

		public String getSignatureVerification() {
			return this.SignatureVerification;
		}

		public String getCavv() {
			return this.Cavv;
		}

		public String getEciFlag() {
			return this.EciFlag;
		}

		public String getACSUrl() {
			return this.ACSUrl;
		}

		public String getPayload() {
			return this.Payload;
		}

		public String getErrorNo() {
			return this.ErrorNo;
		}

		public String getErrorDesc() {
			return this.ErrorDesc;
		}

		public String getWhiteListStatusSource() {
			return this.WhiteListStatusSource;
		}

		public String getWhiteListStatus() {
			return this.WhiteListStatus;
		}
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class ResponseAuth {
		public String ErrorNumber = "NA";
		public String ErrorDescription = "NA";
		public JsonNode Payment;

		private String PAResStatus = "";
		private String SignatureVerification = "";
		private String Cavv = "";
		private String EciFlag = "";

		@JsonSetter("Payment")
		public void setPayment(JsonNode Payment) {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode path = Payment.get("ExtendedData");
			if (path.isObject()) {
				Extended extended = mapper.convertValue(path, Extended.class);
				this.PAResStatus = extended.getPAResStatus();
				this.SignatureVerification = extended.getSignatureVerification();
				this.Cavv = extended.getCavv();
				this.EciFlag = extended.getEciFlag();
			}
		}

		@JsonIgnoreProperties(ignoreUnknown = true)
		public static class Extended {
			public String PAResStatus;
			public String SignatureVerification;
			public String CAVV;
			public String ECIFlag;

			public String getPAResStatus() {
				return this.PAResStatus;
			}

			public String getSignatureVerification() {
				return this.SignatureVerification;
			}

			public String getCavv() {
				return this.CAVV;
			}

			public String getEciFlag() {
				return this.ECIFlag;
			}

		}

		public String getPAResStatus() {
			return this.PAResStatus;
		}

		public String getSignatureVerification() {
			return this.SignatureVerification;
		}

		public String getCavv() {
			return this.Cavv;
		}

		public String getEciFlag() {
			return this.EciFlag;
		}

		public String getErrorNo() {
			return this.ErrorNumber;
		}

		public String getErrorDesc() {
			return this.ErrorDescription;
		}

	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class RequestAuth {
		public String cardholderVerificationMethod;
		private String cavv;
		private String eci;
		private String version;
		private String directoryServerTransactionId;
		public JsonNode threeDSecure;

		@JsonSetter("threeDSecure")
		public void setPayment(JsonNode threeDSecure) {
			System.out.println(threeDSecure);
			ObjectMapper mapper = new ObjectMapper();
			ThreeDSecure obj = mapper.convertValue(threeDSecure, ThreeDSecure.class);
			String cavv = obj.getCavv();
			String aav = obj.getAav();
			String eci = obj.getEci();
			String version = obj.getVersion();
			String directoryServerTransactionId = obj.getDirectoryServerTransactionId();
			
//			String cavv = null;
//			String eci = null;
//			String version = null;
//			String directoryServerTransactionId = null;
//			try {
//				cavv = threeDSecure.path("*avv").asText();
////				cavv = cavv.substring(1, cavv.length() - 1);
//			} catch (Exception e) {
//
//			}
//			try {
//				eci = threeDSecure.path("eci").asText();
////				eci = eci.substring(1, eci.length() - 1);
//			} catch (Exception e) {
//
//			}
//			try {
//				version = threeDSecure.path("version").asText();
////				version = version.substring(1, version.length() - 1);
//			} catch (Exception e) {
//
//			}
//			try {
//				directoryServerTransactionId = threeDSecure.path("directoryServerTransactionId").asText();
////				directoryServerTransactionId = directoryServerTransactionId.substring(1, directoryServerTransactionId.length() - 1);
//			} catch (Exception e) {
//
//			}
			this.cavv = cavv;
			if(aav != null) {
				this.cavv = aav;
			}
			this.eci = eci;
			this.version = version;
			this.directoryServerTransactionId = directoryServerTransactionId;
		}
		
		@JsonIgnoreProperties(ignoreUnknown = true)
		private static class ThreeDSecure {
			public String cavv;
			public String aav;
			public String eci;
			public String version;
			public String directoryServerTransactionId;
			
			private String getCavv() {
				return this.cavv;
			}
			
			private String getAav() {
				return this.aav;
			}
			
			private String getEci() {
				return this.eci;
			}

			private String getVersion() {
				return this.version;
			}

			private String getDirectoryServerTransactionId() {
				return this.directoryServerTransactionId;
			}
			
		}

		public String getcardholderVerificationMethod() {
			return this.cardholderVerificationMethod;
		}

		public String getCavv() {
			return this.cavv;
		}

		public String getEci() {
			return this.eci;
		}

		public String getVersion() {
			return this.version;
		}

		public String getDirectoryServerTransactionId() {
			return this.directoryServerTransactionId;
		}
	}

}
