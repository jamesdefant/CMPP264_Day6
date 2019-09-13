package travelExperts;

/**
 * Utility class that holds static methods
 * Course: CMPP-264 - Java
 * Assignment: Day 6 Exercise
 * Author: James Defant
 * Date: Sep 12 2019
 */
public class Util {

    /**
     * Method to check whether or not string is an integer
     * @param strNum
     * @return Is it an integer?
     */
    public static boolean isNumeric(String strNum) {
        try {
            Integer i = Integer.parseInt(strNum);
        }
        catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    /**
     * Method that prints Agent data to the console (for debugging)
     * @param text
     * @param agent
     */
    public static void print(String text, Agent agent) {

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
}
