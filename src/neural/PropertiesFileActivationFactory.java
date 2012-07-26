package neural;


public class PropertiesFileActivationFactory implements ActivationFactory{

	private NeuralPropertyFactory<Activation> classes;
	
	public PropertiesFileActivationFactory(String filename) {
		classes = new NeuralPropertyFactory<Activation>(filename, "activation");
	}

	@Override
	public Activation getActivation(String name) {
		return classes.getNewInstance(name);
	}
}
