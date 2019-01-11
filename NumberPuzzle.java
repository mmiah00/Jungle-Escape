import java.util.*;

public class NumberPuzzle {

	private String[][]grid;

	public NumberPuzzle() {
		grid = new String[4][4];
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				grid[r][c] = " ";
			}
		}
	addStartNums();
	}

	private void addStartNums() {
		Random randgen = new Random();
		int randRow = randgen.nextInt(4);
		int randCol = randgen.nextInt(4);
		grid[randRow][randCol] = "2"; //first '2' is placed randomly
		randRow = randgen.nextInt(4); //another random row and col
		randCol = randgen.nextInt(4);
		while (grid[randRow][randCol] != " ") { //if the row and col are the same as the first
			randRow = randgen.nextInt(4); //find another random row and col
			randCol = randgen.nextInt(4);
		}
		grid[randRow][randCol] = "2"; //place second '2'
	}

	private void inputNewNum() {
		Random randgen = new Random();
		int randRow = randgen.nextInt(4);
		int randCol = randgen.nextInt(4);
		while (grid[randRow][randCol] != " ") { //if the row and col are the same as the first
			randRow = randgen.nextInt(4); //find another random row and col
			randCol = randgen.nextInt(4);
		}
		grid[randRow][randCol] = "2"; //place second '2'
	}

	public String toString() {
		String s = "";
		for (int r = 0; r < 4; r++) {
			for (int c = 0; c < 4; c++) {
				s = s + grid[r][c];
			}
			s = s + "\n";
		}
		return s;
	}

	public static void main(String[] args) {
		NumberPuzzle A = new NumberPuzzle();
		System.out.println(A.toString());
	}
/*
		for (int i = 0; i < 5; i++) {
			A.moveLeft();
			System.out.println(A.toString());
		}
	*/
}
