package sample;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.play.Play;

import java.io.IOException;

public class Main extends Application {
    private static int width = 1440;
    private static int height = 900;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu/mainMenu.fxml"));
        primaryStage.setTitle("Slay The Spire");
        primaryStage.setScene(new Scene(root, width , height));
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    public static int getWidth(){
        return width;
    }

    public static int getHeight(){
        return height;
    }

    public static void main(String[] args) throws IOException {
        //Play.music();
        launch(args);
    }
}