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

  public static void scene1 (Terminal t){//, String[] path) {

    int x = 0;
		int y = 11;

		Terminal terminal = TerminalFacade.createTextTerminal();
		terminal.enterPrivateMode();

		TerminalSize size = terminal.getTerminalSize();
		terminal.setCursorVisible(false);

		boolean running = true;

		long tStart = System.currentTimeMillis();
		long lastSecond = 0;

    int spaces = 0;

    putString(0, 0, t, " Press SPACE to move forward ");
    putString(0, 1, t, "            ,@@@@@@@,                          ,@@@@@@@,                         ");
    putString(0, 2, t, "    ,,,,   ,@@@@@@/@@,  .oo8888o.      ,,,,   ,@@@@@@/@@,  .oo8888o.      ,,,,   ");
    putString(0, 3, t, " ,&%%&%&&%,@@@@@/@@@@@@,8888\\88/8o  ,&%%&%&&%,@@@@@/@@@@@@,8888\\88/8o  ,&%%&%&&");
    putString(0, 4, t, ",%&\\%&&%&&%,@@@\\@@@/@@@88\\88888/88’,%&\\%&&%&&%,@@@\\@@@/@@@88\\88888/88’,%&\\");
    putString(0, 5, t, "%&&%&%&/%&&%@@\\@@/  /@@@88888\\88888’%&&%&%&/%&&%@@\\@@/  /@@@88888\\88888’%&&%&");
    putString(0, 6, t, "%&&%/ %&%%&&@@\\ V /@@’ ’88\\8 ’/88’ %&&%/ %&%%&&@@\\ V /@@’ ’88\\8 ’/88’ %&&%/ %");
    putString(0, 7, t, "‘&%\\ ‘ /%&’    |.|        \\ ‘|8’   ‘&%\\ ‘ /%&’    |.|        \\ ‘|8’   ‘&%\\ ‘");
    putString(0, 8, t, "    |o|        | |         | |         |o|        | |         | |         |o|    ");
    putString(0, 9, t, "    |.|        | |         | |         |.|        | |         | |         |.|    ");
    putString(0, 10, t, "_\\\\/ ._\\//_/__/  ,\\_//__\\\\/.  \\_//__\\\\/ ._\\//_/__/  ,\\_//__\\\\/.  \\");


		while(running){
			terminal.moveCursor(x,y);
			terminal.applyBackgroundColor(Terminal.Color.WHITE);
			terminal.applyForegroundColor(Terminal.Color.BLACK);
			//applySGR(a,b) for multiple modifiers (bold,blink) etc.
			terminal.putCharacter('O');
			//terminal.putCharacter(' ');
			terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
			terminal.applySGR(Terminal.SGR.RESET_ALL);

			Key key = terminal.readInput();

      long tEnd = System.currentTimeMillis ();
      long millis = tEnd - tStart;
      if(millis/1000 > lastSecond){
        lastSecond = millis / 1000;
      }

      if (lastSecond == 1) {
        putString(0, 20, t, " ______________________________________________________________________________________________________");
        putString(0, 21, t, "|                                                                                                      |");
        putString(0, 22, t, "|    Attempting to escape the zombie apocolype, you decide to start running away from your problems.   |");
        putString(0, 21, t, "|                                                                                                      |");
        putString(0, 23, t, " ______________________________________________________________________________________________________");
        /*
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
  			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
  			terminal.applySGR(Terminal.SGR.RESET_ALL);
        */
      }

      if (lastSecond == 2) {
        putString(0, 20, t, " ______________________________________________________________________________________________________");
        putString(0, 21, t, "|                                                                                                      |");
        putString(0, 22, t, "|  It seemed like there is no end; until you reach the jungle. Of course you should be worrying about  |");
        putString(0, 21, t, "|                                                                                                      |");
        putString(0, 23, t, " ______________________________________________________________________________________________________");
        /*
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
  			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
  			terminal.applySGR(Terminal.SGR.RESET_ALL);
        */

      }

      if (lastSecond == 4) {
        putString(0, 20, t, " ______________________________________________________________________________________________________");
        putString(0, 21, t, "|                                                                                                      |");
        putString(0, 22, t, "|    wild animals or maybe even the possibilities of animal zombies, but you did not stop to think.    |");
        putString(0, 21, t, "|                                                                                                      |");
        putString(0, 23, t, " ______________________________________________________________________________________________________");
        /*
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        terminal.applySGR(Terminal.SGR.RESET_ALL);
        */

      }

      if (lastSecond == 6) {
        putString(0, 20, t, " ______________________________________________________________________________________________________");
        putString(0, 21, t, "|                                                                                                      |");
        putString(0, 22, t, "|                               So you just keep running, mindlessly.                                  |");
        putString(0, 21, t, "|                                                                                                      |");
        putString(0, 23, t, " ______________________________________________________________________________________________________");
        /*
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
  			terminal.applyForegroundColor(Terminal.Color.DEFAULT);
  			terminal.applySGR(Terminal.SGR.RESET_ALL);
        */

      }

      if (lastSecond == 7) {
        putString(0, 20, t, " ______________________________________________________________________________________________________");
        putString(0, 21, t, "|                                                                                                      |");
        putString(0, 22, t, "|                               ** Press SPACE to move forward **                                      |");
        putString(0, 21, t, "|                                                                                                      |");
        putString(0, 23, t, " ______________________________________________________________________________________________________");
        /*
        terminal.applyBackgroundColor(Terminal.Color.DEFAULT);
        terminal.applyForegroundColor(Terminal.Color.DEFAULT);
        terminal.applySGR(Terminal.SGR.RESET_ALL);
        */

      }

			if (key != null)
			{

				if (key.getKind() == Key.Kind.Escape) {

					terminal.exitPrivateMode();
					running = false;

        }
				if (key.getCharacter() == ' ') {
					terminal.moveCursor(x,y);
					terminal.putCharacter(' ');
					x++;
          spaces ++;
			}
    }

      if (spaces == 20) {
        putString(0, 20, t, " ___________________________________________________________________________________");
        putString(0, 21, t, "|                                                                                  |");
        putString(0, 22, t, "|          You have come upon a box and see there's a puzzle on it.                |");
        putString(0, 21, t, "|                                                                                  |");
        putString(0, 23, t, " ___________________________________________________________________________________");
      }
    }
  }

    /*
    path[0] = "o";
    for (int i = 1; i < 35; i++) {
      path[i] = " ";
    }
    putString(0, 10, t, toString(path));
    */
    //personRunning (t);


  /*
  public static void personRunning (Terminal t) {
    long tStart = System.currentTimeMillis();
    int x = 0 ;
    boolean running = true;
    while (running) {
      if (tStart % 1000 == 0) {
        putString (x,11,t, " O ");
        putString (x,12,t, "-|_ ");
        putString (x,13,t, "/ /");
        x += 1;
      }
      else {
        putString (x,11,t, " O ");
        putString (x,12,t, "-|- ");
        putString (x,13,t, "/ \\");
        x += 1;
      }
      if (tStart == 10000) {
        running = false;
      }
    }
  }
  */

  public static String toString(String [] ary) {
    String s = " ";
    for (int i = 0; i < ary.length; i++) {
      s = s + ary[i];
    }
    return s;
  }

  /*
  public static String moveAlongPath(String [] ary) {
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.ArrowLeft) {

        }
        }

  }
  */


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
    scene1 (terminal);

/*
    String[] path1 = new String[35];
    scene1(terminal, path1);
*/

    boolean running = true;
    int mode = 0;
    while (running) {
      Key key = terminal.readInput();
      if (key != null){
        if (key.getKind() == Key.Kind.Escape) {
          terminal.exitPrivateMode();
          running = false;
        }
      }
      if (mode == 0) {
        NumberPuzzle A = new NumberPuzzle();
        A.play2048(terminal);
        /*
        putString(0, 0, terminal, A.toString());
        boolean gameNotDone = true;
        while (gameNotDone){
          gameNotDone = !(A.isComplete());
          putString(0, 0, terminal, A.toString());
          if (key != null){
            if (key.getKind() == Key.Kind.Escape) {
              terminal.exitPrivateMode();
              gameNotDone = false;
              running = false;
            }
            if (key.getKind() == Key.Kind.ArrowLeft) {
              A.moveLeft();
            }
            if (key.getKind() == Key.Kind.ArrowRight) {
              A.moveRight();
            }
            if (key.getKind() == Key.Kind.ArrowUp) {
              A.moveUp();
            }
            if (key.getKind() == Key.Kind.ArrowDown) {
              A.moveDown();
            }
          }
        }
        terminal.exitPrivateMode();*/
      }
    }
    terminal.exitPrivateMode();

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
