package neural.networks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import neural.Network;
import neural.NetworkFactory;
import neural.NeuralException;
import neural.parsec.Script;

public abstract class AbstractNetworkFactory implements NetworkFactory {

	private Properties classes;

	private String FILENAME = "properties/encog.properties";
	
	public void setPropertiesFile(String filename) {
		this.FILENAME = filename;
	}
	
	public void init() throws NetworkFactoryException {
		loadProperties();
	}

	private void loadProperties() throws NetworkFactoryException {
		classes = new Properties();
		try {
			classes.load(new FileInputStream(new File(FILENAME)));
		} catch (FileNotFoundException e) {
			throw new NetworkFactoryException("Missing properties file ("
					+ FILENAME + ")", e);
		} catch (IOException e) {
			throw new NetworkFactoryException("IO issues when reading file ("
					+ FILENAME + ")", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see neural.NetworkFactory#getNetwork(neural.parsec.Script)
	 */
	@Override
	public Network getNetwork(Script definition) {
		String type = "network."+definition.getNetworkDef().getType();
		String netClass = classes.getProperty(type); 
		if (netClass == null)
			throw new NeuralException("Unknown network type (" + type + ")");

		AbstractNetwork network;
		try {
			network = (AbstractNetwork) Class.forName(netClass).newInstance();
		} catch (InstantiationException e) {
			throw new NeuralException(e);
		} catch (IllegalAccessException e) {
			throw new NeuralException(e);
		} catch (ClassNotFoundException e) {
			throw new NeuralException("Missing class for network (" + netClass
					+ ")", e);
		}

		network.initNetwork(definition.getNetworkDef());
		network.initTraining(definition.getTrainingDef());

		return network;
	}

}
