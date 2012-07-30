package neural.parsec.ast.training;

import neural.parsec.ast.TrainingDef;
import neural.parsec.ast.TrainingItem;

public class TrainingEpochItem implements TrainingItem {
	
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
