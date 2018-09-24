public class Game {
	
	private Player player1;
	private Player player2;

	public Game () {};
	
	public void init(int gameMode, int difficulty) {
		player1 = new Player();
		player2 = new Player();
		
		switch(gameMode) {
			case 1:
				player1.createBoard(difficulty);
				break;
			case 2:
				player1.initBoard(difficulty);
				break;
		}
		player2.initBoard(difficulty);
		
		System.out.println("\n Board of Player1:  \n");
		player1.showBoard();
		
		System.out.println("\n Board of Player2:  \n");
		player2.showBoard();
	}
	
	public boolean ended() {
		return player1.allShipsSunk() || player2.allShipsSunk();
	}
	
}
