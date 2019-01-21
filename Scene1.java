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

public class Scene1 extends Scene {

  public Scene1 (Terminal t) {
    path = new String[35];
    t.applyForegroundColor (Terminal.Color.GREEN);
    t.applyBackgroundColor (126,192,238);
    putString(0, 0, t, "            ,@@@@@@@,              ");
    putString(0, 1, t, "    ,,,,   ,@@@@@@/@@,  .oo8888o.  ");
    putString(0, 2, t, " ,&%%&%&&%,@@@@@/@@@@@@,8888\\88/8o ");
    putString(0, 3, t, ",%&\\%&&%&&%,@@@\\@@@/@@@88\\88888/88’");
    putString(0, 4, t, "%&&%&%&/%&&%@@\\@@/  /@@@88888\\88888’");
    putString(0, 5, t, "%&&%/ %&%%&&@@\\ V /@@’ ’88\\8 ’/88’ ");
    putString(0, 6, t, "‘&%\\ ‘ /%&’    |.|        \\ ‘|8’   ");
    putString(0, 7, t, "    |o|        | |         | |     ");
    putString(0, 8, t, "    |.|        | |         | |     ");
    putString(0, 9, t, "_\\\\/ ._\\//_/__/  ,\\_//__\\\\/.  \\_//_");
    t.applyBackgroundColor (Terminal.Color.DEFAULT);
    path[0] = "o";
    for (int i = 1; i < 35; i++) {
      path[i] = " ";
    }
    putString(0, 10, t, toString());

    putString(0, 12, t, "| Attempting to escape the zombie |");
    putString(0, 13, t, "| apocolype, you decide to start  |");
    putString(0, 14, t, "| running and runing until you    |");
    putString(0, 15, t, "| reach the end.                  |");
  }

  public static int playScene1(Terminal terminal) {
    Scene1 A = new Scene1(terminal);

    boolean pathNotDone = true;
    while (pathNotDone) {
      pathNotDone = !(A.isLastSpot());
      putString(0, 10, terminal, A.toString());
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
    return 1;
  }
}
