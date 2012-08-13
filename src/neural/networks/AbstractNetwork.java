package neural.networks;

import neural.Network;
import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.TestingDef;
import neural.parsec.ast.TrainingDef;

public abstract class AbstractNetwork implements Network {

	private String name;
	private String type;
	private String propertiesFilename;
	private TrainingDef trainingDef;
	private TestingDef testingDef;

	@Override
	public String getName() {
		return name;
	}

	public String getPropertiesFilename() {
		return propertiesFilename;
	}

	public void setPropertiesFilename(String propertiesFilename) {
		this.propertiesFilename = propertiesFilename;
	}

	@Override
	public String getType() {
		return type;
	}

	public void train() {
		throw new UnsupportedOperationException("Not implemented in your network");
	}

	@Override
	public void train(double[][] input, double[][] output) {
		throw new UnsupportedOperationException("Not implemented in your network");
		
	}

	public void compute(double[] input, double[] output) {
		throw new UnsupportedOperationException("Not implemented in your network");
	}

	public void compute() {
		throw new UnsupportedOperationException("Not implemented in your network");
	}

	public TrainingDef getTrainingDef() {
		return trainingDef;
	}

	@Override
	public void initNetwork(NetworkDef def) {
		this.name = def.getName();
		this.type = def.getType();
	}

	@Override
	public void initTraining(TrainingDef def) {
		this.trainingDef = def;

	}
	
	@Override
	public void initTesting(TestingDef def) {
		this.testingDef = def;
	}

	public TestingDef getTestingDef() {
		return testingDef;
	}

}