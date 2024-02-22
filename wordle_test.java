import java.util.Scanner;

class WordleTest{
	public static void main(String[] args){
		//runs getWord once
		//runs checkWord with str and test word, "hello"
		//prints the color coded word

		String str = getWord();
		String[] colorCoded = checkWord(str, "hello");

		for(int i = 0; i < 5; i++){
			System.out.print(colorCoded[i]);
		}
	}

	public static String getWord(){
		/*
		 *Asks the user for a string with 5 characters
		 *The user will be prompted until a valid word is chosen
		 */
		Scanner input = new Scanner(System.in);
		String current;
		boolean isValid = false;

		System.out.print("Please enter a 5 letter word: ");

		//While loop tests if the word is valid and loops until a valid word is chosen
		while(!isValid){
			current = input.next();

			if(current.length()!=5){
				System.out.print(current + " is an invalid word. Please enter a 5 letter word: ");
				continue;
			}
			isValid = true;
			return current;
		}
		return "Failed to select word";
	}

	public static String[] checkWord(String guess, String secretWord){
		/*
		 * Compares user's string, guess, with a second string, secretWord
		 * Returns an array of strings that has one colored character per index
		 */

		guess = guess.toUpperCase();
		secretWord = secretWord.toUpperCase();
		
		//Gives us ASCII keywords for adding colors
		final String DEFAULT = "\u001B[0m";
        	final String GREEN = "\u001B[32m";
        	final String YELLOW = "\u001B[33m";
        	final String RED = "\u001B[31m";

		//declares all necessary method variables
		char char1;
		char char2;
		String[] results = new String[5];

		//compares one letter at a time
		for(int currentIdx = 0; currentIdx < 5; currentIdx++){
			char1 = guess.charAt(currentIdx);
			char2 = secretWord.charAt(currentIdx);

			//checks if letter should be green
			if(char1 == char2){
				results[currentIdx] = (GREEN + char1 + DEFAULT);
				continue;
			}

			//checks if letter should be yellow
			for(int i = 0; i < 5; i++){
				char1 = guess.charAt(currentIdx);
				char2 = secretWord.charAt(i);
				if(char1 == char2){
					results[currentIdx] = (YELLOW + char1 + DEFAULT);
					break;
				}
				//sets color to red if the letter should not be green or yellow
				else {
					results[currentIdx] = (RED + char1 + DEFAULT);
					continue;
				}
			}
		}
		return results;
	}
}





