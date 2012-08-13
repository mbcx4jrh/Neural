package neural.networks;

import neural.Network;
import neural.NetworkFactory;
import neural.NeuralPropertyFactory;
import neural.parsec.Script;

public abstract class AbstractNetworkFactory implements NetworkFactory {

	private NeuralPropertyFactory<Network> classes;

	private String filename;

	public void setPropertiesFile(String filename) {
		this.filename = filename;
	}

	public String getPropertiesFile() {
		return filename;
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

		network.setPropertiesFilename(filename);
		network.initNetwork(definition.getNetworkDef());
		network.initTraining(definition.getTrainingDef());
		network.initTesting(definition.getTestingDef());

		return network;
	}

}
