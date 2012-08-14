package neural.parsec.ast;

public class TestingOutput {

	private String type;
	private String id;

	public TestingOutput(String type, String id) {
		this.type = type;
		this.id = id;
	}
	
	public String toString() {
		return "output type:"+type+" id:"+id;
	}

}
