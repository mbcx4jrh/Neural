package neural.parsec.ast;

import java.util.Arrays;

public class TestingDef {

	private double[][] data;

	public void setInputData(Data data) {
		this.data = data.getData();
	}
	
	public String toString() {
		return "testing input "+Arrays.deepToString(data);
	}

}
