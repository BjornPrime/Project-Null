package bankapp.util;

import java.util.Scanner;

public class UserInput {
	private static Scanner scanner = new Scanner(System.in);
	
	public static int integerInput(int max) {
		
		int input;
		
		do {
			while (!scanner.hasNextInt()) { //check that an int has been entered
				scanner.nextLine();
				System.out.println("Please enter a whole number.");
			}
			 input = scanner.nextInt();
			 scanner.nextLine();
			 
			 if(input >= 0 && input <= max) {
				 return input;
			} else {
				System.out.println("Please enter a number between 0 and " + max + ".");
			}
		} while (input < 0 || input > max);
		
		return 0;
	}
	
	public static String stringInput(int max) {
		
		String input;
		
		while (true) {
			input = scanner.nextLine();
			
			input = input.trim();
			if(input.length() > 0 && input.length() <= max) {
				return input;
			} else if (input.length() > max) {
				System.out.println("String exceeded maximum length. Please enter string of " + max + " or fewer characters.");
			} else {
				System.out.println("Content not found. Please enter again.");
			}
		}
	}
	
}
