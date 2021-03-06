package neural.parsec.ast.training;

import neural.parsec.ast.Data;
import neural.parsec.ast.DataDefinition;
import neural.parsec.ast.DataItem;

public class TrainingOutputItem implements DataItem {
	
	private Data data;
	
	public TrainingOutputItem(Data data) {
		this.data = data;
	}
	
	public Data getData() {
		return data;
	}

	@Override
	public void applyTo(DataDefinition def) {
		def.setOutputData(data.getData());
	}

}
