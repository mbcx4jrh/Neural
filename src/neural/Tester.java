package neural;

import neural.parsec.ast.TestingDef;

public abstract class Tester {

	private TestingDef testingDef;
	private String id;

	public void init(String id, TestingDef def) {
		this.testingDef = def;
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void test(Network network) {
		for (double[] input: testingDef.getInputData()) {
			double[] output =new double[input.length];
			network.compute(input, output);
			releaseResult(input, output);
		}
	}
	
	protected abstract void releaseResult(double[] input, double[] output);
}
