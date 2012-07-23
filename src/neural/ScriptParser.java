package neural;

import neural.parsec.NeuralParserFactory;
import neural.parsec.ast.NetworkDef;

public class ScriptParser {

	private NeuralParserFactory parserFactory;
	private NetworkFactory networkFactory;
	
	public ScriptParser() {
		parserFactory = new NeuralParserFactory();
		networkFactory = new NetworkFactory();
	}
	
	public Network parseScript(String script) {
		
		NetworkDef definition = parserFactory.getNeuralParser().parse(script);
		return networkFactory.getNetwork(definition);
	}

}
