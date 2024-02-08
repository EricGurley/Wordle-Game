// CSE 1325 Team Project
// Writers: Eric Gurley, Jace Malone, Brian Villegas, Atoosa Karami


import java.util.Random;
import java.util.Scanner;

class wordle_game {
    public static void main(String[] args) {

        
        String[] word_bank = {  "Night", "Eagle", "Alien", "Otter", "Venom", "Snake",
                                "Prank", "Happy", "Tiger", "Silly", "Queen", "Zebra", 
                                "Gravy", "Maple", "Onion", "Whale", "Crown", "Ghost",
                                "Armor", "Coral", "Panda", "Funky", "Actor", "Koala"};


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

        // This creates the chart for the user
        for (int i = 0; i < 7; i++) {
            System.out.println("----------------");

            // This prevents an extra row of vertical lines once the table is done printing
            if (i < 6) {
                System.out.println("|  |  |  |  |  |");
            }
        }


        Random random = new Random();
        int index = random.nextInt(word_bank.length);   // Selects a random word from the word bank
        String answer = (word_bank[index]);        // Initializing the answer to the randomly chosen word
        System.out.println(answer);


       input.close();
    }
}
