import probingsequence.ProbingSequence;

public class OpenAddressTable extends HashTable {
	
	private int size;
	private ProbingSequence probe;
	private Row[] table;
  
	public OpenAddressTable(int _size, ProbingSequence _probe) {
		size = _size;
		probe = _probe;
		table = new Row[size];
	}
	
	public void insert(String key) {
		int hash = getHash(key)%(size);
		int hash2 = hash,prob = hash;
		int i = 1;
		while (table[prob] != null) {
			int p = probe.getP(key, i++);
			if (p > (Math.pow(size-1, 2))/4 || p < -(Math.pow(size-1, 2))/4) {
				System.out.println("ERROR - Cannot insert given key");
				return;
			}
			prob = hash2 + p;
			if (prob == hash || prob < 0) {
				System.out.println("ERROR - Cannot insert given key");
				return;
			}
			if (prob > size-1) {
				hash2 = 0;
				i = 0;
				prob = 0;
			}
		}
		table[prob] = new Row(key);
	}
  
	public void print() {
		for (int k = 0; k < size; k++) {
			if (table[k] == null) {
				System.out.println(k+". -------- ");
			} else {
				System.out.println(k+". "+table[k].toString());
			}
		}
	}
}