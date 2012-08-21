package neural;

import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.TestingDef;
import neural.parsec.ast.TrainingDef;

public interface Network {

	public String getName();

	public String getType();

	public String getPropertiesFilename();

	public void setPropertiesFilename(String filename);

	public void train();
	
	public void train(double[][] input, double[][] output);

	public double[] compute(double[] input);
	
	public void compute();

	public void initTraining(TrainingDef def);
	
	public void initTesting(TestingDef def);

	public void initNetwork(NetworkDef def);

}
