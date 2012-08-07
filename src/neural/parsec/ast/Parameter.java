package neural.parsec.ast;

public abstract class Parameter {

	private String name;

	public Parameter(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public abstract String getValueAsString();

	public String toString() {
		return "Name: " + name + ", Value: Fuckup (abstract class is being used)";
	}

	public boolean equals(Object o) {
		if (!(o instanceof Parameter))
			return false;
		return o.toString().equals(this.toString());
	}
}
