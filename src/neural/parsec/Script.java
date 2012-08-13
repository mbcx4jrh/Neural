package neural.parsec;

import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.TestingDef;
import neural.parsec.ast.TrainingDef;

public class Script {

	private NetworkDef networkDef;
	private TrainingDef trainingDef;
	private TestingDef testingDef;

	protected Script(NetworkDef networkDef, TrainingDef trainingDef, TestingDef testingDef) {
		this.networkDef = networkDef;
		this.trainingDef = trainingDef;
		this.testingDef = testingDef;
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

	public TestingDef getTestingDef() {
		return testingDef;
	}

	public void setTrainingDef(TrainingDef trainingDef) {
		this.trainingDef = trainingDef;
	}

	public String toString() {
		return networkDef + " | " + trainingDef;
	}
}
