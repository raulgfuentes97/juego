public class ScreenController {

	private static MainMenu mainMenu;
	private static Game game;	
	
	public static void main(String[] args) {
		// INIT MENU
		mainMenu = new MainMenu();
		mainMenu.init();
		
		while(!mainMenu.gameStarted());
		
		// START GAME
		
		game = new Game();
		
		game.init(mainMenu.getGameMode(), mainMenu.getDifficulty());
		
		while(!game.ended());
		
		// END GAME
	}
	
}
