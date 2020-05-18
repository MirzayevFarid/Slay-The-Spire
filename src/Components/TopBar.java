package Components;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.Main;
import sample.Methods;

import java.io.IOException;


public class TopBar extends Node {

    private HBox hbox = new HBox(5);
    private AnchorPane anchorPane = new AnchorPane();

    private ImageView barBackground = new ImageView(new Image(getClass().getResourceAsStream("../Images/play/bar.png")));

    private ImageView mapIcon = new ImageView(new Image(getClass().getResourceAsStream("../Images/play/map.png")));
    private Button mapButton = new Button();

    private ImageView heartIcon = new ImageView(new Image(getClass().getResourceAsStream("../Images/play/heart.png")));

    private ImageView settingsIcon = new ImageView(new Image(getClass().getResourceAsStream("../Images/play/settings.png")));
    private Button settingsButton = new Button();

    Label hp = new Label("100 / 100");


    public Label getHp(){
        return hp;
    }

    public TopBar(){
        addItems();
        addListeners();
    }

    private void addListeners() {
        settingsButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("settings/settings.Fxml",settingsButton, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        mapButton.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen("map/map.Fxml",mapButton, false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    void addItems(){
        mapButton.setGraphic(mapIcon);
        mapButton.setStyle("-fx-background-color: transparent;");
        settingsButton.setGraphic(settingsIcon);
        settingsButton.setStyle("-fx-background-color: transparent;");

        hp.setFont(Font.font(30));
        hp.setTextFill(Color.RED);


        anchorPane.getChildren().add(barBackground);
        anchorPane.getChildren().add(hbox);
        HBox.setMargin(heartIcon,new Insets(5,0,0,0));
        hbox.getChildren().add(heartIcon);
        HBox.setMargin(hp,new Insets(15,Main.getWidth() - 400,0,0));
        hbox.getChildren().add(hp);
        hbox.getChildren().add(mapButton);
        hbox.getChildren().add(settingsButton);

    }

    public Node getTopBar(){
        return anchorPane;
    }

    @Override
    protected NGNode impl_createPeer() {
        return null;
    }

    @Override
    public BaseBounds impl_computeGeomBounds(BaseBounds bounds, BaseTransform tx) {
        return null;
    }

    @Override
    protected boolean impl_computeContains(double localX, double localY) {
        return false;
    }

    @Override
    public Object impl_processMXNode(MXNodeAlgorithm alg, MXNodeAlgorithmContext ctx) {
        return null;
    }
}
