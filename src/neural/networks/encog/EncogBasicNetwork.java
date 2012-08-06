package neural.networks.encog;

import neural.NeuralPropertyFactory;
import neural.TrainMethodAdapter;
import neural.networks.AbstractNetwork;
import neural.parsec.ast.Layer;
import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.TrainingDef;

import org.encog.engine.network.activation.ActivationFunction;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;


public class EncogBasicNetwork extends AbstractNetwork {
	

	private BasicNetwork basicNetwork;
	private TrainingDef trainingDef;
	
//	private TrainingContinuation trainingContinuation;

	private NeuralPropertyFactory<ActivationFunction> activationFactory;
	private NeuralPropertyFactory<TrainMethodAdapter> trainerFactory;

	@Override
	public void initNetwork(NetworkDef def) {
		super.initNetwork(def);
		basicNetwork = new BasicNetwork();
		activationFactory = new NeuralPropertyFactory<ActivationFunction>(this.getPropertiesFilename(), "activation");
		trainerFactory = new NeuralPropertyFactory<TrainMethodAdapter>(this.getPropertiesFilename(), "training");
		createLayers(def);
		basicNetwork.getStructure().finalizeStructure();
		basicNetwork.reset(); // reset weights
	}
	
	public BasicNetwork getEncogNetwork() {
		return basicNetwork;
	}

	private void createLayers(NetworkDef def) {
		Layer layer;
		ActivationFunction activationFunction;
		int numberOfLayers = def.getLayers().size();
		for (int i = 0; i < numberOfLayers; i++) {

			layer = def.getLayers().get(i);
			if (layer.getActivation().equals("input"))
				activationFunction = null;
			else
				activationFunction = activationFactory.getNewInstance(layer.getActivation());

			basicNetwork.addLayer(new BasicLayer(activationFunction, layer.isBiased(), layer.getSize()));
			// System.out.println("Adding layer "+i+" "+layer.toString());
		}
	}

	@Override
	public void initTraining(TrainingDef def) {
		super.initTraining(def);
		this.trainingDef = def;
	}

	@Override
	public void train() {
		basicNetwork.reset();
		TrainMethodAdapter trainer = trainerFactory.getNewInstance(trainingDef.getType());
		trainer.init(trainingDef, this);
		trainer.train();
	}
	
	@Override
	public void train(double[][] input, double[][] output) {
		trainingDef.setInputData(input);
		trainingDef.setOutputData(output);
		train();
	}
	

	@Override
	public void compute(double[] input, double[] output) {
		basicNetwork.compute(input, output);
	}

}
