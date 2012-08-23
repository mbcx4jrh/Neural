package neural.parsec.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import neural.NeuralException;

public class ValidationFactory {
	
	Properties properties;
	
	public ValidationFactory(String filename) {
		readFile(filename);
	}

	public Validator getValidator(String id) {
		Validator v = new Validator();
		v.setMandantory(getList(id+".mandantory"));
		v.setOptional(getList(id+".optional"));
		return v;
	}
	
	private List<String> getList(String id) {
		
		String string = properties.getProperty(id);
		List<String> list = new ArrayList<String>();
		
		if (string == null) return list;
		
		StringTokenizer tokens = new StringTokenizer(string);
		while (tokens.hasMoreTokens()) {
			list.add(tokens.nextToken());
		}
		return list;
	}

	private void readFile(String name) {
		properties = new Properties();
		try {
			properties.load(new FileInputStream(new File(name)));
		} catch (FileNotFoundException e) {
			throw new NeuralException("Missing properties file: "+name);
		} catch (IOException e) {
			throw new NeuralException("IO issues with file "+name);
		}
	}
}
