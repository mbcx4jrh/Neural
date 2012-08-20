package neural.parsec.ast;

public class ExternalInput extends DataLocation implements DataItem {

	public ExternalInput(String type, String id) {
		super(type, id);
	}
	
	public String toString() {
		return "type:"+this.getType()+" id:"+this.getId();
	}

	@Override
	public void applyTo(DataDefinition def) {
		def.setInputLocation(this);
	}

}
