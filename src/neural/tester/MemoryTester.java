package neural.tester;

import neural.Tester;
import neural.parsec.ast.TestingDef;

public class MemoryTester extends Tester {
		
	private double[] lastIn;
	private double[] lastOut;

	public void init(String id, TestingDef def) {
		super.init(id, def);
		MemoryTesterStore.getInstance().store(this);
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
