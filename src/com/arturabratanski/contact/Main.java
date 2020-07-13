/**
 * Developed by Artur Abratanski
 * based on course: https://www.udemy.com/course/java-the-complete-java-developer-course/
 * ended with certificate: https://www.udemy.com/certificate/UC-0ec63629-2fb7-4a2f-96d5-d58a3eb79e1a/
 */

/**
 * This Java code is responsible for application launch.
 * It contains some generated code for JavaFX stage set-up and 2 methods for exceptions handling.
 */

package com.arturabratanski.contact;

import com.arturabratanski.contact.datamodel.ContactData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        primaryStage.setTitle("KontAAkty by " + "\u00A9Artur Abratański");
        primaryStage.setScene(new Scene(root, 900, 556));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override // need to check if it needs to throw an exception
    public void init() { // we're overriding init() to load saved contacts into application at launch
        ContactData.getInstance().loadContacts();
    }

    @Override // need to check if it needs to throw an exception
    public void stop() { // we override stop() to save/update contact list at the moment of app execution
        ContactData.getInstance().saveContacts();
    }
}
