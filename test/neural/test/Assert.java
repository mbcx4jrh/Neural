package neural.test;

import org.junit.ComparisonFailure;
import org.junit.Test;

public class Assert {
	
	public void assertEqualWithin(double error, double expected, double test) {
		if (Math.abs(test-expected) > error)
			throw new ComparisonFailure("Not with error of "+error, ""+expected, ""+test);
	}
	
	
	
	@Test public void testAssertEqualWithinPass() {
		assertEqualWithin(0.01, 0.1, 0.1);
		assertEqualWithin(1, 2, 3);
	}
	
	@Test(expected=ComparisonFailure.class) 
	public void testAssertEqualWithinFail() {
		assertEqualWithin(0.01, 0.1, 0);
		assertEqualWithin(0.1, 0, 0.1000001);
	}

}
