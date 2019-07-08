package automation.project3ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.Reporter;
import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

/**
 * When an assertion fails, don't throw an exception but record the failure.
 * Calling {@code assertAll()} will cause an exception to be thrown if at least
 * one assertion failed.
 */
public class MySoftAssertAll extends MySoftAssert {
	// LinkedHashMap to preserve the order
	private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
	
	List<Object> actualList;
	List<Object> expectedList;
	List<Object> messageList;
	List<Object> resultList;
	String cardNumber;
	
	public MySoftAssertAll() {
		this.actualList = new ArrayList<Object>();
		this.expectedList = new ArrayList<Object>();
		this.messageList = new ArrayList<Object>();
		this.resultList = new ArrayList<Object>();
	}
	
	public MySoftAssertAll(String cardNumber) {
		this.actualList = new ArrayList<Object>();
		this.expectedList = new ArrayList<Object>();
		this.messageList = new ArrayList<Object>();
		this.resultList = new ArrayList<Object>();
		this.cardNumber = cardNumber;
	}

	@Override
	protected void doAssert(IAssert<?> a) {
		onBeforeAssert(a);
		try {
			a.doAssert();
			onAssertSuccess(a);
			  resultList.add("passed");
			  Reporter.getCurrentTestResult().getTestContext().setAttribute("resultList"+cardNumber, resultList);
		} catch (AssertionError ex) {
			onAssertFailure(a, ex);
			m_errors.put(ex, a);
			  resultList.add("failed");
			  Reporter.getCurrentTestResult().getTestContext().setAttribute("resultList"+cardNumber, resultList);
		} finally {
			onAfterAssert(a);
			actualList.add(a.getActual());
			expectedList.add(a.getExpected());
			messageList.add(a.getMessage());
			Reporter.getCurrentTestResult().getTestContext().setAttribute("actualList"+cardNumber, actualList);
			Reporter.getCurrentTestResult().getTestContext().setAttribute("expectedList"+cardNumber, expectedList);
			Reporter.getCurrentTestResult().getTestContext().setAttribute("messageList", messageList);
		}
	}

	public void assertAll() {
		if (!m_errors.isEmpty()) {
			StringBuilder sb = new StringBuilder("The following asserts failed:");
			boolean first = true;
			for (Map.Entry<AssertionError, IAssert<?>> ae : m_errors.entrySet()) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}
				sb.append("\n\t");
				sb.append(ae.getKey().getMessage());
			}
			throw new AssertionError(sb.toString());
		}
	}

}
