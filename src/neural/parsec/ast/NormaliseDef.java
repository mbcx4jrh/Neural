package neural.parsec.ast;

public class NormaliseDef {
	
	private double min;
	private double max;
	
	public NormaliseDef(double min, double max) {
		this.min = min;
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public double getMax() {
		return max;
	}

}
