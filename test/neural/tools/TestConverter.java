package neural.tools;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class TestConverter {

	
	
	@Test
	public void testDoubleToBoolean() { 
		double[] src = new double[] { 1.0, 0.0, 0.8, -1.0, 0.1 };
		boolean[] tst = new boolean[] { true, false, true, false, true };
		assertEquals(Arrays.toString(tst), Arrays.toString(Converter.doubleToBoolean(src)));
		System.out.println(Arrays.toString(tst));
	}
}
