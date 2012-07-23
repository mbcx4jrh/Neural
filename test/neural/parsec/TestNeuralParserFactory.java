package neural.parsec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import neural.parsec.ast.AsExpression;
import neural.parsec.ast.DoubleParameter;
import neural.parsec.ast.ErrorCondition;
import neural.parsec.ast.IntegerParameter;
import neural.parsec.ast.Layer;
import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.NetworkExpression;

import org.codehaus.jparsec.error.ParserException;
import org.junit.Before;
import org.junit.Test;

public class TestNeuralParserFactory {
	
	private NeuralParserFactory npf;
	
	@Before public void setUp() {
		npf = new NeuralParserFactory();
	}
	
	@Test public void testFactoryMethod() {
		assertEquals(npf.getNeuralParser().parse(" network jim is tiger { parameters { size 45 } }").toString(), 
				"Network: jim, type: tiger, params: [size 45], layers: []");
	}
	
	@Test public void testLayerdNetwork() {
		assertEquals("Network: jim, type: tiger, params: [size 45], layers: [(activation sigmoid - size 3)]",
				npf.networkDef().parse("network jim is tiger { parameters { size 45 } layer { activation sigmoid size 3 } }").toString());
		assertEquals("Network: jim, type: tiger, params: [], layers: [(activation sigmoid - size 3)]",
				npf.networkDef().parse("network jim is tiger { layer { activation sigmoid size 3 } }").toString());
	}
	
	@Test public void testNetworkBlock() {
		assertEquals("params: [size 45], layers: [(activation sigmoid - size 3)]", 
				npf.networkBlock().parse("parameters { size 45 } layer { activation sigmoid size 3 }").toString());
	}
	
	@Test public void testDataBlock() {
		assertEquals("[[0.0, 0.1, 1.0], [1.2, 20.0, 0.0010]]", 
				npf.dataBlock().parse("{ 0.0 0.1 1.0, 1.2 20 0.001 }").toString());
		assertEquals("[[0.0], [1.2]]", 
				npf.dataBlock().parse("{ 0.0, 1.2 }").toString());
		assertEquals("[[0.0, 0.1, 1.0]]", 
				npf.dataBlock().parse("{ 0.0 0.1 1.0}").toString());
	
	}
	
	@Test public void testDataRow() {
		assertEquals("[0.0, 0.1, 1.0]", npf.dataRow().parse("0.0 0.1 1.0").toString());
	}
	
	@Test public void testDecimal() {
		assertEquals(new Double(0.1), npf.decimal().parse("0.1"));
	}
	
	@Test public void testType() {
		assertEquals("doodaa", npf.type().parse("type doodaa"));
	}
	
	@Test public void testError() {
		assertEquals(new ErrorCondition(0.01), npf.error().parse(" error 1%"));
	}
	
	@Test public void testPercentage() {
		assertEquals(new Double(0.01), npf.percentage().parse(" 1%"));
		assertEquals(new Double(1.0), npf.percentage().parse(" 100%"));
		assertEquals(new Double(5.0), npf.percentage().parse(" 500%"));
		assertEquals(new Double(0.001), npf.percentage().parse(" 0.1%"));
	}

	
	@Test public void testLayer() {
		Layer l = npf.layer().parse("layer { activation sigmoid size 2 }");
		assertNotNull(l);
		assertEquals("sigmoid", l.getActivation());
		assertEquals(2, l.getSize());
	}
	
	@Test public void testIdentifier() {
		assertEquals("blunder", npf.identifier().parse("blunder"));
	}
	
	@Test public void testActivation() {
		assertEquals("sigmoid", npf.activation().parse("activation sigmoid"));
	}
	
	@Test public void testSize() {
		assertEquals(new Integer(2), npf.size().parse("size 2"));
	}
	
	@Test public void testParameterList() {
		assertEquals(npf.parameterList().parse("parameters { p1 23 p2 3.4 }").toString(), 
				"[p1 23, p2 3.4]");
		assertEquals(npf.parameterList().parse("parameters {p1 23 }").toString(), 
				"[p1 23]");
		assertEquals(npf.parameterList().parse("parameters {p1 23}").toString(), 
				"[p1 23]");
		assertEquals(npf.parameterList().parse("parameters { }").toString(), 
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
	
	@Test public void testHopfieldNetworkDef() {
		//test with methods
		NetworkDef net = npf.networkDef().parse("network joe is hopfield { parameters { size 5 } }");
		assertEquals(net.getName(), "joe");
		assertEquals(net.getType(), "hopfield");

		
		//use toString for rest of tests..
		assertEquals(npf.networkDef().parse("network joe  is kramer{parameters {size 90}}").toString(), 
				"Network: joe, type: kramer, params: [size 90], layers: []");
	}
	
	@Test public void testNetworkExpression() {
		assertEquals( npf.networkExpression().parse("network joe"), new NetworkExpression("joe"));
	}
	
	@Test public void testAsExpression() {
		assertEquals(npf.asExpression().parse("is type1"), new AsExpression("type1"));
	}
	

	

	
	
	@Test public void testWhitespaceInteger() {
		assertEquals(npf.whitespaceInteger().parse("   4"), new Integer(4));
		assertEquals(npf.whitespaceInteger().parse(" 98"), new Integer(98));
	}
	
	@Test(expected=ParserException.class)
	public void testCrappyGrammar() {
		npf.parameterList().parse("{ -blah 234}");
	}

}
