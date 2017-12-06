
import java.sql.SQLException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ken
 */
public class DBConnector {
    public  static Scanner scanner = new Scanner(System.in);
        public  static char choice;
        public static int i, studentId;
        public  static boolean doneAdding;
        public  static String fname, lname, degree, gender;
     
      

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        CrudOps ops = new CrudOps();

    }
    
    // Method used to get the details of a new student to add to the database. 
    
    static void getDetails() {

        System.out.println("Please enter student's ID Number: ");
        studentId = scanner.nextInt();
                        
        System.out.println("Please enter student's First Name: ");
        fname =  scanner.next();
                        
        System.out.println("Please enter student's Last Name: ");
        lname =  scanner.next();
                        
        System.out.println("Please enter student's Degree Programme: ");
        degree =  scanner.next();
                    
        System.out.println("Please select student's gender :('M'/'F')");
        gender = scanner.next(); 
                        
        CrudOps cop = new CrudOps();
            cop.setStudentID(studentId);
            cop.setFname(fname);
            cop.setLname(lname);
            cop.setDegree(degree);
            cop.setGender(gender);
            cop.save();
            
        
        // Loop to get more students to register, while saving them.            
        System.out.println("Are you done registering students?\n ('Y'/'N') ");
        choice = scanner.next().trim().charAt(0);
        
            switch (choice){
    
                case 'Y':
                    System.out.println("You chose to finish adding new students");
                    System.exit(0);
                    break;
                case 'N':
                    System.out.println("You chose to continue adding new students");
                    getDetails();
                    break;
                default:
                    System.out.print("Invalid  option.\n");
                    break;
                }
    }

}
