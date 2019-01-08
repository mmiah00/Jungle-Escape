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

  public void complete () {
    String actual = "0102030405060708091011131415  ";
    String ans = "";
    for (int i = 0; i < nums.length; i ++) {
      ans += nums[i].tens () + nums[i].ones ();
    }
    done = (actual == ans);
  }

  public static void main(String[] args) {
    
  }
}
