package neural.networks;

import neural.Network;
import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.TrainingDef;

public abstract class AbstractNetwork implements Network {

	private String name;
	private String type;
	private String propertiesFilename;

	@Override
	public String getName() {
		return name;
	}
	

	public String getPropertiesFilename() {
		return propertiesFilename;
	}


	public void setPropertiesFilename(String propertiesFilename) {
		this.propertiesFilename = propertiesFilename;
	}


	@Override
	public String getType() {
		return type;
	}

	public void train() {
		throw new UnsupportedOperationException(
				"Not implemented in your network");
	}

	public void compute(double[] input, double[] output) {
		throw new UnsupportedOperationException(
				"Not implemented in your network");
	}

	@Override
	public void initNetwork(NetworkDef def) {
		this.name = def.getName();
		this.type = def.getType();
	}

	@Override
	public void initTraining(TrainingDef def) {

	}

}
