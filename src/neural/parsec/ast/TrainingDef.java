package neural.parsec.ast;

import java.util.Arrays;

public class TrainingDef implements DataDefinition{

	public String getType() {
		return type;
	}

	public double getError() {
		return error;
	}

	public double[][] getInputData() {
		return inputData;
	}

	public double[][] getOutputData() {
		return outputData;
	}

	private String type;
	private double error;
	private double[][] inputData;
	private double[][] outputData;
	private int epochs = 250;
	private int restart = 1;
	private DataLocation inputLocation;
	private DataLocation outputLocation;



	public int getEpochs() {
		return epochs;
	}

	public void setEpochs(int epochs) {
		this.epochs = epochs;
	}

	public int getRestart() {
		return restart;
	}

	public void setRestart(int restart) {
		this.restart = restart;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setError(double error) {
		this.error = error;
	}

	public void setInputData(double[][] inputData) {
		this.inputData = inputData;
	}

	public void setOutputData(double[][] outputData) {
		this.outputData = outputData;
	}

	public String toString() {
		return "type " + type + ", error " + error + ", restart "+restart+", epochs "+epochs+", input " + Arrays.deepToString(inputData) + ", output "
				+ Arrays.deepToString(outputData);
	}

	@Override
	public void setInputLocation(DataLocation location) {
		this.inputLocation = location;
	}

	@Override
	public void setOutputLocation(DataLocation location) {
		this.outputLocation = location;
	}

}
