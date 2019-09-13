package travelExperts;

import java.sql.*;
import java.util.ArrayList;

/**
 * Database access class to manipulate travelexperts.agents table
 * Course: CMPP-264 - Java
 * Assignment: Day 6 Exercise
 * Author: James Defant
 * Date: Sep 10 2019
 */
public class AgentsDB {

    private static final String CONN_STRING = "jdbc:mysql://localhost/travelexperts";

    /**
     * Method to retrieve a Connection object
     * @return
     */
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

    /**
     * Method to retrieve all the Agents from the table
     * @return
     */
    public static ArrayList<Agent> getAgentList() {

        ArrayList<Agent> returnValue = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Define your Connection object
            conn = getConnection();

            // Build the query
            String sql = "SELECT * FROM agents";

            // Prepare the statement
            stmt = conn.prepareStatement(sql);

            // Execute the statement
            rs = stmt.executeQuery(sql);

            // Create a Metadata object from the ResultSet object
            ResultSetMetaData metaData = rs.getMetaData();

            // Process the data
            while (rs.next()) {
                int id = rs.getInt(1);

                String firstName = rs.getString(2); // nullable
                if(rs.wasNull())
                    firstName = null;

                String initial = rs.getString(3);   // nullable
                if(rs.wasNull())
                    initial = null;

                String lastName = rs.getString(4);  // nullable
                if(rs.wasNull())
                    lastName = null;

                String phone = rs.getString(5);     // nullable
                if(rs.wasNull())
                    phone = null;

                String email = rs.getString(6);     // nullable
                if(rs.wasNull())
                    email = null;

                String position = rs.getString(7);  // nullable
                if(rs.wasNull())
                    position = null;

                Integer agencyId = rs.getInt(8);    // nullable
                if(rs.wasNull())
                    agencyId = null;

                // Add the Agent to the list
                returnValue.add(new Agent(id, firstName, initial, lastName, phone, email, position, agencyId));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close the objects
            try { if(rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if(stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return returnValue;
    }


    /**
     * Method to update a single Agent in the agent table
     * @param oldAgent
     * @param newAgent
     * @return
     */
    public static boolean updateAgent(Agent oldAgent, Agent newAgent) {

        boolean isSuccess = true;

        Connection conn = null;
        PreparedStatement stmt = null;

        // Print agent data (for debugging)
        Util.print("Old Agent", oldAgent);
        Util.print("New Agent", newAgent);

        try{
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Define your Connection object
            conn = getConnection();

            String sql = "UPDATE `agents` " +
                         "SET `AgtFirstName` = ?, " +
                            "`AgtMiddleInitial` = ?, " +
                            "`AgtLastName` = ?, " +
                            "`AgtBusPhone` = ?, " +
                            "`AgtEmail` = ?, " +
                            "`AgtPosition` = ?, " +
                            "`AgencyId` = ? " +
                            "WHERE `AgentId` = ? " +
                    "AND (`AgtFirstName` = ? OR " +
                         "`AgtFirstName` IS NULL AND ? IS NULL)" +
                    "AND (`AgtMiddleInitial` = ? OR " +
                         "`AgtMiddleInitial` IS NULL AND ? IS NULL)" +
                    "AND (`AgtLastName` = ? OR " +
                         "`AgtLastName` IS NULL AND ? IS NULL)" +
                    "AND (`AgtBusPhone` = ? OR " +
                         "`AgtBusPhone` IS NULL AND ? IS NULL)" +
                    "AND (`AgtEmail` = ? OR " +
                         "`AgtEmail` IS NULL AND ? IS NULL)" +
                    "AND (`AgtPosition` = ? OR " +
                         "`AgtPosition` IS NULL AND ? IS NULL)" +
                    "AND (`AgencyId` = ? OR " +
                         "`AgencyId` IS NULL AND ? IS NULL)";



            // Create a Prepared Statement object
            stmt = conn.prepareStatement(sql);

            // ------------------------------------------------------------------
            // ASSIGNMENT OF VALUES

            if(newAgent.getAgtFirstName() == null || newAgent.getAgtFirstName().isEmpty())
                stmt.setNull(1, Types.VARCHAR);
            else
                stmt.setString(1, newAgent.getAgtFirstName());

            if(newAgent.getAgtMiddleInitial() == null || newAgent.getAgtMiddleInitial().isEmpty()) {
                stmt.setNull(2, Types.VARCHAR);
                System.out.println("Middle initial is null");
            }
            else
                stmt.setString(2, newAgent.getAgtMiddleInitial());

            if(newAgent.getAgtLastName() == null || newAgent.getAgtLastName().isEmpty())
                stmt.setNull(3, Types.VARCHAR);
            else
                stmt.setString(3, newAgent.getAgtLastName());

            if(newAgent.getAgtBusPhone() == null || newAgent.getAgtBusPhone().isEmpty())
                stmt.setNull(4, Types.VARCHAR);
            else
                stmt.setString(4, newAgent.getAgtBusPhone());

            if(newAgent.getAgtEmail() == null || newAgent.getAgtEmail().isEmpty())
                stmt.setNull(5, Types.VARCHAR);
            else
                stmt.setString(5, newAgent.getAgtEmail());

            if(newAgent.getAgtPosition() == null || newAgent.getAgtPosition().isEmpty())
                stmt.setNull(6, Types.VARCHAR);
            else
                stmt.setString(6, newAgent.getAgtPosition());

            if(newAgent.getAgencyId() == null || newAgent.getAgencyId() == 0)
                stmt.setNull(7, Types.INTEGER);
            else
                stmt.setInt(7, newAgent.getAgencyId());

            // ------------------------------------------------------------------
            // DEFINE WHICH ROW

            stmt.setInt(8, oldAgent.getAgentId());

            // ------------------------------------------------------------------
            // MAINTAIN OPTIMISTIC CONCURRENCY

            stmt.setString(9, oldAgent.getAgtFirstName());
            stmt.setString(10, oldAgent.getAgtFirstName());

            stmt.setString(11, oldAgent.getAgtMiddleInitial());
            stmt.setString(12, oldAgent.getAgtMiddleInitial());

            stmt.setString(13, oldAgent.getAgtLastName());
            stmt.setString(14, oldAgent.getAgtLastName());

            stmt.setString(15, oldAgent.getAgtBusPhone());
            stmt.setString(16, oldAgent.getAgtBusPhone());

            stmt.setString(17, oldAgent.getAgtEmail());
            stmt.setString(18, oldAgent.getAgtEmail());

            stmt.setString(19, oldAgent.getAgtPosition());
            stmt.setString(20, oldAgent.getAgtPosition());

            if(oldAgent.getAgencyId() == null || oldAgent.getAgencyId() == 0)
                stmt.setNull(21, Types.INTEGER);
            else
                stmt.setInt(21, oldAgent.getAgencyId());
            if(oldAgent.getAgencyId() == null || oldAgent.getAgencyId() == 0)
                stmt.setNull(22, Types.INTEGER);
            else
                stmt.setInt(22, oldAgent.getAgencyId());


            // Execute the statement
            int rows = stmt.executeUpdate();

            // Check if it was successful
            if(rows == 0) {
                isSuccess = false;
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            // Close the objects
            try { if(stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if(conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return isSuccess;
    }
}
