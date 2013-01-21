package neural.parsec.ast;

public class DataDef {

	private String name;
	private ExternalInput input;
	private NormaliseDef normaliseDef;
	
	public DataDef(String name, ExternalInput input, NormaliseDef normaliseDef) {
		this.name = name;
		this.input = input;
		this.normaliseDef = normaliseDef;
	}
	
	public String getName() {
		return name;
	}
	
	public ExternalInput getInput() {
		return input;
	}

	public NormaliseDef getNormaliseDef() {
		return normaliseDef;
	}

}
