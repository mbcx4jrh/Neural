package neural;

import neural.data.DataSource;
import neural.data.InlineDataSource;
import neural.parsec.ast.TestingDef;

public abstract class Tester {

	private TestingDef testingDef;
	private String id;
	private DataSource source;

	public void init(String id, TestingDef def) {
		this.testingDef = def;
		this.id = id;
	}
	
	public void setSource(DataSource source) {
		this.source = source;
	}
	
	public String getId() {
		return id;
	}
	
	public void test(Network network) {
		if (source == null) {
			if (testingDef.getInputData() == null) 
				throw new NeuralException("Cannot find a data source (or inline data)");
			source = new InlineDataSource(testingDef.getInputData());
		}
		for (double[] input: source) {

			double[] output = network.compute(input);
			releaseResult(input, output);
		}
	}
	
	protected abstract void releaseResult(double[] input, double[] output);
}
