public class Ship {

	private int size;
	private char[] parts;
	private char notHitChar = 'B'; 
	private char hitChar = 'X';
	private char sunkChar = '#';
	private int posX, posY;
	private String orientation;
	
	public Ship() {};
	
	public void createShip() {
		parts = new char[size];
		for(int i = 0; i<size; ++i) {
			parts[i] = notHitChar;
		}
	}
	
	public Ship(int size) {
		this.size = size;
		createShip();
	}
	
	public void setSize(int size) {
		this.size = size;
		createShip();
	}
	
	public void setPosition(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
	
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	
	public String getOrientation() {
		return this.orientation;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public boolean isSunk() {
		for(int i = 0; i<size; ++i) {
			if(parts[i] == notHitChar) return false;
		}
		
		return true;
	}

	public void setHit(int posX, int posY) {
		
		int position = 0;
		
		if(this.posX != posX) position = this.posX - posX;
		else position = this.posY - posY;
		
		if(position < 0) position *= -1;
		parts[position] = hitChar;
		
		if(isSunk()) {
			for(int i = 0; i<size; ++i) parts[i] = sunkChar;
		}
	}
	
	public char[] getParts() {
		return this.parts;
	}

	public char getNotHitChar() {
		return this.notHitChar;
	}
}
