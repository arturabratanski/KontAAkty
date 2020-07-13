/**
 * Developed by Artur Abratanski
 * based on course: https://www.udemy.com/course/java-the-complete-java-developer-course/
 * ended with certificate: https://www.udemy.com/certificate/UC-0ec63629-2fb7-4a2f-96d5-d58a3eb79e1a/
 */

/**
 * This is a main controller class.
 * It controls exchange of data, table population and all actions redirected from buttons.
 */

package com.arturabratanski.contact;

import com.arturabratanski.contact.datamodel.Contact;
import com.arturabratanski.contact.datamodel.ContactData;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    // defining all columns and binding them with annotations
    @FXML
    private TableView<Contact> contactTableView;
    @FXML
    private TableColumn<Object, String> nameColumn;
    @FXML
    private TableColumn<Object, String> surnameColumn;
    @FXML
    private TableColumn<Object, String> phoneNumberColumn;
    @FXML
    private TableColumn<Object, String> notesColumn;
    @FXML
    private BorderPane mainBorderPane; // declaring BorderPane in order to assign dialog to it

    public void initialize() { // standard function, app runs it when launched

        contactTableView.setItems(ContactData.getInstance().getContacts()); // data binding, a lot of processes are running backstage
        contactTableView.setEditable(true);

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("name")); // setting up cell factory to populate cells with data
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // populating other cells
        surnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("surname"));
        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        phoneNumberColumn.setCellValueFactory(
                new PropertyValueFactory<>("phoneNumber"));
        phoneNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        notesColumn.setCellValueFactory(
                new PropertyValueFactory<>("notes"));
        notesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    @FXML
    public void showNewContactDialog() { // connects dialog with main window

        // we declare and fill dialog window below
        Dialog<ButtonType> dialog = new Dialog<>(); // declaring new dialog
        dialog.initOwner(mainBorderPane.getScene().getWindow()); // assign owner
        dialog.setTitle("Dodaj nowy kontakt"); // set title of dialog (in title bar)
        dialog.setHeaderText("Wypełnij wszystkie poniższe pola aby dodać nowy kontakt"); // set header text of dialog, bigger than <headertext>

        // we connect fxml file to declared dialog in few steps below
        FXMLLoader fxmlLoader = new FXMLLoader(); // built-in loader of fxml files
        fxmlLoader.setLocation(getClass().getResource("contactdialog.fxml")); // show URL of fxml file to loader
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load()); // set loaded fxml file to root of dialog window
        } catch(IOException e) { // error of not finding fxml
            System.out.println("Couldn't load the dialog"); // message to help finding the location of problem, this case is fxml input
            e.printStackTrace(); // print error message to console
            return;
        }

        // when we add OK and Cancel buttons to DialogPane in SceneBuilder they have no action assigned to them, including X sign in right top corner
        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK); // adding OK button to dialog window
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL); // adding Cancel button to dialog window, this way we don't have to create handlers for this actions

        Optional<ButtonType> result = dialog.showAndWait(); // starts up dialog window which now waits for action

        if(result.isPresent() && (result.get() == ButtonType.OK)) { // if for case when all text fields are filled and OK button is pressed
            DialogController controller = fxmlLoader.getController(); // creating an instance(?) of controller, to later use processing of that controller
            Contact newContact = controller.processResult(); // creating newContact by processResult, populating it with data input
            contactTableView.getSelectionModel().select(newContact); // setting selection to just inserted item in TableView
        }
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) { // this method will handle action in which user press key while some contact is selected in TableView, this case checks for delete key
        Contact selectedContact = contactTableView.getSelectionModel().getSelectedItem(); // grabs an item from TableView
        if(selectedContact != null) {
            if(keyEvent.getCode().equals(KeyCode.DELETE)) { // compare code of pressed key with delete key code
                deleteContact(selectedContact);
            }
        }
    }

    @FXML
    public void handleDeleteButton() { // handles delete option from menu
        Contact selectedContact = contactTableView.getSelectionModel().getSelectedItem();
        if(selectedContact != null) {
            deleteContact(selectedContact);
        }
    }

    public void deleteContact(Contact contact) { // delete method, with standard information window of confirmation type used
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // declaring built-in confirmation pop-up
        // below we put all properties of wanted window
        alert.setTitle("Usuwanie kontaktów");
        alert.setHeaderText("Usuń kontakt: " + contact.getName() + " " + contact.getSurname());
        alert.setContentText("Jesteś pewien? Kliknij OK aby potwierdzić " +
                "lub Cancel aby odrzucić zmiany.");

        Optional<ButtonType> result = alert.showAndWait(); // starts up confirmation window which now waits for action

        if(result.isPresent() && (result.get() == ButtonType.OK)) { // check for pressing OK button action
            ContactData.getInstance().deleteContact(contact);
        }
    }

    @FXML
    public void handleExit() { // handles exit button in menu
        Platform.exit();
    }
}
