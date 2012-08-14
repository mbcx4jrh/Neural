package neural.tester;

import neural.Tester;
import neural.parsec.ast.TestingDef;

public class MemoryTester extends Tester {
	
	private double[] lastIn;
	private double[] lastOut;

	public MemoryTester(TestingDef def) {
		super(def);
	}

	@Override
	protected void releaseResult(double[] input, double[] output) {
		lastIn = input;
		lastOut = output;
	}
	
	public double[] lastInput() {
		return lastIn;
	}
	
	public double[] lastOutput() {
		return lastOut;
	}

}
