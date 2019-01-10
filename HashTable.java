import probingsequence.ProbingSequence;

public abstract class HashTable {
	
	public abstract void print();
	public abstract void insert(String key);
  
	protected int getHash(String key) {
		int value = 0;
		for (int a=0;a<key.length();a++) {
			value = value + key.charAt(a);
		}
		return value;
		//Your implementation here
		//return -1;
	}
	
	protected class Row {
		String key;
		
		public Row(String _key) {
			key = _key;
		}
		
		public String toString() {
			return key;
		}
	}
}