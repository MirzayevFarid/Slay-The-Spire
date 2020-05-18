package Components;

import Components.Character.CharacterJSON.ParseCharacterJSONObjects;
import Components.Relic.Relic;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class RelicBar extends Node {

    private HBox hbox = new HBox(4);
    private AnchorPane anchorPane = new AnchorPane();
    private ImageView relicIcon = new ImageView();
    final Tooltip tooltip = new Tooltip();
    ArrayList<Relic> relics = new ArrayList<>();


    public RelicBar(ParseCharacterJSONObjects character){
        this.relics = character.getCharacter().getRelicsOfPlayer().getRelics();
        addItems();
    }

    void addItems(){
        anchorPane.getChildren().add(hbox);
        addRelics();
    }

    void addRelics(){
        for(int i = 0; i< relics.size(); i++) {
            // Create Button
            Button relicButton = new Button();

            // Add Info
            tooltip.setText(
                    relics.get(i).getToolTipString()
            );
            relicButton.setTooltip(tooltip);

            setRelicIconImage(relicButton,relics.get(i).getImage());

            HBox.setMargin(relicButton, new Insets(-5, 0, 0, 0));
            hbox.getChildren().add(relicButton);
            HBox.setMargin(relicButton, new Insets(-5, 0, 0, 0));

        }
    }

    public void setRelicIconImage(Button relicButton, String path){
        relicIcon = new ImageView(new Image(getClass().getResourceAsStream("../" + path)));
        relicIcon.setFitWidth(70);
        relicIcon.setFitHeight(70);
        relicButton.setGraphic(relicIcon);
        relicButton.setStyle("-fx-background-color: transparent;");
    }

    public Node getRelicBar(){
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
