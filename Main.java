package pkg1;

import java.util.Scanner;
/*
 * Author: Jeremy Sowder
 * last edited: 4/7/25
 * type of program: personal passion project
 */
public class Main {

	public static void main(String[] args) {
		boolean goAgain = false;
		boolean exceptionFound = false;
		final int INITIALNUMCARDS = 10; // as described in welcome string
		final Scanner in = new Scanner(System.in); // utilize scanner for user-input
		do {
			// initialize trash-necessary variables
			Hand player1 = new Hand();
			Hand player2 = new Hand();
			Trash game = new Trash(player1, player2);
			// play game
			game.playTrash(INITIALNUMCARDS,INITIALNUMCARDS);
			do {
				try {
					// ask user if they would like another iteration
					System.out.println("Would you like to run another Trash game? (y/n) ");
					String userAnswer = in.nextLine();
					if(userAnswer.equalsIgnoreCase("y")) {
						goAgain = true;
						exceptionFound = false;				
					}
					else if(userAnswer.equalsIgnoreCase("n")) {
						goAgain = false;
						exceptionFound = false;
						System.out.println("Thank you for your time.");
					}
					else {
						throw new IllegalArgumentException();
					}
				}
				// flag any unrecognized responses
				catch(IllegalArgumentException e) {
					System.out.println("Unrecognized input, trying again.");
					exceptionFound = true;
				}	
			}
			while(exceptionFound); // ask question again if user answers in a way the computer does not recognize
		}
		while(goAgain); // reiterate if user desires
		in.close();	
	}
}