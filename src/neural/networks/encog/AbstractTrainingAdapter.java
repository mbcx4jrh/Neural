package neural.networks.encog;

import neural.Network;
import neural.NeuralException;
import neural.NeuralPropertyFactory;
import neural.TrainMethodAdapter;
import neural.data.DataSource;
import neural.parsec.ast.TrainingDef;

import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.ContainsFlat;

public abstract class AbstractTrainingAdapter implements TrainMethodAdapter{

	private EncogBasicNetwork encogNetwork;
	private TrainingDef trainingDef;
	private NeuralPropertyFactory<DataSource> dataSourceFactory;

	public AbstractTrainingAdapter() {
		super();
	}

	@Override
	public void init(TrainingDef definition, Network network) {
		this.trainingDef = definition;
		if (!(network instanceof EncogBasicNetwork)) 
			throw new NeuralException("Can only apply enog trainer to encog basic network");
		
		encogNetwork = (EncogBasicNetwork)network;
		dataSourceFactory = new NeuralPropertyFactory<DataSource>(network.getPropertiesFilename(), "data");
	}

	@Override
	public void train(double[] input, double[] output) {
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
		double[][] input;
		double[][] output;
		
		//input data
		if (trainingDef.getInputData() !=null) {
			input = trainingDef.getInputData();
		}
		else if (trainingDef.getInputLocation() != null) {
			input = loadData(trainingDef.getInputLocation().getType(),
					trainingDef.getInputLocation().getId());
		}
		else throw new NeuralException("Missing input data definition in script");
		
		//output data
		if (trainingDef.getOutputData() !=null) {
			output = trainingDef.getOutputData();
		}
		else if (trainingDef.getOutputLocation() != null) {
			output = loadData(trainingDef.getOutputLocation().getType(),
					trainingDef.getOutputLocation().getId());
		}
		else throw new NeuralException("Missing output data definition in script");
		
	
		return new BasicMLDataSet(input, output);
	
	}
	
	private double[][] loadData(String type, String id) {
		DataSource src = dataSourceFactory.getNewInstance(type);
		src.init(id);
		return src.getAll();
	}

	abstract protected MLTrain getTrainer(TrainingDef definiton);

}