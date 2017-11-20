
import java.sql.Connection;
import java.sql.DriverManager;
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
public class ConnectingTo {
    private Connection con = null;

    // Connection method, used throughout the project by different classes. 
    // this method returns a connection for use 
    
    public Connection connector(){
                
                try{
                Class.forName("com.mysql.jdbc.Driver");   
                }catch(ClassNotFoundException e){
                System.out.println("Where is MySQL driver?");
                e.printStackTrace();
                return con;
                }
            
                try{
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306"
                        + "/ics2101?autoReconnect=true&useSSL=false","root", "%B@d!k3nji1%");
                con.setAutoCommit(false);
                }catch(SQLException e) {
                System.out.println("Connection Failed! Check output console");
                e.printStackTrace();
                return con;
                }
                            if (con != null) {
                            //System.out.println("You made it, take control your database now!");
                            } else {
                            System.out.println("Failed to make connection!");
                            }
                            return con;
    }
    
    // This method closes the DB connection
    public void closeDB(){
               try{
                   con.close();
               }catch(SQLException sql){
               System.out.println("Error: " + sql.getMessage());
               }
    }
}
