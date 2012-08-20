package neural.parsec.ast;

public class DataLocation {

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}
	
	public DataLocation(String type, String id) {
		this.type = type;
		this.id = id;
	}

	protected String type;
	protected String id;

}
