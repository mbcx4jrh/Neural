package neural;

import java.io.File;
import java.io.IOException;

import org.encog.util.file.FileUtil;

public class CommandLine {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			out("A neural script must be passed as the first argument.");
			System.exit(0);
		}
		
		out("Creating parser...");
		ScriptParser parser = new ScriptParser();
		out("Reading file '"+args[0]+"'...");
		String file = FileUtil.readFileAsString(new File(args[0]));
		out("Parsing script...");
		Network network = parser.parseScript(file);
		out("Training network...");
		network.train();
		out("Testing network...");
		network.compute();
		
	}
	
	private static void out(String msg) {
		System.out.println(msg);
	}

}
