package neural.networks;

import neural.Network;
import neural.NetworkFactory;
import neural.NeuralPropertyFactory;
import neural.parsec.Script;

public abstract class AbstractNetworkFactory implements NetworkFactory {

	private NeuralPropertyFactory<Network> classes;

	private String filename = "properties/encog.properties";
	
	public void setPropertiesFile(String filename) {
		this.filename = filename;
	}
	
	public void init() throws NetworkFactoryException {
		classes = new NeuralPropertyFactory<Network>(filename, "network");
	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see neural.NetworkFactory#getNetwork(neural.parsec.Script)
	 */
	@Override
	public Network getNetwork(Script definition) {
		String type = definition.getNetworkDef().getType();
		
		Network network = classes.getNewInstance(type);

		network.initNetwork(definition.getNetworkDef());
		network.initTraining(definition.getTrainingDef());

		return network;
	}

}