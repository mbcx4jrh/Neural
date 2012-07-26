package neural;

import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.TrainingDef;

public interface Network {

	public String getName();

	public String getType();

	public void train();

	public void compute(double[] input, double[] output);

	public abstract void initTraining(TrainingDef def);

	public abstract void initNetwork(NetworkDef def);

}
