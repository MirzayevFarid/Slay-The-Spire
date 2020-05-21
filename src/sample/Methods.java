package sample;

import Components.Character.Character;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Methods {

    public static Character mainChar;


    public static Character getMainChar() {
        return mainChar;
    }



    public static void changeScreen(String path, Node node, boolean hide) throws IOException {
        FXMLLoader loader = new FXMLLoader(Methods.class.getResource(path));
        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        if(hide)
            node.getScene().getWindow().hide();
        stage.setResizable(false);
        stage.show();
    }
}
