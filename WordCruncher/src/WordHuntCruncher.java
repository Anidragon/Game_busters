import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class WordHuntCruncher 

{

	/* 
	* Function handles user input to create the board of 16 letters
	* 
	* 
	*/
	public static ArrayList<String> wordsRet(String letters)
	{
	
	letters = letters.toLowerCase();

	// Creates an array out of the string of letters
	ArrayList<String> lettersArr = new ArrayList<String>();
	for(int i = 0; i < 16; i++) {
	lettersArr.add(letters.substring(i, i+1));
	}
	
	// narrow down the list of words to only those with the characters that appear on the board
	
	try {
		narrowDownWords(lettersArr);
	} 
	
	catch (IOException e) {
		e.printStackTrace();
	}
	
	// Next find all words that can be used in the game
	ArrayList<String> words = null;
	try {
		words = findWords(lettersArr);
	} catch (FileNotFoundException e) 
	{
	
		e.printStackTrace();
	}
	
	// Sort the words based on length
	Collections.sort(words, Comparator.comparing(String::length));
	
    //add all words to an arraylist to return to UI
	ArrayList<String> temp = new ArrayList<String>();
	for(int i = 0; i < words.size(); i++)
	{
	temp.add(words.get(i));
	}
	
	return temp;
	
	
	}
	
	/*
	* Function takes a file of all the words in the English Dictionary and creates 
	* a new file called validwordsWH.txt containing only words that are between 3 and
	* 7 characters whose letters can all be found on the board
	* 
	* @return void
	*/
	private static void narrowDownWords(ArrayList<String> letters) throws IOException {
	
	// Open the file of all words in the English dictionary
	File allWordsFile = new File("Everything.txt");
	Scanner reader = new Scanner(allWordsFile);
	
	// Create a new file to put the valid words
	FileWriter validWordsFile = new FileWriter("EverythingReduced.txt");
	
	// Iterate through all words in the dictionary
	while (reader.hasNextLine()) 
	{
	String curWord = reader.nextLine();
	
	// check if the word size permittable
	if(curWord.length() >= 3 && lettersMatch(letters, curWord)) 
	{
	validWordsFile.write(curWord + "\n");
	}
	}
	
	// Close files
	reader.close();
	validWordsFile.close();
	
	}
	
	/*
	* Function checks whether or not all the characters of the word parameter are
	* in the ArrayList of letters without repeats.
	* 
	* @return boolean
	*/
	private static boolean lettersMatch(ArrayList<String> letters, String word) {
	// Create a duplicate of the letters to remove from so letters aren't double counted
	ArrayList<String> lettersDup = new ArrayList<String>();
	for(int k = 0; k < 16; k++) {
	lettersDup.add(letters.get(k));
	}
	
	// Iterate through every character in the word
	for(int i = 0; i < word.length(); i++) {
	String curChar = word.substring(i,i+1); // Current character in the word
	boolean curCharFoundInLetters = false; 
	
	// Iterate through every letter on the board
	for(int j = 0; j < lettersDup.size(); j++) {
	// If the characters equal each other, the character was found and break
	if(curChar.equals(lettersDup.get(j))) {
	lettersDup.remove(j);
	curCharFoundInLetters = true;
	break;
	}
	}
	// If at the end of the loop, the character was not found, return false
	if(curCharFoundInLetters == false) {
	return false;
	}
	}
	// If every character was found, return true
	return true;
	}

	
	private static ArrayList<String> findWords(ArrayList<String> letters) throws FileNotFoundException 
	{
	
	// Create array of words to return to the main function for printing
	ArrayList<String> toReturn = new ArrayList<String>();
	
	// Create a new board object 
	WordHunter wordHuntBoard = new WordHunter();
	for(int i = 0; i < 16; i++) {
	wordHuntBoard.addTile(i, letters.get(i));
	}
	
	// Open the valid words text file
	File validWordsFile = new File("EverythingReduced.txt");
	Scanner reader = new Scanner(validWordsFile);
	
	// Iterate through all the valid words
	while(reader.hasNextLine()) {
	String curWord = reader.nextLine();

	
	// Convert the current word into an ArrayList of strings for each character
	ArrayList<String> curWordArr = new ArrayList<String>();
	for(int i = 0; i < curWord.length(); i++) {
	curWordArr.add(curWord.substring(i, i+1));
	}
	
	// Check to see if the word is valid
	if(wordHuntBoard.isWordValid(curWordArr)) 
	{
	toReturn.add(curWord);
	}
	}
	reader.close();
	toReturn.sort(Comparator.comparingInt(String::length));
	
	System.out.println(toReturn);
	return toReturn;
	
	
	
	}
	
	public static void sort(ArrayList<String> a)
	{

	    String temp;

	    // Note that we use a.length - 1 in the loop condition
	    // to account for the fact that we will be checking the value of the 
	    // element at the current index against the value of the element at the NEXT index.
	    // Since array indices are zero-based, iterating from 0 to a.length would result
	    // in an ArrayIndexOutOfBoundsException when we index = 7, since the
	    // base case would check a[7] against a[8], the latter of which does not exist

	   
	}
	
	public static void main(String[] args)
	{
		
		wordsRet("roakfrdtetignmog");
		
	}
	
}
