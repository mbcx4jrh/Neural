package neural.networks.encog;

import neural.NeuralException;
import neural.networks.AbstractNetwork;
import neural.parsec.ast.Layer;
import neural.parsec.ast.NetworkDef;

import org.encog.neural.art.ART1;

public class EncogART1Network extends AbstractNetwork {
	
	private ART1 network;

	@Override
	public void compute(double[] input, double[] output) {
		//
	}

	@Override
	public void initNetwork(NetworkDef def) {
		super.initNetwork(def);
		int input = findLayer("input", def);
		int output = findLayer("output", def);
		network = new ART1(input,output);
	}

	private int findLayer(String name, NetworkDef def) {
		for (Layer layer: def.getLayers()) {
			if (layer.getActivation().equals(name)) return layer.getSize();
		}
		throw new NeuralException("Missing layer ("+name+") in ART1 network");
	}
	
	

}
