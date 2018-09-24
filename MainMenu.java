
public class MainMenu {

	private ConfigMenu configMenu;
	private int gameMode, difficulty;
	
	public MainMenu() {
		gameMode = difficulty = 0;
	} ;
	
	public void init() {
		configMenu = new ConfigMenu();
		configMenu.configureOptions();
		
		gameMode = configMenu.getGameMode();
		difficulty = configMenu.getDifficulty();
	}
	
	public boolean gameStarted() {
		return gameMode != 0 && difficulty != 0;
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}
	
	public int getGameMode() {
		return this.gameMode;
	}
}
