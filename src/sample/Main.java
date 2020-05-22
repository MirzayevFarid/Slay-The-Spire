package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu/mainMenu.fxml"));
        primaryStage.setTitle("Slay The Spire");
        primaryStage.setScene(new Scene(root, Constants.width , Constants.height));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static int getWidth(){
        return Constants.width;
    }

    public static int getHeight(){
        return Constants.height;
    }

    public static void main(String[] args) throws IOException {
//        Play.music();
        launch(args);
    }
}