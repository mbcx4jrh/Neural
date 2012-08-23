package neural.networks.encog;

import neural.NeuralPropertyFactory;
import neural.TrainMethodAdapter;
import neural.networks.AbstractNetwork;
import neural.parsec.ast.Layer;
import neural.parsec.ast.NetworkDef;

import org.encog.engine.network.activation.ActivationFunction;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;


public class EncogBasicNetwork extends AbstractNetwork {
	

	private BasicNetwork basicNetwork;

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
			if (layer.getActivation().getName().equals("input"))
				activationFunction = null;
			else
				activationFunction = activationFactory.getNewInstance(layer.getActivation().getType());

			basicNetwork.addLayer(new BasicLayer(activationFunction, layer.isBiased(), layer.getSize()));
			// System.out.println("Adding layer "+i+" "+layer.toString());
		}
	}


	@Override
	public void train() {
		basicNetwork.reset();
		TrainMethodAdapter trainer = trainerFactory.getNewInstance(this.getTrainingDef().getType());
		trainer.init(this.getTrainingDef(), this);
		trainer.train();
	}
	
	@Override
	public void train(double[][] input, double[][] output) {
		this.getTrainingDef().setInputData(input);
		this.getTrainingDef().setOutputData(output);
		train();
	}
	

	@Override
	public double[] compute(double[] input) {
		int outputSize = basicNetwork.getOutputCount();
		double[] output = new double[outputSize];
		basicNetwork.compute(input, output);
		return output;
	}
}
