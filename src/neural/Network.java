package neural;


public interface Network {
	


	public String getName();
	
	public String getType();
	
	public void train();
	
	public void compute(double[] input, double[] output);
	
	
}
