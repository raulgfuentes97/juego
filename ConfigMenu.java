import java.util.Scanner;

public class ConfigMenu {

	private Scanner scanner;
	private int gameMode;
	private int difficulty;
	
	public ConfigMenu() {
		scanner = new Scanner(System.in);
	};
	
	public void configureOptions() {
		System.out.println("Modo de juego: \n"
				+ 		    "1: Jugador vs PC \n"
				+ 			"2: PC vs PC \n");
		
		gameMode = scanner.nextInt();
		
		System.out.println("Dificultad: \n"
				+ 			"1: Facil \n"	// board 10x10
				+ 			"2: Media \n"   // board 12x12
				+ 			"3: Dificil \n");	// board 15x15
		
		difficulty = scanner.nextInt();	
	}

	public int getGameMode() {
		return this.gameMode;
	}
	
	public int getDifficulty() {
		return this.difficulty;
	}
}
