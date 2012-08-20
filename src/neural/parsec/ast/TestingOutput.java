package neural.parsec.ast;

public class TestingOutput extends DataLocation implements DataItem {

	public TestingOutput(String type, String id) {
		super(type, id);
	}
	
	public String toString() {
		return "output type:"+this.getType()+" id:"+this.getId();
	}

	@Override
	public void applyTo(DataDefinition def) {
		def.setOutputLocation(this);
	}

}
