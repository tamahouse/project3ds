package automation.project3ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import automation.project3ds.Z2.RequestAuth;
import automation.project3ds.Z2.ResponseAuth;
import automation.project3ds.Z2.ResponseLookup;

public class Action {
	
	
	public static void assertResult(String t_id, Map<String, String> map) throws Exception {
		String finalEciFlag = null;
		String finalEnrolled = null;
		String finalPAResStatus = null;
		String finalSignatureVerification = null;
		String cardholderVerificationMethod = null;
		ResponseLookup response = Z2.getLookupResponse(t_id);
		String aEnrolled = response.getEnrolled();
		String aPAResStatus = response.getPAResStatus();
		String aSignitureVerification = response.getSignatureVerification();
		String aCavv = response.getCavv();
		String aEciFlag = response.getEciFlag();
		String aACSUrl = response.getACSUrl();
		String aPayload = response.getPayload();
		String aErrorNo = response.getErrorNo();
		String aErrorDesc = response.getErrorDesc();
		String aWhiteListStatusSource = response.getWhiteListStatusSource();
		String aWhiteListStatus = response.getWhiteListStatus();
		String Enrolled = map.get("Enrolled");
		String PAResStatus = map.get("PAResStatus");
		String SignitureVerification = map.get("SignitureVerification");
		String Cavv = map.get("Cavv");
		String EciFlag = map.get("EciFlag");
		String ACSUrl = map.get("ACSUrl");
		String Payload = map.get("Payload");
		String ErrorNo = map.get("ErrorNo");
		String ErrorDesc = map.get("ErrorDesc");
		String WhiteListStatusSource = map.get("WhiteListStatusSource");
		String WhiteListStatus = map.get("WhiteListStatus");
		if (EciFlag.equals("value")) {
			finalEciFlag = aEciFlag;
		}
		finalEnrolled = aEnrolled;
		finalPAResStatus = aPAResStatus;
		finalSignatureVerification = aSignitureVerification;

		ResponseAuth response2 = Z2.getAuthResponse(t_id);

		RequestAuth request3 = Z2.getAuthRequest(t_id);

		MySoftAssertAll assertion = new MySoftAssertAll();
		String expected_results_authentication = map.get("expected_results_authentication");
		if (response2 == null) {
			assertion.assertString3DS(expected_results_authentication, "NA", "[expected_results_authentication]");
		} else {
			String aPAResStatus2 = response2.getPAResStatus();
			String aSignitureVerification2 = response2.getSignatureVerification();
			String aCavv2 = response2.getCavv();
			String aEciFlag2 = response2.getEciFlag();
			String aErrorNo2 = response2.getErrorNo();
			String aErrorDesc2 = response2.getErrorDesc();
			String PAResStatus2 = map.get("PAResStatus2");
			String SignitureVerification2 = map.get("SignitureVerification2");
			String Cavv2 = map.get("Cavv2");
			String EciFlag2 = map.get("EciFlag2");
			if (EciFlag2.equals("value")) {
				finalEciFlag = aEciFlag2;
			}
			finalEnrolled = aEnrolled;
			finalPAResStatus = aPAResStatus;
			finalSignatureVerification = aSignitureVerification;
			String ErrorNo2 = map.get("ErrorNo2");
			String ErrorDesc2 = map.get("ErrorDesc2");

			assertion.assertString3DS(aPAResStatus2, PAResStatus2, "[PAResStatus2]");
			assertion.assertString3DS(aSignitureVerification2, SignitureVerification2, "[SignitureVerification2]");
			assertion.assertString3DS(aCavv2, Cavv2, "[Cavv2]");
			assertion.assertString3DS(aEciFlag2, EciFlag2, "[EciFlag2]");
			assertion.assertString3DS(aErrorNo2, ErrorNo2, "[ErrorNo2]");
			assertion.assertString3DS(aErrorDesc2, ErrorDesc2, "[ErrorDesc2]");
		}

		assertion.assertString3DS(aEnrolled, Enrolled, "[Enrolled]");
		assertion.assertString3DS(aPAResStatus, PAResStatus, "[PAResStatus]");
		assertion.assertString3DS(aSignitureVerification, SignitureVerification, "[SignitureVerification]");
		assertion.assertString3DS(aCavv, Cavv, "[Cavv]");
		assertion.assertString3DS(aEciFlag, EciFlag, "[EciFlag]");
		assertion.assertString3DS(aACSUrl, ACSUrl, "[ACSUrl]");
		assertion.assertString3DS(aPayload, Payload, "[Payload]");
		assertion.assertString3DS(aErrorNo, ErrorNo, "[ErrorNo]");
		assertion.assertString3DS(aErrorDesc, ErrorDesc, "[ErrorDesc]");
		assertion.assertString3DS(aWhiteListStatusSource, WhiteListStatusSource, "[WhiteListStatusSource]");
		assertion.assertString3DS(aWhiteListStatus, WhiteListStatus, "[WhiteListStatus]");

		String expected_cardholderVerificationMethod = map.get("cardholderVerificationMethod");
		if (expected_cardholderVerificationMethod.equals("value")) {
			if (finalEciFlag.equals("00") || finalEciFlag.equals("07") || finalEciFlag.equals("")
					|| finalEciFlag.equals("01")) {
				expected_cardholderVerificationMethod = "CVV2";
			} else if ((finalEciFlag.equals("02") || finalEciFlag.equals("05")) && finalEnrolled.equals("Y")
					&& (finalPAResStatus.equals("Y") || finalPAResStatus.equals("C")
							|| finalPAResStatus.equals("A") && finalSignatureVerification.equals("Y"))) {
				expected_cardholderVerificationMethod = "THREEDS";
			} else if ((finalEciFlag.equals("01") || finalEciFlag.equals("06")) && finalEnrolled.equals("Y")
					&& (finalPAResStatus.equals("Y") || finalPAResStatus.equals("C")
							|| finalPAResStatus.equals("A") && finalSignatureVerification.equals("Y"))) {
				expected_cardholderVerificationMethod = "THREEDS_ATTEMPT";
			}
		}

		if (expected_cardholderVerificationMethod.equals("NO_REQUEST")) {
			assertion.assertNull(request3, "[cardholderVerificationMethod]");
		} else if (expected_cardholderVerificationMethod.equals("YES_REQUEST")) {
			assertion.assertNotNull(request3, "[cardholderVerificationMethod]");
		} else if (expected_cardholderVerificationMethod.equals("CVV2")) {
			cardholderVerificationMethod = request3.getcardholderVerificationMethod();
			assertion.assertString3DS(cardholderVerificationMethod, expected_cardholderVerificationMethod,
					"[cardholderVerificationMethod3]");
		} else if (!expected_cardholderVerificationMethod.equals("CVV2")) {
			try {
				cardholderVerificationMethod = request3.getcardholderVerificationMethod();
				String cavv = request3.getCavv();
				String eci = request3.getEci();
				String version = request3.getVersion();
				String directoryServerTransactionId = request3.getDirectoryServerTransactionId();
				System.out.println("cardholderVerificationMethod: " + cardholderVerificationMethod);
				System.out.println("cardholderVerificationMethod - cavv : " + cavv);
				System.out.println("cardholderVerificationMethod - eci : " + eci);
				assertion.assertString3DS(cardholderVerificationMethod, expected_cardholderVerificationMethod,
						"[cardholderVerificationMethod3]");
				assertion.assertNotNull(cavv, "[cavv]");
				assertion.assertString3DS(version, "V2", "[version]");
				assertion.assertNotNull(directoryServerTransactionId, "[directoryServerTransactionId]");
//				assertion.assertString3DS(cavv, aCavv, "[cavv]");
//				assertion.assertString3DS(eci, aEciFlag, "[eci]");
			} catch (Exception e) {
				throw e;
//				assertion.assertString3DS("null", "request is existed", "[requestAuth]");
			}
		}

		System.out.println("-----------");
		assertion.assertAll();
	}
	
	public static void assertResultV1(String t_id, Map<String, String> map) throws Exception {
		ResponseLookup response = Z2.getLookupResponse(t_id);
		ResponseLookup response2 = Z2.getAuthResponseV1(t_id);
		assertResultV1(t_id, map, response, response2);
	}
	
	public static void assertResult_1v5(String t_id, Map<String, String> map) throws Exception {
		ResponseLookup response = Z2.getLookupResponse_1v5(t_id);
		ResponseLookup response2 = Z2.getAuthResponseV1_1v5(t_id);
		assertResultV1(t_id, map, response, response2);
	}
	
	public static void assertResultV1(String t_id, Map<String, String> map, ResponseLookup response, ResponseLookup response2) throws Exception {
		String aEnrolled = response.getEnrolled();
		String aEciFlag = response.getEciFlag();
		String aACSUrl = response.getACSUrl();
		String aPayload = response.getPayload();
		String aErrorNo = response.getErrorNo();
		String aErrorDesc = response.getErrorDesc();
		String aVersion = response.getVersion();
		String Enrolled = map.get("Enrolled");
		String EciFlag = map.get("EciFlag");
		String ACSUrl = map.get("ACSUrl");
		String Payload = map.get("Payload");
		String ErrorNo = map.get("ErrorNo");
		String ErrorDesc = map.get("ErrorDesc");
		
		String version = aVersion.substring(0,1);
	
//		RequestAuth request3 = Z2.getAuthRequest(t_id);

		MySoftAssertAll assertion = new MySoftAssertAll();
		String expected_results_authentication = map.get("expected_results_authentication");
		if (response2 == null) {
			assertion.assertString3DS(expected_results_authentication, "NA", "[expected_results_authentication]");
		} else {
			String aPAResStatus2 = response2.getPAResStatus();
			String aSignitureVerification2 = response2.getSignatureVerification();
			String aCavv2 = response2.getCavv();
			String aEciFlag2 = response2.getEciFlag();
			String aErrorNo2 = response2.getErrorNo();
			String aErrorDesc2 = response2.getErrorDesc();
			String aXid = response2.getXid();
			String PAResStatus2 = map.get("PAResStatus2");
			String SignitureVerification2 = map.get("SignatureVerification2");
			String Cavv2 = map.get("Cavv2");
			String EciFlag2 = map.get("EciFlag2");
			String Xid = map.get("Xid");
			String ErrorNo2 = map.get("ErrorNo2");
			String ErrorDesc2 = map.get("ErrorDesc2");

			assertion.assertString3DS(aPAResStatus2, PAResStatus2, "[PAResStatus2]");
			assertion.assertString3DS(aSignitureVerification2, SignitureVerification2, "[SignitureVerification2]");
			assertion.assertString3DS(aCavv2, Cavv2, "[Cavv2]");
			assertion.assertString3DS(aEciFlag2, EciFlag2, "[EciFlag2]");
			assertion.assertString3DS(aErrorNo2, ErrorNo2, "[ErrorNo2]");
			assertion.assertString3DS(aErrorDesc2, ErrorDesc2, "[ErrorDesc2]");
			assertion.assertString3DS(aXid, Xid, "[Xid]");
		}
		assertion.assertEquals(version, "1","[Version]");
		assertion.assertString3DS(aEnrolled, Enrolled, "[Enrolled]");
		assertion.assertString3DS(aEciFlag, EciFlag, "[EciFlag]");
		assertion.assertString3DS(aACSUrl, ACSUrl, "[ACSUrl]");
		assertion.assertString3DS(aPayload, Payload, "[Payload]");
		assertion.assertString3DS(aErrorNo, ErrorNo, "[ErrorNo]");
		assertion.assertString3DS(aErrorDesc, ErrorDesc, "[ErrorDesc]");

//		String expected_cardholderVerificationMethod = map.get("cardholderVerificationMethod");
//		if (expected_cardholderVerificationMethod.equals("value")) {
//			if (finalEciFlag.equals("00") || finalEciFlag.equals("07") || finalEciFlag.equals("")
//					|| finalEciFlag.equals("01")) {
//				expected_cardholderVerificationMethod = "CVV2";
//			} else if ((finalEciFlag.equals("02") || finalEciFlag.equals("05")) && finalEnrolled.equals("Y")
//					&& (finalPAResStatus.equals("Y") || finalPAResStatus.equals("C")
//							|| finalPAResStatus.equals("A") && finalSignatureVerification.equals("Y"))) {
//				expected_cardholderVerificationMethod = "THREEDS";
//			} else if ((finalEciFlag.equals("01") || finalEciFlag.equals("06")) && finalEnrolled.equals("Y")
//					&& (finalPAResStatus.equals("Y") || finalPAResStatus.equals("C")
//							|| finalPAResStatus.equals("A") && finalSignatureVerification.equals("Y"))) {
//				expected_cardholderVerificationMethod = "THREEDS_ATTEMPT";
//			}
//		}

//		if (expected_cardholderVerificationMethod.equals("NO_REQUEST")) {
//			assertion.assertNull(request3, "[cardholderVerificationMethod]");
//		} else if (expected_cardholderVerificationMethod.equals("YES_REQUEST")) {
//			assertion.assertNotNull(request3, "[cardholderVerificationMethod]");
//		} else if (expected_cardholderVerificationMethod.equals("CVV2")) {
//			cardholderVerificationMethod = request3.getcardholderVerificationMethod();
//			assertion.assertString3DS(cardholderVerificationMethod, expected_cardholderVerificationMethod,
//					"[cardholderVerificationMethod3]");
//		} else if (!expected_cardholderVerificationMethod.equals("CVV2")) {
//			try {
//				cardholderVerificationMethod = request3.getcardholderVerificationMethod();
//				String cavv = request3.getCavv();
//				String eci = request3.getEci();
//				String version = request3.getVersion();
//				String directoryServerTransactionId = request3.getDirectoryServerTransactionId();
//				System.out.println("cardholderVerificationMethod: " + cardholderVerificationMethod);
//				System.out.println("cardholderVerificationMethod - cavv : " + cavv);
//				System.out.println("cardholderVerificationMethod - eci : " + eci);
//				assertion.assertString3DS(cardholderVerificationMethod, expected_cardholderVerificationMethod,
//						"[cardholderVerificationMethod3]");
//				assertion.assertNotNull(cavv, "[cavv]");
//				assertion.assertString3DS(version, "V2", "[version]");
//				assertion.assertNotNull(directoryServerTransactionId, "[directoryServerTransactionId]");
////				assertion.assertString3DS(cavv, aCavv, "[cavv]");
////				assertion.assertString3DS(eci, aEciFlag, "[eci]");
//			} catch (Exception e) {
//				throw e;
////				assertion.assertString3DS("null", "request is existed", "[requestAuth]");
//			}
//		}

		System.out.println("-----------");
		assertion.assertAll();
	}

	public static List<Map<String, String>> getTestData() throws Exception{
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(utility.ConfigFile.filePath);
		Recordset record = connection.executeQuery("SELECT * FROM data WHERE Available = '1'");
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		while (record.next()) {
			Map<String, String> map = new HashMap<String, String>();
			String PAN = record.getField("PAN");
			String testName = record.getField("testName");
			String Enrolled = record.getField("Enrolled");
			String PAResStatus = record.getField("PAResStatus");
			String SignitureVerification = record.getField("SignatureVerification");
			String Cavv = record.getField("Cavv");
			String EciFlag = record.getField("EciFlag");
			String ACSUrl = record.getField("ACSUrl");
			String Payload = record.getField("Payload");
			String ErrorNo = record.getField("ErrorNo");
			String ErrorDesc = record.getField("ErrorDesc");
			String WhiteListStatusSource = record.getField("WhiteListStatusSource");
			String WhiteListStatus = record.getField("WhiteListStatus");
			String cardholderVerificationMethod = record.getField("cardholderVerificationMethod");
			map.put("PAN", PAN);
			map.put("testName", testName);
			map.put("Enrolled", Enrolled);
			map.put("PAResStatus", PAResStatus);
			map.put("SignitureVerification", SignitureVerification);
			map.put("Cavv", Cavv);
			map.put("EciFlag", EciFlag);
			map.put("ACSUrl", ACSUrl);
			map.put("Payload", Payload);
			map.put("ErrorNo", ErrorNo);
			map.put("ErrorDesc", ErrorDesc);
			map.put("WhiteListStatusSource", WhiteListStatusSource);
			map.put("WhiteListStatus", WhiteListStatus);
			map.put("cardholderVerificationMethod", cardholderVerificationMethod);
			String expected_results_authentication = record.getField("expected_results_authentication");
			map.put("expected_results_authentication", expected_results_authentication);
			if (!expected_results_authentication.equals("NA")) {
				String PAResStatus2 = record.getField("PAResStatus2");
				String SignitureVerification2 = record.getField("SignatureVerification2");
				String Cavv2 = record.getField("Cavv2");
				String EciFlag2 = record.getField("EciFlag2");
				String ErrorNo2 = record.getField("ErrorNo2");
				String ErrorDesc2 = record.getField("ErrorDesc2");
				map.put("PAResStatus2", PAResStatus2);
				map.put("SignitureVerification2", SignitureVerification2);
				map.put("Cavv2", Cavv2);
				map.put("EciFlag2", EciFlag2);
				map.put("ErrorNo2", ErrorNo2);
				map.put("ErrorDesc2", ErrorDesc2);
			}
			mapList.add(map);
		}
		connection.close();
		return mapList;
	}
	
	public static List<Map<String, String>> getTestData(String filePath, String sheetName) throws Exception{
		Fillo fillo = new Fillo();
		Connection connection = fillo.getConnection(filePath);
		Recordset record = connection.executeQuery("SELECT * FROM "+sheetName);
		List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
			List <String> fieldNames = record.getFieldNames();
			while (record.next()) {
				Map<String, String> map = new HashMap<String, String>();
				for(String fieldName: fieldNames) {
					String value = record.getField(fieldName);
					map.put(fieldName, value);
				}
				mapList.add(map);   
			}
			
//			Map<String, String> map = new HashMap<String, String>();
//			String PAN = record.getField("PAN");
//			String testName = record.getField("testName");
//			String Enrolled = record.getField("Enrolled");
//			String EciFlag = record.getField("EciFlag");
//			String ACSUrl = record.getField("ACSUrl");
//			String Payload = record.getField("Payload");
//			String ErrorNo = record.getField("ErrorNo");
//			String ErrorDesc = record.getField("ErrorDesc");
////			String cardholderVerificationMethod = record.getField("cardholderVerificationMethod");
//			map.put("PAN", PAN);
//			map.put("testName", testName);
//			map.put("Enrolled", Enrolled);
//			map.put("EciFlag", EciFlag);
//			map.put("ACSUrl", ACSUrl);
//			map.put("Payload", Payload);
//			map.put("ErrorNo", ErrorNo);
//			map.put("ErrorDesc", ErrorDesc);
////			map.put("cardholderVerificationMethod", cardholderVerificationMethod);
//			String expected_results_authentication = record.getField("expected_results_authentication");
//			map.put("expected_results_authentication", expected_results_authentication);
//			if (!expected_results_authentication.equals("NA")) {
//				String PAResStatus2 = record.getField("PAResStatus2");
//				String SignitureVerification2 = record.getField("SignatureVerification2");
//				String Cavv2 = record.getField("Cavv2");
//				String EciFlag2 = record.getField("EciFlag2");
//				String ErrorNo2 = record.getField("ErrorNo2");
//				String ErrorDesc2 = record.getField("ErrorDesc2");
//				String Xid = record.getField("Xid");
//				map.put("PAResStatus2", PAResStatus2);
//				map.put("SignitureVerification2", SignitureVerification2);
//				map.put("Cavv2", Cavv2);
//				map.put("EciFlag2", EciFlag2);
//				map.put("ErrorNo2", ErrorNo2);
//				map.put("ErrorDesc2", ErrorDesc2);
//				map.put("Xid", Xid);
//			}
//			mapList.add(map);
//		}
		connection.close();
		return mapList;
	}
}
