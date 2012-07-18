package neural;

import neural.networks.HopfieldNetwork;
import neural.parsec.NetworkDef;

public class NetworkFactory {

	public Network getNetwork(NetworkDef definition) {
		if (!definition.getType().equals("hopfield")) 
			return null;
		
		return new HopfieldNetwork(definition);
	}

}
