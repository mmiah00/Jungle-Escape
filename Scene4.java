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

public class Scene4 extends Scene {

  public Scene4 (Terminal t) {
    path = new String[35];
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

    path[0] = "o";
    for (int i = 1; i < 35; i++) {
      path[i] = " ";
    }
    putString(0, 10, t, toString());

    putString(0, 12, t, "|    Keep running, you've almost    |");
    putString(0, 13, t, "|       reached the safehouse.      |");
    putString(0, 14, t, "|  What's that up ahead? A highway? |");
    putString(0, 15, t, "|  Quick! Cross the highway without |");
    putString(0, 16, t, "|getting hit by a zombie-driven car!|");

  }

  public static int playScene4(Terminal terminal) {
    Scene4 A = new Scene4(terminal);

    boolean pathNotDone = true;
    while (pathNotDone) {
      pathNotDone = !(A.isLastSpot());
      putString(0, 10, terminal, A.toString());
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          pathNotDone = false;
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
    return 4; //6;
  }
}
