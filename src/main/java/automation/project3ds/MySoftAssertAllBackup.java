package automation.project3ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.asserts.IAssert;
import org.testng.collections.Maps;

import automation.project3ds.MySoftAssert.MyIAssert;

/**
 * When an assertion fails, don't throw an exception but record the failure.
 * Calling {@code assertAll()} will cause an exception to be thrown if at least
 * one assertion failed.
 */
public class MySoftAssertAllBackup extends MySoftAssertBackup {
	// LinkedHashMap to preserve the order
	private final Map<AssertionError, IAssert<?>> m_errors = Maps.newLinkedHashMap();
	
	List<MyIAssert<?>> assertCommandList;
	
	public MySoftAssertAllBackup() {
		assertCommandList = new ArrayList<MyIAssert<?>>();
	}
	

	@Override
	protected void doAssert(IAssert<?> a) {
		onBeforeAssert(a);
		try {
			a.doAssert();
			onAssertSuccess(a);
		} catch (AssertionError ex) {
			onAssertFailure(a, ex);
			m_errors.put(ex, a);
		} finally {
			onAfterAssert(a);
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
