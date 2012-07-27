package neural;

import neural.networks.NetworkFactoryException;
import neural.parsec.NeuralParserFactory;
import neural.parsec.Script;

public class ScriptParser {

	private NeuralParserFactory parserFactory;
	private NetworkFactory networkFactory;

	public ScriptParser() {
		parserFactory = new NeuralParserFactory();
		setUnderlyingLibrary("neural.networks.EncogNetworkFactory");
	}

	public void setUnderlyingLibrary(String className) {
		try {

			networkFactory = (NetworkFactory) Class.forName(className).newInstance();
			networkFactory.init();

		} catch (InstantiationException e) {
			throw new NeuralException(e);
		} catch (IllegalAccessException e) {
			throw new NeuralException(e);
		} catch (ClassNotFoundException e) {
			throw new NeuralException("Could not find library class (" + className + ")", e);
		} catch (NetworkFactoryException e) {
			throw new NeuralException(e);
		}
	}

	public Network parseScript(String script) {

		Script definition = parserFactory.getNeuralParser().parse(script);
		return networkFactory.getNetwork(definition);
	}

}
