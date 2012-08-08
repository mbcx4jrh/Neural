package EncogExamples;

import java.io.IOException;

import neural.ScriptParser;

import org.junit.Test;

import common.HopfieldNetworkTest;

public class EncogHopfieldTest {
	
	@Test
	public void test() throws IOException {
		HopfieldNetworkTest tester = new HopfieldNetworkTest();
		ScriptParser parser = new ScriptParser();
		parser.setUnderlyingLibrary("neural.networks.EncogNetworkFactory");
		tester.setParser(parser);
		tester.hopfieldTest();
	} 

}
