package neural.parsec.ast;

public class ErrorCondition {

	private double error;

	public ErrorCondition(Double error) {
		this.error = error.doubleValue();
	}

	public double getError() {
		return error;
	}

	public boolean equals(Object o) {
		if (o instanceof ErrorCondition)
			return ((ErrorCondition) o).getError() == error;
		else
			return false;
	}

}
