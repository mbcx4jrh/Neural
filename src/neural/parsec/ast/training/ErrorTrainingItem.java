package neural.parsec.ast.training;

import neural.parsec.ast.AbstractTrainingItem;
import neural.parsec.ast.TrainingDef;

public class ErrorTrainingItem extends AbstractTrainingItem {
	
	private double error;
	
	public ErrorTrainingItem(double error) {
		this.error = error;
	}
	
	public double getError() {
		return error;
	}

	@Override
	public void applyTo(TrainingDef def) {
		def.setError(error);
	}

}
