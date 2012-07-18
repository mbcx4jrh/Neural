package neural.parsec;

public class IntegerParameter extends Parameter {
	
	private Integer value;

	public IntegerParameter(String name, Integer value) {
		super(name);
		this.value = value;
	}

	public String toString() {
		return "Name: "+getName()+", Value: "+value;
	}
}
