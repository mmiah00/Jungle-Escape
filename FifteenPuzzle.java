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


public class FifteenPuzzle {
  private static Tile[] nums;
  private static boolean done;

  public FifteenPuzzle () {
    done = false;
    nums = new Tile [16];

    nums[0] = new Tile ('1', '0', 2, 2);
    nums[1] = new Tile ('0', '2', 13, 2);
    nums[2] = new Tile ('1', '3', 24, 2);
    nums[3] = new Tile ('0', '4', 35, 2);

    nums[4] = new Tile ('1', '2', 2, 7);
    nums[5] = new Tile ('0', '9', 13, 7);
    nums[6] = new Tile ('0', '7', 24, 7);
    nums[7] = new Tile ('1', '5', 35, 7);

    nums[8] = new Tile ('0', '1', 2, 12);
    nums[9] = new Tile ('1', '1', 13, 12);
    nums[10] = new Tile ('1', '4', 24, 12);
    nums[11] = new Tile ('0', '6', 35, 12);

    nums[12] = new Tile ('0', '3', 2, 17);
    nums[13] = new Tile ('0', '5', 13, 17);
    nums[14] = new Tile ('0', '8', 24, 17);
    nums[15] = new Tile (' ', ' ', 35, 17);
  }

  public void flip (Tile one, Tile another) {
    nums[getIndex (one)] = another;
    nums[getIndex (another)] = one;
  }

  private int getIndex (Tile aTile) {
    for (int x = 0; x < nums.length; x ++) {
      if (nums[x] == aTile) {
        return x;
      }
    }
    return -1;
  }


  public static void putString (Tile aTile, Terminal t) {
    int x = aTile.xcor ();
    int y = aTile.ycor ();
    String s = aTile.toString ();
    for (int i = 0; i < s.length (); i ++) {
      t.moveCursor (x, y);
      t.putCharacter(s.charAt (i));
      if (s.charAt (i) != '\n') {
        x += 1;
      }
      else {
        x = aTile.xcor ();
        y += 1;
      }
    }
  }

  public static void putString(int r, int c,Terminal t, String s){
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}


  public void complete () {
    String actual = "0102030405060708091011131415  "; //the order it should be
    String ans = "";
    for (int i = 0; i < nums.length; i ++) {
      ans += nums[i].tens () + nums[i].ones (); //going through each tile to find their nums and add them to the string
    }
    done = (actual == ans); //update done variable, if done that means its complete
  }


  public static void main(String[] args){
    FifteenPuzzle board = new FifteenPuzzle ();

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize size = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    long tStart = System.currentTimeMillis();
    long lastSecond = 0;

    //terminal.moveCursor(x,y);
    if (done) {
      putString (1,2,terminal, "You Finished!");
    }

    while(!done){
      Tile space = board.nums[15];

			for (int i = 0; i < board.nums.length; i ++) {
        putString (board.nums[i], terminal);
      }
			//terminal.putCharacter(' ');
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			terminal.applySGR(Terminal.SGR.RESET_ALL);

      Key key = terminal.readInput();

      if (key != null){
        if (key.getKind() == Key.Kind.Escape) { //to exit out of terminal
          terminal.exitPrivateMode();
          done = true;
        }

        if (key.getKind() == Key.Kind.ArrowLeft) {
          int spacex = board.getIndex (space);
          if (spacex != 0 && spacex != 4 && spacex != 8 && spacex != 12) { //checking if within bounds
            //switch so the space moves to the left
            nums [spacex] = nums[spacex - 1]; //puts the tile to the left in its position
            nums [spacex - 1] = space; //puts the space tile in the position to the left
            space = nums [spacex - 1]; //stores the new empty Tile in the space variable
          }
        }

        if (key.getKind() == Key.Kind.ArrowRight) {
          int spacex = board.getIndex (space);
          if (spacex != 3 && spacex != 7 && spacex != 11 && spacex != 15) { //checking if within bounds
            //switch so the space moves to the left
            nums [spacex] = nums[spacex + 1]; //puts the tile to the left in its position
            nums [spacex + 1] = space; //puts the space tile in the position to the left
            space = nums [spacex + 1]; //stores the new empty Tile in the space variable
          }
        }

        if (key.getKind() == Key.Kind.ArrowUp) {
          int spacex = board.getIndex (space);
          if (spacex != 0 && spacex != 1 && spacex != 2 && spacex != 3) { //checking if within bounds
            //switch so the space moves to the left
            nums [spacex] = nums[spacex - 4]; //puts the tile to the left in its position
            nums [spacex - 4] = space; //puts the space tile in the position to the left
            space = nums [spacex - 4]; //stores the new empty Tile in the space variable
          }
        }

        if (key.getKind() == Key.Kind.ArrowDown) {
          int spacex = board.getIndex (space);
          if (spacex != 12 && spacex != 13 && spacex != 14 && spacex != 15) { //checking if within bounds
            //switch so the space moves to the left
            nums [spacex] = nums[spacex + 4]; //puts the tile to the left in its position
            nums [spacex + 4] = space; //puts the space tile in the position to the left
            space = nums [spacex + 4]; //stores the new empty Tile in the space variable
          }
        }
      }

      board.complete (); //check to see if the board is in order , updates the done variable
    }
  }
}

//javac -cp "lanterna.jar;." FifteenPuzzle.java
