package neural.test;

import org.junit.ComparisonFailure;
import org.junit.Test;

public class Assert {

	static public void assertEqualWithin(double error, double expected, double test) {
		if (Math.abs(test - expected) > error)
			throw new ComparisonFailure("Not within error of " + error + " (" + Math.abs(test - expected) + ")", ""
					+ expected, "" + test); 
	}

	static public void assertEqualWithin(double error, double[] expected, double[] test) {
		for (int i = 0; i < expected.length; i++)
			assertEqualWithin(error, expected[i], test[i]);
	}

	@Test
	public void testArrayEquals() {
		assertEqualWithin(0.1, new double[] { 0.0, 2.0 }, new double[] { 0.1, 1.95 });
	}

	@Test
	public void testAssertEqualWithinPass() {
		assertEqualWithin(0.01, 0.1, 0.1);
		assertEqualWithin(1, 2, 3);
		assertEqualWithin(0.1, 2.0, 1.91);
	}

	@Test(expected = ComparisonFailure.class)
	public void testAssertEqualWithinFail() {
		assertEqualWithin(0.01, 0.1, 0);
		assertEqualWithin(0.1, 0, 0.1000001);
	}

}
