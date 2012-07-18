package stories;

import static org.junit.Assert.assertNotNull;

import neural.Network;
import neural.ScriptParser;

import org.junit.Test;



public class US01_Basics {
	
	private String GIBBERISH = "network joe is hopfield { size 2}";

	@Test public void simpleInstantiation() {
		ScriptParser parser = new ScriptParser();
		Network network = parser.parseScript(GIBBERISH);
		assertNotNull("Null network", network);
	}
}
