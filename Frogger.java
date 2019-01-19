//API : http://mabe02.github.io/lanterna/apidocs/2.1/
import com.googlecode.lanterna.terminal.Terminal.SGR;
import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.LanternaException;
import com.googlecode.lanterna.input.CharacterPattern;
import com.googlecode.lanterna.input.InputDecoder;
import com.googlecode.lanterna.input.InputProvider;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.KeyMappingProfile;
<<<<<<< HEAD

=======
>>>>>>> testing
import java.util.*;

public class Frogger {
  private String[][] world;
  private int lives;
  private int currentRow; //ycor of player
  private int currentCol; //xcor of player
  //u+1F697 car unicode

  public Frogger() {
<<<<<<< HEAD
    world = new String [10][8];
=======
    world = new String[10][8];
    for (int c = 0; c < 8; c++) {
      world[0][c] = "***";
      world[9][c] = "***";
    }
>>>>>>> testing
    for (int r = 1; r < 9; r++) {
      for (int c = 0; c < 8; c++) {
        world[r][c] = "   ";
      }
      addCars(r);
    }
<<<<<<< HEAD
    lives = 3;
=======
    world[9][4] = "o**";
>>>>>>> testing
    currentRow = 9;
    currentCol = 4;
  }

  public void addCars(int r) {
    Random randgen = new Random();
    for (int i = 0; i < 3; i++) {
      int randCol = randgen.nextInt(8);
      world[r][randCol] = "o-o";
    }
  }

  public void moveCarsLeft(int r) {
    int max = 8;
    for (int c = 0; c < max; c++) {
      if (world[r][c].equals("o-o")) {
        if (c == 0) {
          world[r][7] = "o-o";
          world[r][c] = "   ";
          max--;
        }
        else {
          world[r][c - 1] = "o-o";
          world[r][c] = "   ";
        }
      }
    }
  }

  public void moveCarsRight(int r) {
    int min = -1;
    for (int c = 7; c > min; c--) {
      if (world[r][c].equals("o-o")) {
        if (c == 7) {
          world[r][0] = "o-o";
          world[r][c] = "   ";
          min++;
        }
        else {
          world[r][c + 1] = "o-o";
          world[r][c] = "   ";
        }
      }
    }
  }

  public void movePlayer(int horizontal, int vertical) {
    int newRow = currentRow + vertical;
    int newCol = currentCol + horizontal;
    if (newRow == 0) {
      world[newRow][newCol] = "o**";
    }
    else {
      world[newRow][newCol] = "o  ";
    }
    currentRow = currentRow + vertical;
    currentCol = currentRow + horizontal;
  }

  public boolean isCrash(int horizontal, int vertical) {
    int newRow = currentRow + vertical;
    int newCol = currentCol + horizontal;
    return world[newRow][newCol].equals("o-o");
  }

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

  public boolean isComplete() {
    return (currentRow == 0);
  }

  public static void putString(int r, int c,Terminal t, String s){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public static void main(String[] args) {
    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize size = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    Frogger A = new Frogger();
    putString(0, 0, terminal, A.toString());

    boolean gameNotDone = true;
    while (gameNotDone) {
      A.moveCarsLeft(1);
      A.moveCarsLeft(3);
      A.moveCarsLeft(4);
      A.moveCarsLeft(6);
      A.moveCarsRight(2);
      A.moveCarsRight(5);
      A.moveCarsRight(7);
      A.moveCarsRight(8);

      gameNotDone = !(A.isComplete());
      putString(0, 0, terminal, A.toString());
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          gameNotDone = false;
        }
        if (key.getKind() == Key.Kind.ArrowUp) {
          if (A.isCrash(0, 1)) {
            terminal.clearScreen();
            System.out.println("You died");
          }
          else {
            A.movePlayer(-1, 0);
          }
        }
      }
    }
    terminal.exitPrivateMode();
  }
/*
  public static void main (String args[]) {
    Frogger A = new Frogger();
    System.out.println(A.toString());
    if (!(A.movePlayer(1))) {
      System.out.println "CRASHED"
    }
    System.out.println(A.toString());
  }
  */
}
