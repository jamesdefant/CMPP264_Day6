package travelExperts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Window;

import java.sql.*;
import java.util.ArrayList;

/**
 * Database access class to manipulate travelexperts.agents table
 * Author: James Defant
 * Date: Sep 10 2019
 */
public class AgentsDB {

    private static final String CONN_STRING = "jdbc:mysql://localhost/travelexperts";

    private static Connection getConnection() {
        Connection conn = null;

        try {
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Define your Connection object
            conn = DriverManager.getConnection(CONN_STRING, "admin", "password");
        }
         catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
        return conn;
    }

    public static ArrayList<Agent> getAgentList() {
        ArrayList<Agent> returnValue = new ArrayList<>();
        Connection conn = null;

        try {
            // Define your Connection object
            conn = getConnection();
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
        catch(SQLException e) {
            e.printStackTrace();
        }


        return returnValue;
    }

    public static boolean updateAgents(Agent newAgent) {
        boolean isSuccess = true;

        System.out.println(newAgent.getAgtFirstName() + " " + newAgent.getAgtLastName() + "\n" +
                           "ID: " + newAgent.getAgentId() + " | AgencyID: " + newAgent.getAgencyId());

        try{
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Define your Connection object
            Connection conn = DriverManager.getConnection(CONN_STRING, "admin", "password");

            String sql = "UPDATE `agents` " +
                         "SET `AgtFirstName`=? " +
                            "WHERE `AgentId`=?";

            // Create a Prepared Statement object
            PreparedStatement prerpStmt = conn.prepareStatement(sql);
            prerpStmt.setString(1, newAgent.getAgtFirstName());
            prerpStmt.setInt(2, newAgent.getAgentId());

            System.out.println("SQL: " + sql);
            int rows = prerpStmt.executeUpdate();

            if(rows == 0) {
                isSuccess = false;
            }

            // Close the connection
            conn.close();
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
}
