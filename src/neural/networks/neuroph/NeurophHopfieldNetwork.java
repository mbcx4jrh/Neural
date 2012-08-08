package neural.networks.neuroph;

import java.util.Arrays;

import neural.networks.AbstractNetwork;
import neural.parsec.ast.NetworkDef;

import org.neuroph.core.learning.TrainingElement;
import org.neuroph.core.learning.TrainingSet;
import org.neuroph.nnet.Hopfield;
import org.neuroph.nnet.comp.InputOutputNeuron;
import org.neuroph.util.NeuronProperties;
import org.neuroph.util.TransferFunctionType;

public class NeurophHopfieldNetwork extends AbstractNetwork {
	
	private Hopfield network;
	private int size;
	private int maxCycles = 100;

	@Override
	public void initNetwork(NetworkDef def) {
		super.initNetwork(def);
		this.size = def.getIntParameter("size");
		if (def.hasParam("max-cycles")) 
			this.maxCycles = def.getIntParameter("max-cycles");
		
		NeuronProperties neuronProperties = new NeuronProperties();
		neuronProperties.setProperty("neuronType", InputOutputNeuron.class);
		//neuronProperties.setProperty("bias", new Double());
		neuronProperties.setProperty("transferFunction", TransferFunctionType.SGN);
		//neuronProperties.setProperty("transferFunction.yHigh", new Double(1));
		//neuronProperties.setProperty("transferFunction.yLow", new Double(-1));

		network = new Hopfield(size, neuronProperties); 
		
	}

	@Override
	public void train(double[][] input, double[][] output) {
		TrainingSet<TrainingElement> trainingSet = new TrainingSet<TrainingElement>(size);
		for (int i=0; i< input.length; i++){
			trainingSet.addElement(new TrainingElement(input[i]));
		}
		network.learn(trainingSet);
	}

	@Override
	public void compute(double[] input, double[] output) {
		String prev;
		String current = Arrays.toString(input);
		double[] result;
		int cycles = 0;
		network.setInput(input);
		network.calculate();
		do {
			System.out.println("Cycle "+cycles);
			prev = current;
			network.calculate();
			result = network.getOutput();
			current = Arrays.toString(result);
		} while (!prev.equals(current) && cycles++ < maxCycles);
		System.arraycopy(result, 0, output, 0, result.length);
	}
	
	

}
