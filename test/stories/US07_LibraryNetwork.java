package stories;

import static org.junit.Assert.assertEquals;
import neural.Network;
import neural.ScriptParser;

import org.junit.Before;
import org.junit.Test;

public class US07_LibraryNetwork {
	
	private ScriptParser parser;
	
	private String hopfield1 = " network joe is hopfield {\n" +
	                           "  parameters {\n"+
							   "    size 5\n"+
	                           "  }\n" +
							   "}";
	
	@Before public void setUp() {
		parser = new ScriptParser();
	}
	
	@Test public void hopfield() { 
		Network net = parser.parseScript(hopfield1);
		assertEquals("Network name", "joe", net.getName());
		assertEquals("Network type", "hopfield", net.getType());
		//assertEquals("Network size", 5, net.getSize());
	}


}
