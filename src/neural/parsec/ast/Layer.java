package neural.parsec.ast;

public class Layer {

	private String activation;
	private int size;
	private boolean biased;

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

	public String getActivation() {
		return activation;
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
