
public class Player {

	private Board  board;
	
	public Player() {
		board = new Board();
	};
		
	public void createBoard(int difficulty) {
		board.createBoard(difficulty);
	}
	
	public void initBoard(int difficulty) {
		board.initBoard(difficulty);
	}
	
	public boolean allShipsSunk() {
		return board.allShipsSunk();
	}

	public void showBoard() {
		board.showBoard();
	}
}


