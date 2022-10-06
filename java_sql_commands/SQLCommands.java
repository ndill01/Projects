package project520;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class SQLCommands {
	
	// Function adds a course to the Course database
	public void addCourse(Connection conn, Scanner scan) throws SQLException, IOException {
		
		// Get course code from the user
		Statement st = conn.createStatement();
		System.out.println("Add a Course");
		System.out.print("Please input course code: ");
		String courseCode = scan.nextLine().toUpperCase().trim();
		
		// Get course name from the user
		System.out.println("Please input course title: ");
		String courseTitle = scan.nextLine().trim();
		
		// Query to get course code from user inputed code
		String query = "select code from Course Where code = '" + courseCode + "'";
		ResultSet rs = st.executeQuery(query);
		
		// Checks if course is already in database
		if (rs.next()) {
			
			System.out.println("Course Already Exists");
			return;
			
		}
		
		// Query to insert the new course into the database
		query = "Insert into Course (code, title) values ('" + courseCode + "', '" + courseTitle + "')";
		
		// Execute the updated query
		try {
			
			st.executeUpdate(query);
			
		} 
		
		catch (SQLException e) {
			
			System.out.println("Message: " + e.getMessage());
		
		}
		
		rs.close();
		st.close();
		System.out.println("A New Course is Added.");
		
	}
	
	// Function shows all courses in the Course database
	public void showCourses(Connection conn) throws SQLException, IOException {
		
		try {
			
			// Query to get all attributes from all courses in the database
			System.out.println("Show All Courses");
			Statement st = conn.createStatement();
			String query = "select * from Course";
			ResultSet rs = st.executeQuery(query);
			
			// Display course info for all entries in the database
			while (rs.next()) {
				
				 String code = rs.getString("code");
				 String title = rs.getString("title");
			     System.out.println("Code: " + code + "\tTitle: " + title);
			     
			}
			
			rs.close();
			st.close();
			
		}
		
		catch (SQLException e) {
			
			System.out.println("Message: " + e.getMessage());
		
		}
		
	}
	
	// Function deletes a course from the Registered and Course database
	public void deleteCourse(Connection conn, Scanner scan) throws SQLException, IOException {
		
		// Get course code from the user
		Statement st = conn.createStatement();
		System.out.println("Delete a Course");
		System.out.print("Please input course code: ");
		String courseCode = scan.nextLine().toUpperCase().trim();
		
		// Query to get course code from user inputed code
		String query = "select code from Course where code = '" + courseCode + "'";
		ResultSet rs = st.executeQuery(query);
		
		if(rs.next()) {
			
			// Query statement to delete course from Registered database
			String regQuery = "delete from Registered where code = '" + courseCode + "'";
			
			// Query statement to delete course from Course database
			query = "delete from Course where code = '" + courseCode + "'";
			
			// Execute the updated query
			try {
				
				st.executeUpdate(regQuery);
				st.executeUpdate(query);
				
			}
			
			catch(SQLException e) {
				
				System.out.println("Message: " + e.getMessage());
				
			}
			
		}
		
		rs.close();
		st.close();
		
		System.out.println("\nA Course is Deleted");
		
	}
	
	// Function adds a student to the Student database
	public void addStudent(Connection conn, Scanner scan) throws SQLException, IOException {
		
		// Get student ssn from the user
		Statement st = conn.createStatement();
		System.out.println("Add a Student");
		System.out.print("Please input student ssn code: ");
		String ssn = scan.nextLine().toUpperCase().trim();
		
		// Get student name from the user
		System.out.print("\nPlease input student name: ");
		String name = scan.nextLine().toUpperCase().trim();
		
		// Get student address from the user
		System.out.print("\nPlease input student address: ");
		String address = scan.nextLine().toUpperCase().trim();
		
		// Get student major from the user
		System.out.print("\nPlease input student major: ");
		String major = scan.nextLine().toUpperCase().trim();
		
		// Query to get student ssn from user inputed ssn
		String query = "select ssn from Student where ssn = '" + ssn + "'";
		ResultSet rs = st.executeQuery(query);
		
		// Checks if student is already in the database
		if(rs.next()) {
			
			System.out.println("\nStudent Already Exists");
			return; 
			
		}
		
		// Query to insert the new student entry into the database
		query = "insert into Student (ssn, name, address, major) values ('" + ssn + "', '" + 
				name + "', '" + address + "', '" + major + "')";
		
		// Execute the updated query
		try {
			
			st.executeUpdate(query);
			
		}
		
		catch(SQLException e) {
			
			System.out.println("Message: " + e.getMessage());
			
		}
		
		rs.close();
		st.close();
		
		System.out.println("A New Student is Added");
		
	}
	
	// Function deletes a student from the Registered and Student database
	public void deleteStudent(Connection conn, Scanner scan) throws SQLException, IOException {
		
		// Get student ssn from the user
		Statement st = conn.createStatement();
		System.out.println("Delete a Student");
		System.out.print("Please input student ssn code: ");
		String ssn = scan.nextLine().toUpperCase().trim();
		
		// Query to get the student ssn from user inputed ssn
		String query = "select ssn from Student where ssn = '" + ssn + "'";
		ResultSet rs = st.executeQuery(query);
		
		if(rs.next()) {
			
			// Query statement to delete the entry from the Registered database
			String regQuery = "delete from Registered where ssn = '" + ssn + "'";
			
			// Query statement to delete the entry from the Student database
			query = "delete from Student where ssn = '" + ssn + "'";
			
			// Execute the updated query
			try {
				
				st.executeUpdate(regQuery);
				st.executeUpdate(query);
				
			}
			
			catch(SQLException e) {
				
				System.out.println("Message: " + e.getMessage());
				
			}
			
		}
		
		rs.close();
		st.close();
		
		System.out.println("\nA Student is Deleted");
		
	}
	
	// Function registers a new course for a student in the Registered database
	public void registerCourse(Connection conn, Scanner scan) throws SQLException, IOException {
		
		// Get the student ssn from the user
		Statement st = conn.createStatement();
		System.out.println("Register a Course");
		System.out.print("Please input student ssn code: ");
		String ssn = scan.nextLine().toUpperCase().trim();
		
		// Get the course code from the user
		System.out.print("\nPlease input course code: ");
		String courseCode = scan.nextLine().toUpperCase().trim();
		
		// Get the register year from the user
		System.out.print("\nPlease input year: ");
		int registerYear = scan.nextInt();
		
		// Get the register semester from the user
		System.out.print("\nPlease input semester: ");
		String registerSemester = scan.nextLine().toUpperCase().trim();
		
		// Query to get all the attributes from entry matching user inputed ssn, code, year, and semester
		String query = "select * from Registered where ssn = '" + ssn + "' and code = '" + 
				courseCode + "' and year = '" + registerYear + "' and semester = '" + 
				registerSemester + "'";
		ResultSet rs = st.executeQuery(query);
		
		// Checks if course is already registered in the database
		if(rs.next()) {
			
			System.out.println("\nCourse Already Registered");
			return; 
			
		}
		
		// Query to insert the new entry into the database
		query = "insert into Registered (ssn, code, year, semester) values ('" + ssn + "', ;" + 
				courseCode + "', '" + registerYear + "', '" + registerSemester + "')";
		
		// Execute the updated query
		try {
			
			st.executeUpdate(query);
			
		}
		
		catch(SQLException e) {
			
			System.out.println("Message: " + e.getMessage());
			
		}
		
		rs.close();
		st.close();
		
		System.out.println("\nCourse is Registered");
		
	}
	
	// Function displays all courses a student is registered for from the Registered database
	public void checkRegistration(Connection conn, Scanner scan) throws SQLException, IOException {
		
		// Get the student ssn from the user
		Statement st = conn.createStatement();
		System.out.println("Check Student Registration");
		System.out.print("Please input student ssn code: ");
		String ssn = scan.nextLine().toUpperCase().trim();
		
		// Query to get the code from Registered that matches user inputed ssn
		String query = "select code from Registered where ssn = '" + ssn + "'";
		ResultSet rs = st.executeQuery(query);
		
		if(rs.next()) {
			
			// Query to get all attributes from course that matches the course code
			query = "select * from Course where code = '" + rs.getString("code") + "'";
			
			// Execute the updated query and display course info
			try {
				
				st.executeUpdate(query);
				String code = rs.getString("code");
				String title = rs.getString("title");
			    System.out.println("Code: " + code + "\tTitle: " + title);
				
			}
			
			catch(SQLException e) {
				
				System.out.println("Message: " + e.getMessage());
				
			}
			
		}
		
		rs.close();
		st.close();
		
	}
	
}
