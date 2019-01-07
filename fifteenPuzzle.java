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


public class FifteenPuzzle {
  Tile[] nums;

  public FifteenPuzzle () {
    nums = new Tile [16];
    int xcor = 10;
    int ycor = 10;
    nums[8] = new Tile ('0', '1', xcor, ycor);
    xcor += 5;
    nums[1] = new Tile ('0', '2', xcor, ycor);
    xcor += 5;
    nums[12] = new Tile ('0', '3', xcor, ycor);
    xcor += 5;
    nums[3] = new Tile ('0', '4', xcor, ycor);
    xcor += 5;
    nums[13] = new Tile ('0', '5', xcor, ycor);
    xcor = 10;
    ycor += 5;
    nums[11] = new Tile ('0', '6', xcor, ycor);
    xcor += 5;
    nums[6] = new Tile ('0', '7', xcor, ycor);
    xcor += 5;
    nums[14] = new Tile ('0', '8', xcor, ycor);
    xcor += 5;
    nums[5] = new Tile ('0', '9', xcor, ycor);
    xcor = 10;
    ycor += 5;
    nums[0] = new Tile ('1', '0', xcor, ycor);
    xcor += 5;
    nums[9] = new Tile ('1', '1', xcor, ycor);
    xcor += 5;
    nums[4] = new Tile ('1', '2', xcor, ycor);
    xcor += 5;
    nums[2] = new Tile ('1', '3', xcor, ycor);
    xcor = 10;
    ycor += 5;
    nums[10] = new Tile ('1', '4', xcor, ycor);
    xcor += 5;
    nums[7] = new Tile ('1', '5', xcor, ycor);
    xcor += 5;
    nums[15] = new Tile (' ', xcor, ycor);
    xcor += 5;

  }

  public String toString () {
  }

  public static void putString(int r, int c,Terminal t, String s){
   	 t.moveCursor(r,c);
   	 for(int i = 0; i < s.length();i++){
   		 t.putCharacter(s.charAt(i));
   	 }
    }

    public static void main(String[] args) {


   	 int x = 10;
   	 int y = 10;

	Tile space = new Tile (' ', 10,10);
	Tile one = new Tile ('1', 10,10);
	Tile two = new Tile ('1', 10,10);
	Tile three = new Tile ('1', 10,10);
	Tile four = new Tile ('1', 10,10);
	Tile five = new Tile ('1', 10,10);
	Tile six = new Tile ('1', 10,10);
	Tile seven = new Tile ('1', 10,10);
	Tile eight = new Tile ('1', 10,10);
	Tile nine = new Tile ('1', 10,10);
	Tile ten = new Tile ('1', 10,10);


   	 Terminal terminal = TerminalFacade.createTextTerminal();
   	 terminal.enterPrivateMode();

   	 TerminalSize size = terminal.getTerminalSize();
   	 terminal.setCursorVisible(false);

   	 boolean running = true;

   	 long tStart = System.currentTimeMillis();
   	 long lastSecond = 0;

   	 while(running){

   		 terminal.moveCursor(x,y);
   		 terminal.applyBackgroundColor(Terminal.Color.WHITE);
   		 terminal.applyForegroundColor(Terminal.Color.BLACK);
   		 //applySGR(a,b) for multiple modifiers (bold,blink) etc.
   		 terminal.applySGR(Terminal.SGR.ENTER_UNDERLINE);
   		 terminal.putCharacter('\u00a4');
   		 //terminal.putCharacter(' ');
   		 terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
   		 terminal.applyForegroundColor(Terminal.Color.DEFAULT);
   		 terminal.applySGR(Terminal.SGR.RESET_ALL);


   		 terminal.moveCursor(size.getColumns()-5,5);
   		 terminal.applyBackgroundColor(Terminal.Color.RED);
   		 terminal.applyForegroundColor(Terminal.Color.YELLOW);
   		 terminal.applySGR(Terminal.SGR.ENTER_BOLD);
   		 terminal.putCharacter(' ');
   		 terminal.putCharacter(' ');
   		 terminal.putCharacter('\u262d');
   		 terminal.putCharacter(' ');
   		 terminal.moveCursor(size.getColumns()-5,6);
   		 terminal.putCharacter(' ');
   		 terminal.putCharacter(' ');
   		 terminal.putCharacter(' ');
   		 terminal.putCharacter(' ');
   		 terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
   		 terminal.applyForegroundColor(Terminal.Color.DEFAULT);

   		 Key key = terminal.readInput();

   		 if (key != null)
   		 {

   			 if (key.getKind() == Key.Kind.Escape) {

   				 terminal.exitPrivateMode();
   				 running = false;
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
   		 }
   	 }
    }
}

/*
for (int x = 1; x - 1 < 16; x ++) {
  if (x == 5 || x == 9 || x == 13) {
    xcor = 10;
    ycor += 5;
  }

  if (x <= 4 && x >= 1) {
    nums [x] = new Tile ((char) x, xcor, ycor);
    xcor += 5;
  }

  if (x <= 5 && x >= 8) {
    nums [x] = new Tile ((char) x, xcor, ycor);
    xcor += 5;
  }

  if (x <= 9 && x >= 12) {
    nums [x] = new Tile ((char) x, xcor, ycor);
    xcor += 5;
  }

  if (x <= 13 && x >= 15) {
    nums [x] = new Tile ((char) x, xcor, ycor);
    xcor += 5;
  }

}
nums [15] = new Tile (' ', xcor, ycor);
}
*/
