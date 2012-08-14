package neural.parsec.ast;

import java.util.Arrays;

import neural.parsec.ast.training.TrainingInputItem;

public class TestingDef {

	private double[][] data;
	private TestingOutput output;

	public TestingDef(TrainingInputItem input, TestingOutput output) {
		this.data = input.getData().getData();
		this.output = output;
	}
	
	public double[][] getData() {
		return data;
	}

	public String toString() {
		String out =  "testing input "+Arrays.deepToString(data);
		if (output != null) out += (" " +output.toString());
		return out;
	}

}
