package neural.parsec.ast;

public class AsExpression {

	private String type;

	public AsExpression(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof AsExpression))
			return false;
		return ((AsExpression) obj).getType().equals(type);
	}

}
