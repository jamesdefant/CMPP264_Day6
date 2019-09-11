package sample;

import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgentsDB {

    private static String connectionString = "jdbc:mysql://localhost:8080/travelexperts";

    public static ArrayList<Agent> getAgents() {
        ArrayList<Agent> returnValue = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection(connectionString);

        } catch (ClassNotFoundException | SQLException e) {

        }

        return returnValue;
    }
}
