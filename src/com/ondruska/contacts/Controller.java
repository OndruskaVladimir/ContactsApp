package com.ondruska.contacts;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {
    @FXML
    private TableView<Contact> myTable;
    @FXML
    private BorderPane mainBorderPane;

    public void initialize() {
        //everytime, there is a change in your table, it will change in GUI
        myTable.setItems(ContactData.getInstance().getContacts());
    }

    //Method used to add contact to our table
    @FXML
    public void addContactHandle() {
        //create and setup new dialog windows
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add new contact");
        dialog.setHeaderText("Use this windows to add new contact.");

        //load fxml file for our dialog(dialogWindow.fxml)
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("dialogWindow.fxml"));
        try{
            //set content of our dialog using fxml file we made for dialogs
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        //add buttontypes to our dialog
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        //open dialog and wait for button to be clicked.
        Optional<ButtonType> result = dialog.showAndWait();
        //is there is result and it was button OK then we create instance of dialog controller and addContact to our list
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController dialogController = fxmlLoader.getController();
            dialogController.addContact();
        }

    }

    //used to edit contact, works similiar to our addContactHandle but will actually edit selected contact instead of adding new
    @FXML
    public void editContactHandle(ActionEvent actionEvent) {
        Contact selectedContact = myTable.getSelectionModel().getSelectedItem();
        if(selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No contact selected");
            alert.setHeaderText("You did not choose any contact to update");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Edit contact");
        dialog.setHeaderText("Use this windows to edit contact.");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("dialogWindow.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        DialogController dialogController = fxmlLoader.getController();
        dialogController.fillEditForm(selectedContact);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            dialogController.updateContact(selectedContact);
        }

    }

    //used to delete selected contact
    @FXML
    public void deleteContactHandle(ActionEvent actionEvent) {
        //if we didnt select any contact to be deleted, the alert will pop up saying you should choose contact
        Contact selectedContact = myTable.getSelectionModel().getSelectedItem();
        if(selectedContact == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No contact selected");
            alert.setHeaderText(null);
            alert.setContentText("You did not choose any contact to delete");
            alert.showAndWait();
            return;
        }

        //if contact was chosen, we will delete it after user click on the OK button
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete contact?");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete contact " + selectedContact.getFirstName() + " " + selectedContact.getLastName());
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            ContactData.getInstance().deleteContact(selectedContact);
        }
    }

    //used to exit program safely
    @FXML
    public void exitHandle() {
        Platform.exit();
    }
}
