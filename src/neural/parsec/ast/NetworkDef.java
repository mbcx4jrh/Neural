package neural.parsec.ast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import neural.NeuralException;

public class NetworkDef {

	private String name;
	private String type;
	private List<Parameter> params2;
	private Map<String, Parameter> params;
	private List<Layer> layers;
	private Map<String, ActivationDefinition> activationMap = new HashMap<String, ActivationDefinition>();

	public NetworkDef(String name, String type, NetworkBlock block) {
		this.name = name;
		this.type = type;
		if (block != null) {
			this.params2 = block.getParams();
			setParams(block.getParams());
			this.layers = block.getLayers();
		}
		for (Layer l: layers) {
			l.setNetwork(this);
		}
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public String toString() {
		String s = "Network: " + name + ", type: " + type;
		s = s + ", params: " + params2 + ", layers: " + layers;
		return s;
	}

	public String getParameter(String name) {
		return null;
	}

	public int getIntParameter(String string) { 
		Parameter p = params.get(string);
		if (!(p instanceof IntegerParameter)) 
			throw new NeuralException("Invalid parameter asked for ("+string+" is not an integer)");
		return ((IntegerParameter)p).getValue().intValue();
	}

	public boolean hasParam(String string) { 
		return params.containsKey(string);
	}
	
	public void setActivationMap(Map<String, ActivationDefinition> activationMap) {
		this.activationMap = activationMap;
	}
	
	public Map<String, ActivationDefinition> getActivationMap() {
		return activationMap;
	}

	private void setParams(List<Parameter> list) {
		params = new HashMap<String, Parameter>();
		for (Parameter p: list) {
			params.put(p.getName(), p);
		}
	}
}
