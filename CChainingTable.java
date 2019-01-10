import probingsequence.ProbingSequence;

public class CChainingTable extends HashTable {
	
	private int size;
	private int cellarSize;
	private ProbingSequence probe;
	private CRow[] table;
	
	public CChainingTable(int _size, int _cellarSize, ProbingSequence _probe) {
		size = _size;
		cellarSize = _cellarSize;
		probe = _probe;
		table = new CRow[size+cellarSize];
	}
	
	public void insert(String key) {
		int hash = getHash(key)%size;
		int i=1;
		if (table[hash] == null) {
			table[hash] = new CRow(key);
			return;
		}
		int hash2 = size + cellarSize - 1;
		int hash3 = hash2;
		i = 0;
		while (table[hash2] != null) {
			hash2 = hash3 - probe.getP(key, i++);
			if (hash2 < 0) {
				System.out.println("ERROR - Cannot insert given key");
				return;
			}
		}
		table[hash2] = new CRow(key);
		table[hash].next = table[hash2];
		table[hash].nextIndex = hash2;
	}
  
	public void print() {
		for (int k = 0; k < size+cellarSize; k++) {
			if (table[k] == null) {
				System.out.println(k+". -------- ( -------- )");
			} else {
				System.out.println(k+". "+table[k].toString());
			}
		}
	}
  
  
	protected class CRow extends Row {
		CRow next = null;
		int nextIndex = -1;
		
		public CRow(String _key) {
			super(_key);
		}
		
		public void addRef(int _nextIndex, CRow _next) {
			next = _next;
			nextIndex = _nextIndex;
		}
		
		@Override
		public String toString() {
			return key + " ( "+nextIndex + " - "+(next != null ? next.key : "#") + " )";
		}
	}
  
}