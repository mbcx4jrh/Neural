package neural;

import neural.parsec.ast.TrainingDef;

public interface TrainMethodAdapter {
	
	public void init(TrainingDef definition, Network network);
	
	public void train(double[] input, double[] output);
	
	public void train();
	
}
