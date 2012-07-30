package neural.parsec.ast.training;

import neural.parsec.ast.TrainingDef;
import neural.parsec.ast.TrainingItem;

public class TrainingTypeItem implements TrainingItem {
	
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
