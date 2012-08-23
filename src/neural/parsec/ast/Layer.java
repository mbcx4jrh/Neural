package neural.parsec.ast;

public class Layer {

	private String activation;
	private int size;
	private boolean biased;
	private NetworkDef network;

	public boolean isBiased() {
		return biased;
	}

	public Layer(String activation, Integer size, Boolean biased) {
		this.activation = activation;
		this.size = size.intValue();
		if (biased == null)
			this.biased = false;
		else
			this.biased = biased.booleanValue();
	}
	
	public void setNetwork(NetworkDef network) {
		this.network = network;
	}

	public ActivationDefinition getActivation() {
	
		if ((network !=null) && (network.getActivationMap().containsKey(activation)))
			return network.getActivationMap().get(activation);
		else 
			return new ActivationDefinition(activation, activation);
	}

	public int getSize() {
		return size;
	}

	public String toString() {
		String s = "(activation " + activation + " - size " + size;
		if (biased)
			s = s + " - biased";
		s = s + ")";
		return s;
	}
}
