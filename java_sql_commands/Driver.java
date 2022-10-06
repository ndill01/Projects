package project520;

import java.io.*;
import java.util.Scanner;
import java.sql.*;

public class Driver {


    public static void main(String[] args) throws SQLException, IOException {
    	
    	// Connect to database server	
        try {
        	
			Class.forName("oracle.jdbc.driver.OracleDriver"); // Load the Oracle JDBC driver
			
		}
        
		catch(ClassNotFoundException e) {
			
			System.out.println("Could not load the driver");
			
		}
        
		try { // Connect to oracle and validate the user and password
			
			String oracleUser = "c##testd";
			String oraclePass = "t123456";
			String url = "jdbc:oracle:thin:@mcs.uscupstate.edu:1521:xe";
			Connection conn = DriverManager.getConnection(url, oracleUser, oraclePass); // Connect oracle

		    // Print menu
			Scanner scan = new Scanner(System.in);
			SQLCommands SQL = new SQLCommands();
			int command = 0;
			String input = "";
	        boolean keepGoing = true;
	        while (keepGoing == true) {
	        	
	            printmenu();
	            input = scan.nextLine();
	            command = Integer.parseInt(input);
				
				switch(command) {
					case 1: 
						SQL.addCourse(conn, scan); 
						break;
					case 2: 
						SQL.showCourses(conn); 
						break;
					case 3: 
						SQL.deleteCourse(conn, scan);
						break;
					case 4: 
						SQL.addStudent(conn, scan);
						break; 
					case 5: 
						SQL.deleteStudent(conn, scan);
						break; 
					case 6: 
						SQL.registerCourse(conn, scan);
						break; 
					case 7: 
						SQL.checkRegistration(conn, scan);
						break; 
					case 0: 
						System.out.println("The Session has been ended, Thank you!");
	               	    keepGoing = false;
	               	    conn.close(); // Close database connection   
						break;
						
				}    
				
		    }
	        
		}
		
		catch (SQLException ex) {
			
			System.out.println("An error occurred when connecting to the database server.");
			ex.printStackTrace();
			
		}
		
  }
    
    public static void printmenu() {
    	
    		System.out.println();
    		System.out.println("*********************************************************************");
	      	System.out.println("");
	      	System.out.println("***                                                               ***");
	      	System.out.println("");
	      	System.out.println("***              Welcome to Online Registration System            ***");
	      	System.out.println("");
	      	System.out.println("***                                                               ***");
	      	System.out.println("");
	      	System.out.println("*********************************************************************");
	      	System.out.println("1. Add a Course");
	      	System.out.println("2. Show All Courses");
	      	System.out.println("3. Delete a Course");
	      	System.out.println("4. Add a Student");
	      	System.out.println("5. Delete a Student");
	      	System.out.println("6. Register a Course");
	      	System.out.println("7. Check Student Registration");
	      	System.out.println("0. Quit ");
	      	System.out.println();
	      	
	      	System.out.println("Please Choose an Option");
	      	
    }
    
}
