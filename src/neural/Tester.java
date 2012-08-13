package neural;

import neural.parsec.ast.TestingDef;

public abstract class Tester {

	private TestingDef testingDef;

	public Tester(TestingDef def) {
		this.testingDef = def;
	}
	
	public void test(Network network) {
		for (double[] input: testingDef.getData()) {
			double[] output =new double[input.length];
			network.compute(input, output);
			releaseResult(input, output);
		}
	}
	
	protected abstract void releaseResult(double[] input, double[] output);
}
