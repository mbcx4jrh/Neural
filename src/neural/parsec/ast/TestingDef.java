package neural.parsec.ast;

import java.util.Arrays;

public class TestingDef implements DataDefinition {

	private double[][] inputData;
	private double[][] outputData;
	private DataLocation output;
	private DataLocation input;


	
	public void setInputData(double[][] data) {
		this.inputData = data;
	}
	
	public void setOutputData(double[][] data) {
		this.outputData = data;
	}
	
	public double[][] getInputData() {
		return inputData;
	}
	
	public String getOutputType() {
		if (output == null) return null;
		else return output.getType();
	}
	
	public String getOutputId() {
		return output.getId();
	}

	public String toString() {
		String out =  "testing input ";
		if (inputData != null) out += Arrays.deepToString(inputData);
		else out+= input.toString();
		if (output != null) out += (" " +output.toString());
		return out;
	}

	public DataLocation getInput() {
		return input;
	}

	public void setInputLocation(DataLocation input) {
		this.input = input;
	}
	
	public void setOutputLocation(DataLocation output) {
		this.output = output;
	}

}
