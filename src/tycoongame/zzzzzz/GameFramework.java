package tycoongame.zzzzzz;

import java.util.ArrayList;
import java.util.List;

import tycoongame.game.managers.Player;
import tycoongame.io.Input;
import tycoongame.zzzzzz.event.EventListener;

public abstract class GameFramework {
	/**if the game is currently being played */
	protected boolean playing = true;
	/**the games player */
	protected Player player;
	/**the menu for the game */
	protected static Menu menu;
	/**the method of input the player uses */
	protected Input input;
	/**the conversion from objects to strings */
	protected DisplayAdapter dispAdpt;
	/**the manager for the displays in the game */
	// protected Manager guiManager;
	/**the list of objects listening to game events */
	protected List<EventListener> listeners;


	protected void setupGame() 
	{
		listeners = new ArrayList<EventListener>();

		player = new Player();
		listeners.add( player );
		
		input = new Input();
		menu = new Menu();
		dispAdpt = new DisplayAdapter(); 
		// guiManager = new Manager();
		
		player.startGame();
	}

	protected void message(String message) 
	{
		if (message != null)
			System.out.println(message);
	}
}
