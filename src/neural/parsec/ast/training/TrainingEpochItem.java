package neural.parsec.ast.training;

import neural.parsec.ast.AbstractTrainingItem;
import neural.parsec.ast.TrainingDef;

public class TrainingEpochItem extends AbstractTrainingItem {
	
	private int epochs;

	public TrainingEpochItem(Integer epochs) {
		this.epochs = epochs.intValue();
	}
	
	public int getEpochs() {
		return epochs;
	}

	@Override
	public void applyTo(TrainingDef def) {
		def.setEpochs(epochs);
	}

}
