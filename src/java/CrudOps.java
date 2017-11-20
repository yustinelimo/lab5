
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author badi
 */
public class CrudOps {
        private int studentID;
    private String fname, lname, degree, gender ;
    
    // Setters
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    // Getters
    public int getStudentID() {
        return studentID;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getDegree() {
        return degree;
    }

    public String getGender() {
        return gender;
    }

            // Methods used to process gender from string to 
            // integer and vice versa
    
        public int processGender(String g){
               if (g.equalsIgnoreCase("M")){
                        return 1;
                }return 0;
        }


        public String processGender(int g){
               if (g == 1 ){
                        return "Male";
                }return "Female";
        }
    
        //method to verify users on login
    public boolean checkRecord(int reg_number) {
        ResultSet rs = null; 
        PreparedStatement pst = null;
        Connection con = new ConnectingTo().connector();
    // Check that the record exists
        try{
        
        pst = con.prepareStatement("SELECT * FROM student_details WHERE student_id = ?");
        pst.setInt(1,getStudentID());
        rs = pst.executeQuery();
        if (rs.next())
        {        
            return true;
        }
        else
            {
                return false;
            }
        }catch(SQLException sqle)
            {
                return false;
            }
        }
        
        // Method to add new records to the database
    /**
     * This method saves records into the database as received from the users
     * @return true if record saves successfully, and false otherwise. 
     */
    public boolean save()
    {
        PreparedStatement pst = null;
        Connection con = new ConnectingTo().connector();
        
        try{
               
            pst = con.prepareStatement("INSERT INTO student_details VALUES(?,?,?,?,?)");
                pst.setInt(1,getStudentID());
                pst.setString(2,getFname());
                pst.setString(3,getLname());
                pst.setInt(4,processGender(getGender()));
                pst.setString(5,getDegree());
                pst.executeUpdate();
                con.commit();
           
            }catch(SQLException sqle)
                {
                return false;
                }
            return true;
        }
    
    /**
     * this method saves student number and name into the database
     * @param name this is the student name
     * @param student_number this is the student number
     * @return true if saved successfully, false otherwise. 
     * @deprecated 
     */
    
    public boolean save(String name, int student_number){
    return true;
    }
    
    
    // Method to remove record from database 
    public boolean removeRecord(int reg_number)
        {
        
        PreparedStatement pst = null;
        Connection con = new ConnectingTo().connector();
        try{
               
            pst = con.prepareStatement("DELETE FROM student_details WHERE student_id = ?");
                pst.setInt(1,getStudentID());
                int xy = pst.executeUpdate();
                    if (xy == 1)
                    {
                        System.out.println("Student ID " + getStudentID() 
                                + " has been remoed successfully." );
                        con.commit();
                    }else{
                        System.out.println("Student ID " + getStudentID()
                                +" does not exist" );
                    }
            }catch(SQLException sqle)
                {
                    return false;
                }
        return true;
        }
    
    // This method updates the record in the database
    public boolean updateRecord()
        {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection con = new ConnectingTo().connector();
        
        // Check that the record exists
        try{
        
        pst = con.prepareStatement("SELECT * FROM student_details WHERE student_id = ?");
        pst.setInt(1,getStudentID());
        rs = pst.executeQuery();
        // If the record exists, ask user which parameter they wuld like to update
        
        if (rs.next())
        {                    
        System.out.println("Which field would you like to update:\n"
                        + "1. Student ID\n2. First name\n"
                        + "3. Last Name\n4. Gender\n5. Programme" );
        int param = 0;//DBConnector.scanner.nextInt();
        
        // Depending on their selection, use setter to set value, and update database
        switch (param)
                    {
                    case 1:
                        System.out.println("Enter new ID for the student:");
                        int i = DBConnector.scanner.nextInt();
                        pst = con.prepareStatement("UPDATE student_details SET "
                                + "student_id = ? WHERE student_id = " 
                                + getStudentID());
                        pst.setInt(1,i);
                        pst.executeUpdate();
                    break;
                    case 2 :
                        System.out.println("Enter new first name for the student:");
                        String name = DBConnector.scanner.next();
                        pst = con.prepareStatement("UPDATE student_details SET "
                                + "first_name = ? WHERE student_id = " 
                                + getStudentID());
                        pst.setString(1,name);
                        pst.executeUpdate();
                    break;
                    case 3 :
                        System.out.println("Enter new last name for the student:");
                        String lname = DBConnector.scanner.next();
                        pst = con.prepareStatement("UPDATE student_details SET "
                                + "last_name = ? where student_id = " 
                                + getStudentID());
                        pst.setString(1,lname);
                        pst.executeUpdate();
                    break;
                    case 4 :
                        System.out.println("Enter new gender for the student:");
                        String g = DBConnector.scanner.next();
                        pst = con.prepareStatement("UPDATE student_details SET "
                                + "gender = ? where student_id =  " 
                                + getStudentID());
                        pst.setInt(1,processGender(g));
                        pst.executeUpdate();
                    break;
                    case 5 :
                        System.out.println("Enter new degree programme for the student:");
                        String prog = DBConnector.scanner.next();
                        pst = con.prepareStatement("UPDATE student_details SET "
                                + "degree_programme = ? where student_id = "
                                + getStudentID());
                        pst.setString(1,prog);
                        pst.executeUpdate();
                    break;
                    default:
                        System.out.println("Invalid parameter selected");
                    }
        // Commit your changes, otherwise they will not be stored on the database
                    con.commit();
        }
        else{
            System.out.println("That StudentID does not exist on our system.");
        }
            }catch(SQLException sqle)
                {
                return false;
                }
            return true;
        }
    
    // Archiving a record. This method is used for delete and update operations
    // since we would still like to have the previous data stored for future reference. 
    
    
    public boolean archiveRecord(int reg_number) {
        ResultSet rs = null; 
        PreparedStatement pst = null;
        Connection con = new ConnectingTo().connector();
        
        try{
                //Get the record from the database;
                
                pst = con.prepareStatement("SELECT * FROM student_details "
                        + "WHERE student_id = ?");
                pst.setInt(1,getStudentID());
                rs = pst.executeQuery();
                
                // exctract the details to be filled in the old 
                // (archive table)
                while (rs.next())
                    {
                        setStudentID(rs.getInt(1));
                        setFname(rs.getString(2));
                        setLname(rs.getString(3));
                        setGender(processGender(rs.getInt(4)));
                        setDegree(rs.getString(5));
                    
                        pst = con.prepareStatement("INSERT INTO student_old "
                                + "(student_id, first_name, last_name, gender, "
                                + "degree_programme) VALUES (?,?,?,?,?)");    
                            pst.setInt(1,getStudentID());
                            pst.setString(2,getFname());
                            pst.setString(3,getLname());
                            pst.setInt(4,processGender(getGender()));
                            pst.setString(5,getDegree());
                            int rts = pst.executeUpdate();
                        
                        // Run as a transaction. Should the return status be 
                        //success, commit, else rollback.
                        if (rts == 1)
                            {                            
                                con.commit();
                            return true;
                            }else {
                                con.rollback();
                            return false;}
                            }
            }catch(SQLException sqle){
                System.out.print(sqle.getMessage());
                return false;
            }
        return true;
        }
}
