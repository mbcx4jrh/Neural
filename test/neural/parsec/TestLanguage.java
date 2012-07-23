package neural.parsec;

import static org.junit.Assert.*;
import neural.Network;
import neural.ScriptParser;

import org.junit.Before;
import org.junit.Test;

public class TestLanguage {
	
	private String basic = "network basic_net is basic {\n" +
				           "    layer {\n" +
				           "        activation input\n" +
				           "        size 2\n" +
				           "    }\n	 " +
				           "    layer {\n" +
				           "        activation sigmoid\n" +
				           "        size 3\n" +
				           "    }\n" +
				           "    layer {\n" +
				           "        activation sigmoid\n" +
				           "        size 1\n" +
				           "    }\n"+
				           "}"; 
	
	private ScriptParser parser;
	
	@Before public void setup() {
		parser = new ScriptParser();
	}
	
	
	@Test public void testBasicNet() {
		Network network = parser.parseScript(basic);
	    //assertNotNull(network);
	}

}
