package neural.parsec.ast;

public class NetworkExpression {
	
	private String name;
	
	public NetworkExpression(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NetworkExpression)) return false;
		return ((NetworkExpression)obj).getName().equals(name);
	}
	
	
}
