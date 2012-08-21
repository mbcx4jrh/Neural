package neural.data;

import java.util.Iterator;

public interface DataSource extends Iterator<double[]>, Iterable<double[]>{
	
	public void init(String id);
	
	public void reset();

}
