/**
 * Developed by Artur Abratanski
 * based on course: https://www.udemy.com/course/java-the-complete-java-developer-course/
 * ended with certificate: https://www.udemy.com/certificate/UC-0ec63629-2fb7-4a2f-96d5-d58a3eb79e1a/
 */

/**
 * This is a simple class for data conversion.
 * It contains all fields used in application, it exchange data with "database" in XML file.
 */

package com.arturabratanski.contact.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Contact {

    // creates all fields to store contact properties, we use SimpleStringProperty because of TableView preferences
    // it enables DataBinding
    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty notes;

    // constructor with every field declared as parameter
    public Contact(SimpleStringProperty name, SimpleStringProperty surname, SimpleStringProperty phoneNumber, SimpleStringProperty notes) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public Contact() { // empty constructor for ContactData class
        this(
                new SimpleStringProperty("default_name"),
                new SimpleStringProperty("default_surname"),
                new SimpleStringProperty("default_phone_number"),
                new SimpleStringProperty("default_notes"));
    }

    // getters and setters for all
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getNotes() {
        return notes.get();
    }

    public SimpleStringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }
}
