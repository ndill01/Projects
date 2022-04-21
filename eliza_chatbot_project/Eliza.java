/**
 * This program allows the user to simulate a conversation with a psychotherapist.
 * @author Nicholas Dill
 */

import java.util.Scanner;

public class Eliza {
	public static void main(String[] args) {
		//Stores the responses that Eliza will give to the user based on what they enter.
		String[] responses = {"Can you think of a specific example?", "Is that the real reason?", 
				"Please don't apologize.", "You don't seem very certain", "Do you really think so?", 
				"We were discussing you, not me.", "Why do you think so?", "Why not?", 
				"I am sorry to hear that you are ", "Your " };
		
		//Stores the responses that Eliza will give when the user input does not have any keywords.
		String[] unsureResponses = {"What does that suggest to you?", "I see.", 
				"I'm not sure I understand you fully.", "Can you elaborate?", "That is quite interesting." };
		
		Scanner input = new Scanner(System.in);
		System.out.println("The doctor is in.");
		
		//Asks for the first line of user input.
		System.out.print("\nWhat's on your mind? \n- ");
		
		//Stores, trims, and converts input to lower case.
		String userInput = input.nextLine().trim().toLowerCase();
		
		//Declared variable for random numbers for the random responses.
		int randResponse;
		
		
		//While loop will run and ask the user questions continuously until the user input includes "bye".
		while(userInput.indexOf("bye") == -1) {
			//Removes punctuation in input if present.
			if(userInput.indexOf(".") != -1 || userInput.indexOf("?") != -1 || userInput.indexOf("!")
					!= -1) {					
				userInput = userInput.substring(0, userInput.length() - 1);
			}
			
			//Checks for specific keywords in the users input.
			if(userInput.indexOf("always") != -1) {
				System.out.print(responses[0] +  "\n- ");
				userInput = input.nextLine().trim().toLowerCase();
			}
			else if(userInput.indexOf("because") != -1) {
				System.out.print(responses[1] + "\n- ");
				userInput = input.nextLine().trim().toLowerCase();
			}
			else if(userInput.indexOf("sorry") != -1) {
				System.out.print(responses[2] + "\n- ");
				userInput = input.nextLine().trim().toLowerCase();
			}
			else if(userInput.indexOf("maybe") != -1) {
				System.out.print(responses[3] + "\n- ");
				userInput = input.nextLine().trim().toLowerCase();
			}
			else if(userInput.indexOf("i think") != -1) {
				System.out.print(responses[4] + "\n- ");
				userInput = input.nextLine().trim().toLowerCase();
			}
			else if(userInput.indexOf("you") != -1) {
				System.out.print(responses[5] + "\n- ");
				userInput = input.nextLine().trim().toLowerCase();
			}
			else if(userInput.indexOf("yes") != -1) {
				System.out.print(responses[6] + "\n- ");
				userInput = input.nextLine().trim().toLowerCase();
			}
			else if(userInput.indexOf("no") != -1) {
				System.out.print(responses[7] + "\n- ");
				userInput = input.nextLine().trim().toLowerCase();
			}
			
			//Checks for specific keywords and responds with portion of the users own input.
			else if(userInput.indexOf("i am") != -1) {
				System.out.print(responses[8] + userInput.substring(userInput.indexOf("i am") + 
						5, userInput.length()) +  ".\n- ");
				userInput = input.nextLine().trim().toLowerCase();
			}
			else if(userInput.indexOf("i'm") != -1) {
				System.out.print(responses[8] + userInput.substring(userInput.indexOf("i'm") + 
						4, userInput.length()) +  ".\n- ");
				userInput = input.nextLine().trim().toLowerCase();
			}
			else if(userInput.indexOf("my") != -1) {
				//If user states "me", then it is replaced with "you" when Eliza responds.
				if(userInput.indexOf("me") != -1) {
					System.out.print(responses[9] + userInput.substring(userInput.indexOf("my") + 
							3, userInput.indexOf("me")) +  "you.\n- ");
					userInput = input.nextLine().trim().toLowerCase();
				}
				
				//Otherwise, Eliza will state the users input starting after "my".
				else {
					System.out.print(responses[9] + userInput.substring(userInput.indexOf("my") + 
							3, userInput.length()) +  ".\n- ");
					userInput = input.nextLine().trim().toLowerCase();
				}
			}
			
			/*
			 * If the user input includes no keywords, then randResponse is set to 
			 * a random number and a response is picked from the unsureResponses Array via an index based
			 * on the random number.
			 */
			else {
				randResponse = (int)(Math.random() * unsureResponses.length);
				if(randResponse == 0) {
					System.out.print(unsureResponses[0] + "\n- ");
					userInput = input.nextLine().trim().toLowerCase();
				}
				else if(randResponse == 1) {
					System.out.print(unsureResponses[1] + "\n- ");
					userInput = input.nextLine().trim().toLowerCase();
				}
				else if(randResponse == 2) {
					System.out.print(unsureResponses[2] + "\n- ");
					userInput = input.nextLine().trim().toLowerCase();
				}
				else if(randResponse == 3) {
					System.out.print(unsureResponses[3] + "\n- ");
					userInput = input.nextLine().trim().toLowerCase();
				}
				else if(randResponse == 4) {
					System.out.print(unsureResponses[4] + "\n- ");
					userInput = input.nextLine().trim().toLowerCase();
				}
				else if(randResponse == 5) {
					System.out.print(unsureResponses[5] + "\n- ");
					userInput = input.nextLine().trim().toLowerCase();
				}
			}
		}
		input.close();
	}
}