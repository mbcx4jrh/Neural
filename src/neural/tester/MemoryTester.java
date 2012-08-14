package neural.tester;

import java.util.ArrayList;
import java.util.List;

import neural.Tester;
import neural.parsec.ast.TestingDef;

public class MemoryTester extends Tester {
		
	private double[] lastIn;
	private double[] lastOut;
	
	private List<double[]> input;
	private List<double[]> output;

	public void init(String id, TestingDef def) {
		super.init(id, def);
		MemoryTesterStore.getInstance().store(this);
		input = new ArrayList<double[]>();
		output = new ArrayList<double[]>();
	}
	
	@Override
	protected void releaseResult(double[] input, double[] output) {
		lastIn = input;
		lastOut = output;
		this.input.add(lastIn);
		this.output.add(lastOut);
	}
	
	public double[] lastInput() {
		return lastIn;
	}
	
	public double[] lastOutput() {
		return lastOut;
	}
	
	public double[][] getInput() {
		return this.input.toArray(new double[0][0]);
	}
	
	public double[][] getOutput() {
		return this.output.toArray(new double[0][0]);
	}

}
