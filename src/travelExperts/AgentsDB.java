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

    private static void print(String text, Agent agent) {

        String name = "";
        if(agent.getAgtMiddleInitial() != "")
            name = agent.getAgtFirstName() + " " + agent.getAgtMiddleInitial() + " " + agent.getAgtLastName();
        else
            name = agent.getAgtFirstName() + " " + agent.getAgtLastName();

        System.out.println(text + "\n" +
                name + "\n" +
                "ID: " + agent.getAgentId() + " | AgencyID: " + agent.getAgencyId() + "\n" +
                "Phone: " + agent.getAgtBusPhone() + " | Email: " + agent.getAgtEmail() + "\n" +
                "Position: " + agent.getAgtPosition() + "\n\n");

    }

    public static boolean updateAgent(Agent oldAgent, Agent newAgent) {
        boolean isSuccess = true;

//        System.out.println("New Agent\n" + newAgent.getAgtFirstName() + " " + newAgent.getAgtLastName() + "\n" +
//                           "ID: " + newAgent.getAgentId() + " | AgencyID: " + newAgent.getAgencyId());
        print("Old Agent", oldAgent);
        print("New Agent", newAgent);
        try{
            // Load the driver
            Class.forName("com.mysql.jdbc.Driver");

            // Define your Connection object
            Connection conn = getConnection();

            String sql = "UPDATE `agents` " +
                         "SET `AgtFirstName` = ?, " +
                            "`AgtMiddleInitial` = ?, " +
                            "`AgtLastName` = ?, " +
                            "`AgtBusPhone` = ?, " +
                            "`AgtEmail` = ?, " +
                            "`AgtPosition` = ?, " +
                            "`AgencyId` = ? " +
                            "WHERE `AgentId` = ? " ;

//                               + "AND `AgtFirstName` = ? " +
//                                "AND `AgtMiddleInitial` = ? " +
//                                "AND `AgtLastName` = ? " +
//                                "AND `AgtBusPhone` = ? " +
//                                "AND `AgtEmail` = ? " +
//                                "AND `AgtPosition` = ? " +
//                                "AND `AgencyId` = ? ";

            // Create a Prepared Statement object
            PreparedStatement stmt = conn.prepareStatement(sql);

            if(newAgent.getAgtFirstName() == null)
                stmt.setNull(1, Types.VARCHAR);
            else
                stmt.setString(1, newAgent.getAgtFirstName());

            if(newAgent.getAgtMiddleInitial() == null) {
                stmt.setNull(2, Types.VARCHAR);
                System.out.println("Middle initial is null");
            }
            else
                stmt.setString(2, newAgent.getAgtMiddleInitial());

            if(newAgent.getAgtLastName() == null)
                stmt.setNull(3, Types.VARCHAR);
            else
                stmt.setString(3, newAgent.getAgtLastName());

            if(newAgent.getAgtBusPhone() == null)
                stmt.setNull(4, Types.VARCHAR);
            else
                stmt.setString(4, newAgent.getAgtBusPhone());

            if(newAgent.getAgtEmail() == null)
                stmt.setNull(5, Types.VARCHAR);
            else
                stmt.setString(5, newAgent.getAgtEmail());

            if(newAgent.getAgtPosition() == null)
                stmt.setNull(6, Types.VARCHAR);
            else
                stmt.setString(6, newAgent.getAgtPosition());

            if(newAgent.getAgencyId() == null)
                stmt.setNull(7, Types.INTEGER);
            else
                stmt.setInt(7, newAgent.getAgencyId());

            stmt.setInt(8, oldAgent.getAgentId());


//            if(oldAgent.getAgtFirstName() == null)
//                stmt.setNull(9, Types.VARCHAR);
//            else
//                stmt.setString(9, oldAgent.getAgtFirstName());
//
//            if(oldAgent.getAgtMiddleInitial() == null)
//                stmt.setNull(10, Types.VARCHAR);
//            else
//                stmt.setString(10, oldAgent.getAgtMiddleInitial());
//
//            if(oldAgent.getAgtLastName() == null)
//                stmt.setNull(11, Types.VARCHAR);
//            else
//                stmt.setString(11, oldAgent.getAgtLastName());
//
//            if(oldAgent.getAgtBusPhone() == null)
//                stmt.setNull(12, Types.VARCHAR);
//            else
//                stmt.setString(12, oldAgent.getAgtBusPhone());
//
//            if(oldAgent.getAgtEmail() == null)
//                stmt.setNull(13, Types.VARCHAR);
//            else
//                stmt.setString(13, oldAgent.getAgtEmail());
//
//            if(oldAgent.getAgtPosition() == null)
//                stmt.setNull(14, Types.VARCHAR);
//            else
//                stmt.setString(14, oldAgent.getAgtPosition());
//
//            if(oldAgent.getAgencyId() == null)
//                stmt.setNull(15, Types.INTEGER);
//            else
//                stmt.setInt(15, oldAgent.getAgencyId());

//            stmt.setString(9, oldAgent.getAgtFirstName());
//            stmt.setString(10, oldAgent.getAgtMiddleInitial());
//            stmt.setString(11, oldAgent.getAgtLastName());
//            stmt.setString(12, oldAgent.getAgtBusPhone());
//            stmt.setString(13, oldAgent.getAgtEmail());
//            stmt.setString(14, oldAgent.getAgtPosition());
//            stmt.setInt(15, oldAgent.getAgencyId());

            System.out.println("SQL: " + sql);
            int rows = stmt.executeUpdate();

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
