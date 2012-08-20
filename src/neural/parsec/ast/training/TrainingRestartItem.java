package neural.parsec.ast.training;

import neural.parsec.ast.AbstractTrainingItem;
import neural.parsec.ast.TrainingDef;

public class TrainingRestartItem extends AbstractTrainingItem {

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
