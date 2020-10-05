package com.ondruska.contacts;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class DialogController {
    @FXML
    private TextField firstNameDialog;
    @FXML
    private TextField lastNameDialog;
    @FXML
    private TextField phoneNumberDialog;
    @FXML
    private TextField notesDialog;

    //used to add contacts using textFields in our dialogWindow. If any of the fields is not filled
    //then we will not create new contact.
    public void addContact() {
        String firstName = firstNameDialog.getText().trim();
        String lastName = lastNameDialog.getText().trim();
        String phoneNumber = phoneNumberDialog.getText().trim();
        String notes = notesDialog.getText().trim();

        if((firstName.isEmpty()) || (lastName.isEmpty()) || (phoneNumber.isEmpty()) || (notes.isEmpty())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing information");
            alert.setHeaderText(null);
            alert.setContentText("Contact was not added. You have to fill all text fields.");
            alert.show();
        } else {
            Contact newContact = new Contact(firstName, lastName, phoneNumber, notes);
            ContactData.getInstance().addContact(newContact);
        }
    }

    //used to fill textFields in edit dialog based on inputted contact
    public void fillEditForm(Contact contact) {
        firstNameDialog.setText(contact.getFirstName());
        lastNameDialog.setText(contact.getLastName());
        phoneNumberDialog.setText(contact.getPhoneNumber());
        notesDialog.setText(contact.getNotes());
    }

    //update oldContact with info from dialog if all the fields are filled
    public void updateContact(Contact oldContact) {
        String firstName = firstNameDialog.getText().trim();
        String lastName = lastNameDialog.getText().trim();
        String phoneNumber = phoneNumberDialog.getText().trim();
        String notes = notesDialog.getText().trim();

        if((firstName.isEmpty()) || (lastName.isEmpty()) || (phoneNumber.isEmpty()) || (notes.isEmpty())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing information");
            alert.setHeaderText(null);
            alert.setContentText("Contact was not updated. You have to fill all text fields.");
            alert.show();
        } else {
            Contact newContact = new Contact(firstName, lastName, phoneNumber, notes);
            ContactData.getInstance().updateContact(oldContact, newContact);
        }
    }
}
