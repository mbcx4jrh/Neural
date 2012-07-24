package neural;

import neural.parsec.Script;

public interface NetworkFactory {

	public abstract Network getNetwork(Script definition);

}