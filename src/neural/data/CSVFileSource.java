package neural.data;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import neural.NeuralException;
import au.com.bytecode.opencsv.CSVReader;


public class CSVFileSource implements DataSource{
	
	private int currentRow = 0;
	private double[][] data;

	@Override
	public double[] next() {
		return data[currentRow++];
	}

	@Override
	public void reset() {
		currentRow = 0;
	}

	@Override
	public void init(String id) {
		try {
			readFile(id);
		} catch (IOException e) {
			throw new NeuralException(e);
		}
	}

	@Override
	public boolean hasNext() {
		return currentRow < data.length;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	private void readFile(String name) throws IOException {
		CSVReader reader = new CSVReader(new FileReader(name));
		List<String[]> file = reader.readAll();
		int s = file.size();
		data = new double[s][];
		for (int i=0; i<s; i++) {
			parseLine(i, file.get(i));
		}
		reader.close();
	}

	private void parseLine(int row, String[] line) {
		double val;
		data[row] = new double[line.length];
		for (int col =0; col <line.length; col++) {
			try {
				val = Double.parseDouble(line[col]);
				data[row][col] = val;
			}
			catch (NumberFormatException nfe) {
				throw new NeuralException(nfe);
			}
		}
	}

	@Override
	public Iterator<double[]> iterator() {
		return this;
	}

	@Override
	public double[][] getAll() {
		return data;
	}

}
