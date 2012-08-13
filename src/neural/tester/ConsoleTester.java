package neural.tester;

import java.util.Arrays;

import neural.Tester;
import neural.parsec.ast.TestingDef;

public class ConsoleTester extends Tester {

	public ConsoleTester(TestingDef def) {
		super(def);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void releaseResult(double[] input, double[] output) {
		System.out.println("Input: "+Arrays.toString(input)+"\t Output: "+Arrays.toString(output));
	}

}
