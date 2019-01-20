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

public class Scene3 extends Scene{

  public Scene3 (Terminal t) {

    putString(0, 0, t, "  ,                 _,-,   ");
    putString(0, 1, t, " /(  ___________   T_  |   ");
    putString(0, 2, t, "|  >:===========`  ||`-'   ");
    putString(0, 3, t, " )(                ||      ");
    putString(0, 4, t, " \"\"                ||      ");
    putString(0, 5, t, "                   --      ");

    /*path[0] = "o";
    for (int i = 1; i < 6; i++) {
      path[i] = " ";
    }
    putString(0, 8, t, toString());
*/
    putString(0, 7, t, "|      You opened the box!     |");
    putString(0, 8, t, "|  Now you can build a bridge! |");
    putString(0, 9, t, "|     Put the planks in the    |");
    putString(0, 10, t, "|  correct numerical order to  |");
    putString(0, 11, t, "|       build your bridge.     |");
  }

  public static int playScene3(Terminal terminal) {
    Scene3 A = new Scene3(terminal);

    int counter = 0;
    while (counter != 100000) {
      counter++;
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          counter = 100000;
          return -1;
        }
      //putString(0, 8, terminal, A.toString());
    }
    terminal.clearScreen();
    return 3; //4;
  }
}
