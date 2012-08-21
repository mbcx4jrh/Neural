package neural.data;

import java.util.Iterator;

public class InlineDataSource implements DataSource {
	
	private double[][] data;
	private int currentRow = 0;

	public InlineDataSource(double[][] data) {
		this.data = data;
	}

	@Override
	public boolean hasNext() {
		return currentRow < data.length;
	}

	@Override
	public double[] next() {
		return data[currentRow++];
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void init(String id) {
		
	}

	@Override
	public void reset() {
		currentRow = 0;
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
