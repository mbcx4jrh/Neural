package neural.tools;

import java.util.LinkedList;
import java.util.List;

public class WindowedData {
	
	private int windowSize;
	
	private List<double[]> inputWindow;
	private List<double[]> outputWindow;
	
	public WindowedData(int windowSize) {
		this.windowSize = windowSize;
		inputWindow = new LinkedList<double[]>();
		outputWindow = new LinkedList<double[]>();
	}
	
	public void addData(double[] input, double[] output) {
		addToWindow(input, inputWindow);
		addToWindow(output, outputWindow);
	}
	
	private void addToWindow(double[] data, List<double[]> window) {
		window.add(data);
		if (window.size() > windowSize)
			window.remove(0);
	}
	
	public double[][] getInputData() {
		return inputWindow.toArray(new double[0][0]);
	}
	
	public double[][] getOutputData() {
		return outputWindow.toArray(new double[0][0]);
	}

}
