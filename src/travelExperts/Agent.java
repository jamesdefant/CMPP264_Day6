package travelExperts;

/**
 * Entity class to hold data for 1 row in travelexperts.agents table
 * Course: CMPP-264 - Java
 * Assignment: Day 6 Exercise
 * Author: James Defant
 * Date: Sep 10 2019
 */
public class Agent {

    // Member variables
    private int agentId;
    private String agtFirstName;        // nullable
    private String agtMiddleInitial;    // nullable
    private String agtLastName;         // nullable
    private String agtBusPhone;         // nullable
    private String agtEmail;            // nullable
    private String agtPosition;         // nullable
    private Integer agencyId;           // nullable

    // Constructor
    public Agent(int agentId,
                 String agtFirstName,
                 String agtMiddleInitial,
                 String agtLastName,
                 String agtBusPhone,
                 String agtEmail,
                 String agtPosition,
                 Integer agencyId) {

        this.agentId = agentId;
        this.agtFirstName = agtFirstName;
        this.agtMiddleInitial = agtMiddleInitial;
        this.agtLastName = agtLastName;
        this.agtBusPhone = agtBusPhone;
        this.agtEmail = agtEmail;
        this.agtPosition = agtPosition;
        this.agencyId = agencyId;
    }

    // Properties
    public int getAgentId() {
        return agentId;
    }
    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getAgtFirstName() {
        return agtFirstName;
    }
    public void setAgtFirstName(String agtFirstName) {
        this.agtFirstName = agtFirstName;
    }

    public String getAgtMiddleInitial() {
        return agtMiddleInitial;
    }
    public void setAgtMiddleInitial(String agtMiddleInitial) {
        this.agtMiddleInitial = agtMiddleInitial;
    }

    public String getAgtLastName() {
        return agtLastName;
    }
    public void setAgtLastName(String agtLastName) {
        this.agtLastName = agtLastName;
    }

    public String getAgtBusPhone() {
        return agtBusPhone;
    }
    public void setAgtBusPhone(String agtBusPhone) {
        this.agtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return agtEmail;
    }
    public void setAgtEmail(String agtEmail) {
        this.agtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return agtPosition;
    }
    public void setAgtPosition(String agtPosition) {
        this.agtPosition = agtPosition;
    }

    public Integer getAgencyId() {
        return agencyId;
    }
    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    // Methods
    @Override
    public String toString() {
        return  String.valueOf(agentId) +
                " - " + agtLastName + ", " + agtFirstName;
    }
}
