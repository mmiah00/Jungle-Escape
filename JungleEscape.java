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

public class JungleEscape {

  public static void putString(int r, int c,Terminal t, String s){
    t.moveCursor(r,c);
    for(int i = 0; i < s.length();i++){
      t.putCharacter(s.charAt(i));
    }
  }

  public static void scene1 (Terminal t) {
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
  }

  public static void main(String[] args) {

    Terminal terminal = TerminalFacade.createTextTerminal();
    terminal.enterPrivateMode();

    TerminalSize size = terminal.getTerminalSize();
    terminal.setCursorVisible(false);

    scene1(terminal);

    boolean running = true;
    while (running) {
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          running = false;
        }
      }
    }
  }
}
  /*
  create terminal
  scene1 (forest and clears terminal for 2048)
  play2048
   -takes in keystrokes and calls appropriate moves
   -clears once iscomplete
  scene2 (approaching the river and clears terminal for fifteenpuzzle)
  playfifteen
   -takes keystrokes and calls appropriate moves
   -clears once is isComplete
  scene 3 (walk across bridge and sees highway)
  playfrogger
  victory
  */
