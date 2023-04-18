import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.ArrayList;
 import java.util.Scanner;
 public class AnagramSolver {

 private ArrayList<String> dictionary;
 private ArrayList<String> found;
 private ArrayList<Character> blacklist;
 private ArrayList<String> whitelist;
private ArrayList<Boolean> stricter;
 public AnagramSolver(String letters) {
 init();
 solve(letters);
 }

 // Initializer
 private void init() {
 dictionary = new ArrayList<String>();
 found = new ArrayList<String>();

 
 readFile();
 }


    private ArrayList<String> whitelist(String letters) {

        ArrayList<String> whitelist = new ArrayList<String>();

        for (int i = 0; i < letters.length(); i++) {

            whitelist.add(letters.charAt(i)+"");

        }

      return whitelist;

    }

 

    // Reads in a dictionary text file

    private void readFile() {

        try {

            File file = new File("Everything.txt");

            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
            	
          

               dictionary.add(scanner.nextLine());

            }

        } catch (FileNotFoundException e) {

            e.printStackTrace();

        }

    }


    // Solves an anagram

    private void solve(String letters) {

        letters = letters.toLowerCase();
        int size = letters.length();
        // Add the letters to a white list
       
        

        // Loop to check a word in dictionary

        for (int i = 0; i < getDictionary().size(); i++) {
        	
            ArrayList<Integer> valid = new ArrayList<Integer>();
           
            whitelist = whitelist(letters);
           System.out.println(whitelist + "Start");
            // Validator array stores integers. The purpose of this array is to

            // hold a 1 or 0 for each letter in the selected word from the

            // dictionary. After the whole word has been scanned, a checksum

            // will be calculated. Each integer is multiplied by one another. If

            // there is at least one 0 then the word will be invalid, because

            // it has to be composed of every letter in the whitelist.

      

            // Loop to compare each letter in the selected word
          
            loop: for (int d = 0; d < getDictionary().get(i).length(); d++) {

                int match = 0; 
               
                // Default value of 0 until proven true

                // Compare each whitelist letter to each letter of the

                // selected word.

                for (int c = 0; c < size; c++) {
                    match = 0; // Reassign the value 0.

                    if ( whitelist.contains(getDictionary().get(i).charAt(d)+"")) {
                    	
                    	whitelist.remove(c);
                    	System.out.println(getDictionary().get(i));
                    	  System.out.println(whitelist);
                        // If the selected word contains a letter from the

                      // whitelist then verify a match and move on to the next

                        // letter in the word and delete that letter to remove any repetition

                        match = 1;

                        continue loop;

                    }
                    
                 
                  
                }

                valid.add(match);
          

            }

            int checksum = 1;

            for (Integer v : valid) {

                checksum *= v;

            }
          
            if (checksum == 1

                    && getDictionary().get(i).length() <= size

                    && getDictionary().get(i).length() > 3) {

              found.add(getDictionary().get(i));
              
              

            }

     }
    
          System.out.println(found);

    }

 

    // returns the list of anagrams for a given set of letters to the UI program

    private ArrayList<String> print(String letters) {
    	ArrayList<String> arr = new ArrayList<String>();
        for (int i = 0; i < found.size(); i++) 
        {
        	
            arr.add(found.get(i));

        }
        return arr;

    }

 

    // Retrieves the dictionary

    public ArrayList<String> getDictionary() {

        return dictionary;

    }

 


}
