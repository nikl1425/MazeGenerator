package sample;

// Generate the maze and assign every single Cell as a node
// set up constants to descibe passage direction
// data structures (tree) that assists the algorithm
// Implement the algorithm

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.*;

public class KruskalMaze {
    int rows;
    int cols;
    public Cell[][] cells;
    public HashSet<Cell>[][] sets;
    ArrayList<Cell> neighbors = new ArrayList<>();


    // Random find start sets osv.
    // PosX = (int) ( Math.random() * rows); Bredde
    //PosY = (int) ( Math.random() * cols[0]); Højde
/*
Laver ligesom i Backtracking en konstruktor på klassen.
 */
    public KruskalMaze(int w, int h) {
        this.rows = w;
        this.cols = h;
        cells = new Cell[rows][cols];
        sets = new HashSet[rows][cols];
    }

    /*
    udover at lave et grid med celler, bliver der lagt endnu et grid ovenpå cell griddet.
    Dette ekstra grid er nødvendigt og indeholder Hashsets.
    Hashsets er gode til lige netop denne algoritme, da hashsets bl.a. ikke tillader dublicates...
     */
    public void generateSets(GraphicsContext gc) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Cell c = new Cell(i, j);
                cells[i][j] = c;
                HashSet Set = new HashSet();
                sets[i][j] = Set;
                Set.add(c);
            }
        }
    }

    /*
    Her bliver selve algoritmen implementeret.
     */
    public void kruskalAlgorithm(GraphicsContext gc) {
        Cell bottomNabo = null;
        Cell topNabo = null;
        Cell leftNabo = null;
        Cell rightNabo = null;
/*
Jeg har valgt at den skal køre indtil mit sæt oppe i venstre hjørne af griddet er ligeså stor set-størrelse som selve gridet
 */
        while (sets[0][0].size() < rows * cols) {
            /*
            Laver to nye random ints.
             */
            int posX = (int) (Math.random() * rows); //Bredde
            int posY = (int) (Math.random() * cols); //Højde

            //System.out.println(sets[0][0].size());
/*
sikrer mig at de finder og undgår indexOutOfBounds
tilføjer herefter til min arraylist.
 */
            if (posX - 1 >= 0) {
                leftNabo = cells[posX - 1][posY];
                neighbors.add(leftNabo);
            }
            if (posX + 1 < rows) {
                rightNabo = cells[posX + 1][posY];
                neighbors.add(rightNabo);
            }
            if (posY - 1 >= 0) {
                topNabo = cells[posX][posY - 1];
                neighbors.add(topNabo);
            }
            if (posY + 1 < cols) {
                bottomNabo = cells[posX][posY + 1];
                neighbors.add(bottomNabo);
            }

            Collections.shuffle(neighbors); // ryster posen af naboer

            //RIGHT NABO
            /*
            nedenstående 4loops skal sikre at de sæt der f.eks. tilhøre et sæt der lige et kommet over i et andet sæt skal pege på det sæt istedet for det gamle sæt.
            så man ligger sættene sammen eller med andre ord får dem til at pege på nyeste sæt.
            Herudover at det i disse 4 nedenstående metoder at væggene fjernes alt efter hvilket nogle naboer og current der bliver sat sammen til et sæt.
            loggiken ligger i if sætningen der starter disse 4 statements og her selve algoritmen kører.
            hvis vi er inden for griddet og nuværende set ikke indeholder det f.eks. til højere og naboen er f.eks. den til højre så gør...
             */
            if (posX + 1 < rows && !sets[posX][posY].contains(cells[posX + 1][posY]) && neighbors.get(0) == rightNabo) {
                sets[posX][posY].addAll(sets[posX + 1][posY]);
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (sets[posX][posY].contains(cells[i][j])) {
                            sets[i][j] = sets[posX][posY];
                        }
                    }
                }
                cells[posX][posY].setRightWall(false);
                cells[posX + 1][posY].setLeftWall(false);
            }
            //LEFT NABO
            if (posX - 1 >= 0 && !sets[posX][posY].contains(cells[posX - 1][posY]) && neighbors.get(0) == leftNabo) {
                sets[posX][posY].addAll(sets[posX - 1][posY]);
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (sets[posX][posY].contains(cells[i][j])) {
                            sets[i][j] = sets[posX][posY];
                        }
                    }
                }

                cells[posX][posY].setLeftWall(false);
                cells[posX - 1][posY].setRightWall(false);
            }
            //TOP NABO
            if (posY - 1 >= 0 && !sets[posX][posY].contains(cells[posX][posY - 1]) && neighbors.get(0) == topNabo) {
                sets[posX][posY].addAll(sets[posX][posY - 1]);
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (sets[posX][posY].contains(cells[i][j])) {
                            sets[i][j] = sets[posX][posY];
                        }
                    }
                }
                cells[posX][posY].setUpperWall(false);
                cells[posX][posY - 1].setBottomWall(false);
            }
            //bottom NABO
            if (posY + 1 < cols && !sets[posX][posY].contains(cells[posX][posY + 1]) && neighbors.get(0) == bottomNabo) {
                sets[posX][posY].addAll(sets[posX][posY + 1]);
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < cols; j++) {
                        if (sets[posX][posY].contains(cells[i][j])) {
                            sets[i][j] = sets[posX][posY];
                        }
                    }
                }
                cells[posX][posY].setBottomWall(false);
                cells[posX][posY + 1].setUpperWall(false);
            }

            neighbors.clear(); // tømmer arraylist
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.println(i + " ," + j + ", " + sets[i][j].size());
            }

        }
    }
}




