//packageAutocompleter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MainTest {

	public static void main(String[] args) {
		// Uncomment line below to use your Autocompleter 
		// interactively with words.txt as the dictionary.
		// 
		// Enter a string and press Enter - the autocompletions
		// results from words.txt are printed.
		// 
		interactive_mode();
	
		// Setup
		ArrayList<String> R = new ArrayList<String> ();

		Autocompleter animals = new Autocompleter();
		
		test(animals.size() == 0);
		
		System.out.println("Test insert(): If some tests of this part fail, please directly give only effort score 40."); 
		// Test insert() and size(); 
		animals.insert("aardvark", 629356);
		animals.insert("albatross", 553191);
		animals.insert("alpaca", 852363);
		animals.insert("armadillo", 393754);
		animals.insert("crow", 4592109);
		animals.insert("crocodile", 1658300);
		animals.insert("cat", 46839855);
		animals.insert("camel", 11005001);
		animals.insert("goat", 5231735);
		animals.insert("gorilla", 1931906);
		animals.insert("goose", 3739382);
		animals.insert("goatfish", 19984);
		animals.insert("giraffe", 978584);
		
		test(animals.size() == 13);

		animals.insert("buffalo", 17808542);
        test(animals.size() == 14);
	
		animals.insert("deer", 10007644); 
		test(animals.size() == 15);

		animals.insert("horse", 58453720);        
		test(animals.size() == 16);
	
		animals.insert("bullfrog", 273571);
		test(animals.size() == 17);

	    System.out.println("Test function completions() and insert(): If some tests of this part fail, please directly give only effort score 40."); 
		// Test completions()
		animals.completions("a", R);
		test(R.size() == 3);
		test(R.get(0).equals("alpaca"));
		test(R.get(1).equals("aardvark"));
		test(R.get(2).equals("albatross"));
	
		animals.completions("b", R);
		test(R.size() == 2);
		test(R.get(0).equals("buffalo"));
		test(R.get(1).equals("bullfrog"));
	       
		animals.completions("c", R);
		test(R.size() == 3);
		test(R.get(0).equals("cat"));
		test(R.get(1).equals("camel"));
		test(R.get(2).equals("crow"));
	
		animals.completions("d", R);
		test(R.size() == 1);
		test(R.get(0).equals("deer"));
	
		animals.completions("e", R);
		test(R.size() == 0);
	
		animals.completions("f", R);
		test(R.size() == 0);
	
		animals.completions("g", R);
		test(R.size() == 3);
		test(R.get(0).equals("goat"));
		test(R.get(1).equals("goose"));
		test(R.get(2).equals("gorilla"));
	
		animals.completions("h", R);
		test(R.size() == 1);
		test(R.get(0).equals("horse"));
	
		animals.completions("aa", R);
		test(R.size() == 1);
		test(R.get(0).equals("aardvark"));
	 
		animals.completions("al", R);
		test(R.size() == 2);
		test(R.get(0).equals("alpaca"));
		test(R.get(1).equals("albatross"));
	
		animals.completions("an", R);
		test(R.size() == 0);
	
		animals.completions("bo", R);
		test(R.size() == 0);
	
		animals.completions("da", R);
		test(R.size() == 0);
	
		animals.completions("de", R);
		test(R.size() == 1);
		test(R.get(0).equals("deer"));
	
		animals.completions("go", R);
		test(R.size() == 3);
		test(R.get(0).equals("goat"));
		test(R.get(1).equals("goose"));
		test(R.get(2).equals("gorilla"));
	
		animals.completions("cro", R);
		test(R.size() == 2);
		test(R.get(0).equals("crow"));
		test(R.get(1).equals("crocodile"));
	
		animals.completions("goat", R);
		test(R.size() == 2);
		test(R.get(0).equals("goat"));
		test(R.get(1).equals("goatfish"));
	
		animals.completions("gir", R);
		test(R.size() == 1);
		test(R.get(0).equals("giraffe"));
	
		animals.completions("croc", R);
		test(R.size() == 1);
		test(R.get(0).equals("crocodile"));
	
		animals.completions("crow", R);
		test(R.size() == 1);
		test(R.get(0).equals("crow"));
	
		animals.completions("", R);
		test(R.size() == 3);
		test(R.get(0).equals("horse"));
		test(R.get(1).equals("cat"));
		test(R.get(2).equals("buffalo"));
	
		animals.completions("CAT", R);
		test(R.size() == 0);
	
		animals.completions("cAt", R);
		test(R.size() == 0);
	
		animals.completions("giraffez", R);
		test(R.size() == 0);
	
		animals.completions("robotron", R);
		test(R.size() == 0);
	
		animals.completions("Y", R);
		test(R.size() == 0);
	
		animals.completions("YOLO", R);
		test(R.size() == 0);
	
		animals.completions("!error", R);
		test(R.size() == 0);
		
		Autocompleter words = new Autocompleter();
		test(words.size() == 0);
		
		Scanner reader;
		try {
			
			reader = new Scanner(new FileReader("words.txt"));  
			assert(reader != null); // If this fails, you're missing words.txt
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
				int j = line.indexOf(" "); 
				words.insert(line.substring(0, j), Integer.parseInt(line.substring(j+1))); 		
			}
			reader.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		test(words.size() == 293147);
		 
	
		words.completions("a", R);
		test(R.size() == 3); 
		test(R.get(0).equals("and"));
		test(R.get(1).equals("a"));
		test(R.get(2).equals("are"));
		
		words.completions("b", R);
		test(R.size() == 3);
		test(R.get(0).equals("by"));
		test(R.get(1).equals("be"));
		test(R.get(2).equals("but"));
	
		words.completions("c", R);
		test(R.size() == 3);
		test(R.get(0).equals("can"));
		test(R.get(1).equals("contact"));
		test(R.get(2).equals("click"));
	
		words.completions("!", R);
		test(R.size() == 0);
		
		words.completions("ba", R);
		test(R.size() == 3);
		test(R.get(0).equals("back"));
		test(R.get(1).equals("based"));
		test(R.get(2).equals("baby"));
	
		words.completions("be", R);
		test(R.size() == 3);
		test(R.get(0).equals("be"));
		test(R.get(1).equals("been"));
		test(R.get(2).equals("best"));
	
		words.completions("th", R);
		test(R.size() == 3);
		test(R.get(0).equals("the"));
		test(R.get(1).equals("that"));
		test(R.get(2).equals("this"));
	
		words.completions("aft", R);
		test(R.size() == 3);
		test(R.get(0).equals("after")); 
		test(R.get(1).equals("afternoon")); 
		test(R.get(2).equals("afterwards")); 
	
		words.completions("cat", R);
		test(R.size() == 3);
		test(R.get(0).equals("categories"));
		test(R.get(1).equals("category"));
		test(R.get(2).equals("catalog"));
	
		words.completions("syz", R);
		test(R.size() == 3);
		test(R.get(0).equals("syzygy"));
		test(R.get(1).equals("syzygium"));
		test(R.get(2).equals("syzhthsh"));
	
		words.completions("sy$", R);
		test(R.size() == 0);
	
		words.completions("bird", R);
		test(R.size() == 3);
		test(R.get(0).equals("bird"));
		test(R.get(1).equals("birds"));
		test(R.get(2).equals("birding"));
	
		words.completions("hola", R);
		test(R.size() == 3);
		test(R.get(0).equals("hola"));
		test(R.get(1).equals("holabird"));
		test(R.get(2).equals("holanda"));
	
		words.completions("word", R);
		test(R.size() == 3);
		test(R.get(0).equals("word"));
		test(R.get(1).equals("words"));
		test(R.get(2).equals("wordpress"));
	
		words.completions("birdz", R);
		test(R.size() == 0);
	
		words.completions("yello", R); 
		test(R.size() == 3);
		test(R.get(0).equals("yellow"));
		test(R.get(1).equals("yellowstone"));
		test(R.get(2).equals("yellowpages"));
	
		System.out.println("Assignment Complete.");

		System.out.println("(1) If all tests pass, then check the time complexity: ");
		System.out.println("(2) If insert_recurse(Entry e, Node cur) does not work in O(log n), subtract 15."); 
		System.out.println("(3) If completions_recurse(String x, ArrayList<String> T) does not work in O(log n + k), subtract 15."); 
	}
	
	public static void test(Boolean a) {
		
	    if (a) {}
	    	//System.out.println("Match!!!");
	    else
	    	System.out.println("Fail!");  	
		return;
	}
	
	public static void interactive_mode(){
		
		Autocompleter words = new Autocompleter();
	
		int i = 0;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("words.txt")); 
			assert(reader==null); // If this fails, you're missing words2.txt
			String line = reader.readLine(); 
			
			while(line != null) {
				int j = line.indexOf(" "); 
				
				words.insert(line.substring(0, j), 
						Integer.parseInt(line.substring(j+1))); 
				i++; 
				line = reader.readLine();
			}
			
			reader.close();
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		assert(i == 293147); // If this fails, words2.txt is wrong
	
		ArrayList<String> C = new ArrayList<String>();
		
		Scanner obj = new Scanner(System.in);
		String line = obj.nextLine(); 
		
		while (line!="exit")
		{
			words.completions(line, C);
			for (String s : C)
				System.out.println(s);
		}
		obj.close();
	}

}



