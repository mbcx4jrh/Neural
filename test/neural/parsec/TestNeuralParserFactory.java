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
				"Network: jim, type: tiger, size: 45");
	}
	
	@Test public void testWhitespaceDouble() {
		assertEquals(npf.whitespaceDouble().parse(" 0.1"), new Double(0.1));
		assertEquals(npf.whitespaceDouble().parse("     0.1"), new Double(0.1));
		assertEquals(npf.whitespaceDouble().parse(" 1231.1231231"), new Double(1231.1231231));
	}
	
	@Test public void testDoubleParam() {
		assertEquals(npf.doubleParameter().parse("df4 0.1"), new DoubleParameter("df4", new Double(0.1)));
		assertEquals(npf.doubleParameter().parse("df4     0.1"), new DoubleParameter("df4", new Double(0.1)));
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
		assertEquals(net.getSize(), 5);
		
		//use toString for rest of tests..
		assertEquals(npf.networkDef().parse("network joe  is kramer{size 90}").toString(), 
				"Network: joe, type: kramer, size: 90");
	}
	
	@Test public void testNetworkExpression() {
		assertEquals( npf.networkExpression().parse("network joe"), new NetworkExpression("joe"));
	}
	
	@Test public void testAsExpression() {
		assertEquals(npf.asExpression().parse("is type1"), new AsExpression("type1"));
	}
	
	@Test public void testBlock() {
		assertEquals(npf.block().parse("{ size 2 }"), new SizeExpression(2));
		assertEquals(npf.block().parse("{size 2 }"), new SizeExpression(2));
		assertEquals(npf.block().parse("{size 2}"), new SizeExpression(2));
	}
	
	@Test public void testExpression() {
		assertEquals(npf.expression().parse("size 23"), new SizeExpression(23));
	}
	
	@Test public void testSizeExpression() {
		assertEquals(npf.sizeExpression().parse("size 5"), new SizeExpression(5));
		assertEquals(npf.sizeExpression().parse("size     2"), new SizeExpression(2));
	}
	
	@Test public void testWhitespaceInteger() {
		assertEquals(npf.whitespaceInteger().parse("   4"), new Integer(4));
		assertEquals(npf.whitespaceInteger().parse(" 98"), new Integer(98));
	}
	
	@Test(expected=ParserException.class)
	public void testCrappyGrammar() {
		npf.block().parse("{ blah 234}");
	}

}
