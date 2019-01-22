
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

import java.util.*;

public class Frogger {
  private String[][] world;
  private int currentRow; //ycor of player
  private int currentCol; //xcor of player

  public Frogger() {
    world = new String[10][8];
    for (int c = 0; c < 8; c++) {
      world[0][c] = "***"; //end
      world[9][c] = "***"; //start
    }
    for (int r = 1; r < 9; r++) {
      for (int c = 0; c < 8; c++) {
        world[r][c] = "   ";
      }
      if (r != 2 && r!= 6) {
        addCars(r); //adds cars except of 2 rows (to make it do-able)
      }
    }
    world[9][4] = "o**"; //player
    currentRow = 9; //player location
    currentCol = 4;
  }

  public void addCars(int r) { //adds 3 cars to random location in particular row
    Random randgen = new Random();
    for (int i = 0; i < 3; i++) {
      int randCol = randgen.nextInt(8);
      world[r][randCol] = "o-o";
    }
  }

  public void moveCarsLeft(int r) { //moves cars to left
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

  public void moveCarsRight(int r) { //moves cars to right
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

  public void movePlayer(int horizontal, int vertical) { //moves player
    int newRow = currentRow - vertical;
    int newCol = currentCol + horizontal;
    if (horizontal == 0 && (newRow == 0 || newRow == 9)) { //special cases that involve start and end lines
      world[newRow][newCol] = "o**";
      world[currentRow][currentCol] = "   ";
    }
    else if (vertical == 0 && (newRow == 0 || newRow == 9)) {
      world[newRow][newCol] = "o**";
      world[currentRow][currentCol] = "***";
    }
    else if (currentRow == 9) {
      world[newRow][newCol] = "o  ";
      world[currentRow][currentCol] = "***";
    }
    else {
      world[newRow][newCol] = "o  ";
      world[currentRow][currentCol] = "   ";
    }
    currentRow = newRow; //change player location
    currentCol = newCol;
  }

  public int isCrash(int horizontal, int vertical) {
    int newRow = currentRow - vertical;
    int newCol = currentCol + horizontal;
    if (newRow == -1 || newRow == 10 || newCol == -1 || newCol == 8) { //out of bounds
      return 0;
    }
    else if (world[newRow][newCol].equals("o-o")) { //crashes
      return -1;
    }
    else { //empty space (can move forward)
      return 1;
    }
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

  public boolean isComplete() { //reaches end
    return (currentRow == 0);
  }

  public boolean isRunOver() { //while player is on spot, does a car move into their position
    return (world[currentRow][currentCol].equals("o-o"));
  }

  public static void putString(int r, int c,Terminal t, String s){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public void moveCars(int counter) { //move cars at particular speed
    if (counter % 1300 == 0) {
      moveCarsRight(1);
      moveCarsLeft(3);
      moveCarsRight(4);
      moveCarsLeft(5);
      moveCarsRight(7);
      moveCarsRight(8);
    }
  }

  public void restart() { //goes back to start line
    world[9][4] = "o**";
    currentRow = 9;
    currentCol = 4;
  }

  public static int [] playFrogger(Terminal terminal, int beginMin, int beginSec) {
    Frogger A = new Frogger();
    putString(0, 1, terminal, A.toString());
    putString(0, 12, terminal, "| Run across as fast as you can |");
    putString(0, 13, terminal, "|  without being hit by a car.  |");
    putString(0, 14, terminal, "|  If you're hit, you have to   |");
    putString(0, 15, terminal, "|   restart on the other side.  |");

    int counter = 0;
    boolean gameNotDone = true;
    int [] returns = new int [3]; //return 3 ints
		long lastTime =  System.currentTimeMillis();
    long currentTime = lastTime;
    long timer = 0;
    boolean firstPass = true;

    while (gameNotDone) { //game is not done
      gameNotDone = !(A.isComplete());
      putString(0,1, terminal, A.toString());

      A.moveCars(counter); //move cars
      counter++;
      if (A.isRunOver()) { //if player is runover restart
        A.restart();
      }
      else {
        Key key = terminal.readInput();
        if (key != null){
          if (key.getKind() == Key.Kind.Escape) { //ecits game
            terminal.exitPrivateMode();
            gameNotDone = false;
            returns [0] = -1;
            return returns;
          }
          if (key.getKind() == Key.Kind.ArrowUp) {
            if (A.isCrash(0, 1) == -1) { //checks of crash
              A.restart();
            }
            else if (A.isCrash(0, 1) == 1) { //if not move player
              A.movePlayer(0, 1);
            }
          }
          if (key.getKind() == Key.Kind.ArrowDown) {
            if (A.isCrash(0, -1) == -1) { //checks if crash
              A.restart();
            }
            else if (A.isCrash(0, -1) == 1) { //if not move player
              A.movePlayer(0, -1);
            }
          }
          if (key.getKind() == Key.Kind.ArrowLeft) {
            if (A.isCrash(-1, 0) == -1) { //checks if crash
              A.restart();
            }
            else if (A.isCrash(-1, 0) == 1) {//if not move player
              A.movePlayer(-1, 0);
            }
          }
          if (key.getKind() == Key.Kind.ArrowRight) {
            if (A.isCrash(1, 0) == -1) {//checks if crash
              A.restart();
            }
            else if (A.isCrash(1, 0) == 1) {//if not move player
              A.movePlayer(1, 0);
            }
          }
        }
      }

      lastTime = currentTime;
      currentTime = System.currentTimeMillis();
      timer += (currentTime -lastTime); //changes time
      int minLeft = beginMin - (int)(timer/60000); //changes min left
      String minPassed = String.format("%02d", minLeft);
			int secLeft; //changes sec left
      if ((int)(timer%60000/1000) > beginSec) {
        firstPass = false;
      }
      if (firstPass) {
        secLeft = beginSec -(int)(timer%60000/1000);
      }
      else {
        secLeft = 60 - (int)(timer%60000/1000);
      }
      String secPassed = String.format("%02d", secLeft); //special case
      if (secLeft == 60) {
        minLeft = beginMin - (int)(timer/60000);
        minPassed = String.format("%02d", minLeft);
        secPassed = "00";
      }
      putString(0,0,terminal, "Time Left: "+ minPassed + ":" + secPassed);
      returns[1] = minLeft; //passes on min left at end
      returns[2] = secLeft; //passes on sec left at end

      if (minLeft == 0 && secLeft == 1) { //if time runs out
        gameNotDone = false;
        returns [0] = -2; //fail message
        return returns;
      }
    }
    terminal.clearScreen();
    returns [0] = 7; //next mode
    return returns;
  }
}
