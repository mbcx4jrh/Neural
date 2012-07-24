package neural.parsec;

import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.TrainingDef;

public class Script {
	
	private NetworkDef networkDef;
	private TrainingDef trainingDef;
	
	protected Script(NetworkDef networkDef, TrainingDef trainingDef) {
		this.networkDef = networkDef;
		this.trainingDef = trainingDef;
	}

	public NetworkDef getNetworkDef() {
		return networkDef;
	}

	public void setNetworkDef(NetworkDef networkDef) {
		this.networkDef = networkDef;
	}

	public TrainingDef getTrainingDef() {
		return trainingDef;
	}

	public void setTrainingDef(TrainingDef trainingDef) {
		this.trainingDef = trainingDef;
	}
	
	public String toString() {
		return networkDef+" | "+trainingDef;
	}
}
