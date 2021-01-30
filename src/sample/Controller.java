package sample;
//this is 1.1

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class Controller {

    /*
    Laver 3 knapper.
    Har brugt scenebuilder til at skabe forbindelse mellem gui'en og knapperne.

     */
    @FXML
    Button btnBacktracking;

    @FXML
    Button Kruskal;

    @FXML
    Button RecursiveDevision;

    /*
    Da der er lavet konstrukters på alle vores mazes gøres det derfor nemt at skifte størrelsen på grid.
     */
    BackTrackerMaze maze = new BackTrackerMaze(10, 10);
    KruskalMaze Maze = new KruskalMaze(10, 10);
    RecursiveDivision mazze = new RecursiveDivision(10, 10);

    // Kalder Canvas, dette gøres så vi kan tegne i gui'en.
    @FXML
    Canvas canvas;
    private GraphicsContext gc;


    /*
    initialize køre i starten og sætter griddet op en gang. jeg har valgt at beholde dette, da jeg synes programmet bliver pænere af det.
    Herudover kan programmet heller ikke køre uden initilize metoden køre inden.
     maze.generateGrid(gc); & drawMaze(maze); er ikke behøvet i initialize...
     */
    public void initialize() throws InterruptedException {

        gc = canvas.getGraphicsContext2D();
        maze.generateGrid(gc);
        drawMaze(maze);

    }

    /*
    både Backtrack og Kruskal er bygget op ens.
    Begge metode at indeholder ActionEvent, som er forbundet med knapperne i gui'en.
    maze/Maze/Mazze.generateGrid/sets/fields er her hvor vi tegner griddet af det de objecter de forskellige algoritmer skal bruge.
    Det er vigtigt at understrege at i Controller klassen bliver de forskellige metoder tegnet og i algoritme klasserne bliver de tildelt logik.
    Efter de forskellige grids at lavet, så køres algoritmerne.
    Til sidst bliver de tegnet, når algoritmen er færdig.
     */
    public void Backtrack(ActionEvent event) throws InterruptedException {
        maze.generateGrid(gc);
        maze.backtracking(gc);
        drawMaze(maze);
    }


    public void Kruskal(ActionEvent event) throws InterruptedException {
        Maze.generateSets(gc);
        Maze.kruskalAlgorithm(gc);
        drawKruskalMaze(Maze);
    }

    public void RecursiveDivision(ActionEvent event) throws InterruptedException {
        mazze.generateEmptyField(gc);
        mazze.generateHorizontalLines();
        mazze.generateVerticalLines();
        drawDivision(mazze);

    }

    /*
    Nedenstående metoder bruges til at tegne mazen.
     */
    private void drawMaze(BackTrackerMaze maze) throws InterruptedException {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < maze.rows; i++) {
            for (int j = 0; j < maze.cols; j++) {
                maze.cells[i][j].draw(gc);
            }
        }
    }

    private void drawKruskalMaze(KruskalMaze Maze) throws InterruptedException {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < Maze.rows; i++) {
            for (int j = 0; j < Maze.cols; j++) {
                Maze.cells[i][j].draw(gc);
            }
        }
    }

    private void drawDivision(RecursiveDivision mazze) throws InterruptedException {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = 0; i < maze.rows; i++) {
            for (int j = 0; j < maze.cols; j++) {
                mazze.cells[i][j].draw(gc);
            }
        }
    }
}











