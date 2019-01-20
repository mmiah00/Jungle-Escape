//API : http://mabe02.github.io/lanterna/apidocs/2.1/
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

public class SecondPuzzle {

	private String[][]grid;
  private int row;
  private int col;

	public SecondPuzzle() {
		grid = new String[3][3];
		row = 2;
    col = 2;
    fillGrid();
		scramble(); //adds two "2" to the grid
	}

  public void fillGrid() {
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        grid[r][c] = addSpaces("" + (3*r+c+1)) + (3*r+c+1);
      }
    }
    grid[2][2] = "  ";
  }

  public void scramble() {
    Random randgen = new Random();
    for (int i = 0; i < 40; i++) {
      int direction = randgen.nextInt(4);
      if (direction == 0) {
        moveLeft();
      }
      else if (direction == 1) {
        moveRight();
      }
      else if (direction == 2) {
        moveUp();
      }
      else if (direction == 3) {
        moveDown();
      }
    }
  }

	public String addSpaces(String s) { //adds spaces to account for the 4 spaces that should be in each position
		String spaces = "";
		int length = 2 - s.length();
		for (int i = 0; i < length; i++) {
			spaces = spaces + " ";
		}
		return spaces;
	}

	public void moveLeft() {
		if (col != 2) {
      String newNum = grid[row][col+1].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row][col+1] = "  ";
			col++;
    }
	}

	public void moveRight() {
		if (col != 0) {
      String newNum = grid[row][col-1].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row][col-1] = "  ";
			col--;
    }
	}

	public void moveUp() {
    if (row != 2) {
      String newNum = grid[row+1][col].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row+1][col] = "  ";
			row++;
    }
	}

  public void moveDown() {
    if (row != 0) {
      String newNum = grid[row-1][col].trim();
      grid[row][col] = addSpaces(newNum) + newNum;
      grid[row-1][col] = "  ";
			row--;
    }
	}

	public boolean isComplete() { //checks if there are anymore possible moves
    boolean complete = true;
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        String correct = addSpaces("" + (3*r+c+1)) + (3*r+c+1);
				if (!(r == 3 && c == 3)) {
					if (!(grid[r][c].equals(correct))) {
	          return false;
	        }
				}
      }
    }
    return complete;
  }

	public String toString() { //prints 2-d array grid with a grid around it to mimic game
		String s = "|-----------|\n";
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				if (c == 0) {
					s = s + "|" + grid[r][c] + "|";
				}
				else {
					s = s + grid[r][c] + "|";
				}
			}
			if (r == 2) {
				s = s + "\n|-----------|";
			}
			else {
				s = s + "\n|--+--+--+--|\n";
			}
		}
		return s;
	}

	public static void putString(int r, int c,Terminal t, String s){ //displays String on terminal
		t.moveCursor(r,c);
		for(int i = 0; i < s.length();i++){
			t.putCharacter(s.charAt(i));
		}
	}

	public static int playFifteen(Terminal terminal) {
		SecondPuzzle A = new SecondPuzzle();
		putString(0, 0, terminal, A.toString());
		putString(0, 11, terminal, "|Use the arrow keys |");
		putString(0, 12, terminal, "|to combine numbers |");
		putString(0, 13, terminal, "|  and reach 2048   |");

		boolean gameNotDone = true;
		while (gameNotDone) {
			gameNotDone = !(A.isComplete());
			putString(0, 0, terminal, A.toString());
			Key key = terminal.readInput();
			if (key != null){
				if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          gameNotDone = false;
					return -1;
        }
				if (key.getKind() == Key.Kind.ArrowLeft) {
					A.moveLeft();
				}
				if (key.getKind() == Key.Kind.ArrowRight) {
					A.moveRight();
				}
				if (key.getKind() == Key.Kind.ArrowUp) {
					A.moveUp();
				}
				if (key.getKind() == Key.Kind.ArrowDown) {
					A.moveDown();
				}
			}
		}
		terminal.clearScreen();
		return 5;
	}

}
