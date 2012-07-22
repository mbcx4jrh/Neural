package neural.parsec;

import static org.junit.Assert.assertEquals;

import org.codehaus.jparsec.error.ParserException;
import org.junit.Before;
import org.junit.Test;

public class TestNeuralParserFactory {
	
	private NeuralParserFactory npf;
	
	@Before public void setUp() {
		npf = new NeuralParserFactory();
	}
	
	@Test public void testFactoryMethod() {
		assertEquals(npf.getNeuralParser().parse(" network jim is tiger { size 45}").toString(), 
				"Network: jim, type: tiger, params: [size 45]");
	}
	

	
	@Test public void testParameterList() {
		assertEquals(npf.parameterList().parse("p1 23 p2 3.4").toString(), 
				"[p1 23, p2 3.4]");
		assertEquals(npf.parameterList().parse("p1 23").toString(), 
				"[p1 23]");
		assertEquals(npf.parameterList().parse("").toString(), 
				"[]");
	}
	
	
	@Test public void testParameter() {
		assertEquals(npf.parameter().parse("p1 23").getClass(), IntegerParameter.class);
		assertEquals(npf.parameter().parse("p1 23"), new IntegerParameter("p1", 23));
		assertEquals(npf.parameter().parse("p1 2.3"), new DoubleParameter("p1", 2.3));
		assertEquals(npf.parameter().parse("p1 2.3").getClass(), DoubleParameter.class);
	}
	
	@Test public void testWhitespaceDouble() {
		assertEquals(npf.whitespaceDouble().parse(" 0.1"), new Double(0.1));
		assertEquals(npf.whitespaceDouble().parse("     0.1"), new Double(0.1));
		assertEquals(npf.whitespaceDouble().parse(" 1231.1231231"), new Double(1231.1231231));
	}
	
	@Test public void testDoubleParam() {
		assertEquals(npf.doubleParameter().parse("df4 0.1"), new DoubleParameter("df4", new Double(0.1)));
		assertEquals(npf.doubleParameter().parse("df4     0.1"), new DoubleParameter("df4", new Double(0.1)));
		assertEquals(npf.doubleParameter().parse("p1 2.3"), new DoubleParameter("p1", 2.3));
	}
	
	
	@Test public void testIntegerParam() {
		assertEquals(npf.integerParameter().parse("blah 23"), new IntegerParameter("blah", new Integer(23)));
		assertEquals(npf.integerParameter().parse("blah   23"), new IntegerParameter("blah", new Integer(23)));
	}
	
	@Test public void testNetworkDef() {
		//test with methods
		NetworkDef net = npf.networkDef().parse("network joe is hopfield { size 5 }");
		assertEquals(net.getName(), "joe");
		assertEquals(net.getType(), "hopfield");

		
		//use toString for rest of tests..
		assertEquals(npf.networkDef().parse("network joe  is kramer{size 90}").toString(), 
				"Network: joe, type: kramer, params: [size 90]");
	}
	
	@Test public void testNetworkExpression() {
		assertEquals( npf.networkExpression().parse("network joe"), new NetworkExpression("joe"));
	}
	
	@Test public void testAsExpression() {
		assertEquals(npf.asExpression().parse("is type1"), new AsExpression("type1"));
	}
	
	@Test public void testBlock() {
		assertEquals(npf.block().parse("{ size 2 }").toString(), "[size 2]");
		assertEquals(npf.block().parse("{size 2 }").toString(), "[size 2]");
		assertEquals(npf.block().parse("{size 2}").toString(), "[size 2]");
		assertEquals(npf.block().parse("{ p1 20.2 p2 0 }").toString(),
				"[p1 20.2, p2 0]");
		assertEquals(npf.block().parse("{ \n\tp1 20.2\n p2 0\n }").toString(),
				"[p1 20.2, p2 0]");

	}
	

	
	
	@Test public void testWhitespaceInteger() {
		assertEquals(npf.whitespaceInteger().parse("   4"), new Integer(4));
		assertEquals(npf.whitespaceInteger().parse(" 98"), new Integer(98));
	}
	
	@Test(expected=ParserException.class)
	public void testCrappyGrammar() {
		npf.block().parse("{ -blah 234}");
	}

}
