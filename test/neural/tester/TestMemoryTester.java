package neural.tester;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class TestMemoryTester {
	
	private double[] in1 = { 0.0, 1.0, 2.0 };
	private double[] in2 = { 0.0, -1.0, -2.0 };
	private double[] in3 = { 1.0, 1.0, 2.0 };

	private double[] out1 = { 0.0, -1.0, 2.0 };
	private double[] out2 = { 0.0, 1.0, -2.0 };
	private double[] out3 = { 0.0, -1.0, -2.0 };

	
	@Test 
	public void testStoreAndRetrieve() {
		MemoryTester tester = MemoryTester.storeMemoryTester("xor1", null);
		
		tester.releaseResult(in1, out1);
		
		tester = MemoryTester.getMemoryTester("xor1");
		assertEquals(Arrays.toString(tester.lastInput()), Arrays.toString(in1));
		assertEquals(Arrays.toString(tester.lastOutput()), Arrays.toString(out1));
		
		tester.releaseResult(in2, out2);
		assertEquals(Arrays.toString(tester.lastInput()), Arrays.toString(in2));
		assertEquals(Arrays.toString(tester.lastOutput()), Arrays.toString(out2));
		
		tester.releaseResult(in3, out3);
		assertEquals(Arrays.toString(tester.lastInput()), Arrays.toString(in3));
		assertEquals(Arrays.toString(tester.lastOutput()), Arrays.toString(out3));
	}

}
