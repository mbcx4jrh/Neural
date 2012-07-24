package neural.networks;

import neural.Network;
import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.TrainingDef;

public abstract class AbstractNetwork implements Network {
	
	private String name;
	private String type;
	

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getType() {
		return type;
	}

	public void train() {
		throw new UnsupportedOperationException("Not implemented in your network");
	}
	
	public void initNetwork(NetworkDef def) {
		this.name = def.getName();
		this.type = def.getType(); 
	}
	
	public void initTraining(TrainingDef def) {
		
	}

}
