package sample;

import javafx.scene.canvas.GraphicsContext;

import java.util.*;
/*
Choose a starting point in the field.
Randomly choose a wall at that point and carve a passage through to the adjacent cell, but only if the adjacent cell has not been visited yet. This becomes the new current cell.
If all adjacent cells have been visited, back up to the last cell that has uncarved walls and repeat.
The algorithm ends when the process has backed all the way up to the starting point.
 */

public class BackTrackerMaze {
    Stack<Cell> backtrackerRoute = new Stack<>();
    int rows;
    int cols;
    public Cell[][] cells;
    ArrayList<Cell> neighbors = new ArrayList<>();
    Random random = new Random();
    int i, j;

/*
Laver en construktor for backtracker mazen.
Dette er gjort for at vi kan styre størrelsen af grid og størrelsen af det der bliver  tegner 1 gang.
*/

    public BackTrackerMaze(int w, int h) {
        this.rows = w;
        this.cols = h;
        cells = new Cell[rows][cols];
    }

    /*
    Nedenunder laves der ved hjælp af et dobbel4loop et grid/board med cell c.
    */

    public void generateGrid(GraphicsContext gc) {
        // fylder 2d array med celler
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell c = new Cell(i, j);
                cells[i][j] = c;
            }
        }
    }

    /*
    Nedenstående metode er selve algoritmen og den tilhørende logik, der skal til for, at udhule griddet.
     */
    public void backtracking(GraphicsContext gc) {

        Cell bottomNabo = null; // initializer cell ved at sætte dem til null;
        Cell topNabo = null;
        Cell leftNabo = null;
        Cell rightNabo = null;

        boolean done = false; // boolean til while-løkken

        int xRng = random.nextInt(rows - 1); // metode til at generere random istedet for brug af random.util
        int yRng = random.nextInt(cols - 1);

        Cell current = cells[xRng][yRng]; // finder en current og starter algoritmen derfra
        current.visit(gc);

        while (!done) {
            // tilføjer cell til backtrckr rute
            //sikre os at de findes og ikke er besøgt:
            // nedenstående sikrer at cellerne eksisterer. Herudover bliver de tilføjet til Arraylist
            // CHECK LEFT
            if (i - 1 >= 0 && !cells[i - 1][j].isVisited()) {
                leftNabo = cells[i - 1][j];
                neighbors.add(leftNabo);
            } else {
                leftNabo = null;
            }
            // CHECK RIGHT
            if (i + 1 < rows && !cells[i + 1][j].isVisited()) {
                rightNabo = cells[i + 1][j];
                neighbors.add(rightNabo);
            } else {
                rightNabo = null;
            }
            // CHECK UP
            if (j - 1 >= 0 && !cells[i][j - 1].isVisited()) {
                topNabo = cells[i][j - 1];
                neighbors.add(topNabo);
            } else {
                topNabo = null;
            }
            // CHECK DOWN
            if (j + 1 < cols && !cells[i][j + 1].isVisited()) {
                bottomNabo = cells[i][j + 1];
                neighbors.add(bottomNabo);
            } else {
                bottomNabo = null;
            }
            // hvis listen er større end 0 så bland listen, tag den første altså [0]
            if (neighbors.size() > 0) {
                Collections.shuffle(neighbors);
                Cell nextCurrent = neighbors.get(0);
                //skaber passage igennem grid
                if (current != null) {
                    if (topNabo != null && nextCurrent == topNabo) {
                        current.setUpperWall(false);
                        nextCurrent.setBottomWall(false);
                    }
                    if (bottomNabo != null && nextCurrent == bottomNabo) {
                        current.setBottomWall(false);
                        nextCurrent.setUpperWall(false);
                    }
                    if (rightNabo != null && nextCurrent == rightNabo) {
                        current.setRightWall(false);
                        nextCurrent.setLeftWall(false);
                    }
                    if (leftNabo != null && nextCurrent == leftNabo) {
                        current.setLeftWall(false);
                        nextCurrent.setRightWall(false);
                    }
                }

                current = nextCurrent; // rykker current
                neighbors.clear(); // tømmer arraylist
                current.visit(gc);
                i = current.getI();
                j = current.getJ();
                backtrackerRoute.push(current); // rykker current op i toppen af stack
            } else if (backtrackerRoute.size() > 1) {
                backtrackerRoute.pop(); // fjerner øverste element i stack
                current = backtrackerRoute.lastElement();
                i = current.getI();
                j = current.getJ();
            } else {
                done = true;
                System.out.println("done");
            }
        }
    }
}


