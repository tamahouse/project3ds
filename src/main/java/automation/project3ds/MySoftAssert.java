package automation.project3ds;

import static org.testng.internal.EclipseInterface.ASSERT_LEFT;
import static org.testng.internal.EclipseInterface.ASSERT_MIDDLE;
import static org.testng.internal.EclipseInterface.ASSERT_RIGHT;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.asserts.IAssert;
import org.testng.asserts.IAssertLifecycle;

/**
 * An assert class with various hooks allowing its behavior to be modified by
 * subclasses.
 */

public class MySoftAssert implements IAssertLifecycle {
	

	protected void doAssert(MyIAssert<?> assertCommand) {
		onBeforeAssert(assertCommand);
		try {
			executeAssert(assertCommand);
			onAssertSuccess(assertCommand);
		} catch (AssertionError ex) {
			onAssertFailure(assertCommand, ex);
			throw ex;
		} finally {
			onAfterAssert(assertCommand);
		}
	}

	/**
	 * Run the assert command in parameter. Meant to be overridden by subclasses.
	 */
	@Override
	public void executeAssert(IAssert<?> assertCommand) {
		assertCommand.doAssert();
	}

	/**
	 * Invoked when an assert succeeds. Meant to be overridden by subclasses.
	 */
	@Override
	public void onAssertSuccess(IAssert<?> assertCommand) {

		System.out.println(assertCommand.getMessage() + " expect {" + assertCommand.getActual() + "}  actual {"
				+ assertCommand.getExpected() + "}");
	}
	
	public void onMyAssertSuccess(MyIAssert<?> assertCommand) {

		System.out.println(assertCommand.getMessage() + " expect {" + assertCommand.getActual() + "}"+assertCommand.getAssertType()+"actual {"
				+ assertCommand.getExpected() + "}");
	}

	/**
	 * Invoked when an assert fails. Meant to be overridden by subclasses.
	 * 
	 * @deprecated use onAssertFailure(IAssert assertCommand, AssertionError ex)
	 *             instead of.
	 */
	@Deprecated
	@Override
	public void onAssertFailure(IAssert<?> assertCommand) {
	}

	@Override
	public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
		onAssertFailure(assertCommand);
		System.out.println(assertCommand.getMessage() + " expect {" + assertCommand.getActual() + "} and actual {"
				+ assertCommand.getExpected() + "}");
	}
	
	public void onMyAssertFailure(MyIAssert<?> assertCommand, AssertionError ex) {
		onAssertFailure(assertCommand);
		System.out.println(assertCommand.getMessage() + " expect {" + assertCommand.getActual() + "}"+assertCommand.getAssertType()+"actual {"
				+ assertCommand.getExpected() + "}");
	}

	/**
	 * Invoked before an assert is run. Meant to be overridden by subclasses.
	 */
	@Override
	public void onBeforeAssert(IAssert<?> assertCommand) {
	}

	/**
	 * Invoked after an assert is run. Meant to be overridden by subclasses.
	 */
	@Override
	public void onAfterAssert(IAssert<?> assertCommand) {
	}
	
	public interface MyIAssert<T> extends IAssert{

		  String getMessage();
		  void doAssert();
		  T getActual();
		  T getExpected();
		  String getAssertType();
	}
	
	private static class AssertType{
		static final String EQUAL = " to equal to ";
		static final String NOT_EQUAL = " to not equal to ";
		static final String CONTAIN = " to contain ";
	}

	abstract private static class SimpleAssert<T> implements MyIAssert<T> {
		private final T actual;
		private final T expected;
		private final String m_message;
		private String assertType;

		public SimpleAssert(String message, String assertType) {
			this(null, null, message, assertType);
		}

		public SimpleAssert(T actual, T expected, String assertType) {
			this(actual, expected, null, assertType);
		}

		public SimpleAssert(T actual, T expected, String message, String assertType) {
			this.actual = actual;
			this.expected = expected;
			m_message = message;
			this.assertType = assertType;
		}

		@Override
		public String getMessage() {
			return m_message;
		}

		@Override
		public T getActual() {
			return actual;
		}

		@Override
		public T getExpected() {
			return expected;
		}

		@Override
		public String getAssertType() {
			return assertType;
		}

		@Override
		abstract public void doAssert();
	}

	public void fail(final String message, String assertType, final Throwable realCause) {
		doAssert(new SimpleAssert<Object>(message, assertType) {
			@Override
			public void doAssert() {
				org.testng.Assert.fail(message, realCause);
			}
		});
	}

	public void fail(final String message, String assertType) {
		throw new AssertionError(message);
	}

	public void fail(String assertType) {
		doAssert(new SimpleAssert<Object>(null, assertType) {
			@Override
			public void doAssert() {
				org.testng.Assert.fail();
			}
		});
	}

	private void failNotEqualsCopiedTestngAssert(Object actual, Object expected, String message, String assertType) {
		fail(formatCopiedTestngAssert(actual, expected, message), assertType);
	}

	static String formatCopiedTestngAssert(Object actual, Object expected, String message) {
		String formatted = "";
		if (null != message) {
			formatted = message + " ";
		}

		return formatted + ASSERT_LEFT + expected + ASSERT_MIDDLE + actual + ASSERT_RIGHT;
	}

	

	public <T> void assertEquals(final T actual, final T expected, final String message) {
		doAssert(new SimpleAssert<T>(actual, expected, message, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public <T> void assertEquals3DS(final T actual, final T expected, final String message) {
		doAssert(new SimpleAssert<T>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public <T> void assertEquals(final T actual, final T expected) {
		doAssert(new SimpleAssert<T>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

//  public void assertString (final String actual, final String expected, final String message) {
//    doAssert(new SimpleAssert<String>(actual, expected, message) {
//      @Override
//      public void doAssert() {
//        org.testng.Assert.assertEquals(actual, expected, message);
//      }
//    });
//  }

	public void assertString3DS(final String actual, String expected, final String message) {
		if (expected.equals("blank")) {
			expected = "";
			this.assertEquals(actual, expected, message);
		} else if (expected.equals("value")) {
			this.assertNotNull(actual, message);
//		  this.assertNotEquals(actual, "0", message);
//		  this.assertNotEquals(actual, "07", message);
		} else {
			this.assertEquals(actual, expected, message);
		}

	}

	public <T> void assertBigDecimal(final T actual, final T expected, final String message) {

		doAssert(new SimpleAssert<T>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				BigDecimal actualS = null;
				BigDecimal expectedS = null;
				String assertType = AssertType.EQUAL;
				if (actual instanceof BigDecimal) {
						actualS = (BigDecimal) actual;
				}else if (!(actual instanceof String)) {
					actualS = new BigDecimal((String) actual);
				}

				 if (expected instanceof BigDecimal) {
						expectedS = (BigDecimal) expected;
				}else if (expected instanceof String) {
					expectedS = new BigDecimal((String) expected);
				}

				if ((expected == null) && (actual == null)) {
					return;
				}
				if (expected == null ^ actual == null) {
					failNotEqualsCopiedTestngAssert(actual, expected, message, assertType);
				} else if (expectedS.compareTo(actualS) == 0) {
					return;
				}
				failNotEqualsCopiedTestngAssert(actual, expected, message, assertType);
			}
		});

	}
	
	public <T> void assertContain(final T actualChild, final T expectedParent, final String message) {

		doAssert(new SimpleAssert<T>(actualChild, expectedParent, message, AssertType.CONTAIN) {
			@Override
			public void doAssert() {
				String actual = null;
				String expected = null;
				String assertType = AssertType.CONTAIN;
				if (actualChild instanceof String) {
						actual = (String) actualChild;
				}else if (!(actualChild instanceof String)) {
					actual = String.valueOf(actualChild);
				}

				 if (expectedParent instanceof String) {
						expected = (String) expectedParent;
				}else if (expected instanceof String) {
					expected = String.valueOf(expectedParent);
				}

				if ((expected == null) && (actual == null)) {
					return;
				}
				if (expected == null ^ actual == null) {
					failNotEqualsCopiedTestngAssert(actual, expected, message, assertType);
				} else if (expected.contains(actual)) {
					return;
				}
				failNotEqualsCopiedTestngAssert(actual, expected, message, assertType);
			}
		});

	}

//  public void assertContains (final String actual, final String expected, final String message) {
//	    doAssert(new SimpleAssert<String>(actual, expected, message,  AssertType.EQUAL) {
//	      @Override
//	      public void doAssert() {
//	        org.testng.Assert.assertTrue(actual.contains(expected), message);
//	      }
//	     
//	    });
//	  }
  
	  public void assertEquals(final String actual, final String expected) {
	    doAssert(new SimpleAssert<String>(actual, expected, AssertType.EQUAL) {
	      @Override
	      public void doAssert() {
	        org.testng.Assert.assertEquals(actual, expected);
	      }
	    });
	  }

	public void assertEquals(final double actual, final double expected, final double delta, final String message) {
		doAssert(new SimpleAssert<Double>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, delta, message);
			}
		});
	}

	public void assertEquals(final double actual, final double expected, final double delta) {
		doAssert(new SimpleAssert<Double>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, delta);
			}
		});
	}

	public void assertEquals(final float actual, final float expected, final float delta, final String message) {
		doAssert(new SimpleAssert<Float>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, delta, message);
			}
		});
	}

	public void assertEquals(final float actual, final float expected, final float delta) {
		doAssert(new SimpleAssert<Float>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, delta);
			}
		});
	}

	public void assertEquals(final long actual, final long expected, final String message) {
		doAssert(new SimpleAssert<Long>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public void assertEquals(final long actual, final long expected) {
		doAssert(new SimpleAssert<Long>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

	public void assertBoolean(final boolean actual, final boolean expected, final String message) {
		doAssert(new SimpleAssert<Boolean>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public void assertEquals(final boolean actual, final boolean expected) {
		doAssert(new SimpleAssert<Boolean>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

	public void assertEquals(final byte actual, final byte expected, final String message) {
		doAssert(new SimpleAssert<Byte>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public void assertEquals(final byte actual, final byte expected) {
		doAssert(new SimpleAssert<Byte>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

	public void assertEquals(final char actual, final char expected, final String message) {
		doAssert(new SimpleAssert<Character>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public void assertEquals(final char actual, final char expected) {
		doAssert(new SimpleAssert<Character>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

	public void assertEquals(final short actual, final short expected, final String message) {
		doAssert(new SimpleAssert<Short>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public void assertEquals(final short actual, final short expected) {
		doAssert(new SimpleAssert<Short>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

	public void assertEquals(final int actual, final int expected, final String message) {
		doAssert(new SimpleAssert<Integer>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public void assertEquals(final int actual, final int expected) {
		doAssert(new SimpleAssert<Integer>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

	public void assertNotNull(final Object object) {
		doAssert(new SimpleAssert<Object>(object, null, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNotNull(object);

			}
		});
	}

	public void assertNotNull(final Object object, final String message) {
		doAssert(new SimpleAssert<Object>(object, null, message, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNotNull(object, message);
			}
		});
	}

	public void assertNull(final Object object) {
		doAssert(new SimpleAssert<Object>(object, null, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNull(object);
			}
		});
	}

	public void assertNull(final Object object, final String message) {
		doAssert(new SimpleAssert<Object>(object, null, message, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNull(object, message);
			}
		});
	}

	public void assertSame(final Object actual, final Object expected, final String message) {
		doAssert(new SimpleAssert<Object>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertSame(actual, expected, message);
			}
		});
	}

	public void assertSame(final Object actual, final Object expected) {
		doAssert(new SimpleAssert<Object>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertSame(actual, expected);
			}
		});
	}

	public void assertNotSame(final Object actual, final Object expected, final String message) {
		doAssert(new SimpleAssert<Object>(actual, expected, message,  AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNotSame(actual, expected, message);
			}
		});
	}

	public void assertNotSame(final Object actual, final Object expected) {
		doAssert(new SimpleAssert<Object>(actual, expected, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNotSame(actual, expected);
			}
		});
	}

	public void assertEquals(final Collection<?> actual, final Collection<?> expected) {
		doAssert(new SimpleAssert<Collection<?>>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

	public void assertEquals(final Collection<?> actual, final Collection<?> expected, final String message) {
		doAssert(new SimpleAssert<Collection<?>>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public void assertEquals(final Object[] actual, final Object[] expected, final String message) {
		doAssert(new SimpleAssert<Object[]>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public void assertEqualsNoOrder(final Object[] actual, final Object[] expected, final String message) {
		doAssert(new SimpleAssert<Object[]>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEqualsNoOrder(actual, expected, message);
			}
		});
	}

	public void assertEquals(final Object[] actual, final Object[] expected) {
		doAssert(new SimpleAssert<Object[]>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

	public void assertEqualsNoOrder(final Object[] actual, final Object[] expected) {
		doAssert(new SimpleAssert<Object[]>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEqualsNoOrder(actual, expected);
			}
		});
	}

	public void assertEquals(final byte[] actual, final byte[] expected) {
		doAssert(new SimpleAssert<byte[]>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

	public void assertEquals(final byte[] actual, final byte[] expected, final String message) {
		doAssert(new SimpleAssert<byte[]>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public void assertEquals(final Set<?> actual, final Set<?> expected) {
		doAssert(new SimpleAssert<Set<?>>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

	public void assertEquals(final Set<?> actual, final Set<?> expected, final String message) {
		doAssert(new SimpleAssert<Set<?>>(actual, expected, message,  AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected, message);
			}
		});
	}

	public void assertEquals(final Map<?, ?> actual, final Map<?, ?> expected) {
		doAssert(new SimpleAssert<Map<?, ?>>(actual, expected, AssertType.EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertEquals(actual, expected);
			}
		});
	}

	public void assertNotEquals(final Object actual, final Object expected, final String message) {
		doAssert(new SimpleAssert<Object>(actual, expected, message,  AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, message);
			}
		});
	}

	public void assertNotEquals(final Object actual, final Object expected) {
		doAssert(new SimpleAssert<Object>(actual, expected, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNotEquals(actual, expected);
			}
		});
	}
	
	  private static void assertNotEqualsCopiedFromTestngAssert(Object actual1, Object actual2, String message) {
		    boolean fail;
		    try {
		      Assert.assertEquals(actual1, actual2);
		      fail = true;
		    } catch (AssertionError e) {
		      fail = false;
		    }

		    if (fail) {
		      Assert.fail(message + " expected not ["+ actual2+"] to but still found [" +actual1 +"]");
		    }
		
		  }
	  
	  private static void assertNotEqualsCopiedFromTestngAssert(Object actual1, Object actual2) {
		  assertNotEqualsCopiedFromTestngAssert(actual1, actual2, null);
		  }
	  
	  public static void assertNotEqualsCopiedFromTestngAssert(double actual1, double actual2, double delta, String message) {
		    boolean fail;
		    try {
		      Assert.assertEquals(actual1, actual2, delta, message);
		      fail = true;
		    } catch (AssertionError e) {
		      fail = false;
		    }

		    if (fail) {
		    	Assert.fail(message + " expected not ["+ actual2+"] to but still found [" +actual1 +"]");
		    }
		  }
	  
	  public static void assertNotEqualsCopiedFromTestngAssert(double actual1, double actual2, double delta) {
		  assertNotEqualsCopiedFromTestngAssert(actual1, actual2, delta, null);
		  }
	  
	  public static void assertNotEqualsCopiedFromTestngAssert(float actual1, float actual2, float delta, String message) {
		    boolean fail;
		    try {
		      Assert.assertEquals(actual1, actual2, delta, message);
		      fail = true;
		    } catch (AssertionError e) {
		      fail = false;
		    }

		    if (fail) {
		    	Assert.fail(message + " expected not ["+ actual2+"] to but still found [" +actual1 +"]");
		    }
		  }
	  
	  public static void assertNotEqualsCopiedFromTestngAssert(float actual1, float actual2, float delta) {
		  assertNotEqualsCopiedFromTestngAssert(actual1, actual2, delta, null);
		  }

	public void assertNotEquals(final String actual, final String expected, final String message) {
		doAssert(new SimpleAssert<String>(actual, expected, message,  AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, message);
			}
		});
	}

	public void assertNotEquals(final String actual, final String expected) {
		doAssert(new SimpleAssert<String>(actual, expected, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected);
			}
		});
	}

	public void assertNotEquals(final long actual, final long expected, final String message) {
		doAssert(new SimpleAssert<Long>(actual, expected, message,  AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, message);
			}
		});
	}

	public void assertNotEquals(final long actual, final long expected) {
		doAssert(new SimpleAssert<Long>(actual, expected, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNotEquals(actual, expected);
			}
		});
	}

	public void assertNotEquals(final boolean actual, final boolean expected, final String message) {
		doAssert(new SimpleAssert<Boolean>(actual, expected, message,  AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, message);
			}
		});
	}

	public void assertNotEquals(final boolean actual, final boolean expected) {
		doAssert(new SimpleAssert<Boolean>(actual, expected, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNotEquals(actual, expected);
			}
		});
	}

	public void assertNotEquals(final byte actual, final byte expected, final String message) {
		doAssert(new SimpleAssert<Byte>(actual, expected, message,  AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, message);
			}
		});
	}

	public void assertNotEquals(final byte actual, final byte expected) {
		doAssert(new SimpleAssert<Byte>(actual, expected, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNotEquals(actual, expected);
			}
		});
	}

	public void assertNotEquals(final char actual, final char expected, final String message) {
		doAssert(new SimpleAssert<Character>(actual, expected, message,  AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, message);
			}
		});
	}

	public void assertNotEquals(final char actual, final char expected) {
		doAssert(new SimpleAssert<Character>(actual, expected, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNotEquals(actual, expected);
			}
		});
	}

	public void assertNotEquals(final short actual, final short expected, final String message) {
		doAssert(new SimpleAssert<Short>(actual, expected, message,  AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, message);
			}
		});
	}

	public void assertNotEquals(final short actual, final short expected) {
		doAssert(new SimpleAssert<Short>(actual, expected, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				org.testng.Assert.assertNotEquals(actual, expected);
			}
		});
	}

	public void assertNotEquals(final int actual, final int expected, final String message) {
		doAssert(new SimpleAssert<Integer>(actual, expected, message,  AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, message);
			}
		});
	}

	public void assertNotEquals(final int actual, final int expected) {
		doAssert(new SimpleAssert<Integer>(actual, expected, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected);
			}
		});
	}

	public void assertNotEquals(final float actual, final float expected, final float delta, final String message) {
		doAssert(new SimpleAssert<Float>(actual, expected, message,  AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, delta, message);
			}
		});
	}

	public void assertNotEquals(final float actual, final float expected, final float delta) {
		doAssert(new SimpleAssert<Float>(actual, expected, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, delta);
			}
		});
	}

	public void assertNotEquals(final double actual, final double expected, final double delta, final String message) {
		doAssert(new SimpleAssert<Double>(actual, expected, message,  AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, delta, message);
			}
		});
	}

	public void assertNotEquals(final double actual, final double expected, final double delta) {
		doAssert(new SimpleAssert<Double>(actual, expected, AssertType.NOT_EQUAL) {
			@Override
			public void doAssert() {
				assertNotEqualsCopiedFromTestngAssert(actual, expected, delta);
			}
		});
	}

	protected void doAssert(IAssert<?> assertCommand, ITestContext context) {
		onBeforeAssert(assertCommand);
		try {
			executeAssert(assertCommand);
			onAssertSuccess(assertCommand);
		} catch (AssertionError ex) {
			onAssertFailure(assertCommand, ex);
			throw ex;
		} finally {
			onAfterAssert(assertCommand);
		}
	}

}
