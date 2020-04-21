package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Methods {
    public static void changeScreen(String path, Node node) throws IOException {
        FXMLLoader loader = new FXMLLoader(Methods.class.getResource(path));
        loader.load();
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
//        node.getScene().getWindow().hide();
        stage.setResizable(false);
        stage.show();
    }
}
