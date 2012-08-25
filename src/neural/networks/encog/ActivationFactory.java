package neural.networks.encog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import neural.NeuralException;
import neural.NeuralPropertyFactory;
import neural.parsec.ast.DoubleParameter;
import neural.parsec.ast.IntegerParameter;
import neural.parsec.ast.Parameter;

import org.encog.engine.network.activation.ActivationFunction;

public class ActivationFactory {
	
	private NeuralPropertyFactory<ActivationFunction> encogFactory;
	
	public ActivationFactory(String propertiesFilename) {
		encogFactory = new NeuralPropertyFactory<ActivationFunction>(propertiesFilename, "activation");
	}

	public ActivationFunction getActivationFunction(String name, List<Parameter> params) {
		ActivationFunction af =  encogFactory.getNewInstance(name);
		if (params != null && params.size()>0) setParameters(af, params);
		return af;
	}

	private void setParameters(ActivationFunction af, List<Parameter> params) {
		String[] paramNames = af.getParamNames();
		
		Map<String, Integer> map = buildMap(paramNames);
		
		for (Parameter param: params) {
			double value = checkValue(param);
			int index = getIndex(param.getName(), map);
			af.setParam(index, value);
		}
	}

	private int getIndex(String name, Map<String, Integer> map) {
		if (!map.containsKey(name))
			throw new NeuralException("Parameter '"+name+"' cannot be applid to activation function");
		return map.get(name);
	}

	private double checkValue(Parameter param) {
		if (param instanceof DoubleParameter)
			return ((DoubleParameter)param).getValue();
		if (param instanceof IntegerParameter)
			return ((IntegerParameter)param).getValue();
		throw new NeuralException("Parameter '"+param.getName()+"' is invalid type - must be double or integer");
	}

	private Map<String, Integer> buildMap(String[] paramNames) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i=0; i<paramNames.length; i++) {
			map.put(paramNames[i], i);
		}
		return map;
	}

}
