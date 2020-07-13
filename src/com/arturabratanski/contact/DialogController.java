/**
 * Developed by Artur Abratanski
 * based on course: https://www.udemy.com/course/java-the-complete-java-developer-course/
 * ended with certificate: https://www.udemy.com/certificate/UC-0ec63629-2fb7-4a2f-96d5-d58a3eb79e1a/
 */

/**
 * This is a controller for "create new contact" dialog.
 * It is mainly responsible for converting inputted data to SimpleStringProperty, via standard String.
 */

package com.arturabratanski.contact;

import com.arturabratanski.contact.datamodel.Contact;
import com.arturabratanski.contact.datamodel.ContactData;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DialogController {

    // declaring fields to take in data from dialog window
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextArea notes;

    public Contact processResult() { // handles dialog windows input and convert it into filled Contact instance
        // downloading strings from fxml fields
        String stringName = this.name.getText().trim();
        String stringSurname = this.surname.getText().trim();
        String stringPhoneNumber = this.phoneNumber.getText().trim();
        String stringNotes = this.notes.getText().trim();


        // instantiate a SimpleStringProperty from string
        SimpleStringProperty name  = new SimpleStringProperty(stringName);
        SimpleStringProperty surname = new SimpleStringProperty(stringSurname);
        SimpleStringProperty phoneNumber = new SimpleStringProperty(stringPhoneNumber);
        SimpleStringProperty notes = new SimpleStringProperty(stringNotes);

        Contact newContact = new Contact(name, surname, phoneNumber, notes); // creates new instance of Contact filled with information gathered by dialog window
        ContactData.getInstance().addContact(newContact);
        return newContact;
    }
}
