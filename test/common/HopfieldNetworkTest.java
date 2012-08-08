package common;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import neural.Network;
import neural.ScriptParser;
import neural.networks.encog.EncogHopfieldNetwork;

import org.encog.util.file.FileUtil;
import org.junit.Before;

public class HopfieldNetworkTest {
	
	private final static int WIDTH = 10;
	private final static int HEIGHT = 10;
	
	private ScriptParser parser;
	
	public void setParser(ScriptParser parser) {
		this.parser = parser;
	}
	
	
	
	public void hopfieldTest() throws IOException {
		String script = FileUtil.readFileAsString(new File("scripts/hopfield-1.neural"));
		Network network = parser.parseScript(script);
		
		double[][] training = convertPattern(TRAIN);
		double[][] testing = convertPattern(TEST);
		double[] output = new double[100];
		
		out("Training:");
		for (int i=0; i<training.length; i++) {
			out(training[i]);
			out("---");
		}
		network.train(training, null);
		
		for (int i=0; i< testing.length; i++) {
			network.compute(testing[i], output);
			console(testing[i], output);
			assertEquals(Arrays.toString(training[i]), Arrays.toString(output));
		} 
		
		
		 
	}
	
	private void console(double[] ds, double[] ds2) {
		out("Input:");
		out(ds);
		out("Output:");
		out(ds2);
	}
	
	

	private void out(double[] ds) {
		StringBuffer sb = new StringBuffer();
		char ch;
		for (int i=0; i<HEIGHT; i++) {
			for (int j=0; j<WIDTH; j++) {
				if (ds[i*WIDTH+j] >0)
					ch = 'O';
				else 
					ch = ' ';
				sb.append(ch);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private void out(String m) {
		System.out.println(m);
	}

	private double[][] convertPattern(String[][] data)
	{
		int resultIndex = 0;
		double[][] result = new double[data.length][(WIDTH*HEIGHT)];
		for (int index =0; index<data.length; index++) {
			for(int row=0;row<HEIGHT;row++)
			{
				for(int col=0;col<WIDTH;col++)
				{
					char ch = data[index][row].charAt(col);
					if  (ch != 'O')
						result[index][resultIndex++] = -1;
					else
						result[index][resultIndex++] = 1;
				}
			}
			resultIndex = 0;
		}
		return result;
	}
	
	/**
	 * The neural network will learn these patterns.
	 */
	public static final String[][] TRAIN  = { { 
		"O O O O O ",
        " O O O O O",
        "O O O O O ",
        " O O O O O",
        "O O O O O ",
        " O O O O O",
        "O O O O O ",
        " O O O O O",
        "O O O O O ",
        " O O O O O"  },

      { "OO  OO  OO",
        "OO  OO  OO",
        "  OO  OO  ",
        "  OO  OO  ",
        "OO  OO  OO",
        "OO  OO  OO",
        "  OO  OO  ",
        "  OO  OO  ",
        "OO  OO  OO",
        "OO  OO  OO"  },
        
   
            
      { "OOOOO     ",
        "OOOOO     ",
        "OOOOO     ",
        "OOOOO     ",
        "OOOOO     ",
        "     OOOOO",
        "     OOOOO",
        "     OOOOO",
        "     OOOOO",
        "     OOOOO"  },


      { "OOOOOOOOOO",
        "O        O",
        "O OOOOOO O",
        "O O    O O",
        "O O OO O O",
        "O O OO O O",
        "O O    O O",
        "O OOOOOO O",
        "O        O",
        "OOOOOOOOOO"  } };

	
	/**
	 * The neural network will be tested on these patterns, to see
	 * which of the last set they are the closest to.
	 */
	public static final String[][] TEST = { { 
		"          ",
        "          ",
        "          ",
        "          ",
        "          ",
        " O O O O O",
        "O O O O O ",
        " O O O O O",
        "O O O O O ",
        " O O O O O"  },

      { "OOO O    O",
        " O  OOO OO",
        "  O O OO O",
        " OOO   O  ",
        "OO  O  OOO",
        " O OOO   O",
        "O OO  O  O",
        "   O OOO  ",
        "OO OOO  O ",
        " O  O  OOO"  },

      { "OOOOO     ",
        "O   O OOO ",
        "O   O OOO ",
        "O   O OOO ",
        "OOOOO     ",
        "     OOOOO",
        " OOO O   O",
        " OOO O   O",
        " OOO O   O",
        "     OOOOO"  },


      { "OOOOOOOOOO",
        "O        O",
        "O        O",
        "O        O",
        "O   OO   O",
        "O   OO   O",
        "O        O",
        "O        O",
        "O        O",
        "OOOOOOOOOO"  } };


	@Before
	public void pokeInfinitest() {
		@SuppressWarnings("unused")
		EncogHopfieldNetwork n = new EncogHopfieldNetwork();
	}
}
