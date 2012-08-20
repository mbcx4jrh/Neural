package EncogExamples;

import java.io.File;
import java.io.IOException;

import neural.Network;
import neural.ScriptParser;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

public class EncogART1 {
	
	private Network network;
	
	@Before
	public void loadNetwork() throws IOException {
		ScriptParser parser = new ScriptParser();
		String script = FileUtils.readFileToString(new File("scripts/ART1-1.neural"));
		network = parser.parseScript(script);
	}
	
	@Test
	public void testEncogExample() {
	
	}


}
