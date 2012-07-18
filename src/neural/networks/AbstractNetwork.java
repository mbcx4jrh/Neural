package neural.networks;

import neural.Network;
import neural.parsec.NetworkDef;

public abstract class AbstractNetwork implements Network {
	
	private String name;
	private String type;
	private int size;
	
	public AbstractNetwork(NetworkDef def) {
		this.name = def.getName();
		this.type = def.getType();
		this.size = def.getSize();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public int getSize() {
		return size;
	}

}
