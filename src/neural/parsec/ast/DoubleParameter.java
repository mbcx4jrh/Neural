package neural.parsec.ast;

public class DoubleParameter extends Parameter {

	private Double value;

	public DoubleParameter(String name, Double value) {
		super(name);
		this.value = value;
	}

	public Double getValue() {
		return value;
	}

	public String toString() {
		return getName() + " " + value.toString();
	}
}
