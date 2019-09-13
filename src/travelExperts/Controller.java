package travelExperts;

import java.util.ArrayList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Controller class contains logic for application
 * Course: CMPP-264 - Java
 * Assignment: Day 6 Exercise
 * Author: James Defant
 * Date: Sep 12 2019
 */
public class Controller {

    @FXML
    private TextField uxId,
            uxFirstName,
            uxInitial,
            uxLastName,
            uxBusPhone,
            uxEmail,
            uxPosition,
            uxAgencyID;

    @FXML
    private Button uxEdit, uxSave;

    @FXML
    private ComboBox<Agent> uxAgents;

    private Agent selectedAgent;
    private int selectedIndex;
    private ArrayList<TextField> textFields;

    // -----------------------------------------------------------------------\
    // Event handlers

    // Edit button
    @FXML
    void onAction_uxEdit(ActionEvent event) {
        System.out.println("Edit button clicked");

        toggleEditable(true);
    }

    // Save button
    @FXML
    void onAction_uxSave(ActionEvent event) {
        System.out.println("Save button clicked");

        toggleEditable(false);

        try {
            int agentId = Integer.parseInt(uxId.getText());

            String firstName = getTextFieldValue(uxFirstName);
            String initial = getTextFieldValue(uxInitial);
            String lastName = getTextFieldValue(uxLastName);
            String phone = getTextFieldValue(uxBusPhone);
            String email = getTextFieldValue(uxEmail);
            String position = getTextFieldValue(uxPosition);

            Integer agencyId = null;

            // Check if the AgencyID textfield is non-numeric, numeric, or empty
            if(uxAgencyID.getText().isEmpty()) {
                agencyId = null;
            }
            else if(!Util.isNumeric(uxAgencyID.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Agency ID must be a whole number", ButtonType.OK);
                alert.show();
                uxAgencyID.requestFocus();
                return;
            }
            else if(Util.isNumeric(uxAgencyID.getText())) {
                agencyId = Integer.parseInt(uxAgencyID.getText());
            }

            Agent alteredAgent = new Agent(agentId,
                    firstName,
                    initial,
                    lastName,
                    phone,
                    email,
                    position,
                    agencyId);

            // Submit the query and display a dialog (success/failure)
            if(!AgentsDB.updateAgent(selectedAgent, alteredAgent)) {

                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "There was an error updating the table", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Update successful", ButtonType.OK);
                alert.show();
                selectedAgent = alteredAgent;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        loadData();
    }

    // Called at start
    @FXML
    void initialize() {
        assert uxId != null : "fx:id=\"uxId\" was not injected: check your FXML file 'sample.fxml'.";
        assert uxFirstName != null : "fx:id=\"uxFirstName\" was not injected: check your FXML file 'sample.fxml'.";
        assert uxInitial != null : "fx:id=\"uxInitial\" was not injected: check your FXML file 'sample.fxml'.";
        assert uxLastName != null : "fx:id=\"uxLastName\" was not injected: check your FXML file 'sample.fxml'.";
        assert uxBusPhone != null : "fx:id=\"uxBusPhone\" was not injected: check your FXML file 'sample.fxml'.";
        assert uxEmail != null : "fx:id=\"uxEmail\" was not injected: check your FXML file 'sample.fxml'.";
        assert uxPosition != null : "fx:id=\"uxPosition\" was not injected: check your FXML file 'sample.fxml'.";
        assert uxAgencyID != null : "fx:id=\"uxAgencyID\" was not injected: check your FXML file 'sample.fxml'.";
        assert uxAgents != null : "fx:id=\"uxAgents\" was not injected: check your FXML file 'sample.fxml'.";
        assert uxEdit != null : "fx:id=\"uxEdit\" was not injected: check your FXML file 'sample.fxml'.";
        assert uxSave != null : "fx:id=\"uxSave\" was not injected: check your FXML file 'sample.fxml'.";

        // Add a event listener to the selected item property
        uxAgents.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Agent>() {
            @Override
            public void changed(ObservableValue<? extends Agent> observable, Agent oldValue, Agent newValue) {
                if(newValue != null) {
                    selectedAgent = newValue;
                    selectedIndex = uxAgents.getSelectionModel().getSelectedIndex();
                    updateDisplay();
                }
            }
        });

        loadData();
        uxAgents.getSelectionModel().select(0);

        initControls();
    }

    // -----------------------------------------------------------------------
    // Initialization methods

    /**
     * Load the data from the database
     */
    private void loadData() {
        // Add items to comboBox
        ObservableList<Agent> list = FXCollections.observableList(AgentsDB.getAgentList());
        uxAgents.setItems(list);

        updateDisplay();
    }

    /**
     * Add all the TextFields to a list so that we may alter them all at once
     */
    private void initControls() {
        textFields = new ArrayList<>();
        textFields.add(uxFirstName);
        textFields.add(uxInitial);
        textFields.add(uxLastName);
        textFields.add(uxBusPhone);
        textFields.add(uxEmail);
        textFields.add(uxPosition);
        textFields.add(uxAgencyID);

        // Disable all textFields
        toggleEditable(false);
    }

    // -----------------------------------------------------------------------
    // Utility methods

    /**
     * Refresh the display with currently selected Agent
     */
    private void updateDisplay() {

        if (selectedAgent != null) {
            uxId.setText(String.valueOf(selectedAgent.getAgentId()));

            setTextField(uxFirstName, selectedAgent.getAgtFirstName());
            setTextField(uxInitial, selectedAgent.getAgtMiddleInitial());
            setTextField(uxLastName, selectedAgent.getAgtLastName());
            setTextField(uxBusPhone, selectedAgent.getAgtBusPhone());
            setTextField(uxEmail, selectedAgent.getAgtEmail());
            setTextField(uxPosition, selectedAgent.getAgtPosition());
            setTextField(uxAgencyID, selectedAgent.getAgencyId());

            // Select the previously selected item
            uxAgents.getSelectionModel().select(selectedIndex);
        }
    }

    /**
     * Get the value of a TextField - null if it is empty
     * @param tf
     * @return String or null
     */
    private String getTextFieldValue(TextField tf) {
        if(tf.getText().isEmpty())
            return null;
        return tf.getText();
    }

    /**
     * Set the value of a TextField - "" if it is null
     * @param tf
     * @param o
     */
    private void setTextField(TextField tf, Object o) {
        if(o != null) {
            if (o instanceof String) { tf.setText((String) o); }
            else if (o instanceof Integer || o == (Integer) o) { tf.setText(String.valueOf(o)); }
        }
        else {
            tf.setText("");
        }
    }

    /**
     * Toggle the texFields "editable" properties
     */
    private void toggleEditable(boolean isEnable) {
        for (TextField t : textFields ) {
            t.setEditable(isEnable);
        }

        // Set button state
        uxSave.setDisable(!isEnable);
        uxEdit.setDisable(isEnable);
    }
}
