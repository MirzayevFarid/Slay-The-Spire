package sample.map;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Map {

    static int numberOfColumns = 7;
    static int numberOfRows = 10;
    int[][] map = new int[numberOfRows][numberOfColumns];     // 2D integer array with 4 rows
    static int[][] map2 = new int[numberOfRows][numberOfColumns];

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
        if (!control){
            addCirclesToGridPane(pn);
            textWriter();
            control = true;
        }
        System.out.println("////////icinde");
        drawLines2();
    }

    private void textWriter() throws IOException {
        FileWriter fw = new FileWriter(file);
        for(int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                txt += map[i][j];
            }
            txt += "\n";
        }

        for(int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                map2 [i][j] = map[i][j];
            }
        }
        fw.write(txt);
        fw.close();
    }


    private void drawLines2(){
        int singleX = width / (numberOfColumns*2);
        int doubleX = width / numberOfColumns;
        int singleY = height / (numberOfRows*2);
        int doubleY = height / numberOfRows;

        for (int i = 0; i < numberOfRows-1 ; i++) {
            ArrayList<Integer> above = new ArrayList<>();
            ArrayList<Integer> bottom = new ArrayList<>();
            for(int j = 0; j < numberOfColumns; j++){
                if (map2[i][j] == 1){
                    above.add(j);
                }
                if (map2[i+1][j] == 1){
                    bottom.add(j);
                }
            }
            System.out.println("above size: " + above.size());
            System.out.println("bottom size: " + bottom.size());

            if (above.size() > 1 && bottom.size() > 1){
                while (above.size() > 1 && bottom.size() > 1 ){
                    System.out.println("above > 1 , bottom > 1");
                    System.out.println("above.get0 : " + above.get(0) + " Bottom 0 : " + bottom.get(0));

                    int strtLocX = singleX + (above.get(0) * doubleX);
                    int strtLocY =  doubleY * i + singleY;

                    int endLocX = singleX + (bottom.get(0) * doubleX);
                    int endLocY = (doubleY * (i + 1) + singleY);
                    above.remove(0);
                    bottom.remove(0);
                    window.getChildren().addAll(new Line(strtLocX, strtLocY, endLocX,endLocY));
                }

            }
            if (above.size() == 1 && bottom.size() > 1){
                System.out.println( "above 1 bottom > 1");
                System.out.println("above.get0 : " + above.get(0) + " Bottom 0 : " + bottom.get(0));
                while (bottom.size() != 0){
                    int strtLocX = singleX + (above.get(0) * doubleX);
                    int strtLocY =  (doubleY * i + singleY);

                    int endLocX = singleX + (bottom.get(0) * doubleX);
                    int endLocY = (doubleY * (i + 1) + singleY);
                    bottom.remove(0);
                    window.getChildren().addAll(new Line(strtLocX, strtLocY, endLocX,endLocY));
                }
                above.remove(0);
            }
            if (above.size() > 1 && bottom.size() == 1){
                System.out.println( "above > 1 bottom = 1");
                System.out.println("above.get0 : " + above.get(0) + " Bottom 0 : " + bottom.get(0));
                while (above.size() != 0){
                    int strtLocX = singleX + (above.get(0) * doubleX);
                    int strtLocY = (doubleY * i + singleY);

                    int endLocX = singleX + (bottom.get(0) * doubleX);
                    int endLocY = (doubleY * (i + 1) + singleY);
                    above.remove(0);
                    window.getChildren().addAll(new Line(strtLocX, strtLocY, endLocX,endLocY));
                }
                bottom.remove(0);
            }
            if(above.size() == 1 && bottom.size() == 1) {
                System.out.println( "above 1 bottom 1");
                System.out.println("above.get0 : " + above.get(0) + " Bottom 0 : " + bottom.get(0));
                int strtLocX = singleX + (above.get(0) * doubleX);
                int strtLocY =  (doubleY * i + singleY);

                int endLocX = singleX + (bottom.get(0) * doubleX);
                int endLocY = (doubleY * (i + 1) + singleY);
                bottom.remove(0);
                above.remove(0);
                window.getChildren().addAll(new Line(strtLocX, strtLocY, endLocX,endLocY));
            }
        }
    }


    public void addCirclesToGridPane(GridPane gridPane)
    {
        Random random = new Random();
        int rowNumber = random.nextInt(3);
        rowNumber++;

        int ranY = 9;
        String str;

        System.out.println("FIRST ROW");
        for(int i = 0; i <= rowNumber; i++){
            int ranX = random.nextInt(numberOfColumns); // random value from 0 to width
            gridPane.add(new Circle(10, Color.RED), ranX, ranY);

            // Add 1 to map
            map[ranY][ranX] = 1;

            System.out.println(ranX + "   ,    " + ranY);
        }


        System.out.println("OTHER ROWS");
        for (; ranY >= 0; ranY--) {
            rowNumber = random.nextInt( 2);
            rowNumber++;

            for  (int i = 0; i<= rowNumber; i++){
                int ranX = random.nextInt(numberOfColumns);  // random value from 0 to width
                gridPane.add(new Circle(10, Color.RED), ranX, ranY);
                // Add 1 to map
                map[ranY][ranX] = 1;
                System.out.println(ranX + "   ,    " + ranY);
            }
        }
    }
}
