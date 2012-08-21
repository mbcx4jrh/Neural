package neural.data;

import java.util.Iterator;

public abstract class AbstractMemoryDataSource implements DataSource {
	
	@Override
	public double[][] getAll() {
		return data;
	}

	private int currentRow;
	private double[][] data;
	protected String id;
	
	protected abstract double[][] loadData();

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
	public Iterator<double[]> iterator() {
		return this;
	}

	@Override
	public void init(String id) {
		this.id = id;
		data = loadData();
	}

	@Override
	public void reset() {
		currentRow = 0;
	}

}
