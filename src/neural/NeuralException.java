package neural;

public class NeuralException extends RuntimeException {
	

	private static final long serialVersionUID = -5002867189852573304L;

	public NeuralException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public NeuralException(Throwable t) {
		super(t);
	}

	public NeuralException(String string) {
		super(string);
	}

}
