package neural.networks.encog;

import neural.Network;
import neural.NeuralException;
import neural.TrainMethodAdapter;
import neural.parsec.ast.TrainingDef;

import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.ContainsFlat;

public abstract class AbstractTrainingAdapter implements TrainMethodAdapter{

	private EncogBasicNetwork encogNetwork;
	private TrainingDef trainingDef;

	public AbstractTrainingAdapter() {
		super();
	}

	@Override
	public void init(TrainingDef definition, Network network) {
		this.trainingDef = definition;
		if (!(network instanceof EncogBasicNetwork)) 
			throw new NeuralException("Can only apply enog trainer to encog basic network");
		
		encogNetwork = (EncogBasicNetwork)network;
	}

	@Override
	public void train(double[] input, double[] output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void train() {
		boolean canRestart = false;
		int restarts = trainingDef.getRestart();
		//Log.write("Beginning training");
		do {
			encogNetwork.getEncogNetwork().reset();
			//Log.write("Input : " + Arrays.deepToString(trainingDef.getInputData()));
			//Log.write("output: " + Arrays.deepToString(trainingDef.getOutputData()));
			//MLDataSet dataSet = new BasicMLDataSet(trainingDef.getInputData(), trainingDef.getOutputData());
			MLTrain trainer = getTrainer(trainingDef);
			
	
			int epoch = 0;
			int maxEpoch;
			maxEpoch = trainingDef.getEpochs();
			do {
				trainer.iteration();
				epoch++;
				//Log.write("Epoch " + epoch + ": error = " + trainer.getError());
		
			} while ((trainer.getError() > trainingDef.getError()) &&
					    (epoch <= maxEpoch));
			canRestart = (trainingDef.getError() > 0) && (trainer.getError() > trainingDef.getError());
		}
		while (canRestart && (restarts-- > 0)); 
	
	}

	protected ContainsFlat getUnderLyingNetwork() {
		return encogNetwork.getEncogNetwork();
	}

	protected MLDataSet getData() {
		return new BasicMLDataSet(trainingDef.getInputData(), trainingDef.getOutputData());
	
	}

	abstract protected MLTrain getTrainer(TrainingDef definiton);

}