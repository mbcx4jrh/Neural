package neural.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import neural.NeuralException;

import org.apache.commons.io.FileUtils;

/**
 * Loads data from file with format:
 * 
 * ----O---O
 * -OOOO----
 * 
 * where - is tranformed to a -1 and O is transormed to +1
 * 
 * @author joe
 *
 */
public class BiPolarPatternSource extends AbstractMemoryDataSource{
	
	public final static char OFF = '-';
	public final static char ON = 'O';

	@Override
	protected double[][] loadData() {
		double[][] data;
		File f = new File(this.id);
		try {
			List<String> lines = FileUtils.readLines(f);
			int row = 0;
			data = new double[lines.size()][];
			for (String line: lines) {
				data[row++] = parseLine(line);
			}
			
		} catch (FileNotFoundException e) {
			throw new NeuralException(e);
		} catch (IOException e) {
			throw new NeuralException(e);
		}
		return data;
	}

	private double[] parseLine(String line) {
		double[] row = new double[line.length()];
		for (int col = 0; col<line.length(); col++) {
			if (line.charAt(col) == OFF) row[col] = -1;
			else if (line.charAt(col) == ON) row[col] = 1;
			else throw new NeuralException("Invalid file format ("+this.id+")");
		}
		return row;
	}


}
