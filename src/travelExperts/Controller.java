package travelExperts;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    private ArrayList<TextField> textFields;

    // Event handlers
    @FXML
    void onAction_uxEdit(ActionEvent event) {
        System.out.println("Edit button clicked");

        toggleTextFields(true);
    }

    @FXML
    void onAction_uxSave(ActionEvent event) {
        System.out.println("Save button clicked");

        toggleTextFields(false);

        try {
            int agentId = Integer.parseInt(uxId.getText());
            int agencyId = Integer.parseInt(uxAgencyID.getText());

            Agent alteredAgent = new Agent(agentId,
                    uxFirstName.getText(),
                    uxInitial.getText(),
                    uxLastName.getText(),
                    uxBusPhone.getText(),
                    uxEmail.getText(),
                    uxPosition.getText(),
                    agencyId);
            if(!AgentsDB.updateAgents(alteredAgent)) {

                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "There was an error updating the table", ButtonType.OK);
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Update successful", ButtonType.OK);
                alert.show();
            }
        }
        catch(Exception e) {

        }
    }

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
                selectedAgent = newValue;
                updateDisplay(newValue);
            }
        });

        // Add items to comboBox
        ObservableList<Agent> list = FXCollections.observableList(AgentsDB.getAgentList());
        uxAgents.setItems(list);
        uxAgents.getSelectionModel().select(0);

        initTextFields();
        // Disable all textFields
        toggleTextFields(false);
    }

    /**
     * Refresh the display with currently selected Agent
     * @param selectedAgent
     */
    private void updateDisplay(Agent selectedAgent) {
        if (selectedAgent != null) {
            uxId.setText(String.valueOf(selectedAgent.getAgentId()));
            uxFirstName.setText(selectedAgent.getAgtFirstName());
            uxInitial.setText(selectedAgent.getAgtMiddleInitial());
            uxLastName.setText(selectedAgent.getAgtLastName());
            uxBusPhone.setText(selectedAgent.getAgtBusPhone());
            uxEmail.setText(selectedAgent.getAgtEmail());
            uxPosition.setText(selectedAgent.getAgtPosition());
            uxAgencyID.setText(String.valueOf(selectedAgent.getAgencyId()));
        }
    }

    /**
     * Toggle the texFields "editable" properties
     */
    private void toggleTextFields(boolean isEnable) {
        for (TextField t : textFields ) {
            t.setEditable(isEnable);
        }
    }

    private void initTextFields() {
        textFields = new ArrayList<>();
//        textFields.add(uxId);
        textFields.add(uxFirstName);
        textFields.add(uxInitial);
        textFields.add(uxLastName);
        textFields.add(uxBusPhone);
        textFields.add(uxEmail);
        textFields.add(uxPosition);
        textFields.add(uxAgencyID);
    }

}
