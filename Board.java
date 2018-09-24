import java.util.concurrent.ThreadLocalRandom;

public class Board {

	private int boardSize;
	private char[][] Sea;
	private char[][] privateSea;
	private char freeCellChar = '-';
	private Ship[] ships;
	private int numberOfShips = 10;
 	
	public Board() {
		ships = new Ship[numberOfShips];
		ships[0] = new Ship(2); 
		ships[1] = new Ship(2);
		ships[2] = new Ship(3);
		ships[3] = new Ship(3);
		ships[4] = new Ship(3);
		ships[5] = new Ship(4);
		ships[6] = new Ship(4);
		ships[7] = new Ship(4);
		ships[8] = new Ship(5);
		ships[9] = new Ship(5);
	};
	
	private boolean allocateShip(int ship) {
		
		boolean allocated = false;
		int shipSize = ships[ship].getSize();
		String orientation = ships[ship].getOrientation();
		
		for(int i = 1; i<(boardSize+1) && !allocated; ++i) {
			for(int j = 1; j<(boardSize+1) && !allocated; ++j) {
				if(privateSea[i][j] == freeCellChar) {
					boolean possibleAllocation = true;
					switch(orientation) {
						case "VERTICAL":
							if(i-shipSize >= 1) { // UP
								for(int u = 0; u<shipSize && possibleAllocation; ++u) {
									if(privateSea[i-u][j] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i-u][j+1] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i-u][j-1] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i-u+1][j] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i-u-1][j] != freeCellChar) possibleAllocation = false;
								}
								for(int u = 0; u<shipSize && possibleAllocation; ++u) {
									privateSea[i-u][j] = ships[ship].getParts()[u];
								}
								if(possibleAllocation) {
									ships[ship].setPosition(i, j);
									allocated = true;
								}

							}
							else if(i+shipSize < boardSize+1) { // DOWN
								for(int u = 0; u<shipSize && possibleAllocation; ++u) {
									if(privateSea[i+u][j] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i+u][j+1] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i+u][j-1] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i+u+1][j] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i+u-1][j] != freeCellChar) possibleAllocation = false;
								}
								for(int u = 0; u<shipSize && possibleAllocation; ++u) {
									privateSea[i+u][j] = ships[ship].getParts()[u];
								}
								if(possibleAllocation) {
									ships[ship].setPosition(i, j);
									allocated = true;
								}
							}
							break;
						case "HORIZONTAL":
							if(j+shipSize < boardSize+1) { // RIGHT
								for(int u = 0; u<shipSize && possibleAllocation; ++u) {
									if(privateSea[i][j+u] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i+1][j+u] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i-1][j+u] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i][j+u-1] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i][j+u+1] != freeCellChar) possibleAllocation = false;
								}
								for(int u = 0; u<shipSize && possibleAllocation; ++u) {
									privateSea[i][j+u] = ships[ship].getParts()[u];
								}
								if(possibleAllocation) {
									ships[ship].setPosition(i, j);
									allocated = true;
								}
							}
							else if(j-shipSize >= 1) { // LEFT
								for(int u = 0; u<shipSize && possibleAllocation; ++u) {
									if(privateSea[i][j-u] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i+1][j-u] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i-1][j-u] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i][j-u-1] != freeCellChar) possibleAllocation = false;
									else if(privateSea[i][j-u+1] != freeCellChar) possibleAllocation = false;
								}
								for(int u = 0; u<shipSize && possibleAllocation; ++u) {
									privateSea[i][j-u] = ships[ship].getParts()[u];
								}
								if(possibleAllocation) {
									ships[ship].setPosition(i, j);
									allocated = true;
								}
							}
							break;
						default:
							break;
					}
				}
			}
		}
		return allocated;
	}
	
	private void generateShips() {
		
		for(int i = 0; i<numberOfShips; ++i) {
			int rand = ThreadLocalRandom.current().nextInt(1, 3); // randomInt [1,2]
			if(rand == 1) ships[i].setOrientation("VERTICAL");
			else if(rand == 2) ships[i].setOrientation("HORIZONTAL");
			
			if(!allocateShip(i)) {
				if(ships[i].getOrientation().equals("VERTICAL")) ships[i].setOrientation("HORIZONTAL");
				else ships[i].setOrientation("VERTICAL");
				if(!allocateShip(i)) System.out.println("NO SE PUEDE COLOCAR EL BARCO " + i);;
			}
		}
	}
	
	private void generateSea(int difficulty) {
		if(difficulty == 1) boardSize = 10;
		else if(difficulty == 2) boardSize = 12;
		else if(difficulty == 3) boardSize = 15;
		Sea = new char[boardSize][boardSize];
		for(int i = 0; i<boardSize; ++i) {
			for(int j = 0; j<boardSize; ++j) {
				Sea[i][j] = freeCellChar;
			}
		}
	}
	
	private void generatePrivateSea(int difficulty) {
		if(difficulty == 1) boardSize = 10;
		else if(difficulty == 2) boardSize = 12;
		else if(difficulty == 3) boardSize = 15;
		privateSea = new char[boardSize+2][boardSize+2];
		for(int i = 0; i<boardSize+2; ++i) {
			for(int j = 0; j<boardSize+2; ++j) {
				privateSea[i][j] = freeCellChar;
			}
		}
	}
	
	private void privateSeaToSea() {
		Sea = new char[boardSize][boardSize];
		for(int i = 0; i<boardSize; ++i) {
			for(int j = 0; j<boardSize; ++j) {
				Sea[i][j] = privateSea[i+1][j+1];
			}
		}
	}
	
	private void rotateBoard() {
		int bs = boardSize;
		char[][] tempSea = new char[bs][bs];
		int angle = ThreadLocalRandom.current().nextInt(0, 4); // randomInt [0,3]
		for(int i = 0; i<bs; ++i) {
			for(int j = 0; j<bs; ++j) {
				if(angle == 1) tempSea[i][j] = Sea[bs-1-j][i];
				else if(angle == 2) tempSea[i][j] = Sea[bs-1-i][bs-1-j];
				else if(angle == 3) tempSea[i][j] = Sea[j][bs-1-i];
			}
		}
		Sea = tempSea;
	}
	
	public void initBoard(int difficulty) {
		// automatically generated board
		generatePrivateSea(difficulty);	
		generateShips();
		privateSeaToSea();
		rotateBoard();
	}
	
	public void createBoard(int difficulty) {
		// player configures the board
		generateSea(difficulty);
		
	}
	
	public boolean allShipsSunk() {
		for(int i = 0; i<numberOfShips; ++i) {
			if(ships[i].isSunk() == false) return false;
		}
		return true;
	}

	public void showBoard() {
		for(int i = 0; i<boardSize; ++i) {
			for(int j = 0; j<boardSize; ++j) {
				System.out.print(Sea[i][j] + " ");
			}
			System.out.println();
		}
	}
}
