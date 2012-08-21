package neural.networks.encog;

import neural.NeuralException;
import neural.networks.AbstractNetwork;
import neural.parsec.ast.Layer;
import neural.parsec.ast.NetworkDef;

import org.encog.mathutil.matrices.BiPolarUtil;
import org.encog.ml.data.specific.BiPolarNeuralData;
import org.encog.neural.art.ART1;

public class EncogART1Network extends AbstractNetwork {
	
	private ART1 network;
	private int inputSize;
	private int outputSize;

	@Override
	public double[] compute(double[] input) {
		BiPolarNeuralData biInput = new BiPolarNeuralData(BiPolarUtil.double2bipolar(input));
		BiPolarNeuralData biOutput = new BiPolarNeuralData(outputSize);
		network.compute(biInput, biOutput);
		double[] output = new double[outputSize];
		if (network.hasWinner()) {
			output[network.getWinner()] = 1;
		}
		return output;
	}

	@Override
	public void initNetwork(NetworkDef def) {
		super.initNetwork(def);
		inputSize = findLayer("input", def);
		outputSize = findLayer("output", def);
		network = new ART1(inputSize,outputSize);
	}

	private int findLayer(String name, NetworkDef def) {
		for (Layer layer: def.getLayers()) {
			if (layer.getActivation().equals(name)) return layer.getSize();
		}
		throw new NeuralException("Missing layer ("+name+") in ART1 network");
	}
	
	

}
