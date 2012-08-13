package neural.parsec.ast;

import java.util.Arrays;

public class TestingDef {

	private double[][] data;

	public void setData(Data data) {
		this.data = data.getData();
	}
	
	public double[][] getData() {
		return data;
	}

	public String toString() {
		return "testing input "+Arrays.deepToString(data);
	}

}
