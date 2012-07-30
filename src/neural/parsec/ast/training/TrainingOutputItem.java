package neural.parsec.ast.training;

import neural.parsec.ast.Data;
import neural.parsec.ast.TrainingDef;
import neural.parsec.ast.TrainingItem;

public class TrainingOutputItem implements TrainingItem {
	
	private Data data;
	
	public TrainingOutputItem(Data data) {
		this.data = data;
	}
	
	public Data getData() {
		return data;
	}

	@Override
	public void applyTo(TrainingDef def) {
		def.setOutputData(data.getData());
	}

}
