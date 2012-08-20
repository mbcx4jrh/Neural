package neural.parsec.ast.training;

import neural.parsec.ast.Data;
import neural.parsec.ast.DataDefinition;
import neural.parsec.ast.DataItem;

public class InlineInputItem implements DataItem {
	
	private Data data;
	
	public InlineInputItem(Data data) {
		this.data = data;
	}
	
	@Override
	public void applyTo(DataDefinition def) {
		def.setInputData(data.getData());
	}

	
	public String toString() {
		return data.toString();
	}

}
