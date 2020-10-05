package com.ondruska.contacts;

import javafx.fxml.FXML;
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

    public void addContact() {
        String firstName = firstNameDialog.getText().trim();
        String lastName = lastNameDialog.getText().trim();
        String phoneNumber = phoneNumberDialog.getText().trim();
        String notes = notesDialog.getText().trim();

        Contact newContact = new Contact(firstName, lastName, phoneNumber, notes);
        ContactData.getInstance().addContact(newContact);
    }

    public void fillEditForm(Contact contact) {
        firstNameDialog.setText(contact.getFirstName());
        lastNameDialog.setText(contact.getLastName());
        phoneNumberDialog.setText(contact.getPhoneNumber());
        notesDialog.setText(contact.getNotes());
    }

    public void updateContact(Contact oldContact) {
        String firstName = firstNameDialog.getText().trim();
        String lastName = lastNameDialog.getText().trim();
        String phoneNumber = phoneNumberDialog.getText().trim();
        String notes = notesDialog.getText().trim();

        Contact newContact = new Contact(firstName, lastName, phoneNumber, notes);
        ContactData.getInstance().updateContact(oldContact, newContact);
    }
}
