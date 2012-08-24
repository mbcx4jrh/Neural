package neural.parsec.validation;

import java.util.Arrays;
import java.util.Map;

import neural.NeuralException;
import neural.parsec.Script;
import neural.parsec.ast.ActivationDefinition;

public class ScriptValidator {
	
	private ValidationFactory validationFactory;
	
	public ScriptValidator(String properties) {
		validationFactory = new ValidationFactory(properties);
	}
	
	public void validateScript(Script script) throws NeuralException {
		validateActivationFunctions(script.getActivationMap());
	}

	private void validateActivationFunctions(Map<String, ActivationDefinition> activationMap) {
		for (ActivationDefinition def: activationMap.values()) {
			Validator validator = validationFactory.getValidator(def.getType());
			validator.validate(def.getParameters());
			if (!validator.isValid()) {
				throw new NeuralException("Invalid activation definition '"+def.getName() 
						+"' "+Arrays.toString(validator.getIssues()));
			}
		}
		
	}

}
