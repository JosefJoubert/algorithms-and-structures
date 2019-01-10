/*
Complete your details...
Name and Surname: JH Joubert
Student/staff Number: 12048918
*/

//package textfiles;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/*You must complete this class to create a class capable of hashing a list of words using the Cichelli's algorithm*/
public class Cichelli
{
	/*
	You may not take away from the public interface of this class.  You may however
	add any additional methods and/or field which you may require to aid you 
	in the completion of this assignment.
	*/
	String[] words;
	String[] table;
	Integer[] gValues;
	int lines;
	int max;
	Boolean failed = false;
	
	public Cichelli(String fileName, int max)
	{
		/*
		The constructor. The first parameter is the name of the file which
		contains the words to be hashed.  You may assume this file will always 
		be in the same directory as this class.  The second value is the max 
		parameter required by the algorithm.
		
		If the words cannot be hashed for whatever reason, all other methods 
		should simply return the error case values as discussed in these 
		methods.
		*/
		try {
			lines = readLines(fileName);
			words = openFile(fileName, lines);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}
		gValues = new Integer[26];
		table = new String[lines];
		for (int a = 0; a <26 ; a++) gValues[a] = -1;
		Arrays.sort(words);
		words = sort(words);
		ArrayList<String> wordlist = new ArrayList<String>();
		for (int a=0;a<lines;a++) wordlist.add(words[a]);
		this.max = max;
		failed = !search(wordlist);
		
		
		
	}
	
	private boolean search(ArrayList<String> wordlist) {
			if (wordlist.isEmpty()) {
				return true;
			}
			String word = wordlist.remove(0);
			int first = gValues[ASCII(word.charAt(0))];
			int last = gValues[ASCII(word.charAt(word.length()-1))];
			if (gValueOf(word.charAt(0)) != -1 && gValueOf(word.charAt(word.length()-1)) != -1) {
				if (probeer(word,-1,-1)) {
					return search(wordlist);
				}
			}
			else if (gValueOf(word.charAt(0)) == -1 && gValueOf(word.charAt(word.length()-1)) == -1) {
				for (int a=0; a != max+1;a++) {
					for (int b=0; b != max+1;b++) {
						if(probeer(word,a,b)) {
							if (search(wordlist)) return true;
							table[hashOf(word)] = null;
						}
					}
				}
			}
			else if (gValueOf(word.charAt(0)) == -1) {
				for (int a=0;a != max+1;a++) {
					if(probeer(word,a,-1)) {
						if (search(wordlist)) return true;
						table[hashOf(word)] = null;
					}
				}
			}
			else if (gValueOf(word.charAt(word.length()-1)) == -1) {
				for (int a=0; a != max+1; a++) {
					if (probeer(word,-1,a)) {
						if (search(wordlist)) return true;
						table[hashOf(word)] = null;
					}
				}
			}
			wordlist.add(0,word);
			gValues[ASCII(word.charAt(word.length()-1))] = last;
			gValues[ASCII(word.charAt(0))] = first;
			return false;
		
	}
	
	private boolean probeer(String word,int first,int last) {
		int eerste, laaste;
		if (first == -1) eerste = gValueOf(word.charAt(0));
		else eerste = first;
		if (last == -1) laaste = gValueOf(word.charAt(word.length()-1));
		else laaste = last;
		
		if (table[(word.length() + eerste + laaste)%lines] == null) {
			table[(word.length() + eerste + laaste)%lines] = word;
			if (first != -1) {
				gValues[ASCII(word.charAt(0))] = first;
			}
			if (last != -1) {
				gValues[ASCII(word.charAt(word.length()-1))] = last;
			}
			return true;
		}
		return false;
	}
	
	private String[] sort(String[] words) {
		Integer[] scores = new Integer[lines];
		Integer[] scores2 = new Integer[26];
		for (int a=0;a<lines;a++) {
			int first = ASCII(words[a].charAt(0));
			if (scores2[first] == null) {
				scores2[first] = 0;
			}
			else {
				scores2[first] = scores2[first]+1;
			}
			int last = ASCII(words[a].charAt(words[a].length()-1));
			if (scores2[last] == null) {
				scores2[last] = 0;
			}
			else {
				scores2[last] = scores2[last]+1;
			}
		}
		for (int a=0;a<lines;a++) {
			scores[a] = scores2[ASCII(words[a].charAt(0))] + scores2[ASCII(words[a].charAt(words[a].length()-1))];
		}
		String[] temp = new String[lines];
		for (int a =0;a<lines;a++) {
			int maks = 0;
			for (int b=1;b<lines;b++) {
				if (scores[b].compareTo(scores[maks]) > 0) {
					maks = b;
				}
			}
			temp[a] = words[maks];
			scores[maks] = -1;
		}
		return temp;
	}
	
	private int readLines(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader bf = new BufferedReader(fr);
		String line;
		int i = 0;
		
		while ((line = bf.readLine()) != null) i++;
		bf.close();
		return i;
		
	}
	
	private String[] openFile(String filename, int lines) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader bf = new BufferedReader(fr);
		String words[] = new String[lines];
		for (int a=0;a < lines;a++) {
			words[a] = bf.readLine();
		}
		bf.close();
		return words;
	}
	
	public int hashOf(String word)
	{
		/*
		This method should return the hash value of the word passed in as a 
		parameter.  If there is no such value (possibly because the word list 
		could not be hashed or the word was not part of the words to begin 
		with), -1 should be returned.
		*/
		if (failed) return -1;
		if (word == null) return -1;
		if (word.charAt(0) == word.charAt(word.length()-1)) {
			return (word.length() + gValueOf(word.charAt(0)))%lines;
		}
		return (word.length() + gValueOf(word.charAt(0)) + gValueOf(word.charAt(word.length()-1)))%lines;
		
	}
	
	public int gValueOf(char c)
	{
		/*
		This method should return the g-value of the character passed as a 
		parameter.  If the character does not have a g-value, -1 should be
		returned.
		*/
		c = Character.toUpperCase(c);
		int index = c-65;
		return gValues[index];
	}
	
	public String wordAt(int hashValue)
	{
		if (hashValue > lines-1) return "";
		if (table[hashValue] == null) return "";
		return table[hashValue];
				
	}
	private int ASCII(char c) {
		c = Character.toUpperCase(c);
		return  c-65;	
	}
	
}
