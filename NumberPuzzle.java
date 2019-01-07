/*
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
*/
import java.util.*;

public class NumberPuzzle {

	private char[][]grid;

	public NumberPuzzle() {
		grid = new char[9][13];
		for (int r = 0; r < 9; r++) {
			int rows = r % 2;
			for (int c = 0; c < 13; c++) {
				int cols = c % 3;
				if (c == 0 || c == 12 || (rows == 1 && cols == 0)) {
					grid[r][c] = '|';
				}
				else if (r == 0 || r == 8 || (rows == 0 && cols != 0)) {
					grid[r][c] = '-';
				}
				else if (rows == 0 && cols == 0) {
					grid[r][c] = '+';
				}
				//else if (rows = 1 && (c == 1||c == 4||c == 7||c == 10)) {
				//	grid[r][c] = 'f'
				//}
				else {
					grid[r][c] = ' ';
				}
				grid[1][8] = '2';
				grid[3][8] = '2';
				grid[5][8] = '2';
				grid[7][8] = '2';
			}
		}
		addStartNums();
	}

	private void addStartNums() {
		Random randgen = new Random();
		int randRow = randgen.nextInt(4) * 2 + 1;
		int randCol = randgen.nextInt(4) * 3 + 2;
		grid[randRow][randCol] = '2';
		//grid[randRow][randCol-1] = 't';
		randRow = randgen.nextInt(4) * 2 + 1;
		randCol = randgen.nextInt(4) * 3 + 2;
		while (grid[randRow][randCol] != ' ') {
			randRow = randgen.nextInt(4) * 2 + 1;
			randCol = randgen.nextInt(4) * 3 + 2;
		}
		grid[randRow][randCol] = '2';
	}

	public void moveLeft() {
		for (int r = 1; r < 8; r = r + 2) {
			for (int c = 5; c < 12; c = c + 3) {
				int orig = c;
				if (grid[r][c-3] != ' ' && grid[r][c-3] == grid[r][c]) {
					int newNum = (grid[r][c] - '0') * 2;
					grid[r][c-3] = (char)(newNum + '0');
					grid[r][c] = ' ';
					c = c -3;
				}
				while (c != 2 && grid[r][c-3] == ' ') {
					grid[r][c-3] = grid[r][c];
					grid [r][c] = ' ';
					c = c-3;
					if (c != 2 && grid[r][c-3] != ' ' && grid[r][c-3] == grid[r][c]) {
						int newNum = (grid[r][c] - '0') * 2;
						grid[r][c-3] = (char)(newNum + '0');
						grid[r][c] = ' ';
						c = c -3;
					}
				}
				c = orig;
			}
		}
	}

  public void moveRight() {
		for (int r = 1; r < 8; r = r + 2) {
			for (int c = 8; c > 1; c = c - 3) {
				int orig = c;
				if (grid[r][c+3] != ' ' && grid[r][c+3] == grid[r][c]) {
					int newNum = (grid[r][c] - '0') * 2;
					grid[r][c+3] = (char)(newNum + '0');
					grid[r][c] = ' ';
					c = c+3;
				}
				while (c != 11 && grid[r][c+3] == ' ') {
					grid[r][c+3] = grid[r][c];
					grid [r][c] = ' ';
					c = c+3;
					if (c != 11 && grid[r][c+3] != ' ' && grid[r][c+3] == grid[r][c]) {
						int newNum = (grid[r][c] - '0') * 2;
						grid[r][c+3] = (char)(newNum + '0');
						grid[r][c] = ' ';
						c = c+3;
					}
				}
				c = orig;
			}
		}
	}

  public void moveUp() {
		for (int c = 2; c < 12; c = c + 3) {
			for (int r = 3; r < 8; r = r + 2) {
				int orig = r;
				if (grid[r-2][c] != ' ' && grid[r-2][c] == grid[r][c]) {
					int newNum = (grid[r][c] - '0') * 2;
					grid[r-2][c] = (char)(newNum + '0');
					grid[r][c] = ' ';
          r = r-2;
				}
				while (r != 1 && grid[r-2][c] == ' ') {
					grid[r-2][c] = grid[r][c];
					grid [r][c] = ' ';
					r = r-2;
					if (r != 1 && grid[r-2][c] != ' ' && grid[r-2][c] == grid[r][c]) {
						int newNum = (grid[r][c] - '0') * 2;
						grid[r-2][c] = (char)(newNum + '0');
						grid[r][c] = ' ';
						r = r-2;
					}
				}
				r = orig;
			}
		}
	}

	public String toString() {
		String s = "";
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 13; c++) {
				s = s + grid[r][c];
			}
			s = s + "\n";
		}
		return s;
	}

	public static void main(String[] args) {
		NumberPuzzle A = new NumberPuzzle();
		System.out.println(A.toString());
		A.moveUp();
		System.out.println(A.toString());
	}

}
