package neural;

import neural.networks.NetworkFactoryException;
import neural.parsec.Script;

public interface NetworkFactory {

	public void init() throws NetworkFactoryException;

	public abstract Network getNetwork(Script definition);

}