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
	
	@Test public void testNetworkExpression() {
		NetworkExpression net = npf.networkExpression().parse("network joe");
		
	}
	
	@Test public void testAsExpression() {
		assertEquals(npf.asExpression().parse("as type1"), new AsExpression("type1"));
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
