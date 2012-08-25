package neural.parsec.validation;

import java.util.Arrays;
import java.util.Map;

import neural.NeuralException;
import neural.parsec.Script;
import neural.parsec.ast.ActivationDefinition;
import neural.parsec.ast.NetworkDef;

public class ScriptValidator {
	
	private ValidationFactory validationFactory;
	
	public ScriptValidator(String properties) {
		validationFactory = new ValidationFactory(properties);
	}
	
	public void validateScript(Script script) throws NeuralException {
		validateActivationFunctions(script.getActivationMap());
		validateNetworkParameters(script.getNetworkDef());
	}

	private void validateNetworkParameters(NetworkDef networkDef) {
		Validator validator = validationFactory.getValidator("network."+networkDef.getType());
		validator.validate(networkDef.getParameters());
		if (!validator.isValid()) {
			throw new NeuralException("Invalid network definition '"+networkDef.getName()
					+"' "+ Arrays.toString(validator.getIssues()));
		}
	}

	private void validateActivationFunctions(Map<String, ActivationDefinition> activationMap) {
		for (ActivationDefinition def: activationMap.values()) {
			Validator validator = validationFactory.getValidator("activation."+def.getType());
			validator.validate(def.getParameters());
			if (!validator.isValid()) {
				throw new NeuralException("Invalid activation definition '"+def.getName() 
						+"' "+Arrays.toString(validator.getIssues()));
			}
		}
		
	}

}
