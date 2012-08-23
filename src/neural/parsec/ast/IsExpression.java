package neural.parsec.ast;

public class IsExpression {

	private String type;

	public IsExpression(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof IsExpression))
			return false;
		return ((IsExpression) obj).getType().equals(type);
	}

}
