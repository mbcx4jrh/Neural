package neural.parsec.ast;

import java.util.Arrays;

public class TrainingDef {
	
	private String type;
	private double error;
	private double[][] inputData;
	private double[][] outputData;
	
	public TrainingDef(String type, ErrorCondition ec, Data in, Data out) {
		this.type = type;
		this.error = ec.getError();
		this.inputData = in.data;
		this.outputData = out.data;
	}
	
	public String toString() {
		return "type "+type+", error "+error+", input "+Arrays.deepToString(inputData)
				                            +", output "+Arrays.deepToString(outputData);
	}

}
