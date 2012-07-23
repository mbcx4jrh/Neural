package neural.networks;

import neural.Network;
import neural.parsec.ast.NetworkDef;

public abstract class AbstractNetwork implements Network {
	
	private String name;
	private String type;
	
	public AbstractNetwork(NetworkDef def) {
		this.name = def.getName();
		this.type = def.getType();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return type;
	}



}
