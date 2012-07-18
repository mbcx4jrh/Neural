package neural.parsec;

public class NetworkDef {
	
	private String name;
	private String type;
	private int size;

	public NetworkDef(String name, String type, int size) {
		this.name = name;
		this.type = type;
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getSize() {
		return size;
	}
	
	public String toString() {
		return "Network: "+name+", type: "+type+", size: "+size; 
	}

}
