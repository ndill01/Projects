// Nicholas Dill
// Programming Languages Concepts

import java.io.*;
import java.util.*;

public class LexAnalyzer {
	
	public static void main(String[] args) throws IOException {
		
		// Prompts user to give file name/path using Scanner.
		Scanner input  = new Scanner(System.in);
		System.out.println("Please input file name/path");
		String userFile = input.nextLine();
		BufferedReader reader = new BufferedReader(new FileReader(new File(userFile)));
		String line = reader.readLine();
		
		// Incrementing the input file line by line, finding every recognized token.
		while(line != null) {
			
			getLexemes(line);
			line = reader.readLine();
			
		}
		
		reader.close();
		input.close();
		
	}
	
	// Method that searches the given String str to gather the lexemes.
	private static void getLexemes(String str) {
		
		str.trim();
		String lexeme = "";
		
		// for loop that traverses through the given String str.
		for(int i = 0; i < str.length(); i++) {
			
			// If the current position is not a space and not an operator, adds current character to lexeme String.
			if((!(Character.isWhitespace(str.charAt(i)))) && !(isOperator(str.charAt(i)))) {
				
				lexeme += "" + str.charAt(i);
				
			}
			else {
				
				// If the current position is operator, print the character and operator token.
				if(isOperator(str.charAt(i))) {
					
					System.out.println("operator: " + str.charAt(i));
					
				}
				
				// Calls method to find the proper token for the given lexeme.
				getToken(lexeme);
				lexeme = "";
				
			}
			
		}
		
	}
	
	// Method that finds the proper token from the given String str.
	private static void getToken(String str) {
		
		// If the String str contains a keyword, print the token and lexeme back to the user.
		if((str.contains("int") || str.contains("double") || str.contains("String"))) {
			
			System.out.println("keyword: " + str);
			
		}
		// If the String str is enclosed in " ", print the token and lexeme back to the user.
		else if(str.startsWith("\"") && str.endsWith("\"")) {
			
			System.out.println("String constant: " + str);
			
		}
		else {
			
			// If the String str matches an int constant format, print the token and lexeme back to the user.
			if(str.matches("\\d+")) {
				
				System.out.println("int constant: " + str);
				
			}
			// If the String str matches a double constant format, print the token and lexeme back to the user.
			else if(str.matches("\\d+\\.\\d+")) {
				
				System.out.println("double constant: " + str);
				
			}
			// If the String str has varied format as an identifier, print the token and lexeme back to the user.
			else if(str.matches(".*[a-z]*.")) {
				
				System.out.println("identifier: " + str);
				
			}
			// Otherwise, everything else is not recognized and prints error back to the user.
			else {
				
				System.out.println("error: " + str);
				
			}
			
		}
		
	}
	
	// Method that determines if the given character is recognized as a Java operator.
	private static boolean isOperator(char ch) {
		
		return (ch == '=' || ch == '(' || ch == ')' || ch == '+' || ch == '-' || ch == '*' || ch == '/' || 
				ch == ',' || ch == ';');
		
	}
	
}
