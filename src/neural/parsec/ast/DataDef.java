package neural.parsec.ast;

public class DataDef {

	private String name;
	private ExternalInput input;
	
	public DataDef(String name, ExternalInput input) {
		this.name = name;
		this.input = input;
	}
	
	public String getName() {
		return name;
	}
	
	public ExternalInput getInput() {
		return input;
	}

}
