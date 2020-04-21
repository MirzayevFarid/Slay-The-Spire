package sample.map;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.Random;

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

    public void initialize(){
        addCirclesToGridPane(pn);

        for(int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(map[i][j] + ", ");
            }
            System.out.println();
        }

        drawLines();
    }

    private void drawLines() {

        int previousCount = 0;
        int currentCount = 0;


        for(int i = 0; i<numberOfColumns; i++) {
            if(map[9][i] == 1){
                previousCount++;
            }
            if(map[8][i] == 1){
                currentCount++;
            }
        }

        int [] arr = distributeCandies(currentCount, previousCount);

        for (int value : arr) {
            System.out.print(value + "\t");
        }

        int index = 0;
        for(int i = 0; i< numberOfColumns; i++){
            if(map[9][i] == 1){
            int count = arr[index];
                for(int j = 0; j < numberOfColumns && count> 0; j++) {
                    if(map[8][j] == 1){

                        int singleX = width/(numberOfColumns*2);
                        int doubleX = width/numberOfColumns;
                        int singleY = height/(numberOfRows*2);
                        int doubleY = height/numberOfRows;

                        int strtLocX = singleX + (i * doubleX);
                        int strtLocY = height - (singleY);

                        int endLocX = singleX + (j * doubleX);
                        int endLocY = height - (singleY + (doubleY));

                        window.getChildren().addAll(new Line(strtLocX, strtLocY, endLocX,endLocY));
                        map[8][j] = 0;
                        count--;
                    }
                }
                index++;
            }
        }

        System.out.println();

        for(int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(map[i][j] + ", ");
            }
            System.out.println();
        }
    }

    static int [] distributeCandies(int n, int k)
    {
        // Count number of complete turns
        int count = 0;

        // Get the last term
        int ind = 1;

        // Stores the number of candies
        int []arr=new int[k];

        for(int i=0;i<k;i++)
            arr[i]=0;

        int low = 0, high = n;

        // Do a binary search to find the number whose
        // sum is less than N.
        while (low <= high) {

            // Get mide
            int mid = (low + high) >> 1;
            int sum = (mid * (mid + 1)) >> 1;

            // If sum is below N
            if (sum <= n) {

                // Find number of complete turns
                count = mid / k;

                // Right halve
                low = mid + 1;
            }
            else {

                // Left halve
                high = mid - 1;
            }
        }

        // Last term of last complete series
        int last = (count * k);

        // Subtract the sum till
        n -= (last * (last + 1)) / 2;

        int j = 0;

        // First term of incomplete series
        int term = (count * k) + 1;

        while (n > 0) {
            if (term <= n) {
                arr[j++] = term;
                n -= term;
                term++;
            }
            else {
                arr[j] += n;
                n = 0;
            }
        }

        // Count the total candies
        for (int i = 0; i < k; i++)
            arr[i] += (count * (i + 1))
                    + (k * (count * (count - 1)) / 2);

        // Print the total candies
        System.out.println("*************************");
        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("*************************");

        return arr;
    }
//    public int[] distributeCandies(int candies, int previousCount) {
//        int arr[]=new int[previousCount];
//        int j=1,sum=0;
//        for(int i=0; candies-sum>0;i=(previousCount+i)%previousCount){
//            if(candies-sum>=j){
//                arr[i]=arr[i]+j;
//                sum+=j;
//                j++;
//                i++;
//            }
//            else{
//                arr[i]=arr[i]+candies-sum;
//                sum=sum+arr[i]+j;
//            }
//        }
//        return arr;
//    }


    public void addCirclesToGridPane(GridPane gridPane)
    {
        Random random = new Random();
        int rowNumber = random.nextInt(3);
        rowNumber++;

        int ranY = 9;

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
            rowNumber = random.nextInt( 6);
            rowNumber++;

            ranY--;
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
