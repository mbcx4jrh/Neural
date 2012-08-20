package neural.parsec.ast;

public abstract class AbstractTrainingItem implements TrainingItem {

	@Override
	public void applyTo(DataDefinition def) {
		if (def instanceof TrainingDef) {
			applyTo((TrainingDef)def);
		}
	}


}
