/**
 * Add comments at the heading describing what the program does
 * as well as who authored it.
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Snowman {
	List<String> wordList = new ArrayList<String>();

	//private char nextChoice;
	private int incorrect;
	private String word;
		
	// Read in the list of words
	public void readWords(String fileName) throws FileNotFoundException {
		Scanner s = new Scanner(new File(fileName));
		while (s.hasNext())
			wordList.add(s.next());
		s.close();
	}
	
	// returns a random word from wordList
	public String getWord() {
		Random r = new Random();
		return wordList.get(r.nextInt(wordList.size()));
	}
	
	//running the game
	public void playGame() {
		
		this.word = getWord();
		System.out.println(word);
		char[] newA = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
        	newA[i] = '_';
        	System.out.print(" _ ");
        }
        
        int incorrect = this.incorrect;
        ArrayList<Character> guess = new ArrayList<Character>();

    	while(true) {
    		//get letter
    		Scanner scanner = new Scanner(System.in);
            System.out.println();
    		System.out.print("Your choice: ");
    		char nextChoice = scanner.next().charAt(0);
    		System.out.println("you entered " + nextChoice);
    		//making sure the input is a letter
    		if(Character.isLetter(nextChoice) == true) {   
        		//setting the input to lower case	
        		nextChoice = Character.toLowerCase(nextChoice);
    		//if letter is in word
			if(inWord(nextChoice) == true) {
				System.out.println(nextChoice + " is in the word!");
				newA = correctPrint(nextChoice, newA);
			
				printArray(newA);
				
				//winning
				boolean winner = false;
			
					for(int i = 0; i < word.length(); i++) {
						if(newA[i] == word.charAt(i)) {
							winner = true;
						} else {
							winner = false;
						}
					}		
					if(winner == true) {
						System.out.println("\n");
						System.out.println("word: " + word);
						System.out.println("YOU WIN");
						break;
					}	
			} else {
				//if letter is not in word
				for(int i = 0; i < guess.size(); i++) {
					if(nextChoice == guess.get(i)) {
						System.out.println("you already guessed this letter");
					}
				}
				System.out.println(nextChoice + " is not in the word");
				newA = correctPrint(nextChoice, newA);
				printArray(newA);
				incorrect = incorrect + 1;
			
					//if you lose
					if(incorrect == 6) {
						System.out.println("\n");
						System.out.println("You lose");
						System.out.println("the word was: " + word);
						break;
					}
				
				 System.out.println();
				 System.out.println("wrong answers: " + (incorrect));
			}
			
		//if not a letter 	
    	} else {
    		System.out.println("not a letter, try again");
    	}
    	}  
    	
	}
	
	
	//printing the array with spaces
	public void printArray(char[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			System.out.print(" ");
		}
	}
	
	
	//test to see if the letter is in the word to return true
	public boolean inWord(char nextChoice) {
		boolean correct = false;
		String word = this.word;
			for(int i = 0; i < word.length(); i++) {
				if (nextChoice == word.charAt(i)) {
					return correct = true;
				} else {
					correct = false;
				}
			}		
		return correct;
	}
	
	
	
	//if in word, put in print out
	public char[] correctPrint(char nextChoice, char[] newA) {
				
		char letter = nextChoice;
		String word = this.word;
		char[] cArray = newA;
		this.incorrect = 0;
		
		for(int i = 0; i < word.length(); i++) {
			if(cArray[i] == '_') {
				if (word.charAt(i) == nextChoice) {
					cArray[i] = letter;
				} else {
					cArray[i] = '_';
				}
			} else {
				cArray[i] = cArray[i];
			}
		}
		return cArray;
	}
		
	
	public static void main(String[] args) {
		Snowman game = new Snowman();

		Scanner scanner = new Scanner(System.in);
		System.out.println("here is a game of 'Snowman'\n"
				+ "this game is similar to hangman, \n"
				+ "you will enter a letter and try and guess the hidden word, \n"
				+ "as you play you can guess 6 inncorrect letters, \n"
				+ "once you hit 6 your snowman is built");
		System.out.println("Here's your word!");
		
		try {
			game.readWords("words.txt");
			game.playGame();
		} catch (FileNotFoundException fnf) {
			System.err.println("words.txt file Not Found");
		}

		String userInput = scanner.nextLine();
	}

}
