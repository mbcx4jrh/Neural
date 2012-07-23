package neural.parsec.ast;

import java.util.List;

public class NetworkDef {
	
	private String name;
	private String type;
	private List<Parameter> params;
	private List<Layer> layers;

	public NetworkDef(String name, String type, NetworkBlock block) {
		this.name = name;
		this.type = type;
		this.params = block.getParams();
		this.layers = block.getLayers();
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}


	
	public String toString() {
		String s = "Network: "+name+", type: "+type;
		s = s + ", params: "+params +", layers: "+layers;
		return s;
	}

}
