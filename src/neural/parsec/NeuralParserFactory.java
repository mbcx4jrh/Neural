package neural.parsec;

import java.util.ArrayList;
import java.util.List;

import neural.parsec.ast.AsExpression;
import neural.parsec.ast.DoubleParameter;
import neural.parsec.ast.IntegerParameter;
import neural.parsec.ast.Layer;
import neural.parsec.ast.NetworkBlock;
import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.NetworkExpression;
import neural.parsec.ast.Parameter;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.Tokens.Fragment;
import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.functors.Map2;
import org.codehaus.jparsec.functors.Map3;


public class NeuralParserFactory {
	
	
	
	static final String example = "network joe is hopfield {\n"+
	                              "  size 8\n" +
	                              "}";

	

	
	protected Parser<Integer> whitespaceInteger() {
		return Scanners.WHITESPACES.next(Scanners.INTEGER).map(new Map<String, Integer>() {

			public Integer map(String arg0) {
				return Integer.parseInt(arg0);
			}
				
		});
	}




//	protected Parser<List<Parameter>> block() {
//		return Parsers.between(Scanners.string("{"),
//				Scanners.WHITESPACES.skipMany().next(parameterList()), 
//				Scanners.WHITESPACES.skipMany().next(Scanners.string("}")));
//}


	protected Parser<AsExpression> asExpression() {
		return Scanners.string("is").next(Scanners.WHITESPACES)
									.next(Terminals.Identifier.TOKENIZER)
									.map(new Map<Fragment, AsExpression>(){

										public AsExpression map(Fragment from) {
											return new AsExpression(from.toString());
										}
			
									});
	}

	protected Parser<String> identifier() {
		return Terminals.Identifier.TOKENIZER.map(new Map<Fragment, String>(){

			public String map(Fragment from) {
				return from.toString();
			}
		});
	}

	protected Parser<NetworkExpression> networkExpression() {
		return Scanners.string("network").next(Scanners.WHITESPACES)
				.next(Terminals.Identifier.TOKENIZER)
				.map(new Map<Fragment, NetworkExpression>(){

					public NetworkExpression map(Fragment from) {
						return new NetworkExpression(from.toString());
					}

				});
	}


	protected Parser<NetworkDef> networkDef() {
		return Parsers.sequence(networkExpression(), 
							    Scanners.WHITESPACES.next(asExpression()), 
							    Parsers.between(Scanners.WHITESPACES.optional().next(Scanners.string("{")), 
							            		Scanners.WHITESPACES.optional().next(networkBlock()), 
							            		Scanners.WHITESPACES.optional().next(Scanners.string("}"))),
				new Map3<NetworkExpression, AsExpression, NetworkBlock, NetworkDef>() {

					@Override
					public NetworkDef map(NetworkExpression a, AsExpression b,
							NetworkBlock d) {
						return new NetworkDef(a.getName(), b.getType(), d);
					}
					
				});
	}
	
	protected Parser<NetworkBlock> networkBlock() {
		return Parsers.sequence(parameterList().optional(), 
				                Scanners.WHITESPACES.optional().next(layer().sepBy(Scanners.WHITESPACES)), 
				                new Map2<List<Parameter>, List<Layer>, NetworkBlock>() {

					@Override
					public NetworkBlock map(List<Parameter> a, List<Layer> b) {
						if (a == null) a = new ArrayList<Parameter>();
						return new NetworkBlock(a, b);
					}

			});
		
	}


	public Parser<NetworkDef> getNeuralParser() {
		return Scanners.WHITESPACES.skipMany().next(networkDef());
	}


	protected Parser<IntegerParameter> integerParameter() {
		return Parsers.sequence(Terminals.Identifier.TOKENIZER, whitespaceInteger(),
				new Map2<Fragment, Integer, IntegerParameter>() {

			@Override
			public IntegerParameter map(Fragment name, Integer value) {
				return new IntegerParameter(name.toString(), value);
			}
			
		});
	}


	protected Parser<DoubleParameter> doubleParameter() {
		return Parsers.sequence(Terminals.Identifier.TOKENIZER, whitespaceDouble(),
				new Map2<Fragment, Double, DoubleParameter>() {

			@Override
			public DoubleParameter map(Fragment name, Double value) {
				return new DoubleParameter(name.toString(), value);
			}
			
		});
	}


	protected Parser<Double> whitespaceDouble() {
		return Scanners.WHITESPACES.next(Scanners.DECIMAL).map(new Map<String, Double>() {

			public Double map(String arg0) {
				return Double.parseDouble(arg0);
			}
				
		});
	}
	
	protected Parser<Parameter> parameter() {
		//return Parsers.or(doubleParameter(), integerParameter());
		return Parsers.longer(integerParameter(), doubleParameter());
	}
	
	protected Parser<List<Parameter>> parameterList() {
		return Parsers.between(Scanners.string("parameters {").next(Scanners.WHITESPACES.optional()), 
				                             parameter().sepBy(Scanners.WHITESPACES), 
				                             Scanners.WHITESPACES.optional().next(Scanners.string("}")));
	}
	
	protected Parser<Integer> size() {
		return Scanners.string("size").next(whitespaceInteger());
	}

	protected Parser<String> activation() {
		return Scanners.string("activation").next(Scanners.WHITESPACES).next(identifier());
	}
	
	protected Parser<Layer> layer() {
		return Scanners.string("layer")
				       .next(Scanners.WHITESPACES)
				       .next(Parsers.between(Scanners.string("{"), 
				                    		 Scanners.WHITESPACES
				                    		 .next(Parsers.sequence(activation(), 
				                    				          Scanners.WHITESPACES 
				                    				                  .next(size()),
				                    				                  new Map2<String, Integer, Layer>() {
				                    				                	  public Layer map(String s, Integer i) {
				                    				                		  return new Layer(s, i);
				                    				                	  }
				                    				                  })), 
				                    				                  
				                    		 Scanners.WHITESPACES.next(Scanners.string("}"))));
	}
}
