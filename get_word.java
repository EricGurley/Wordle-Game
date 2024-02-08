import java.util.Scanner;

class WordleTest{
	public static void main(String[] args){
		//runs getWord once and prints the result

		String str = getWord();
		System.out.println(str);
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
}





