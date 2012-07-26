package neural;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class NeuralPropertyFactory<T> {
	
	private Properties properties;
	private String prefix;
	
	public NeuralPropertyFactory(String filename, String prefix) {
		this.prefix = prefix+".";
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(filename)));
		} catch (FileNotFoundException e) {
			throw new NeuralException("Missing properties file: ("+filename+")", e);
		} catch (IOException e) {
			throw new NeuralException(e);
		}
		
	}
	
	public T getNewInstance(String name) {
		String clazz = properties.getProperty(prefix+name);
		if (clazz == null) throw new NeuralException("Unknown property ("+prefix+name+")");
		try {
			return (T) Class.forName(clazz).newInstance();
		} catch (InstantiationException e) {
			throw new NeuralException(e);
		} catch (IllegalAccessException e) {
			throw new NeuralException(e);
		} catch (ClassNotFoundException e) {
			throw new NeuralException(e); 
		}
	}

}
