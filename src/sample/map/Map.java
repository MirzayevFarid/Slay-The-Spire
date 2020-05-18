package sample.map;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import sample.Methods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Map {

    int numberOfColumns = 7;
    int numberOfRows = 10;
    int[][] map = new int[numberOfRows][numberOfColumns];     // 2D integer array with 4 rows

    @FXML
    private AnchorPane window;

    @FXML
    private GridPane pn;

    int width = 1440;
    int height = 900;
    String txt = "";
    String path = "src/sample/map/map.txt";
    File file = new File(path);
    static boolean control = false;

    public void initialize() throws IOException {

        int[][] mapToRead;
        if (file.exists()) {
            control = true;
        }
        if (!control) {
            mapToRead = generateCircleToMap();
            textWriter(mapToRead);
            control = true;
        }
        else {
            mapToRead = readTxt();
            textWriter(mapToRead);
        }
        mapToRead[2][0] = 2;
        addCircleFromTxt(pn, mapToRead);
        drawLines2(mapToRead);
    }

    /**
     * It reads the generated circles and writes into map.txt
     * @throws IOException
     */
    private void textWriter(int[][] mapToRead) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                txt += mapToRead[i][j];
            }
            txt += "\n";
        }
        fw.write(txt);
        fw.close();
    }


    /**
     * It reads map.txt file, and record the data to map2.
     * @return map2 holds map.txt data
     * @throws FileNotFoundException
     */
    private int[][] readTxt() throws FileNotFoundException {
        int[][] map2 = new int[numberOfRows][numberOfColumns];
        Scanner scan = new Scanner(file);
        String line = "";
        int colIndex = 0;
        int rowIndex = 0;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '1') {
                    map2[rowIndex][colIndex] = 1;
                }
                if (line.charAt(i) == '2') {
                    map2[rowIndex][colIndex] = 2;
                }
                if (line.charAt(i) == '3') {
                    map2[rowIndex][colIndex] = 3;
                }
                if (line.charAt(i) == '4') {
                    map2[rowIndex][colIndex] = 4;
                }
                if (line.charAt(i) == '5') {
                    map2[rowIndex][colIndex] = 5;
                }
                colIndex++;
            }
            colIndex = 0;
            rowIndex++;
        }
        return map2;
    }

    /**
     * read data is used in order to add circles to pane
     * @param gridPane  to add the circles on the screen
     * @param map2 the 2 dimensional array holding text data
     */
    private void addCircleFromTxt(GridPane gridPane, int[][] map2) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {

                // 1 means enemy
                if (map2[i][j] == 1) {
                    ImageView settingsIcon = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/settings.png")));
                    Button legendButton = new Button();
                    legendButton.setGraphic(settingsIcon);
                    legendButton.setStyle("-fx-background-color: transparent;");

                    legendButton.onMouseClickedProperty().set((MouseEvent t) -> {
                        try {
                            Methods.changeScreen("play/play.Fxml",pn, true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    gridPane.add(legendButton, j, i);
                }

                // 2 means current character location
                if (map2[i][j] == 2) {
                    ImageView restIcon = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/rest.png")));
                    Button currentChar = new Button();
                    currentChar.setGraphic(restIcon);
                    currentChar.setStyle("-fx-background-color: transparent;");

                    currentChar.onMouseClickedProperty().set((MouseEvent t) -> {
                        try {
                            Methods.changeScreen("play/play.Fxml",pn, true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    gridPane.add(currentChar, j, i);
                }
                //3 means merchant
                if (map2[i][j] == 3) {
                    ImageView shopIcon = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/monster.png")));
                    Button currentChar = new Button();
                    currentChar.setGraphic(shopIcon);
                    currentChar.setStyle("-fx-background-color: transparent;");

                    currentChar.onMouseClickedProperty().set((MouseEvent t) -> {
                        try {
                            //TODO shop fxml will be implemented then fallowing line will be changed.
                            Methods.changeScreen("play/play.Fxml",pn, true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    gridPane.add(currentChar, j, i);
                }

                // 4 means treasure
                if (map2[i][j] == 4) {
                    ImageView treasureIcon = new ImageView(new Image(getClass().getResourceAsStream("../../IMAGES/play/map.png")));
                    Button currentChar = new Button();
                    currentChar.setGraphic(treasureIcon);
                    currentChar.setStyle("-fx-background-color: transparent;");

                    currentChar.onMouseClickedProperty().set((MouseEvent t) -> {
                        try {
                            //TODO treasure fxml will be implemented then fallowing line will be changed.
                            Methods.changeScreen("play/play.Fxml",pn, true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    gridPane.add(currentChar, j, i);
                }
                // 5 means rest
                if (map2[i][j] == 5){
                    ImageView restIcon = new ImageView(new Image(getClass().getResourceAsStream("../../Images/play/rest.png")));
                    Button currentChar = new Button();
                    currentChar.setGraphic(restIcon);
                    currentChar.setStyle("-fx-background-color: transparent;");

                    currentChar.onMouseClickedProperty().set((MouseEvent t) -> {
                        try {
                            Methods.changeScreen("play/play.Fxml",pn, true);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    gridPane.add(currentChar, j, i);
                }
            }
        }
    }

    /**
     * It draw lines of the map
     * @param map2 holds the data of the map.txt
     */

    private void drawLines2(int[][] map2) {
        int singleX = width / (numberOfColumns * 2);
        int doubleX = width / numberOfColumns;
        int singleY = height / (numberOfRows * 2);
        int doubleY = height / numberOfRows;

        for (int i = 0; i < numberOfRows - 1; i++) {
            ArrayList<Integer> above = new ArrayList<>();
            ArrayList<Integer> bottom = new ArrayList<>();
            for (int j = 0; j < numberOfColumns; j++) {
                if (map2[i][j] != 0) {
                    above.add(j);
                }
                if (map2[i + 1][j] != 0) {
                    bottom.add(j);
                }
            }

            if (above.size() > 1 && bottom.size() > 1) {
                while (above.size() > 1 && bottom.size() > 1) {

                    int strtLocX = singleX + (above.get(0) * doubleX);
                    int strtLocY = doubleY * i + singleY;

                    int endLocX = singleX + (bottom.get(0) * doubleX);
                    int endLocY = (doubleY * (i + 1) + singleY);
                    above.remove(0);
                    bottom.remove(0);
                    window.getChildren().addAll(new Line(strtLocX, strtLocY, endLocX, endLocY));
                }

            }
            if (above.size() == 1 && bottom.size() > 1) {

                while (bottom.size() != 0) {
                    int strtLocX = singleX + (above.get(0) * doubleX);
                    int strtLocY = (doubleY * i + singleY);

                    int endLocX = singleX + (bottom.get(0) * doubleX);
                    int endLocY = (doubleY * (i + 1) + singleY);
                    bottom.remove(0);
                    window.getChildren().addAll(new Line(strtLocX, strtLocY, endLocX, endLocY));
                }
                above.remove(0);
            }
            if (above.size() > 1 && bottom.size() == 1) {
                while (above.size() != 0) {
                    int strtLocX = singleX + (above.get(0) * doubleX);
                    int strtLocY = (doubleY * i + singleY);

                    int endLocX = singleX + (bottom.get(0) * doubleX);
                    int endLocY = (doubleY * (i + 1) + singleY);
                    above.remove(0);
                    window.getChildren().addAll(new Line(strtLocX, strtLocY, endLocX, endLocY));
                }
                bottom.remove(0);
            }
            if (above.size() == 1 && bottom.size() == 1) {
                int strtLocX = singleX + (above.get(0) * doubleX);
                int strtLocY = (doubleY * i + singleY);

                int endLocX = singleX + (bottom.get(0) * doubleX);
                int endLocY = (doubleY * (i + 1) + singleY);
                bottom.remove(0);
                above.remove(0);
                window.getChildren().addAll(new Line(strtLocX, strtLocY, endLocX, endLocY));
            }
        }
    }

    /**
     * it generates circles randomly
     */

    public int [][] generateCircleToMap() {
        Random random = new Random();
        int rowNumber = random.nextInt(2);
        rowNumber++;

        int ranY = 9;

        // first row
        for (int i = 0; i <= rowNumber; i++) {
            int ranX = random.nextInt(numberOfColumns); // random value from 0 to width
            // Add 1 to map for enemies
            map[ranY][ranX] = 1;
        }

        for (; ranY >= 0; ranY--) {
            rowNumber = random.nextInt(2);
            rowNumber++;

            for (int i = 0; i <= rowNumber; i++) {
                int ranX = random.nextInt(numberOfColumns);  // random value from 0 to width
                if(ranY == 0 || ranY == 4 ||ranY == 5){
                    map[ranY][ranX] = 1;
                }
                // merchant
                if (ranY == 2 || ranY == 6){
                    map[ranY][ranX] = 3;
                }
                //Treasure
                if (ranY == 1 ||ranY == 8){
                    map[ranY][ranX] = 4;
                }
                //REst
                if (ranY == 3 || ranY == 7){
                    map[ranY][ranX] = 5;
                }

            }
        }
        return map;
    }
}
