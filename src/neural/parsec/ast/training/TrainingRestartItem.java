package neural.parsec.ast.training;

import neural.parsec.ast.TrainingDef;
import neural.parsec.ast.TrainingItem;

public class TrainingRestartItem implements TrainingItem {

	private int restarts;
	
	public TrainingRestartItem(Integer restarts) {
		this.restarts = restarts.intValue();
	}
	
	public int getRestarts() {
		return restarts;
	}

	@Override
	public void applyTo(TrainingDef def) {
		def.setRestart(restarts);
	}

}
