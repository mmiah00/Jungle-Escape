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
  private int lives;
  private int currentRow; //ycor of player
  private int currentCol; //xcor of player
  //u+1F697 car unicode

  public Frogger() {
    world = new String [10][8];
    for (int r = 1; r < 9; r++) {
      for (int c = 0; c < 8; c++) {
        world[r][c] = "     \n     ";
      }
      addCars(r);
    }
    lives = 3;
    currentRow = 9;
    currentCol = 3;
  }

  public void addCars(int r) {
    Random randgen = new Random();
    for (int i = 0; i < 4; i++) {
      int randCol = Math.abs (randgen.nextInt () % 8);
      while (!(world[r][randCol].equals("     \n     "))) {
        randCol = randgen.nextInt(8);
      }
      world[r][randCol] = "[ = ]\n o o ";
    }
  }

  public void moveCarsLeft(int r) {
    for (int c = 0; c < 8; c++) {
      if (world[r][c].equals("[ = ]\n o o ")) {
        world[r][c-1] = "[ = ]\n o o ";
        world[r][c] = "     \n     ";
      }
    }
  }

  public void moveCarsRight(int r) {
    for (int c = 0; c < 8; c++) {
      if (world[r][c].equals("[ = ]\n o o ")) {
        world[r][c+1] = "[ = ]\n o o ";
        world[r][c] = "     \n     ";
      }
    }
  }

  public void movePlayer(int horizontal, int vertical) {
    currentCol += horizontal;
    currentRow += vertical;
  }

  public boolean crash () {
    if (world [currentCol + 1] != null) {
      return true;
    }
    return false;
  }

  public String toString() {
    String s = "";
    for (int r = 0; r < 10; r++) {
      for (int c = 0; c < 8; c++) {
        s = s + world[r][c];
      }
    }
    return s;
  }

  public static void putString(int r, int c,Terminal t, String s){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public static void main (String args[]) {
    Frogger world = new Frogger ();

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize size = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    putString (0,0, terminal, world.toString ()); 

    Key key = terminal.readInput();
    if (key != null){
      if (key.getKind() == Key.Kind.Escape) {
        terminal.exitPrivateMode();
        //return 5;
        //gameNotDone = false;
      }
      if (key.getKind() == Key.Kind.ArrowLeft) {
        world.movePlayer (-1,0);
      }
      if (key.getKind() == Key.Kind.ArrowRight) {
        world.movePlayer (1,0);
      }
      if (key.getKind() == Key.Kind.ArrowUp) {
        world.movePlayer (0,1);
      }
      if (key.getKind() == Key.Kind.ArrowDown) {
        world.movePlayer (0,-1);
      }
    }
  }
}
