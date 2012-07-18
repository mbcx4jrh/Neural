import static org.junit.Assert.assertEquals;

import org.codehaus.jparsec.Parser;

import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.Tokens.Fragment;
import org.junit.Test;




public class Prototyping {
	
	
	//private final static Terminals NOUNS = 
	
	
	@Test public void prototype() {
		assertEquals(parseString().parse("abc").toString(), "abc");
		assertEquals(parseString().parse("asDSD2_ff").toString(), "asDSD2_ff");
		
		
	}

	private Parser<Fragment> parseString() {
		return Terminals.Identifier.TOKENIZER;
	}
	
	

}
