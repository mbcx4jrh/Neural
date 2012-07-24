package neural.parsec;

import java.util.ArrayList;
import java.util.List;

import neural.parsec.ast.AsExpression;
import neural.parsec.ast.Data;
import neural.parsec.ast.DoubleParameter;
import neural.parsec.ast.ErrorCondition;
import neural.parsec.ast.IntegerParameter;
import neural.parsec.ast.Layer;
import neural.parsec.ast.NetworkBlock;
import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.NetworkExpression;
import neural.parsec.ast.Parameter;
import neural.parsec.ast.TrainingDef;

import org.codehaus.jparsec.Parser;
import org.codehaus.jparsec.Parsers;
import org.codehaus.jparsec.Scanners;
import org.codehaus.jparsec.Terminals;
import org.codehaus.jparsec.Tokens.Fragment;
import org.codehaus.jparsec.functors.Map;
import org.codehaus.jparsec.functors.Map2;
import org.codehaus.jparsec.functors.Map3;
import org.codehaus.jparsec.functors.Map4;


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


	public Parser<Script> getNeuralParser() {

		return Scanners.WHITESPACES.optional()
				.next(Parsers.sequence(networkDef(),
						               Scanners.WHITESPACES.optional().next(training().optional()),
						               new Map2<NetworkDef, TrainingDef, Script>() {

										@Override
										public Script map(NetworkDef a,
												TrainingDef b) {
											return new Script(a, b);
										}
					
				})).followedBy(Scanners.WHITESPACES.optional());
	
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
		return Scanners.WHITESPACES.next(decimal());
	}
	
	protected Parser<Double> decimal() {
		return Scanners.DECIMAL.map(new Map<String, Double>() {

			public Double map(String arg0) {
				return Double.parseDouble(arg0);
			}
				
		});
	}
	
	protected Parser<String> type() {
		return Scanners.string("type")
				       .next(Scanners.WHITESPACES)
				       .next(identifier());
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
	
	protected Parser<Double> percentage() {
		return Scanners.WHITESPACES.next(Scanners.DECIMAL)
				                   .followedBy(Scanners.string("%"))
				                   .map(new Map<String, Double>() {

									@Override
									public Double map(String from) {
										return new Double(Double.valueOf(from)*0.01);
									}
				                	   
				                   });
	}
	
	protected Parser<ErrorCondition> error() {
		return Scanners.string("error")
				       .next(percentage())
				       .map(new Map<Double, ErrorCondition>() {

							@Override
							public ErrorCondition map(Double from) {
								return new ErrorCondition(from);
							}
					    	   
				       });
	}
	
	protected Parser<List<Double>> dataRow() {
		return decimal().sepBy(Scanners.WHITESPACES);
	}
	
	protected Parser<List<List<Double>>> dataBlock() {
		return Parsers.between(Scanners.string("{").next(Scanners.WHITESPACES.optional()), 
				               dataRow().sepBy(Scanners.string(",").next(Scanners.WHITESPACES.optional())),
				               Scanners.WHITESPACES.optional().next(Scanners.string("}")));
	}
	
	protected Parser<Data> inputBlock() {
		return namedDataBlock("input");
	}
	
	protected Parser<Data> outputBlock() {
		return namedDataBlock("output");
	}
	
	protected Parser<Data> namedDataBlock(String name) {
		return Scanners.string(name)
				       .next(Scanners.WHITESPACES)
				       .next(dataBlock().map(new Map<List<List<Double>>, Data>() {

						@Override
						public Data map(List<List<Double>> from) {
							return new Data(from);
						}
				    	   
				       }));
	}
	
	protected Parser<TrainingDef> training() {
		return Scanners.string("training")
				       .next(Scanners.WHITESPACES)
				       .next(Parsers.between(Scanners.string("{"),
				    	             Parsers.sequence(Scanners.WHITESPACES.next(type()),
				    	            		          Scanners.WHITESPACES.next(error()),
				    	            		          Scanners.WHITESPACES.next(inputBlock()),
				    	            		          Scanners.WHITESPACES.next(outputBlock()),
				    	            		          new Map4<String, ErrorCondition, Data, Data, TrainingDef>() {

														@Override
														public TrainingDef map(
																String a,
																ErrorCondition b,
																Data c, 
																Data d) {
															return new TrainingDef(a, b, c, d);
														}
				    	   
				       								  }),
				    		         Scanners.WHITESPACES.optional().next(Scanners.string("}"))));
	}
}
