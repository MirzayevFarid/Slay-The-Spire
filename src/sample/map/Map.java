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
        } else {
            mapToRead = readTxt();
            textWriter(mapToRead);
        }

        addCircleFromTxt(pn, mapToRead);
        drawLines2(mapToRead);
    }

    /**
     * It reads the generated circles and writes into map.txt
     *
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
     *
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
                map2[rowIndex][colIndex] = Integer.parseInt(String.valueOf(line.charAt(i)));
                colIndex++;
            }
            colIndex = 0;
            rowIndex++;
        }
        return map2;
    }

    /**
     * read data is used in order to add circles to pane
     *  1 - Combat
     *  2 - Rest
     *  3 - Treasure
     *  4 - Shop
     *  5 - Current
     * @param gridPane to add the circles on the screen
     * @param map2     the 2 dimensional array holding text data
     */
    private void addCircleFromTxt(GridPane gridPane, int[][] map2) {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {

                switch (map2[i][j]) {
                    case 1:
                        addButtons("../../Images/play/combatIcon.png", "play/play.Fxml", gridPane, i, j);
                        break;
                    case 2:
                        addButtons("../../Images/play/restIcon.png", "play/play.Fxml", gridPane, i, j);
                        break;
                    case 3:
                        addButtons("../../Images/play/treasureIcon.png", "play/play.Fxml", gridPane, i, j);
                        break;
                    case 4:
                        addButtons("../../IMAGES/play/shopIcon.png", "play/play.Fxml", gridPane, i, j);
                        break;
                    case 5:
                        addButtons("../../Images/play/currentIcon.png", "play/play.Fxml", gridPane, i, j);
                        break;
                }
            }
        }
    }


    public void addButtons(String imagePath, String screenPath, GridPane gridPane, int i, int j) {
        ImageView restIcon = new ImageView(new Image(getClass().getResourceAsStream(imagePath)));
        Button currentChar = new Button();
        currentChar.setGraphic(restIcon);
        currentChar.setStyle("-fx-background-color: transparent;");

        currentChar.onMouseClickedProperty().set((MouseEvent t) -> {
            try {
                Methods.changeScreen(screenPath, pn, true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        gridPane.add(currentChar, j, i);
    }


    /**
     * It draw lines of the map
     *
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

    public int[][] generateCircleToMap() {
        Random random = new Random();
        int rowNumber = random.nextInt(2);
        rowNumber++;

        int ranY = 9;
        int ranNum;

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
                if (ranY == 0) {
                    map[ranY][ranX] = 1;
                }
                //Rest
                if (ranY == 3 || ranY == 7) {
                    map[ranY][ranX] = 2;
                } else {
                    ranNum = random.nextInt(4) + 1;
                    map[ranY][ranX] = ranNum;
                }
            }
        }
        return map;
    }
}

