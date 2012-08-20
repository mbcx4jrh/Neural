package neural.parsec.ast.training;

import neural.parsec.ast.AbstractTrainingItem;
import neural.parsec.ast.TrainingDef;

public class TrainingTypeItem extends AbstractTrainingItem {
	
	private String type;
	
	public TrainingTypeItem(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	@Override
	public void applyTo(TrainingDef def) {
		def.setType(type);
	}

}
