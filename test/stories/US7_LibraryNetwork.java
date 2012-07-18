package stories;

import static org.junit.Assert.assertEquals;
import neural.Network;
import neural.NetworkInfo;
import neural.ScriptParser;

import org.junit.Before;

public class US7_LibraryNetwork {
	
	private ScriptParser parser;
	
	private String hopfield1 = "network joe is hopfield:" +
							   "  size 4\n";
	
	@Before public void setUp() {
		parser = new ScriptParser();
	}
	
	public void hopfield() {
		Network net = parser.parseScript(hopfield1);
		NetworkInfo info = net.getInfo();
		assertEquals("Network name", "joe", info.getName());
		assertEquals("Network type", "hopfield", info.getType());
		assertEquals("Network size", 5, info.getNetworkSize());
	}


}
