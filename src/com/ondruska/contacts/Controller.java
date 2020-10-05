package com.ondruska.contacts;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
        myTable.setItems(ContactData.getInstance().getContacts());
    }

    @FXML
    public void addContactHandle() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add new contact");
        dialog.setHeaderText("Use this windows to add new contact.");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("dialogWindow.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            DialogController dialogController = fxmlLoader.getController();
            dialogController.addContact();
        }

    }

    @FXML
    public void editContactHandle(ActionEvent actionEvent) {
    }

    @FXML
    public void deleteContactHandle(ActionEvent actionEvent) {
    }

    @FXML
    public void exitHandle() {
        Platform.exit();
    }
}