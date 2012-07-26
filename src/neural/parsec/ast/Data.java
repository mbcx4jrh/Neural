package neural.parsec.ast;

import java.util.Arrays;
import java.util.List;

public class Data {

	double[][] data;

	public Data(List<List<Double>> objData) {
		int i = 0;
		int j = 0;
		int mj = objData.size();
		if (mj == 0) {
			data = null;
		} else {
			int mi = objData.get(0).size(); // assumes all rows same size !!!
			data = new double[mj][mi];
			for (List<Double> sublist : objData) {
				for (Double d : sublist) {
					data[j][i] = d.doubleValue();
					i++;
				}
				i = 0;
				j++;
			}
		}
	}

	public String toString() {
		return "" + Arrays.deepToString(data);
	}
}
