package sample;

import javafx.scene.canvas.GraphicsContext;
/*
Dette er klassen der bruges i grid og som indeholder alle variabler osv. som vores algoritmer skal benytte sig af
de vigtigste i forhold til det visuele er de 4 nedenstående booleans, som bruges til at bestemme om der skal være vægge.
herudover skal der være kordinater og to variabler som kan skabe bredde og højde.
 */
public class Cell {
    private boolean upperWall = true;
    private boolean rightWall = true;
    private boolean leftWall = true;
    private boolean bottomWall = true;
    private int x;
    private int y;
    private int i;
    private int j;
    boolean WallMeetswall = false;



    private int w = 20; // size of cells
    private boolean visited = false;

    /*
    Første konstruktor som bruges i Backtracking og Kruskals.
    Det er muligt at lave 2 konstrukters så længe de har forskellige parametre.
    den anden konstruktor bruges i recursive devision.
     */

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
        x = i * w;
        y = j * w;

    }

    public Cell (int i, int j, boolean upperWall, boolean bottomWall, boolean leftWall, boolean rightWall, boolean WallMeetsWall){
        this.i = i;
        this.j = j;
        x = i * w;
        y = j * w;
        this.upperWall = upperWall;
        this.bottomWall = bottomWall;
        this.rightWall = rightWall;
        this.leftWall = leftWall;
        this.WallMeetswall = WallMeetsWall;
    }
/*
tegner vores 4 linjer, så der bliver tegnet noget der ligner en firkant, som der kan kopieres.
tegner fra første kordinater til x2,y2.
 */
    public void draw(GraphicsContext gc) {
        if(upperWall) {
            gc.strokeLine(x, y, x + w, y); // top
        }
        if(rightWall) {
            gc.strokeLine(x + w, y, x + w, y + w);
        }// right
        if(bottomWall) {
            gc.strokeLine(x, y + w, x + w, y + w); // bottom
        }
        if(leftWall) {
            gc.strokeLine(x, y, x, y + w); // left
        }

    }


    public void visit(GraphicsContext gc) {
        gc.fillRect(x, y, 20, 20);
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isUpperWall() {
        return upperWall;
    }

    public void setUpperWall(boolean upperWall) {
        this.upperWall = upperWall;
    }

    public boolean isRightWall() {
        return rightWall;
    }

    public void setRightWall(boolean rightWall) {
        this.rightWall = rightWall;
    }

    public boolean isLeftWall() {
        return leftWall;
    }

    public void setLeftWall(boolean leftWall) {
        this.leftWall = leftWall;
    }

    public boolean isBottomWall() {
        return bottomWall;
    }

    public void setBottomWall(boolean bottomWall) {
        this.bottomWall = bottomWall;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public boolean isWallMeetswall() {
        return WallMeetswall;
    }

    public void setWallMeetswall(boolean wallMeetswall) {
        WallMeetswall = wallMeetswall;
    }

}
