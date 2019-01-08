import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;
import java.io.IOException;
import java.awt.Color;


public class FifteenPuzzle {
  private static Tile[] nums;
  private static boolean done;

  public FifteenPuzzle () {
    done = false;
    nums = new Tile [16];

    nums[0] = new Tile ('1', '0', 10, 10);
    nums[1] = new Tile ('0', '2', 21, 10);
    nums[2] = new Tile ('1', '3', 32, 10);
    nums[3] = new Tile ('0', '4', 43, 10);

    nums[4] = new Tile ('1', '2', 10, 15);
    nums[5] = new Tile ('0', '9', 21, 15);
    nums[6] = new Tile ('0', '7', 32, 15);
    nums[7] = new Tile ('1', '5', 43, 15);

    nums[8] = new Tile ('0', '1', 10, 20);
    nums[9] = new Tile ('1', '1', 21, 20);
    nums[10] = new Tile ('1', '4', 32, 20);
    nums[11] = new Tile ('0', '6', 43, 20);

    nums[12] = new Tile ('0', '3', 10, 25);
    nums[13] = new Tile ('0', '5', 21, 25);
    nums[14] = new Tile ('0', '8', 32, 25);
    nums[15] = new Tile (' ', ' ', 43, 25);
  }

  /*
  public static void putString (Tile aTile, Terminal t) {
    t.moveCursor (aTile.xcor(), aTile.ycor());
    String s = aTile.toString ();
    for (int i = 0; i < s.length (); i ++) {
      t.putCharacter(s.charAt (i));
    }
  }
  */

  public static void putString(Tile aTile, Screen screen) {
    String str = aTile.toString ();
    int x = aTile.xcor ();
    int y = aTile.ycor ();
		for (int i = 0; i < str.length(); ++i) {
			screen.setCharacter(x+i, y, new TextCharacter(str.charAt(i)));
		}
	}

  public void complete () {
    String actual = "0102030405060708091011131415  ";
    String ans = "";
    for (int i = 0; i < nums.length; i ++) {
      ans += nums[i].tens () + nums[i].ones ();
    }
    done = (actual == ans);
  }

  public static void main(String[] args) throws IOException {
    FifteenPuzzle board = new FifteenPuzzle ();

    int x = board.nums[15].xcor();
    int y = board.nums[15].ycor();

     Screen screen = new DefaultTerminalFactory().createScreen();
     screen.startScreen();

     long tStart = System.currentTimeMillis();
     long lastSecond = 0;

   	 while(!done){
       for (int i = 0; i < board.nums.length; i ++) {
         putString (board.nums[i], screen);
       }

       /*KeyStroke key = screen.pollInput();

   		 if (key != null)
   		 {

   			 if (key.getKind() == Key.Kind.Escape) {

   				 terminal.exitPrivateMode();
   			 }

   			 if (key.getKind() == Key.Kind.ArrowLeft) {
   				 terminal.moveCursor(x,y);
   				 terminal.putCharacter(' ');
   				 x--;
   			 }

   			 if (key.getKind() == Key.Kind.ArrowRight) {
   				 terminal.moveCursor(x,y);
   				 terminal.putCharacter(' ');
   				 x++;
   			 }

   			 if (key.getKind() == Key.Kind.ArrowUp) {
   				 terminal.moveCursor(x,y);
   				 terminal.putCharacter(' ');
   				 y--;
   			 }

   			 if (key.getKind() == Key.Kind.ArrowDown) {
   				 terminal.moveCursor(x,y);
   				 terminal.putCharacter(' ');
   				 y++;
   			 }
   			 //space moves it diagonally
   			 if (key.getCharacter() == ' ') {
   				 terminal.moveCursor(x,y);
   				 terminal.putCharacter(' ');
   				 y++;
   				 x++;
   			 }
   			 putString(1,4,terminal,"["+key.getCharacter() +"]");
   			 putString(1,1,terminal,key+"    	");//to clear leftover letters pad withspaces
   		 }

   		 //DO EVEN WHEN NO KEY PRESSED:
   		 long tEnd = System.currentTimeMillis();
   		 long millis = tEnd - tStart;
   		 if(millis/1000 > lastSecond){
   			 lastSecond = millis / 1000;
   			 //one second has passed.
   			 putString(1,3,terminal,"Seconds since start of program: "+lastSecond);
   		 }*/
   	 }

   }
}
