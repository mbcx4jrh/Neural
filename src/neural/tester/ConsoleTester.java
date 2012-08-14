package neural.tester;

import java.util.Arrays;

import neural.Tester;
import neural.parsec.ast.TestingDef;

public class ConsoleTester extends Tester {

	
	public void init(TestingDef def) {
		super.init(null, def);
	}

	@Override
	protected void releaseResult(double[] input, double[] output) {
		System.out.println("Input: "+Arrays.toString(input)+"\t Output: "+Arrays.toString(output));
	}

}
