package sample;

import contacts.ContactBook;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        ContactBook.getInstance().deserializeContacts();
        Parent loader1 = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Address Book");
        primaryStage.setScene(new Scene(loader1, 1000, 650));
        primaryStage.show();
    }

    public static Stage getStage(){
        return stage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
