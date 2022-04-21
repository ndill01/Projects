/**
 * This program displays statistics about the team and players of the Atlanta Braves from their 1999 season
 * based on user commands.
 * @author Nicholas Dill
 */

//Imports Scanner for user input and DecimalFormat for formatting batting and slugging averages.
import java.util.Scanner;
import java.text.DecimalFormat;

public class BravesStats {
	
	//The following instance variables store the name and data of the players on the Atlanta Braves team.
	private String[] players = {"Battle, Howard", "Lombard, George", "Speier, Justin", "Jones, Chipper", 
			"Lopez, Javy", "Simon, Randall", "Klesko, Ryan", "Jordan, Brian", "Jones, Andruw", 
			"Williams, Gerald", "Smoltz, John", "Hernandez, Jose", "Myers, Greg", "Lockhart, Keith", 
			"Boone, Bret", "Perez, Eddie", "Hunter, Brian", "Guillen, Ozzy", "Garcia, Freddy", "Weiss, Walt", 
			"Nixon, Otis", "Fabregas, Jorge", "Maddux, Greg", "Millwood, Kevin", "Galvine, Tom", 
			"Perez, Odalis", "Matos, Pascual", "Bergman, Sean", "Mulholland, Terry", "Chen, Bruce", 
			"DeRosa, Mark", "Ebert, Derrin", "Hudek, John", "McGlinchy, Kevin", "Remlinger Mike", 
			"Seanez, Rudy", "Bowie, Micah", "Cather, Mike", "Cortes, David", "Rocker, John", "Spinger, Russ", 
			"Stull, Everett", "Winkelsas, Joe", "Wohlers, Mark"};
	private int[] gamesPlayed = {15, 6, 18, 157, 65, 90, 133, 153, 162, 143, 29, 48, 34, 108, 152, 104, 
			114, 92, 2, 110, 84, 6, 31, 31, 33, 17, 6, 6, 16, 15, 7, 5, 12, 61, 70, 52, 3, 4, 4, 69, 46, 1, 
			1, 2};
	private int[] atBats = {17, 6, 3, 567, 246, 218, 404, 576, 592, 422, 62, 166, 72, 161, 608, 309, 181, 
			232, 2, 279, 151, 8, 64, 78, 65, 30, 8, 0, 16, 11, 8, 1, 1, 2, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0};
	private int[] runsScored = {2, 1, 0, 116, 34, 26, 55, 100, 97, 76, 11, 22, 10, 20, 102, 30, 28, 21, 1, 
			38, 31, 0, 7, 4, 3, 1, 0, 0, 2, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
	private int[] hits = {6, 2, 1, 181, 78, 69, 120, 163, 163, 116, 17, 42, 16, 42, 153, 77, 45, 56, 1, 63, 
			31, 0, 11, 12, 9, 4, 1, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private int[] doubles = {0, 0, 0, 41, 18, 16, 28, 28, 35, 24, 4, 8, 2, 3, 38, 17, 12, 16, 0, 13, 2, 0, 1, 
			2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private int[] triples = {0, 0, 0, 1, 1, 0, 2, 4, 5, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 4, 1, 0, 1, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private int[] homeRuns = {1, 0, 0, 45, 11, 5, 21, 23, 26, 17, 1, 4, 2, 1, 20, 7, 6, 1, 1, 2, 0, 0, 
			2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private int[] runsBattedIn = {5, 0, 0, 110, 45, 25, 80, 115, 84, 68, 7, 19, 9, 21, 63, 30, 30, 20, 1, 
			29, 8, 0, 7, 6, 4, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private int[] walks = {2, 1, 0, 126, 20, 17, 53, 51, 76, 33, 5, 12, 13, 19, 47, 17, 31, 15, 1, 35, 23, 
			0, 1, 2, 5, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
	private int[] strikeouts = {3, 2, 2, 94, 41, 25, 69, 81, 103, 67, 28, 44, 16, 21, 112, 40, 40, 17, 1, 
			48, 15, 0, 18, 29, 17, 10, 1, 0, 4, 6, 2, 1, 1, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0};
	
	/**
	 * Method Name: displayMenu
	 * Purpose: Displays the menu listing the commands that the user can select.
	 */
	public void displayMenu() {
		System.out.println("Commands:\tc - Show category leaders \n\t\tp - Show stats for a player "
				+ "\n\t\tt - Show totals for all players \n\t\tq - Quit");
	}
	
	/**
	 * Method Name: findCommand
	 * Purpose: Runs an infinite loop, getting commands from the user to display certain statistics.
	 */
	public void findCommand() {
		//Scanner to gather user input later in the method.
		Scanner input = new Scanner(System.in);
		String playerName;
		String key;
		
		//Infinite Loop to repeatedly ask users for commands.
		while(true) {
			//Gets command from user.
			System.out.print("\nEnter command (c, p, t, or q): ");
			key = input.nextLine().trim();
			System.out.println();
			//First command: Calls another method to find the leading players.
			if(key.equals("c")) {
				leadingPlayers();
			}
			//Second Command: Calls another method to find stats of a certain player.
			else if(key.equals("p")) {
				//Get input from user on which player's stats they wish to see.
				System.out.print("Enter player name, last name first: ");
				playerName = input.nextLine().toLowerCase().trim();
				matchPlayerWithInput(playerName);
			}
			//Third Command: Calls another method to find the stats of the entire team.
			else if(key.equals("t")) {
				displayTeamStats();
			}
			//Fourth Command: Terminates the program.
			else if(key.equals("q")) {
				System.exit(0);
			}
			//The following is a message printed if the user enters an unrecognizable command.
			else {
				System.out.println("Command was not recognized; please try again.");
			}
		}
	}
	
	/**
	 * Method Name: leadingPlayers
	 * Purpose: Finds the leading players in each category.
	 */
	private void leadingPlayers() {
		//The data[][] array stores the Instance Variables to be called in order in a for loop later in the method.
		int[][] data = {atBats, runsScored, hits, doubles, triples, homeRuns, runsBattedIn, walks, 
				strikeouts};
		//The categories[] array stores several Strings that will preface the value of the categories.
		String[] categories = {"At bats: ", "Runs scored: ", "Hits: ", "Doubles: ", "Triples: ", "Home runs: ", 
				"Runs batted in: ", "Walks: ", "Strikeouts: "};
		int index = 0;
		int index2 = 0;
		int highest;
		//The formatter variable stores the format for the Batting and Slugging Average doubles.
		DecimalFormat formatter = new DecimalFormat("#.000");
		
		//This for loop runs through each Instance Variable stored in the data array.
		for(int i = 0; i < data.length; i++) {
			System.out.println(categories[i]);
			//Default value for highest to be compared with other values later.
			highest = data[i][0];
			
			//This for loop runs through each Instance Variable.
			for(int j = 1; j < data[i].length; j++) {
				//Compares current value in the Instance Variable to the value in the highest variable.
				//If the current value is higher, it will be placed in highest and the index is saved.
				if(data[i][j] > highest) {
					highest = data[i][j];
					index = j;
				}
			}
			//Prints the leading player in the category.
			System.out.println(players[index] + " " + highest);
		}
		
		//Initializing variables for later use.
		double highestBattingAverage = 0.0;
		double highestSluggingAverage = 0.0;
		
		//This for loop runs through atBats and assigns a default value for the highestBattingAverage
		//and highestSluggingAverage variables to be compared with other values later.
		for(int k = 0; k < atBats.length; k++) {
			if(atBats[k] >= 100) {
				highestBattingAverage = battingAverage(hits[k], atBats[k]);
				highestSluggingAverage = sluggingAverage(doubles[k], triples[k], homeRuns[k], atBats[k], 
						hits[k]);
			}
		}
		
		//This for loop goes through each player, calculating their Batting and Slugging Average and then
		//comparing that value to the default highestBattingAverage and highestSlugging Average variables.
		for(int l = 0; l < players.length; l++) {
			//Compares current batting average to highestBattingAverage. If it is higher at the player's
			//atBats value is 100 or greater, the current value is made highest and the index is saved.
			if(battingAverage(hits[l], atBats[l]) > highestBattingAverage && atBats[l] >= 100) {
				highestBattingAverage = battingAverage(hits[l], atBats[l]);
				index = l;
			}
			//Compares current slugging average to highestSluggingAverage. If it is higher at the player's
			//atBats value is 100 or greater, the current value is made highest and the index is saved.
			if(sluggingAverage(doubles[l], triples[l], homeRuns[l], atBats[l], hits[l])
					> highestSluggingAverage && atBats[l] >= 100) {
				highestSluggingAverage = sluggingAverage(doubles[l], triples[l], homeRuns[l], atBats[l], 
						hits[l]);
				index2 = l;
			}
		}
		//Prints the leading batting and slugging average.
		System.out.println("Batting Average: " + "\n" + players[index] + " "
				+ formatter.format(highestBattingAverage));
		System.out.println("Slugging Average: " + "\n" + players[index2] + " " 
				+ formatter.format(highestSluggingAverage));
	}
	
	/**
	 * Method Name: matchPlayerWithInput
	 * Parameters: String name - Name of the payer the user wants to find.
	 * Purpose: Finds the player the user is searching for and returns that player's stats.
	 */
	private void matchPlayerWithInput(String name) {
		//The data[][] array stores the Instance Variables to be called in order in a for loop later in the method.
		int[][] data = {gamesPlayed, atBats, runsScored, hits, doubles, triples, homeRuns, runsBattedIn, 
				walks, strikeouts};
		//The categories[] array stores several Strings that will preface the value of the categories.
		String[] categories = {"Games played: ", "At bats: ", "Runs scored: ", "Hits: ", "Doubles: ", "Triples: ", "Home runs: ", 
				"Runs batted in: ", "Walks: ", "Strikeouts: "};
		//The formatter variable stores the format for the Batting and Slugging Average doubles.
		DecimalFormat formatter = new DecimalFormat("#.000");
		int index;
		
		//This for loop goes through the array of players to find the player the user is searching for.
		for(int i = 0; i < players.length; i++) {
			//Compares current player in the array to the name the user is searching for.
			if(players[i].toLowerCase().startsWith(name)) {
				index = i;
				System.out.println("Name: " + players[i]);
				//This for loop goes through each array and finds the players data to be displayed.
				for(int j = 0; j < data.length; j++) {
					System.out.println(categories[j] + data[j][index]);
				}
				//Prints the batting and slugging average of the player.
				System.out.println("Batting Average: " + formatter.format(battingAverage(hits[i], atBats[i])));
				System.out.println("Slugging Average: " + formatter.format(sluggingAverage(doubles[i], triples[i], 
						homeRuns[i], atBats[i], hits[i])));
				System.out.println();
			}
		}
	}
	
	/**
	 * Method Name: displayTeamStats
	 * Purpose: Finds the displays the combined stats of the entire team.
	 */
	private void displayTeamStats() {
		//The data[][] array stores the Instance Variables to be called in order in a for loop later in the method.
		int[][] data = {atBats, runsScored, hits, doubles, triples, homeRuns, runsBattedIn, walks, 
				strikeouts};
		//The categories[] array stores several Strings that will preface the value of the categories.
		String[] categories = {"At bats: ", "Runs scored: ", "Hits: ", "Doubles: ", "Triples: ", "Home runs: ", 
				"Runs batted in: ", "Walks: ", "Strikeouts: ",};
		//The formatter variable stores the format for the Batting and Slugging Average doubles.
		DecimalFormat formatter = new DecimalFormat("#.000");
		//Initialized variables to find overall sums and parts to find batting and slugging averages.
		int sum;
		int atBatsTotal = 0;
		int hitsTotal = 0;
		int doublesTotal = 0;
		int triplesTotal = 0;
		int homeRunsTotal = 0;
		
		//This for loop goes through each of the Instance Variables.
		for(int i = 0; i < data.length; i++) {
			sum = 0;
			System.out.print(categories[i]);
			//This for loop goes through each individual Instance Variables and adds together their values
			//to be stored in the variable sum.
			for(int j = 0; j < data[i].length; j++) {
				sum += data[i][j];
				//Checks for if the for loop is at a certain point and collects necessary data to calculate
				//batting and slugging averages.
				if(categories[i].equals("At bats: "))
					atBatsTotal = sum;
				else if(categories[i].equals("Hits: "))
					hitsTotal = sum;
				else if(categories[i].equals("Doubles: "))
					doublesTotal = sum;
				else if(categories[i].equals("Triples: "))
					triplesTotal = sum;
				else if(categories[i].equals("Home runs; "))
					homeRunsTotal = sum;
			}
			//Prints out the sum of each category.
			System.out.println(sum);
		}
		//Prints the team's batting and slugging average.
		System.out.println("Batting average: " + formatter.format(battingAverage(hitsTotal, atBatsTotal)));
		System.out.println("Slugging average: " + formatter.format(sluggingAverage(doublesTotal, triplesTotal, 
				homeRunsTotal, atBatsTotal, hitsTotal)));
	}

	/**
	 * Method Name: battingAverage
	 * Parameters: int numHits - Number of hits the player has made. int numAtBats - Number of times the
	 * player has been at bat.
	 * Purpose: Finds the batting average of a player based on the number of their hits and times they were 
	 * at bat.
	 */
	private double battingAverage(int numHits, int numAtBats) {
		//Checks to see if both parameters are equal to zero, if they are return zero else continue operation.
		if(numHits == 0 && numAtBats == 0)
			return 0.0;
		else
			return (double)numHits / numAtBats;
	}
	
	/**
	 * Method Name: sluggingAverage
	 * Parameters: int numDoubles- Number of doubles the player has made. int numTriples - Number of 
	 * triples the player has made. int numHomeRuns - Number of home runs the player has made. int numAtBats - 
	 * Number of times the player has been at bat. int numHits - Number of hits the player has made.
	 * player has been at bat.
	 * Purpose: Finds the slugging average of a player based on the number of their doubles, triples, home runs, 
	 * times they were at bat, and hits.
	 */
	private double sluggingAverage(int numDoubles, int numTriples, int numHomeRuns, int numAtBats, 
			int numHits) {
		double numSingles = (double)numHits - numDoubles - numTriples - numHomeRuns;
		if(numAtBats == 0)
			return 0.0;
		else
			return ((numSingles) + (numDoubles) * 2 + (numTriples) * 3 + (numHomeRuns) * 4) / (numAtBats);
	}
	
	/**
	 * Method Name: main
	 * Purpose: Creates an instance of the class and tests the function of the program. Also finds the
	 * commands from the user.
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		BravesStats rand = new BravesStats();
		
		rand.displayMenu();
		rand.findCommand();
	}
	
}
