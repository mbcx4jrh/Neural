package neural.parsec.ast.training;

import neural.parsec.ast.TrainingDef;
import neural.parsec.ast.TrainingItem;

public class ErrorTrainingItem implements TrainingItem {
	
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
