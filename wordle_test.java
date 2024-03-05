import java.util.Scanner;

class WordleTest{
	public static void main(String[] args){
		//runs getWord once
		//runs checkWord with str and test word, "hello"
		//prints the color coded word

		String word = "hello";
		String str = getWord();
		String[] colorCoded = checkWord(str, word);

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
		input.close();
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

	public static String[] checkWord(String guess, String word){
		/*
		 * Compares user's string, guess, with a second string, secretWord
		 * Returns an array of strings that has one colored character per index
		 */

		guess = guess.toUpperCase();
		word = word.toUpperCase();
		char[] secretWord = new char[5];
		for(int i = 0; i < 5; i++){
			secretWord[i] = word.charAt(i);
		}
		
		//Gives us ASCII keywords for adding colors
		final String DEFAULT = "\u001B[0m";
        	final String GREEN = "\u001B[32m";
        	final String YELLOW = "\u001B[33m";
        	final String RED = "\u001B[31m";

		//declares all necessary method variables
		char char1;
		char char2;
		String[] results = new String[5];
		
		/*
		checking for green letters first and
		replacing those characters with '_'
		is necessary to avoid a bug for the following case
		secretWord: STAIR
		guess: STATS
		*/
		
		//checks if a letter is in the correct spot
		for(int currentIdx = 0; currentIdx < 5; currentIdx++){
			char1 = guess.charAt(currentIdx);
			char2 = secretWord[currentIdx];

			//checks if letter should be green and sets the color accordingly
			if(char1 == char2){
				results[currentIdx] = (GREEN + char1 + DEFAULT);
				secretWord[currentIdx] = ' ';
				continue;
			}
		}

		//compares one letter at a time and sets it to yellow or red
		for(int currentIdx = 0; currentIdx < 5; currentIdx++){
			char1 = guess.charAt(currentIdx);
			char2 = secretWord[currentIdx];
			
			//safety case to ensure green letters are not set to red
			if(secretWord[currentIdx] == ' ') {
				continue;
			}

			//checks if letter should be yellow and sets the color accordingly
			for(int i = 0; i < 5; i++){
				char1 = guess.charAt(currentIdx);
				char2 = secretWord[i];
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





