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

public class Scene1 {

  String[] path = new String[35];
  int index = 0;

  public Scene1 (Terminal t) {
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
  }

  public String toString() {
    String s = " ";
    for (int i = 0; i < 35; i++) {
      s = s + path[i];
    }
    return s;
  }

  public void moveLeft() {
    if (index != 0) {
      path[index-1] = "o";
      path[index] = " ";
      index--;
    }
  }

  public void moveRight() {
    if (index != 34) {
      path[index+1] = "o";
      path[index] = " ";
      index++;
    }
  }

  public boolean isLastSpot() {
    return index == 34;
  }

  public static void putString(int r, int c,Terminal t, String s){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public static void main(String[] args) {

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize size = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

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
        }
        if (key.getKind() == Key.Kind.ArrowLeft) {
          A.moveLeft();
        }
        if (key.getKind() == Key.Kind.ArrowRight) {
          A.moveRight();
        }
      }
    }
    terminal.exitPrivateMode();
  }
}
