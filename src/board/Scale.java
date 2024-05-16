package board;

public class Scale {

	private int x, y, size;
	
	protected Scale(int x, int y, int size){
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getSize() {
		return this.size;
	}
	
}
