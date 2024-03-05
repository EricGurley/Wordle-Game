import java.util.Scanner;
import java.util.Random;

class NewWordleGame{
	public static void main(String[] args){
		
		//Gives us ASCII keywords for adding colors
		final String DEFAULT = "\u001B[0m";
		final String BLUE = "\u001B[38;5;51m";
		
		//initializes a word bank
		String[] word_bank = {  "Night", "Eagle", "Alien", "Otter", "Venom", "Snake",
                "Prank", "Happy", "Tiger", "Silly", "Queen", "Zebra", 
                "Gravy", "Maple", "Onion", "Whale", "Crown", "Ghost",
                "Armor", "Coral", "Panda", "Funky", "Actor", "Koala"};
		
		//introduction with rules for the game
		System.out.println("Welcome to Wordle!");
        System.out.print("Do you want to see the rules? (Type yes or no) ");
		
		boolean done_with_prompt = false;
        Scanner input = new Scanner(System.in);
        String response;


        while(done_with_prompt != true) {   // If the user types something other than yes or no, the loop restarts

            response = input.nextLine();
            response = response.toLowerCase();  // This is so that the input does not have to be case sensitive


            // If the input is yes, the rules are printed
            if (response.equals("yes")) {
                System.out.println( "\n\tOkay! Here are the rules!\n\nYou have to guess a word." + 
                                    "\nYou have 6 guesses.\nIf a letter is red, it is not in the word." +
                                    "\nIf a letter is yellow, it is in the word but is not in the right spot." +
                                    "\nIf a letter is green, it is in the word and is in the right spot." + 
                                    "\nGood luck and have fun!\n");
            }


            // If the input is no, it moves on to the next if statement, which ends the while loop
            if ((response.equals("no"))) {
                System.out.println("Okay then. Have fun!");
            }


            // If the input is either yes or no, then the input is valid and the while loop ends
            if ((response.equals("yes")) || (response.equals("no"))){
                done_with_prompt = true;
            }


            // If the input is not yes or no, the input is invalid, so the prompt
            // will restart because the while loop condition is not satisfied yet 
            else {
                System.out.print("That is not a valid response. Please try again. ");
            }
        }
        
        //sets a random secret word
        Random random = new Random();
        int index = random.nextInt(word_bank.length);    // Selects a random word from the word bank
        String secretWord = (word_bank[index]); // Initializing the answer to the randomly chosen word
        secretWord = secretWord.toUpperCase();
	// System.out.println(secretWord);	// Shows the answer to help with debugging

        //initializes a grid
        String[][] grid = {{"_","_","_","_","_"},{"_","_","_","_","_"},{"_","_","_","_","_"},{"_","_","_","_","_"},{"_","_","_","_","_"},{"_","_","_","_","_"}};

        //asks the user for 6 words and prints them to the grid
        String guess;
        for(int i = 0; i < 6; i++) {
        	guess = getWord();
			guess = guess.toUpperCase();

        	String[] colorCoded = checkWord(guess, secretWord);
        	grid[i] = colorCoded;
        	//this may be replaced by a 2D array print function if we make one
        	for(int j = 0; j < 6; j++) {
        		for(int k = 0; k < 5; k++) {
        			System.out.print(grid[j][k]);
        		}
        		System.out.println("");
        		//TO-DO: check if the word is correct and exit the loop if it is
        	}
        	System.out.println("");
			if (guess.equals(secretWord) == true) {
				System.out.println(BLUE + "CONGRATULATIONS!!!\n" + DEFAULT);
                break;
            }
			if (guess.equals(secretWord) == false && i == 5) {
			System.out.println("You lost!!! The word was " + secretWord + "!!!");
		}
        }
		

        //TO-DO: print success if the correct word was found
        //print fail if the correct word was not found
        //maybe ask to play again?
        input.close();
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
