/**
 * Name: Nicholas Dill
 * Class: Design & Analysis of Algorithms
 * Description: A LCS dynamic programming algorithm that takes two strings from the user and returns the LCS.
 */

import java.util.Scanner;
  
public class LCS {
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		// Prompts the user to enter two string sequences to find the LCS of.
		System.out.println("Please enter the first string sequence.");
		String sequence1 = input.nextLine();
		System.out.println("Please enter the second string sequence.");
		String sequence2 = input.nextLine();
		
		// Call to LCS method which will find the LCS of the given sequences.
		longestCommonSubsequence(sequence1, sequence2, sequence1.length(), sequence2.length());
		
		input.close();
		
	}
	
	/*
	 * Method that will get the length of the LCS sequence. 
	 * Parameters: String a, String b (Two user given sequences), int aLength, int bLength (Length of the user 
	 * given string sequences).
	 */
	public static void longestCommonSubsequence(String a, String b, int aLength, int bLength) {
		
		// This matrix will be used to store the length of the LCS.
		int[][] c = new int[aLength + 1][bLength + 1];
		
		// Constructs the c array that will get the length of the LCS.
		for(int i = 0; i < aLength + 1; i++) {
			
			for(int j = 0; j < bLength + 1; j++) {
				
				// If we are at the top leftmost position of the array, set it equal to 0.
				if(i == 0 || j == 0) {
					
					c[i][j] = 0;
					
				}
				// Else if the last characters of a and b match, the LCS length is 1 + the element at c located at
				// aLength - 1 and bLength - 1.
				else if(a.charAt(i - 1) == b.charAt(j - 1)) {
					
					c[i][j] = c[i - 1][j - 1] + 1;
					
				}
				// Else if the last characters of a and b do not match, find the maximum of the two and store it in
				// the c matrix. 
				else if(c[i - 1][j] > c[i][j - 1]) {
					
					c[i][j] = c[i - 1][j];
					
				}
				else {
					
					c[i][j] = c[i][j - 1];
					
				}
				
			}
			
		}
		
		System.out.println("Matrix: ");
		
		for(int i = 0; i < c.length; i++) {
			
			for(int j = 0; j < c[i].length; j++) {
				
				System.out.print(c[i][j] + " ");
				
			}
			
			System.out.println();
			
		}
		
		System.out.print("LCS: ");
		
		// Call to method that will print out the LCS.
		printLongestCommonSubsequence(c, a, b, aLength, bLength);
		
	}
	
	/*
	 * Method that recursively prints out the LCS back to the user.
	 * Parameters: c (Given matrix with length of LCS), a (First string sequence), b (Second string sequence), 
	 * aLength (Length of string sequence a), bLength (Length of string sequence b).
	 */
	public static void printLongestCommonSubsequence(int[][] c, String a, String b, int aLength, int bLength) {
		
		// If we have reached the beginning of the string, return from the method.
		if(aLength == 0 || bLength == 0) {
			
			return;
			
		}
		// Else if the character at the end of strings a and b match, make a recursive call to the next character 
		// and then print out the matching character.
		else if(a.charAt(aLength - 1) == b.charAt(bLength - 1)) {
			
			printLongestCommonSubsequence(c, a, b, aLength - 1, bLength - 1);
			System.out.print(a.charAt(aLength - 1));
			
		}
		// Else if the character is not found in both strings, then find the larger of the two values and begin 
		// moving in that direction. 
		else if(c[aLength - 1][bLength] > c[aLength][bLength - 1]) {
			
			printLongestCommonSubsequence(c, a, b, aLength - 1, bLength);
			
		}
		else {
			
			printLongestCommonSubsequence(c, a, b, aLength, bLength - 1);
			
		}
		
	}
	
}
