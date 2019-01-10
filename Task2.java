import probingsequence.*;

public class Task2 {
	public static void main(String[] args) {
    
		String[] input = {"robothugs1", "This is not the algorithm. This is close.", "mmkay5", "ForPony!9", "ever Friday5", "RibbyTheRib0", "Wololo0", "Not with a bang"};
    
		int tableSize = 11;
		int cellarSize = 2;
    
		HashTable table = new CChainingTable(tableSize, cellarSize, new LinearProbe());
		for (int k = 0; k < input.length; k++) {
			table.insert(input[k]);
		}
		table.print();
    
		/*
		After inserting these inputs, calling table.print() should output:
		
		  0. -------- ( -------- )
		  1. -------- ( -------- )
		  2. This is not the algorithm. This is close. ( 12 - mmkay5 )
		  3. -------- ( -------- )
		  4. robothugs1 ( 11 - ForPony!9 )
		  5. -------- ( -------- )
		  6. -------- ( -------- )
		  7. Not with a bang ( 0 - # )
		  8. Wololo0 ( 7 - Not with a bang )
		  9. RibbyTheRib0 ( 0 - # )
		  10. ever Friday5 ( 9 - RibbyTheRib0 )
		  11. ForPony!9 ( 10 - ever Friday5 )
		  12. mmkay5 ( 8 - Wololo0 )
		
		*/
    
    
	}
}