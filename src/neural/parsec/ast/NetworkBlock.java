package neural.parsec.ast;

import java.util.List;

public class NetworkBlock {

	private List<Parameter> params;
	private List<Layer> layers;

	public NetworkBlock(List<Parameter> params, List<Layer> layers) {
		this.params = params;
		this.layers = layers;
	}

	public List<Parameter> getParams() {
		return params;
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public String toString() {
		return "params: " + params.toString() + ", layers: " + layers.toString();
	}
}
