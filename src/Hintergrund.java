// FlappyFox2
public class Hintergrund {
	
	private int posX;
	
	public Hintergrund (int x) {
		posX = x;
	}
	
	public int getPosBgX () {
		return posX;
	}
	
	public void updatePosBgX () {
		posX = posX - 2;
	}
	
	public void setPosBgX (int x) {
		posX = x;
	}
	
	public void testIfBgEnd () {
		if ((posX+900) == 0) {
			posX = 900;
		}
	}

}
