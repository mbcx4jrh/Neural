package neural.tester;

import java.util.HashMap;
import java.util.Map;

public class MemoryTesterStore {
	
	private static MemoryTesterStore instance;
	
	private Map<String, MemoryTester> store;
	
	public static MemoryTesterStore getInstance() {
		if (instance == null)
			instance = new MemoryTesterStore();
		return instance;
	}
	
	public MemoryTesterStore() {
		store = new HashMap<String, MemoryTester>();
	}
	
	public void store(MemoryTester tester) {
		store.put(tester.getId(), tester);
	}
	
	public MemoryTester retrieve(String id) {
		return store.get(id);
	}

}
