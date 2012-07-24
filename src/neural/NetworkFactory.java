package neural;

import neural.networks.HopfieldNetwork;
import neural.networks.NullNetwork;
import neural.parsec.Script;

public class NetworkFactory {

	public Network getNetwork(Script definition) {
		if (!definition.getNetworkDef().getType().equals("hopfield")) 
			return new NullNetwork();
		
		return new HopfieldNetwork(definition.getNetworkDef());
	}

}
