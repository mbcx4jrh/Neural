package neural.parsec;

public class SizeExpression extends Expression{
	
	private int size;

	public SizeExpression(int size) {
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof SizeExpression)) return false;
		return ((SizeExpression)obj).getSize() == size;
	}
	
	
}
