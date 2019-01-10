import probingsequence.*;

public class Task1 {
	public static void main(String[] args) {
		
		String[] input = {"robothugs1", "This is not the algorithm. This is close.", "mmkay5", "ForPony!9", "ever Friday5", "RibbyTheRib0", "Wololo0", "Not with a bang"};
    
		/*The digits at the end of the inputs are contrived to induce clashes. 
		  Because we are nice, we have provided you with the expected hash values of these keys:
			#(robothugs1) = 1038
			#(This is not the algorithm. This is close.) = 3731
			#(mmkay5) = 596
			#(ForPony!9) = 807
			#(ever Friday5) = 1126
			#(RibbyTheRib0) = 1126
			#(Wololo0) = 684
			#(Not with a bang) = 1350
		*/
    
		int tableSize = 11;
    
		HashTable table = new OpenAddressTable(tableSize, new LinearProbe());
		for (int k = 0; k < input.length; k++) {
			table.insert(input[k]);
		}
		table.insert("mmkay5");
		table.insert("mmkay5");
		table.insert("mmkay5");
		table.insert("mmkay5");
		//table.insert("mmkay5");
		
		table.print();
    
		/*
		After inserting these inputs, calling table.print() with the LinearProbe should output:
		
		  0. --------
		  1. --------
		  2. This is not the algorithm. This is close.
		  3. mmkay5
		  4. robothugs1
		  5. ForPony!9
		  6. ever Friday5
		  7. RibbyTheRib0
		  8. Wololo0
		  9. Not with a bang
		  10. --------
		
		Also note that when these inputs are inserted using the SecondHashProbe, it is expected that "Wololo0" will be impossible to insert.
		What does this tell you about the quality of the SecondHash function?
		*/
    
    
	}
}