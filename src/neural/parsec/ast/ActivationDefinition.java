package neural.parsec.ast;

public class ActivationDefinition {
	
	private String name;
	private String type;

	public ActivationDefinition(String name, String type) {
		this.name = name;
		this.type = type;
	}

	public String toString() {
		return "name:"+name+" type:"+type;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
}
