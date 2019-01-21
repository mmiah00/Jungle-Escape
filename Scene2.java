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

public class Scene2 extends Scene{

  public Scene2 (Terminal t) {
    path = new String[6];

    t.applyBackgroundColor (Terminal.Color.GREEN);
    putString(0, 0, t, "-------------------------");
    putString (0,1,t, "            /");
    putString (0,2,t, "           /");
    putString (0,3,t, "          /");
    putString (0,4,t, "         /");
    putString (0,5,t, "        /");
    putString (0,6,t, "       /");
    putString (0,7,t, "      /");
    putString (0,8,t, "     /");

    putString (16,1,t, " \\       ");
    putString (17,2,t, " \\      ");
    putString (18,3,t, " \\     ");
    putString (19,4,t, " \\    ");
    putString (20,5,t, " \\   ");
    putString (21,6,t, " \\  ");
    putString (22,7,t, " \\ ");

    t.applyBackgroundColor(Terminal.Color.BLUE);
    putString(13, 1, t, "   .");
    putString(12, 2, t, " ` .  ");
    putString(11, 3, t, " .   `  ");
    putString(10, 4, t, ".   `  `  ");
    putString(9, 5, t, "  ` . `  . .");
    putString(8, 6, t, " `  .   .  `  ");
    putString(7, 7, t, " .  `   ` .  .  ");
    t.applyBackgroundColor (Terminal.Color.GREEN);
    putString(0, 6, t, "  ");
    t.applyBackgroundColor (Terminal.Color.DEFAULT); 
    t.moveCursor (2,6);
    t.putCharacter ('\u2610');

    path[0] = "o";
    for (int i = 1; i < 6; i++) {
      path[i] = " ";
    }
    putString(0, 8, t, toString());

    putString(0, 10, t, "|  Oh No! There's a river! |");
    putString(0, 11, t, "|   Wait, there's a box!   |");
    putString(0, 12, t, "| Solve the puzzle to open |");
    putString(0, 13, t, "| the box and get materials|");
    putString(0, 14, t, "|    to build a bridge.    |");
  }

  public static void putString(int r, int c,Terminal t, String s, Terminal.Color forg, Terminal.Color back ){
    t.moveCursor(r,c);
    t.applyBackgroundColor(forg);
    t.applyForegroundColor(Terminal.Color.BLACK);

    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
    t.applyBackgroundColor(Terminal.Color.DEFAULT);
    t.applyForegroundColor(Terminal.Color.DEFAULT);
  }

  public static int playScene2(Terminal terminal) {
    Scene2 A = new Scene2(terminal);

    boolean pathNotDone = true;
    while (pathNotDone) {
      pathNotDone = !(A.isLastSpot());
      putString(0, 8, terminal, A.toString());
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          pathNotDone = false;
          return -1;
        }
        if (key.getKind() == Key.Kind.ArrowLeft) {
          A.moveLeft();
        }
        if (key.getKind() == Key.Kind.ArrowRight) {
          A.moveRight();
        }
      }
    }
    terminal.clearScreen();
    return 2;
  }
}
