package neural.parsec.ast;

public class ExternalOutput extends DataLocation implements DataItem {

	public ExternalOutput(String type, String id) {
		super(type, id);
	}
	
	public String toString() {
		return "type:"+this.getType()+" id:"+this.getId();
	}

	@Override
	public void applyTo(DataDefinition def) {
		def.setOutputLocation(this);
	}

}
