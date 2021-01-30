package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.Random;
/*
ikke blevet færdig med denne algoritme...
 */
public class RecursiveDivision {
    public Cell[][] cells;
    int rows;
    int cols;
    Random random = new Random();


    public RecursiveDivision(int w, int h) {
        this.rows = w;
        this.cols = h;
        cells = new Cell[rows][cols];
    }

    public void generateEmptyField(GraphicsContext gc) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell c = new Cell(i, j, false, false, false, false, false);
                cells[i][j] = c;
            }
        }
    }

    public void generateHorizontalLines() {
        for (int j = 0; j < 1; j++) {


            int findrandomX = random.nextInt(rows - 1);
            for (int i = 0; i < rows; i++) {
                // tegner "tom" firkant...
                cells[i][0].setUpperWall(true);
                cells[0][i].setLeftWall(true);
                cells[i][rows - 1].setBottomWall(true);
                cells[rows - 1][i].setRightWall(true);

                cells[i][findrandomX].setBottomWall(true);
                cells[i][findrandomX].setWallMeetswall(true);

                Cell current = cells[findrandomX][findrandomX];
                current.setBottomWall(false);

            }
        }
    }

    public void generateVerticalLines() {
        for (int j = 0; j < 1; j++) {
            int findrandomY = random.nextInt(cols - 1);
            for (int i = 0; i < cols; i++) {
                cells[findrandomY][i].setRightWall(true);
                cells[i][findrandomY].setWallMeetswall(true);
                Cell currentY = cells[findrandomY][findrandomY];
                currentY.setRightWall(false);

            }
        }
    }
}

