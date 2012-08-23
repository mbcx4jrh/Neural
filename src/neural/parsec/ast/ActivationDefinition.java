package neural.parsec.ast;

import java.util.ArrayList;
import java.util.List;

public class ActivationDefinition {
	
	private String name;
	private String type;
	private List<Parameter> parameters;

	public ActivationDefinition(String name, String type, List<Parameter> parameters) {
		this.name = name;
		this.type = type;
		this.parameters = parameters;
		if (this.parameters == null) this.parameters = new ArrayList<Parameter>();
	}
	
	public ActivationDefinition(String name, String type) {
		this(name, type, null);
	}

	public String toString() {
		return "name:"+name+" type:"+type+" params:"+parameters;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
}
