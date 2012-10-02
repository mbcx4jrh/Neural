package neural.networks.encog;

import neural.networks.AbstractNetwork;
import neural.parsec.ast.NetworkDef;

import org.encog.mathutil.matrices.BiPolarUtil;
import org.encog.ml.data.specific.BiPolarNeuralData;
import org.encog.neural.thermal.HopfieldNetwork;


public class EncogHopfieldNetwork extends AbstractNetwork {
	
	private int maxCycles = 100;
	private HopfieldNetwork network;

	@Override
	public void initNetwork(NetworkDef def) {
		super.initNetwork(def);
		network = new HopfieldNetwork(def.getIntParameter("size"));
		if (def.hasParam("max-cycles"))
			maxCycles = def.getIntParameter("max-cycles");
	}

	/**
	 * Can ignore the second parameter (output)
	 */
	@Override
	public void train(double[][] input, double[][] output) {
		for (int i=0; i<input.length; i++) {
			BiPolarNeuralData pattern = new BiPolarNeuralData(BiPolarUtil.double2bipolar(input[i]));
			network.addPattern(pattern);
		}
	}
	
	
 

	@Override
	public void train() {
		train(this.getTrainingDef().getInputData(), null);
	}

	@Override
	public double[] compute(double[] input) {
		BiPolarNeuralData data = new BiPolarNeuralData(BiPolarUtil.double2bipolar(input));
		network.setCurrentState(data);
		network.runUntilStable(maxCycles);
		return network.getCurrentState().getData();
	}
	
}
