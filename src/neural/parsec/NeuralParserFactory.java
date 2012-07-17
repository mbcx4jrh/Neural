package neural.parsec;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.Tokens.Fragment;
import org.codehaus.jparsec.functors.Map;


public class NeuralParserFactory {
	
	
	
	static final String example = "network joe is hopfield {\n"+
	                              "  size 8\n" +
	                              "}";

	

	

	
	protected Parser<SizeExpression> sizeExpression() {
		return Scanners.string("size").next(whitespaceInteger()).map(new Map<Integer,SizeExpression>(){

			public SizeExpression map(Integer arg0) {
				return new SizeExpression(arg0);
			}
			
		});
	}


	protected Parser<Integer> whitespaceInteger() {
		return Scanners.WHITESPACES.next(Scanners.INTEGER).map(new Map<String, Integer>() {

			public Integer map(String arg0) {
				return Integer.parseInt(arg0);
			}
				
		});
	}


	protected Parser<SizeExpression> expression() {
		return Parsers.or(sizeExpression());
	}


	public Parser<SizeExpression> block() {
		return Parsers.between(Scanners.string("{"),
				               Scanners.WHITESPACES.skipMany().next(expression()), 
				               Scanners.WHITESPACES.skipMany().next(Scanners.string("}")));
	}


	public Parser<AsExpression> asExpression() {
		return Scanners.string("as").next(Scanners.WHITESPACES)
									.next(Terminals.Identifier.TOKENIZER)
									.map(new Map<Fragment, AsExpression>(){

										public AsExpression map(Fragment from) {
											return new AsExpression(from.toString());
										}
			
									});
	}


	public Parser<NetworkExpression> networkExpression() {
		return Scanners.string("network").next(Scanners.WHITESPACES)
				.next(Terminals.Identifier.TOKENIZER)
				.map(new Map<Fragment, NetworkExpression>(){

					public NetworkExpression map(Fragment from) {
						return new NetworkExpression(from.toString());
					}

				});
	}
}
