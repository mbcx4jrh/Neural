package neural.tester;

import java.util.HashMap;
import java.util.Map;

import neural.NeuralException;
import neural.Tester;
import neural.parsec.ast.TestingDef;
import neural.parsec.ast.TrainingDef;

public class MemoryTester extends Tester {
	
	static private Map<String, MemoryTester> testers = new HashMap<String, MemoryTester>();
	
	private double[] lastIn;
	private double[] lastOut;
	private String id;

	MemoryTester(String id, TestingDef def) {
		super(def);
		this.id = id;
	}
	
	public static MemoryTester getMemoryTester(String id) {
		MemoryTester t = testers.get(id);
		if (t == null) {
			throw new NeuralException("Request for non-existant MemoryTester: "+id);
		}
		return t;
	}
	
	public static MemoryTester storeMemoryTester(String id, TestingDef def) {
		MemoryTester t = new MemoryTester(id, def);
		testers.put(id, t);
		return t;
	}

	@Override
	protected void releaseResult(double[] input, double[] output) {
		lastIn = input;
		lastOut = output;
	}
	
	public double[] lastInput() {
		return lastIn;
	}
	
	public double[] lastOutput() {
		return lastOut;
	}

	public String getId() {
		return id;
	}

}
