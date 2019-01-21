
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
      world[0][c] = "***";
      world[9][c] = "***";
    }
    for (int r = 1; r < 9; r++) {
      for (int c = 0; c < 8; c++) {
        world[r][c] = "   ";
      }
      addCars(r);
    }
    world[9][4] = "o**";
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
    int newRow = currentRow - vertical;
    int newCol = currentCol + horizontal;
    if (horizontal == 0 && (newRow == 0 || newRow == 9)) {
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
    currentRow = newRow;
    currentCol = newCol;
  }

  public int isCrash(int horizontal, int vertical) {
    int newRow = currentRow - vertical;
    int newCol = currentCol + horizontal;
    if (newRow != -1 && newRow != 8 && newCol != -1 && newCol != 8) {
      return 0;
    }
    else if (world[newRow][newCol].equals("o-o")) {
      return -1;
    }
    else {
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

  public boolean isComplete() {
    return (currentRow == 0);
  }

  public boolean isRunOver() {
    return (world[currentRow][currentCol].equals("o-o"));
  }

  public static void putString(int r, int c,Terminal t, String s){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public void moveCars(int counter) {
    if (counter % 2000 == 0) {
      moveCarsLeft(1);
      moveCarsLeft(3);
      moveCarsLeft(4);
      moveCarsLeft(6);
      moveCarsRight(2);
      moveCarsRight(5);
      moveCarsRight(7);
      moveCarsRight(8);
    }
  }

  public void restart() {
    world[9][4] = "o**";
    currentRow = 9;
    currentCol = 4;
  }

  public static int [] playFrogger(Terminal terminal, int beginMin, int beginSec) {
    Frogger A = new Frogger();
    putString(0, 1, terminal, A.toString());

    int counter = 0;
    boolean gameNotDone = true;
    int [] returns = new int [3];
		long lastTime =  System.currentTimeMillis();
    long currentTime = lastTime;
    long timer = 0;
    boolean firstPass = true;

    while (gameNotDone) {
      gameNotDone = !(A.isComplete());
      putString(0,1, terminal, A.toString());

      A.moveCars(counter);
      counter++;
      if (A.isRunOver()) {
        A.restart();
      }
      else {
        Key key = terminal.readInput();
        if (key != null){
          if (key.getKind() == Key.Kind.Escape) {
            terminal.exitPrivateMode();
            gameNotDone = false;
            returns [0] = -1;
            return returns;
          }
          if (key.getKind() == Key.Kind.ArrowUp) {
            if (A.isCrash(0, 1) == -1) {
              A.restart();
            }
            else {
              A.movePlayer(0, 1);
            }
          }
          if (key.getKind() == Key.Kind.ArrowDown) {
            if (A.isCrash(0, -1) == -1) {
              A.restart();
            }
            else {
              A.movePlayer(0, -1);
            }
          }
          if (key.getKind() == Key.Kind.ArrowLeft) {
            if (A.isCrash(-1, 0) == -1) {
              A.restart();
            }
            else {
              A.movePlayer(-1, 0);
            }
          }
          if (key.getKind() == Key.Kind.ArrowRight) {
            if (A.isCrash(1, 0) == -1) {
              A.restart();
            }
            else {
              A.movePlayer(1, 0);
            }
          }
        }
      }

      lastTime = currentTime;
      currentTime = System.currentTimeMillis();
      timer += (currentTime -lastTime);
      int minLeft = beginMin - (int)(timer/60000);
      String minPassed = String.format("%02d", minLeft);
			int secLeft;
      if ((int)(timer%60000/1000) > beginSec) {
        firstPass = false;
      }
      if (firstPass) {
        secLeft = beginSec -(int)(timer%60000/1000);
      }
      else {
        secLeft = 60 - (int)(timer%60000/1000);
      }
      String secPassed = String.format("%02d", secLeft);
      if (secLeft == 60) {
        minLeft = beginMin - (int)(timer/60000);
        minPassed = String.format("%02d", minLeft);
        secPassed = "00";
      }
      putString(0,0,terminal, "Time Left: "+ minPassed + ":" + secPassed);
      returns[1] = minLeft;
      returns[2] = secLeft;

      if (minLeft == 0 && secLeft == 1) {
        gameNotDone = false;
        returns [0] = -1;
        return returns;
      }
    }
    terminal.clearScreen();
    returns [0] = 7;
    return returns;
  }
}

/*
    while (A.getLives() > -1) {
      if (A.isComplete()) {
        terminal.exitPrivateMode();
        A.setLives(-5);
      }
      else {
        A.moveCars(counter);
        counter++;

        putString(0,0, terminal, A.toString());
        if (A.isRunOver()) {
          if (A.getLives() == 0) {
            terminal.exitPrivateMode();
            A.setLives(-5);
            System.out.println("A car hit you! You have no more lives. Press ESC and restart game to try again.");
          }
          else {
            A.setLives(-1);
            System.out.println("A car hit you! You have " + A.getLives() + " more lives.");
          }
        }
        if (A.getLives() > -1) {
          Key key = terminal.readInput();
          if (key != null){
            if (key.getKind() == Key.Kind.Escape) {
              terminal.exitPrivateMode();
              A.setLives(-5);
            }
            if (key.getKind() == Key.Kind.ArrowUp) {
              if (A.isCrash(0, 1) == -1) {
                A.setLives(-1);
                if (A.getLives() > -1) {
                  A.movePlayer(0, 1);
                }
              }
              else {
                A.movePlayer(0, 1);
              }
            }

            if (key.getKind() == Key.Kind.ArrowDown) {
              if (A.isCrash(0, -1) == -1) {
                A.setLives(-1);
                if (A.getLives() > -1) {
                  A.movePlayer(0, -1);
                }
              }
              else {
                A.movePlayer(0, -1);
              }
            }

            if (key.getKind() == Key.Kind.ArrowLeft) {
              if (A.isCrash(-1, 0) == -1) {
                A.setLives(-1);
                if (A.getLives() > -1) {
                  A.movePlayer(-1, 0);
                }
              }
              else {
                A.movePlayer(-1, 0);
              }
            }

            if (key.getKind() == Key.Kind.ArrowRight) {
              if (A.isCrash(1, 0) == -1) {
                A.setLives(-1);
                if (A.getLives() > -1) {
                  A.movePlayer(1, 0);
                }
              }
              else {
                A.movePlayer(1, 0);
              }
            }

          }
        }
      }
    }
    terminal.exitPrivateMode();
    return 7;
  }

}
*/
