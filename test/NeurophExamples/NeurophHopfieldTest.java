package NeurophExamples;

import java.io.IOException;

import neural.ScriptParser;
import neural.networks.neuroph.NeurophHopfieldNetwork;

import org.junit.Before;
import org.junit.Test;

import common.HopfieldNetworkTest;

public class NeurophHopfieldTest {
	
	
	@Test
	public void test() throws IOException {
		HopfieldNetworkTest tester = new HopfieldNetworkTest();
		ScriptParser parser = new ScriptParser();
		parser.setUnderlyingLibrary("neural.networks.NeurophNetworkFactory");
		tester.setParser(parser);
		tester.hopfieldTest();
	}
	
	@Before
	public void pokeInfinitest() {
		@SuppressWarnings("unused")
		NeurophHopfieldNetwork n = new NeurophHopfieldNetwork();
	}

}
