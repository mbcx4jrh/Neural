package EncogExamples;

import static neural.test.Assert.assertEqualWithin;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import neural.Network;
import neural.ScriptParser;
import neural.networks.encog.EncogBasicNetwork;

import org.junit.Before;
import org.junit.Test;

public class XORNetwork {

	private String getNetworkScript(String activation) {
		return "network basic_net is feedforward {\n" + "    layer {\n" + "        activation input\n"
				+ "        size 2\n" + "        biased\n" + "    }\n	 " + "    layer {\n" + "        activation "
				+ activation + "\n" + "        size 3\n" + "        biased \n" + "    }\n" + "    layer {\n"
				+ "        activation sigmoid\n" + "        size 1\n" + "    }\n" + "}\n";
	}

	private String training_script = "training {\n" + "  type resilient_propagation\n" + " restart 5 error 0.1%\n"
			+ "  input {\n" + "    0.0 0.0,\n" + "    0.0 1.0,\n" + "    1.0 0.0,\n" + "    1.0 1.0\n" + "  }\n"
			+ "  output {\n" + "    0.0,\n" + "    1.0,\n" + "    1.0,\n" + "    0.0,\n" + "  }\n" + "  \n" + "}";

	private String fail_training_script = "training {\n" + "  type resilient_propagation\n" + "  error 0.001% epoch 10\n"
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
		trainNetwork("tanh");
	}

	private void trainNetwork(String activation) {

		double[][] input = new double[][] { { 0.0, 0.0 }, { 1.0, 0.0 }, { 0.0, 1.0 }, { 1.0, 1.0 } };
		double[][] output = new double[][] { { 0.0 }, { 1.0 }, { 1.0 }, { 0.0 } };

		ScriptParser parser = new ScriptParser();
		Network network = parser.parseScript(getNetworkScript(activation) + training_script);
		network.train();
		double[] result = new double[1];
		for (int i = 0; i < 4; i++) {
			network.compute(input[i], result);
			System.out.println("v" + i + " input: " + Arrays.toString(input[i]) + " output:" + Arrays.toString(result));
			assertEqualWithin(0.1, output[i], result);
		}
	}

	@SuppressWarnings("unused")
	@Before
	public void infiniTestFix() {
		EncogBasicNetwork n = new EncogBasicNetwork();
	}

}
