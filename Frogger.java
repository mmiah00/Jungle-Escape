import java.util.*;

public class Frogger {
  private String[][] world;
  private int lives;
  private int currentRow;
  private int currentCol;

  public Frogger() {
    world = new String[10][8];
    for (int c = 0; c < 8; c++) {
      world[0][c] = "***";
      world[9][c] = "***";
    }
    for (int r = 1; r < 9; r++) {
      for (int c = 0; c < 8; c++) {
        world[r][c] = "   ";
      }
      addCars(r);
    }
    currentRow = 9;
    currentCol = 3;
  }

  public void addCars(int r) {
    Random randgen = new Random();
    for (int i = 0; i < 3; i++) {
      int randCol = randgen.nextInt(8);
      world[r][randCol] = "o-o";
    }
  }

  public void moveCarsLeft(int r) {
    for (int c = 0; c < 8; c++) {
      if (world[r][c].equals("o-o")) {
        if (c == 0) {
          world[r][7] = "o-o";
          world[r][c] = "   ";
        }
        else {
          world[r][c - 1] = "o-o";
          world[r][c] = "   ";
        }
      }
    }
  }



  public void moveCarsRight(int r) {
    for (int c = 0; c < 8; c++) {
      if (world[r][c].equals("o-o")) {
        if (c == 7) {
          world[r][0] = "o-o";
          world[r][c] = "   ";
        }
        else {
          world[r][c + 1] = "o-o";
          world[r][c] = "   ";
        }
      }
    }
  }


  //public void movePlayer(int horizontal, int vertical) {

  //}

  public String toString() {
    String s = "";
    for (int r = 0; r < 10; r++) {
      for (int c = 0; c < 8; c++) {
        s = s + world[r][c];
      }
      s = s + "\n";
    }
    return s;
  }

  public static void main (String args[]) {
    Frogger A = new Frogger();
    System.out.println(A.toString());
    A.moveCarsLeft(1);
    System.out.println(A.toString());
    A.moveCarsRight(8);
    System.out.println(A.toString());
  }
}
