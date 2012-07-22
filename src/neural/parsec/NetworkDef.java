package neural.parsec;

import java.util.List;

public class NetworkDef {
	
	private String name;
	private String type;
	private List<Parameter> params;

	public NetworkDef(String name, String type, List<Parameter> params) {
		this.name = name;
		this.type = type;
		this.params = params;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}


	
	public String toString() {
		return "Network: "+name+", type: "+type+", params: "+params; 
	}

}
