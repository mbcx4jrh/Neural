package neural.parsec;

import java.util.ArrayList;
import java.util.List;

import neural.parsec.ast.ActivationDefinition;
import neural.parsec.ast.Data;
import neural.parsec.ast.DataDef;
import neural.parsec.ast.DataItem;
import neural.parsec.ast.DoubleParameter;
import neural.parsec.ast.ExternalInput;
import neural.parsec.ast.ExternalOutput;
import neural.parsec.ast.IntegerParameter;
import neural.parsec.ast.IsExpression;
import neural.parsec.ast.Layer;
import neural.parsec.ast.NetworkBlock;
import neural.parsec.ast.NetworkDef;
import neural.parsec.ast.NetworkExpression;
import neural.parsec.ast.NormaliseDef;
import neural.parsec.ast.Parameter;
import neural.parsec.ast.TestingDef;
import neural.parsec.ast.TestingOutput;
import neural.parsec.ast.TrainingDef;
import neural.parsec.ast.training.ErrorTrainingItem;
import neural.parsec.ast.training.InlineInputItem;
import neural.parsec.ast.training.TrainingEpochItem;
import neural.parsec.ast.training.TrainingOutputItem;
import neural.parsec.ast.training.TrainingRestartItem;
import neural.parsec.ast.training.TrainingTypeItem;

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

	protected Parser<Integer> whitespaceInteger() {
		return Scanners.WHITESPACES.next(Scanners.INTEGER).map(new Map<String, Integer>() {

			public Integer map(String arg0) {
				return Integer.parseInt(arg0);
			}

		});
	}

	protected Parser<IsExpression> asExpression() {
		return Scanners.string("is").next(Scanners.WHITESPACES).next(Terminals.Identifier.TOKENIZER)
				.map(new Map<Fragment, IsExpression>() {

					public IsExpression map(Fragment from) {
						return new IsExpression(from.toString());
					}

				});
	}

	protected Parser<String> identifier() {
		return Terminals.Identifier.TOKENIZER.map(new Map<Fragment, String>() {

			public String map(Fragment from) {
				return from.toString();
			}
		});
	}

	protected Parser<NetworkExpression> networkExpression() {
		return Scanners.string("network").next(Scanners.WHITESPACES).next(Terminals.Identifier.TOKENIZER)
				.map(new Map<Fragment, NetworkExpression>() {

					public NetworkExpression map(Fragment from) {
						return new NetworkExpression(from.toString());
					}

				});
	}

	protected Parser<NetworkDef> networkDef() {
		return Parsers.sequence(networkExpression(), Scanners.WHITESPACES.next(asExpression()), Parsers.between(
				Scanners.WHITESPACES.optional().next(Scanners.string("{")),
				Scanners.WHITESPACES.optional().next(networkBlock()),
				Scanners.WHITESPACES.optional().next(Scanners.string("}"))),
				new Map3<NetworkExpression, IsExpression, NetworkBlock, NetworkDef>() {

					@Override
					public NetworkDef map(NetworkExpression a, IsExpression b, NetworkBlock d) {
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
						if (a == null)
							a = new ArrayList<Parameter>();
						return new NetworkBlock(a, b);
					}

				});

	}

	public Parser<Script> getNeuralParser() {

		return Scanners.WHITESPACES
				.optional()
				.next(Parsers.sequence(Scanners.WHITESPACES.optional().next(activationDefinition().many()),
						               Scanners.WHITESPACES.optional().next(networkDef()), 
						               Scanners.WHITESPACES.optional().next(training().optional()),
						               Scanners.WHITESPACES.optional().next(testing().optional()),
						new Map4<List<ActivationDefinition>, NetworkDef, TrainingDef, TestingDef, Script>() {

							@Override
							public Script map(List<ActivationDefinition> a, NetworkDef b, TrainingDef c, TestingDef d) {
								return new Script(a, b, c, d);
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
	
	protected Parser<Double> positiveDecimal() {
		return Scanners.DECIMAL.map(new Map<String, Double>() {

			public Double map(String arg0) {
				return Double.parseDouble(arg0);
			}

		});
	}

	protected Parser<Double> decimal() {
		return Parsers.or(negativeDecimal(), positiveDecimal()); 
	}
	
	protected Parser<Double> negativeDecimal() {
		return Scanners.string("-").next(positiveDecimal()
				.map(new Map<Double, Double>() {

					@Override
					public Double map(Double from) {
						return new Double(0-from.doubleValue());
					}
				
				}));
	}

	protected Parser<String> type() {
		return Scanners.string("type").next(Scanners.WHITESPACES).next(identifier());
	}

	protected Parser<Parameter> parameter() {
		// return Parsers.or(doubleParameter(), integerParameter());
		return Parsers.longer(integerParameter(), doubleParameter());
	}

	protected Parser<List<Parameter>> parameterList() {
		return Parsers.between(Scanners.string("parameters {").next(Scanners.WHITESPACES.optional()), parameter()
				.sepBy(Scanners.WHITESPACES), Scanners.WHITESPACES.optional().next(Scanners.string("}")));
	}

	protected Parser<Integer> size() {
		return Scanners.string("size").next(whitespaceInteger());
	}

	protected Parser<String> activation() {
		return Scanners.string("activation").next(Scanners.WHITESPACES).next(identifier());
	}

	protected Parser<Layer> layer() {
		return Scanners
				.string("layer")
				.next(Scanners.WHITESPACES)
				.next(Parsers.between(Scanners.string("{"), Scanners.WHITESPACES.next(Parsers.sequence(activation(),
						Scanners.WHITESPACES.next(size()), Scanners.WHITESPACES.next(biased()).atomic().optional(),
						new Map3<String, Integer, Boolean, Layer>() {
							public Layer map(String s, Integer i, Boolean b) {
								return new Layer(s, i, b);
							}
						})),

				Scanners.WHITESPACES.next(Scanners.string("}"))));
	}

	protected Parser<Double> percentage() {
		return Scanners.WHITESPACES.next(Scanners.DECIMAL).followedBy(Scanners.string("%"))
				.map(new Map<String, Double>() {

					@Override
					public Double map(String from) {
						return new Double(Double.valueOf(from) / 100);
					}

				});
	}

	protected Parser<ErrorTrainingItem> error() {
		return Scanners.string("error").next(percentage()).map(new Map<Double, ErrorTrainingItem>() {

			@Override
			public ErrorTrainingItem map(Double from) {
				return new ErrorTrainingItem(from.doubleValue());
			}

		});
	}
	
	

	protected Parser<List<Double>> dataRow() {
		return decimal().sepBy(Scanners.WHITESPACES);
	}

	protected Parser<List<List<Double>>> dataBlock() {
		return Parsers.between(Scanners.string("{").next(Scanners.WHITESPACES.optional()),
				dataRow().sepBy(Scanners.string(",").next(Scanners.WHITESPACES.optional())), Scanners.WHITESPACES
						.optional().next(Scanners.string("}")));
	}

	protected Parser<InlineInputItem> inputBlock() {
		return namedDataBlock("input").map(new Map<Data, InlineInputItem>() {

			@Override
			public InlineInputItem map(Data from) {
				return new InlineInputItem(from);
			}
			
		});
	}

	protected Parser<TrainingOutputItem> outputBlock() {
		return namedDataBlock("output").map(new Map<Data, TrainingOutputItem>() {

			@Override
			public TrainingOutputItem map(Data from) {
				return new TrainingOutputItem(from);
			}
		
		});
	}

	protected Parser<Data> namedDataBlock(String name) {
		return Scanners.string(name).next(Scanners.WHITESPACES)
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
					   .next(trainingBlock());
	}
	
	protected Parser<TrainingDef> trainingBlock() {
		return Parsers.between(Scanners.string("{").next(Scanners.WHITESPACES.optional()), 
				               Parsers.or(trainingParameter(), data()).sepBy(Scanners.WHITESPACES)
				                      .map(new Map<List<DataItem>, TrainingDef>() {

										@Override
										public TrainingDef map(List<DataItem> from) {
											TrainingDef def = new TrainingDef();
											for (DataItem item: from) {
												item.applyTo(def);
											}
											return def;
										}
				                    	  
				                      }),	 
				               Scanners.WHITESPACES.optional().next(Scanners.string("}")));
	}
	
	protected Parser<DataItem> trainingParameter() {
		return Parsers.or(error(), trainingType(), restart(), maxEpochs());
	}
	
	protected Parser<DataItem> data() {
		return Parsers.or(inputBlock(), inputSource(), outputBlock(), outputSource());
	}
	
	protected Parser<DataItem> trainingType() {
		return type().map(new Map<String, DataItem>() {

			@Override
			public DataItem map(String from) {
				return new TrainingTypeItem(from);
			}
			
		});
	}

	protected Parser<Boolean> biased() {
		// return Terminals.("biased").map(new Map<String, Boolean>() {
		return Scanners.string("biased").map(new Map<Void, Boolean>() {

			@Override
			public Boolean map(Void from) {
				return Boolean.TRUE;
			}

		});
	}

	protected Parser<TrainingEpochItem> maxEpochs() {
		return Scanners.string("epochs").next(whitespaceInteger())
				       .map(new Map<Integer, TrainingEpochItem>() {

						@Override
						public TrainingEpochItem map(Integer from) {
							return new TrainingEpochItem(from);
						}
				    	   
				       });
	}

	protected Parser<TrainingRestartItem> restart() {
		return Scanners.string("restart").next(whitespaceInteger())
				       .map(new Map<Integer, TrainingRestartItem>() {

						@Override
						public TrainingRestartItem map(Integer from) {
							return new TrainingRestartItem(from);
						}
				    	   
				       });
	}
	
	protected Parser<TestingDef> testing() {
		return Scanners.string("testing").next(Scanners.WHITESPACES)
				                         .next(testingBlock());
	}
	
	protected Parser<TestingDef> testingBlock() {
		return Parsers.between(Scanners.string("{").next(Scanners.WHITESPACES), 
				               Parsers.sequence(input(),
				            		            Scanners.WHITESPACES.optional().next(testingOutput().optional()), 
				            		          new Map2<DataItem, TestingOutput, TestingDef>() {

			@Override
			public TestingDef map(DataItem a, TestingOutput b) {
				TestingDef def = new TestingDef();
				a.applyTo(def);
				if (b!=null) b.applyTo(def);
				return def;
			}
		}),
								Scanners.WHITESPACES.optional().next(Scanners.string("}")));
	}
	
	protected Parser<DataItem> input() {
		return Parsers.or(inputBlock(), inputSource());
	}
	
	protected Parser<TestingOutput> testingOutput() {
		return Scanners.string("output").next(Scanners.WHITESPACES)
				                        .next(Parsers.sequence(identifier(), 
				                                               Scanners.WHITESPACES.next(filename()), 
				                             new Map2<String, String, TestingOutput>() {

													@Override
													public TestingOutput map(String a, String b) {
														return new TestingOutput(a,b);
													}
												
				                        		}));
	}

	protected Parser<ExternalInput> inputSource() {
		
		return Scanners.string("input").next(Scanners.WHITESPACES)
            .next(Parsers.sequence(identifier(), 
                                   Scanners.WHITESPACES.next(filename()), 
                 new Map2<String, String, ExternalInput>() {

						@Override
						public ExternalInput map(String a, String b) {
							return new ExternalInput(a,b);
						}
					
            		}));
	}	
	
	protected Parser<ExternalOutput> outputSource() {
	
		return Scanners.string("output").next(Scanners.WHITESPACES)
            .next(Parsers.sequence(identifier(), 
                                   Scanners.WHITESPACES.next(filename()), 
                 new Map2<String, String, ExternalOutput>() {

						@Override
						public ExternalOutput map(String a, String b) {
							return new ExternalOutput(a,b);
						}
					
            		}));
	}
	
	protected Parser<String> filename() {
		return Scanners.DOUBLE_QUOTE_STRING.map(new Map<String, String>() {

			@Override
			public String map(String from) {
				return from.substring(1, from.length()-1);
			}
		});
		
	}
	
	protected Parser<String> activationExpression() {
		return Scanners.string("activation")
				.next(Scanners.WHITESPACES)
				.next(identifier());
	}
	
	protected Parser<ActivationDefinition> activationDefinition() {
		return Parsers.sequence(activationExpression(),
				                Scanners.WHITESPACES.next(asExpression()),
				                Scanners.WHITESPACES.next(Parsers.between(Scanners.string("{").next(Scanners.WHITESPACES.optional()), 
				                		                                  parameter().sepBy(Scanners.WHITESPACES), 
				                		                                  Scanners.WHITESPACES.optional().next(Scanners.string("}")))).optional(),
				                new Map3<String, IsExpression, List<Parameter>, ActivationDefinition>() {

									@Override
									public ActivationDefinition map(String a, IsExpression b, List<Parameter> c) {
										return new ActivationDefinition(a, b.getType(), c);
									}
				
		});
	}
	
	protected Parser<DataDef> dataDefinition() {
		return Scanners.string("data").next(Scanners.WHITESPACES)
				 .next(Parsers.between(Scanners.string("{").next(Scanners.WHITESPACES.optional()), 
											Parsers.sequence(name(), 
													         Scanners.WHITESPACES.optional().next(inputSource()), 
													         Scanners.WHITESPACES.optional().next(normalise().optional()),
													         new Map3<String, ExternalInput, NormaliseDef, DataDef>() {

												@Override
												public DataDef map(String name, ExternalInput source, NormaliseDef normDef) {
													return new DataDef(name, source, normDef);
												}
												
											}),
											Scanners.WHITESPACES.optional().next(Scanners.string("}"))));
	}
	
	protected Parser<String> name() {
		return Scanners.string("name").next(Scanners.WHITESPACES)
				                      .next(identifier());
	}
	
	protected Parser<NormaliseDef> normalise() {
		return Parsers.between(Scanners.string("normalise {").next(Scanners.WHITESPACES.optional()), doubleParameter()
				.sepBy(Scanners.WHITESPACES), Scanners.WHITESPACES.optional().next(Scanners.string("}")))
				                           .map(new Map<List<DoubleParameter>, NormaliseDef>() {

						@Override
						public NormaliseDef map(List<DoubleParameter> from) {
							double min = 0, max = 1;
							for (DoubleParameter p: from) {
								if (p.getName().equals("min")) min = p.getValue();
								if (p.getName().equals("max")) max = p.getValue();
							}
							
							return new NormaliseDef(min,max);
						}

				       });
	}
	
}
