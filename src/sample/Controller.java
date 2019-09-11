package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField uxId;

    @FXML
    private TextField uxFirstName;

    @FXML
    private TextField uxInitial;

    @FXML
    private TextField uxLastName;

    @FXML
    private TextField uxBusPhone;

    @FXML
    private TextField uxEmail;

    @FXML
    private TextField uxPosition;

    @FXML
    private TextField uxAgencyID;

    @FXML
    private ComboBox<Agent> uxAgents;

    @FXML
    private Button uxEdit;

    @FXML
    private Button uxSave;

    @FXML
    void onAction_uxAgents(ActionEvent event) {

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

    }
}
