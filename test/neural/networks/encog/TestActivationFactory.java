package neural.networks.encog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import neural.parsec.ast.DoubleParameter;
import neural.parsec.ast.Parameter;

import org.encog.engine.network.activation.ActivationFunction;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationStep;
import org.junit.Before;
import org.junit.Test;

public class TestActivationFactory {
	
	private ActivationFactory activationFactory;
	List<Parameter> params;

	@Before
	public void setUp() {
		activationFactory = new ActivationFactory("properties/encog.properties");
		params = new ArrayList<Parameter>();
	}
	
	@Test
	public void testNoParams() {
		ActivationFunction af = activationFactory.getActivationFunction("sigmoid", params);
		assertNotNull(af);
		assertTrue(af instanceof ActivationSigmoid);
	}
	
	@Test
	public void testStepParams() {
		params.add(new DoubleParameter("center", 0.2));
		params.add(new DoubleParameter("low", 0.01));
		params.add(new DoubleParameter("high", 0.9));
		
		ActivationFunction af = activationFactory.getActivationFunction("threshold", params);
		
		assertNotNull(af);
		
		assertEquals("[center, low, high]", Arrays.toString(af.getParamNames()));
		assertEquals("[0.2, 0.01, 0.9]", Arrays.toString(af.getParams()));
	}

	@Test
	public void printParams() {
		ActivationStep a = new ActivationStep();
		System.out.println("params: "+Arrays.toString(a.getParamNames()));
	}
}
