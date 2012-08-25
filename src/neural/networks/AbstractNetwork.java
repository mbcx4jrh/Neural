package neural.networks;

import neural.Network;
import neural.NeuralException;
import neural.NeuralPropertyFactory;
import neural.Tester;
import neural.data.DataSource;
import neural.parsec.ast.DataLocation;
import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.TestingDef;
import neural.parsec.ast.TrainingDef;
import neural.tester.ConsoleTester;

public abstract class AbstractNetwork implements Network {

	private String name;
	private String type;
	private String propertiesFilename;
	private TrainingDef trainingDef;
	private TestingDef testingDef;
	
	private NeuralPropertyFactory<Tester> testerFactory;
	private NeuralPropertyFactory<DataSource> dataSourceFactory;
	
	

	@Override
	public String getName() {
		return name;
	}

	public String getPropertiesFilename() {
		return propertiesFilename;
	}

	public void setPropertiesFilename(String propertiesFilename) {
		this.propertiesFilename = propertiesFilename;
		this.testerFactory = new NeuralPropertyFactory<Tester>(propertiesFilename, "tester");
		this.dataSourceFactory = new NeuralPropertyFactory<DataSource>(propertiesFilename, "data");
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

	public double[] compute(double[] input) {
		throw new UnsupportedOperationException("Not implemented in your network");
	}

	public void compute() {
		if (this.getTestingDef() == null) 
			throw new NeuralException("Cannot use the compute() function unless testing data defined");
		Tester tester;
		if (this.getTestingDef().getOutputType() == null) {
			tester = new ConsoleTester();
			tester.init(null, this.getTestingDef());
		}
		else {
			tester = testerFactory.getNewInstance(this.getTestingDef().getOutputType());
			tester.init(this.getTestingDef().getOutputId(), this.getTestingDef());
		}
		
		DataLocation location = this.getTestingDef().getInput();
		if (location != null) {
			DataSource source = dataSourceFactory.getNewInstance(location.getType());
			source.init(location.getId());
			tester.setSource(source);
		}
		tester.test(this);
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