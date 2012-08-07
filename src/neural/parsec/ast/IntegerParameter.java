package neural.parsec.ast;

public class IntegerParameter extends Parameter {

	private Integer value;

	public IntegerParameter(String name, Integer value) {
		super(name);
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public String getValueAsString() {
		return ""+value;
	}

	public String toString() {
		return getName() + " " + value.toString();
	}
}
