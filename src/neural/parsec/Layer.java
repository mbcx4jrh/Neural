package neural.parsec;

public class Layer {
	
	private String activation;
	private int size;
	
	public Layer(String activation, Integer size) {
		this.activation = activation;
		this.size = size.intValue();
	}

	public String getActivation() {
		return activation;
	}
	
	public int getSize() {
		return size;
	}
	
	public String toString() {
		return "(activation "+activation+" - size "+size+")";
	}
}
