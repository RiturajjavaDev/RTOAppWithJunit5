package com.transport.rto.genrator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
/**
 * 
 * this class is used to write unit  test case for IdGenrator class
 * @author Rituraj
 *
 */
public class IdGenratorTest {

	/**
	 * this method is used to test whether registration genrate is valid or not 
	 */
	@Test
	public void getRegNumber() {
		String regNumber = IdGenrator.getRegNumber();
		assertNotNull(regNumber);
		if (regNumber.length() != 7) {
			fail();
		}
	}
	
	/**
	 * this mehtod is  used to test for private method that generate two digit number  
	 * @throws Exception
	 */
	@Test
	public void randomIntTest() throws Exception {
		Class<IdGenrator> cls = IdGenrator.class;
		Method method = cls.getDeclaredMethod("randomInt");
		method.setAccessible(true);
		Object invoke = method.invoke(cls);
		if (invoke instanceof Integer) {
			Integer it = (Integer) invoke;
			assertNotNull(it);
		} else {
			fail();
		}
	}

	/**
	 * this method is used to test private method that write string based on length 
	 * @throws Exception
	 */
	@Test
	public void randomStringTest() throws Exception {
		Class<IdGenrator> cls = IdGenrator.class;
		Method method = cls.getDeclaredMethod("randomString", new Class[] { Integer.class });
		method.setAccessible(true);
		Object invoke = method.invoke(cls, 3);
		if (invoke instanceof String) {
			String str = (String) invoke;
			assertNotNull(str);
			if (str.length() != 3) {
				fail();
			}
		} else {
			fail();
		}

	}

}
