package neural.parsec.ast;

public interface DataDefinition {
	
	public void setInputData(double[][] data);
	public void setOutputData(double[][] data);
	
	public void setInputLocation(DataLocation location);
	public void setOutputLocation(DataLocation location);

}
