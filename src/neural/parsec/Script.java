package neural.parsec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import neural.parsec.ast.ActivationDefinition;
import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.TestingDef;
import neural.parsec.ast.TrainingDef;


public class Script {

	private NetworkDef networkDef;
	private TrainingDef trainingDef;
	private TestingDef testingDef; 
	private Map<String, ActivationDefinition> activationMap;
	

	protected Script(List<ActivationDefinition> activationDefinitions,
			         NetworkDef networkDef, 
			         TrainingDef trainingDef, 
			         TestingDef testingDef) {
		createActivationLookup(activationDefinitions);
		this.networkDef = networkDef;
		this.trainingDef = trainingDef;
		this.testingDef = testingDef;
		
		this.networkDef.setActivationMap(activationMap);
		
	}

	public Map<String, ActivationDefinition> getActivationMap() {
		return activationMap;
	}

	private void createActivationLookup(List<ActivationDefinition> activationDefinitions) {
		activationMap = new HashMap<String, ActivationDefinition>();
		for (ActivationDefinition a: activationDefinitions) {
			activationMap.put(a.getName(), a);
		}
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
