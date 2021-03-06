package NeurophExamples;

import static neural.test.Assert.assertEqualWithin;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import neural.Network;
import neural.ScriptParser;
import neural.tester.MemoryTester;
import neural.tester.MemoryTesterStore;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

public class NeurophXORNetwork { 

	private ScriptParser parser;
	
	private String getNetworkScript(String activation) {
		return "network basic_net is feedforward {\n" + "    layer {\n" + "        activation input\n"
				+ "        size 2\n" + "        biased\n" + "    }\n	 " + "    layer {\n" + "        activation "
				+ activation + "\n" + "        size 3\n" + "        biased \n" + "    }\n" + "    layer {\n"
				+ "        activation sigmoid\n" + "        size 1\n" + "    }\n" + "}\n";
	}

	private String training_script = "training {\n" + "  type resilient_propagation\n" + " epochs 1000 restart 5 error 0.1%\n"
			+ "  input {\n" + "    0.0 0.0,\n" + "    0.0 1.0,\n" + "    1.0 0.0,\n" + "    1.0 1.0\n" + "  }\n"
			+ "  output {\n" + "    0.0,\n" + "    1.0,\n" + "    1.0,\n" + "    0.0,\n" + "  }\n" + "  \n" + "}";

	@Before
	public void initParser() {  
		parser = new ScriptParser();
		parser.setUnderlyingLibrary("neural.networks.NeurophNetworkFactory");
	}
	
	@Test
	public void createNetwork() {
		Network network = parser.parseScript(getNetworkScript("sigmoid") + training_script);
		assertNotNull(network);
		assertEquals("feedforward", network.getType()); 
		assertEquals("basic_net", network.getName());
	}

	@Test
	public void testActivation() {
		trainNetwork("sigmoid");
		trainNetwork("tanh");
	}

	private void trainNetwork(String activation) {


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
		testUsingScript("scripts/xor-3.neural");
	}
	
	private void trainUsingScript(String name) throws IOException {
		String script = FileUtils.readFileToString(new File(name));
		Network network = parser.parseScript(script);
		network.train();
		testXor(network);
	}
	
	private void testUsingScript(String name) throws IOException {
		String script = FileUtils.readFileToString(new File(name));
		Network network = parser.parseScript(script);
		network.train();
		network.compute();	
		
		MemoryTester tester = MemoryTesterStore.getInstance().retrieve("xor-3");
		

		
		double[][] input = new double[][] { { 0.0, 0.0 }, { 1.0, 0.0 }, { 0.0, 1.0 }, { 1.0, 1.0 } };
		double[][] output = new double[][] { { 0.0 }, { 1.0 }, { 1.0 }, { 0.0 } };
		
		for (int i=0; i<input.length; i++) {
			assertEqualWithin(0.2, input[i], tester.getInput()[i]);
			assertEqualWithin(0.2, output[i], tester.getOutput()[i]);
		}
	}


	@Before
	public void infiniTestFix() {
		//NeurophFeedForwardNetwork n = new NeurophFeedForwardNetwork(); 
	}

}
