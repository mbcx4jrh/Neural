package EncogExamples;

import static neural.test.Assert.assertEqualWithin;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import neural.Network;
import neural.NeuralException;
import neural.ScriptParser;
import neural.networks.encog.EncogBasicNetwork;
import neural.tester.MemoryTester;
import neural.tester.MemoryTesterStore;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

public class EncogXORNetwork {

	private String getNetworkScript(String activation) {
		return "network basic_net is feedforward {\n" + "    layer {\n" + "        activation input\n"
				+ "        size 2\n" + "        biased\n" + "    }\n	 " + "    layer {\n" + "        activation "
				+ activation + "\n" + "        size 3\n" + "        biased \n" + "    }\n" + "    layer {\n"
				+ "        activation sigmoid\n" + "        size 1\n" + "    }\n" + "}\n";
	}

	private String training_script = "training {\n" + "  type resilient_propagation\n" + " epochs 100000 restart 5 error 0.01%\n"
			+ "  input {\n" + "    0.0 0.0,\n" + "    0.0 1.0,\n" + "    1.0 0.0,\n" + "    1.0 1.0\n" + "  }\n"
			+ "  output {\n" + "    0.0,\n" + "    1.0,\n" + "    1.0,\n" + "    0.0,\n" + "  }\n" + "  \n" + "}";

	@Test
	public void createNetwork() {
		ScriptParser parser = new ScriptParser();
		Network network = parser.parseScript(getNetworkScript("sigmoid") + training_script);
		assertNotNull(network);
		assertEquals("feedforward", network.getType()); 
		assertEquals("basic_net", network.getName());
	}

	@Test
	public void testActivation() {
		trainNetwork("sigmoid");
	}

	private void trainNetwork(String activation) {


		ScriptParser parser = new ScriptParser();
		Network network = parser.parseScript(getNetworkScript(activation) + training_script);
		network.train();
		testXor(network);
	}

	private void testXor(Network network) {
		double[][] input = new double[][] { { 0.0, 0.0 }, { 1.0, 0.0 }, { 0.0, 1.0 }, { 1.0, 1.0 } };
		double[][] output = new double[][] { { 0.0 }, { 1.0 }, { 1.0 }, { 0.0 } };
		double[] result;
		for (int i = 0; i < 4; i++) {
			result = network.compute(input[i]);
			System.out.println("v" + i + " input: " + Arrays.toString(input[i]) + " output:" + Arrays.toString(result));
			assertEqualWithin(0.2, output[i], result);
		}
	}
	
	
	@Test
	public void testUsingScript() throws IOException {
		trainUsingScript("scripts/xor-1.neural");
		trainUsingScript("scripts/xor-2.neural");
	}
	
	@Test 
	public void testInMemoryOutput() throws IOException {
		testUsingScript("scripts/xor-3.neural");
	}
	
	@Test
	public void testCSVFileInput() throws IOException {
		testUsingScript("scripts/xor-4.neural");
	}
	
	@Test
	public void testExternalTrainingData() throws IOException {
		String script = FileUtils.readFileToString(new File("scripts/xor-1.neural"));
		ScriptParser parser = new ScriptParser();
		Network network = parser.parseScript(script);
		
		double[][] input = new double[][] { { 0.0, 0.0 }, { 1.0, 0.0 }, { 0.0, 1.0 }, { 1.0, 1.0 } };
		double[][] output = new double[][] { { 0.0 }, { 1.0 }, { 1.0 }, { 0.0 } };

		network.train(input, output);
		
		testXor(network); 
	}
	
	@Test
	public void testDefinedActivation() throws IOException {
		testUsingScript("scripts/activation-1-xor.neural");
	}
	
	@Test(expected=NeuralException.class)
	public void testInvalidActivationParameters() throws IOException {
		testUsingScript("scripts/invalid-1-xor.neural"); 
	}
	
	@Test(expected=NeuralException.class)
	public void testInvalidTestingCall() throws IOException {
		testUsingScript("scripts/invalid-2-xor.neural");
	}

	@Test(expected=NeuralException.class)
	public void testInvalidNetworkParams() throws IOException {
		testUsingScript("scripts/invalid-3-xor.neural");
	}
	
	@Test 
	public void testSigmoidParameters() throws IOException {
		testUsingScript("scripts/params-sigmoid-1.neural");
	}
	
	@Test
	public void testStepParameters() throws IOException {
		testUsingScript("scripts/params-step-1.neural");
	}
	
	private void trainUsingScript(String name) throws IOException {
		String script = FileUtils.readFileToString(new File(name));
		ScriptParser parser = new ScriptParser();
		Network network = parser.parseScript(script);
		network.train();
		testXor(network);
	}
	
	private void testUsingScript(String name) throws IOException {
		String script = FileUtils.readFileToString(new File(name));
		ScriptParser parser = new ScriptParser();
		Network network = parser.parseScript(script);
		network.train();
		network.compute();	
		
		MemoryTester tester = MemoryTesterStore.getInstance().retrieve("xor-3");
		
		assertEqualWithin(0.2, new double[] { 0 }, tester.lastOutput());
		
		double[][] input = new double[][] { { 0.0, 0.0 }, { 1.0, 0.0 }, { 0.0, 1.0 }, { 1.0, 1.0 } };
		double[][] output = new double[][] { { 0.0 }, { 1.0 }, { 1.0 }, { 0.0 } };
		
		for (int i=0; i<input.length; i++) {
			assertEqualWithin(0.2, input[i], tester.getInput()[i]);
			assertEqualWithin(0.2, output[i], tester.getOutput()[i]);
		}
	}

	@SuppressWarnings("unused")
	@Before
	public void infiniTestFix() { 
		EncogBasicNetwork n = new EncogBasicNetwork();
	}

}
