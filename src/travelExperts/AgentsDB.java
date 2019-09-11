package travelExperts;

import java.sql.*;
import java.util.ArrayList;

/**
 * Database access class to manipulate travelexperts.agents table
 * Author: James Defant
 * Date: Sep 10 2019
 */
public class AgentsDB {

    private static final String CONN_STRING = "jdbc:mysql://localhost/travelexperts";

    public static ArrayList<Agent> getAgentList() {
        ArrayList<Agent> returnValue = new ArrayList<>();

        try {
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Define your Connection object
            Connection conn = DriverManager.getConnection(CONN_STRING, "admin", "password");

            // Create a Statement object
            Statement stmt = conn.createStatement();

            // Execute the statement
            ResultSet rs = stmt.executeQuery("SELECT * FROM agents");

            // Create a Metadata object from the ResultSet object
            ResultSetMetaData metaData = rs.getMetaData();

            // Process the data
            while (rs.next()) {

                returnValue.add(new Agent(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getInt(8) ));
            }

            // Close the connection
            conn.close();
        }
        catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    public static boolean updateAgents(int id) {
        boolean isSuccess = false;

        try{
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Define your Connection object
            Connection conn = DriverManager.getConnection(CONN_STRING, "admin", "password");

            // Create a Statement object
            Statement stmt = conn.createStatement();

            // Execute the statement
//            isSuccess = stmt.execute("UPDATE ")

            // Close the connection
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}
