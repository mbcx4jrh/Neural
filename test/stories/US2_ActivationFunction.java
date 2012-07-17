package stories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import neural.Network;
import neural.NetworkInfo;
import neural.ScriptParser;

import org.junit.Before;

public class US2_ActivationFunction {
	
	private String simple1 = "define activation blah as dooby";
	//private String simple2 = "define activation thingy as threshold 0.5";
	private ScriptParser parser;
	
	@Before public void setUp() {
		parser = new ScriptParser();
	}
	
	 public void defineFunction() {
		NetworkInfo info1 = infoFromScript(simple1);
		//NetworkInfo info2 = infoFromScript(simple2);
		String activationInfo1 = info1.getActivationFunction("blah");
		assertNotNull("Null activation function info", activationInfo1);
		assertEquals("Wrong info for info1", info1, "Name: blah"); 
	}

	private NetworkInfo infoFromScript(String script) {
		Network network = parser.parseScript(script);
		assertNotNull("Null network", network);
		NetworkInfo info = network.getInfo();
		assertNotNull("Info null", info);
		return info;
	}
}
