package neural.tools;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestWindowedData {
	
	
	@Test
	public void testDataAdding() {
		WindowedData window = new WindowedData(5);
		
		for (int i=0; i<20; i++) {
			window.addData(new double[]{ i }, new double[]{ i*2});
		}
		
		double[][] input = window.getInputData();
		assertEquals(5, input.length);
		
		double[][] output = window.getOutputData();
		assertEquals(5, output.length);
		
		assertEquals(15, input[0][0], 0.1);
		assertEquals(30, output[0][0], 0.1);
		assertEquals(19, input[4][0], 0.1);
	}

}
