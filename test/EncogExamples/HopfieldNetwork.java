package EncogExamples;

import java.io.File;
import java.io.IOException;

import neural.Network;
import neural.ScriptParser;

import org.encog.util.file.FileUtil;

public class HopfieldNetwork {
	
	private final static int WIDTH = 10;
	private final static int HEIGHT = 10;
	
	
	//@Test 
	public void hopfieldTest() throws IOException {
		int epochs = 10;
		String script = FileUtil.readFileAsString(new File("scripts/hopfield-1.neural"));
		ScriptParser parser = new ScriptParser();
		Network network = parser.parseScript(script);
		
		for (int i=0; i<epochs; i++) {
			for (int d=0; d<TRAIN.length; d++) {
				//network.train(convertPattern(TRAIN, d), null);
			}
		}
		
	}
	
	private double[] convertPattern(String[][] data, int index)
	{
		int resultIndex = 0;
		double[] result = new double[(WIDTH*HEIGHT)];
		for(int row=0;row<HEIGHT;row++)
		{
			for(int col=0;col<WIDTH;col++)
			{
				char ch = data[index][row].charAt(col);
				if (ch != 'O')
					result[resultIndex++] =1;
			}
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

      { "O  O  O  O",
        " O  O  O  ",
        "  O  O  O ",
        "O  O  O  O",
        " O  O  O  ",
        "  O  O  O ",
        "O  O  O  O",
        " O  O  O  ",
        "  O  O  O ",
        "O  O  O  O"  },

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

      { "O  OOOO  O",
        "OO  OOOO  ",
        "OOO  OOOO ",
        "OOOO  OOOO",
        " OOOO  OOO",
        "  OOOO  OO",
        "O  OOOO  O",
        "OO  OOOO  ",
        "OOO  OOOO ",
        "OOOO  OOOO"  },

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


}
