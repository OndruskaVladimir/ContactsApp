package com.ondruska.contacts;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setTitle("Contacts");
        primaryStage.setScene(new Scene(root, 800, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    //when the app starts, we load contacts from our XML file
    @Override
    public void init() {
        try{
            ContactData.getInstance().loadContacts();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //when the app closes, we save contacts to our XML file
    @Override
    public void stop() {
        try{
            ContactData.getInstance().saveContacts();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
