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
    putString(0, 1, t, "------------------------");
    putString(0, 2, t, "            /   .\\      ");
    putString(0, 3, t, "           / ` .  \\     ");
    putString(0, 4, t, "          / .   `  \\    ");
    putString(0, 5, t, "         /.   `  `  \\   ");
    putString(0, 6, t, "        /  ` . `  . .\\  ");
    putString(0, 7, t, "  []   / `  .   .  `  \\ ");
    putString(0, 8, t, "      / .  `   ` .  .  \\");

    path[0] = "o";
    for (int i = 1; i < 6; i++) {
      path[i] = " ";
    }
    putString(0, 9, t, toString());

    putString(0, 11, t, "|  Oh no! There's a river! |");
    putString(0, 12, t, "| Maybe that box can help. |");
    putString(0, 13, t, "|  Solve the puzzle on the |");
    putString(0, 14, t, "|   box to get materials   |");
    putString(0, 15, t, "|    to build a bridge.    |");
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

  public static int [] playScene2(Terminal terminal, int beginMin, int beginSec) {
    Scene2 A = new Scene2(terminal);
    int [] returns = new int [3];

    boolean pathNotDone = true;
    long lastTime =  System.currentTimeMillis();
    long currentTime = lastTime;
    long timer = 0;
    boolean firstPass = true;

    while (pathNotDone) {
      pathNotDone = !(A.isLastSpot());
      putString(0, 9, terminal, A.toString());
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          pathNotDone = false;
          returns[0] = -1;
          return returns;
        }
        if (key.getKind() == Key.Kind.ArrowLeft) {
          A.moveLeft();
        }
        if (key.getKind() == Key.Kind.ArrowRight) {
          A.moveRight();
        }
      }

      lastTime = currentTime;
      currentTime = System.currentTimeMillis();
      timer += (currentTime -lastTime);
      A.setMinLeft(beginMin - (int)(timer/60000));
      String minPassed = String.format("%02d", A.getMinLeft());
      if ((int)(timer%60000/1000) > beginSec) {
        firstPass = false;
      }
      if (firstPass) {
        A.setSecLeft(beginSec -(int)(timer%60000/1000));
      }
      else {
        A.setSecLeft(60 - (int)(timer%60000/1000));
      }
      String secPassed = String.format("%02d", A.getSecLeft());
      if (A.getSecLeft() == 60) {
        A.setMinLeft(beginMin - (int)(timer/60000));
        minPassed = String.format("%02d", A.getMinLeft());
        secPassed = "00";
      }
      putString(0,0,terminal, "Time Left: "+ minPassed + ":" + secPassed);
      returns[1] = A.getMinLeft();
      returns[2] = A.getSecLeft();

      if (A.getMinLeft() == 0 && A.getSecLeft() == 1) {
        pathNotDone = false;
        returns [0] = -1;
        return returns;
      }
    }
    terminal.clearScreen();
    returns [0] = 2;
    return returns;
  }
}
