package neural.parsec.ast.training;

import neural.parsec.ast.Data;
import neural.parsec.ast.TrainingDef;
import neural.parsec.ast.TrainingItem;

public class TrainingInputItem implements TrainingItem {
	
	private Data data;
	
	public TrainingInputItem(Data data) {
		this.data = data;
	}

	public Data getData() {
		return data;
	}
	
	@Override
	public void applyTo(TrainingDef def) {
		def.setInputData(data.getData());
	}

}
