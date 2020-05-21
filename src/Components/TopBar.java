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

    private HBox hbox = new HBox(10);
    private AnchorPane anchorPane = new AnchorPane();

    private ImageView barBackground = new ImageView(new Image(getClass().getResourceAsStream("../Images/play/bar.png")));

    private ImageView mapIcon = new ImageView(new Image(getClass().getResourceAsStream("../Images/play/map.png")));
    private Button mapButton = new Button();

    private ImageView heartIcon = new ImageView(new Image(getClass().getResourceAsStream("../Images/play/heart.png")));

    private ImageView settingsIcon = new ImageView(new Image(getClass().getResourceAsStream("../Images/play/settings.png")));
    private Button settingsButton = new Button();

    private ImageView potionIcon1 = new ImageView();
    private Button potionButton1 = new Button();

    private ImageView potionIcon2 = new ImageView();
    private Button potionButton2 = new Button();

    private ImageView potionIcon3 = new ImageView();
    private Button potionButton3 = new Button();

    private ImageView gold = new ImageView(new Image(getClass().getResourceAsStream("../Images/play/gold.png")));
    private Button goldButton = new Button();

    Label hp = new Label("100 / 100");
    Label goldLabel = new Label("100");

    public Label getHp(){
        return hp;
    }

    public Label getGold(){
        return goldLabel;
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
        goldButton.setGraphic(gold);
        goldButton.setStyle("-fx-background-color: transparent;");

        hp.setFont(Font.font(30));
        hp.setTextFill(Color.RED);
        goldLabel.setFont(Font.font(30));
        goldLabel.setTextFill(Color.YELLOW);


        anchorPane.getChildren().add(barBackground);
        anchorPane.getChildren().add(hbox);
        HBox.setMargin(heartIcon,new Insets(5,0,0,0));
        hbox.getChildren().add(heartIcon);
        HBox.setMargin(hp,new Insets(15,0,0,0));
        hbox.getChildren().add(hp);

        HBox.setMargin(potionButton1, new Insets(-5, 0, 0, 0));
        hbox.getChildren().add(potionButton1);

        HBox.setMargin(potionButton2, new Insets(-5, 0, 0, 0));
        hbox.getChildren().add(potionButton2);

        HBox.setMargin(potionButton3, new Insets(-5, 25, 0, 0));
        hbox.getChildren().add(potionButton3);

        HBox.setMargin(goldButton, new Insets(-5, -20, 0, 0));
        hbox.getChildren().add(goldButton);

        HBox.setMargin(goldLabel, new Insets(10, Main.getWidth() - 775, 0, 0));
        hbox.getChildren().add(goldLabel);

        hbox.getChildren().add(mapButton);
        hbox.getChildren().add(settingsButton);
    }

    public Node getTopBar(){
        return anchorPane;
    }

    public ImageView getPotionIcon1() {
        return potionIcon1;
    }

    public void setPotionIcon1(ImageView potionIcon1) {
        this.potionIcon1 = potionIcon1;
    }

    public Button getPotionButton1() {
        return potionButton1;
    }

    public void setPotionButton1(Button potionButton1) {
        this.potionButton1 = potionButton1;
    }

    public ImageView getPotionIcon2() {
        return potionIcon2;
    }

    public void setPotionIcon2(ImageView potionIcon2) {
        this.potionIcon2 = potionIcon2;
    }

    public Button getPotionButton2() {
        return potionButton2;
    }

    public void setPotionButton2(Button potionButton2) {
        this.potionButton2 = potionButton2;
    }

    public ImageView getPotionIcon3() {
        return potionIcon3;
    }

    public void setPotionIcon3(ImageView potionIcon3) {
        this.potionIcon3 = potionIcon3;
    }

    public Button getPotionButton3() {
        return potionButton3;
    }

    public void setPotionButton3(Button potionButton3) {
        this.potionButton3 = potionButton3;
    }

    public void setPotionImage1(String path){
        System.out.println(path);
        potionIcon1 = new ImageView(new Image(getClass().getResourceAsStream("../" + path)));
        potionIcon1.setFitWidth(60);
        potionIcon1.setFitHeight(60);
        potionButton1.setGraphic(potionIcon1);
        potionButton1.setStyle("-fx-background-color: transparent;");
    }

    public void setPotionImage2(String path){
        System.out.println(path);
        potionIcon2 = new ImageView(new Image(getClass().getResourceAsStream("../" + path)));
        potionIcon2.setFitWidth(60);
        potionIcon2.setFitHeight(60);
        potionButton2.setGraphic(potionIcon2);
        potionButton2.setStyle("-fx-background-color: transparent;");
    }

    public void setPotionImage3(String path){
        System.out.println(path);
        potionIcon3 = new ImageView(new Image(getClass().getResourceAsStream("../" + path)));
        potionIcon3.setFitWidth(60);
        potionIcon3.setFitHeight(60);
        potionButton3.setGraphic(potionIcon3);
        potionButton3.setStyle("-fx-background-color: transparent;");
    }

    /*public void updatePotionImages(int index){
        if ( index == 1 ){

        }
        else if ( index == 2 ){

        }
        else if ( index == 3 ){
            setPotionImage3("../IMAGES/PotionImages/NoPotion.png");
        }
    }*/

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
