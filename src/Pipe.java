// FlappyFox2
public class Pipe {

	private int posX;
	private int zz;
	
	public Pipe(int pipePosX) {
		posX = pipePosX;
		zz = (int)(Math.random() *400 ) +100;
	}

	public void newPosX () {
		posX = posX - 2;
	}



	public int getPosX () {
		return posX;
	}

	public void setPosX (int x) {
		posX = x;
	}
	
	public void newRandomHole () {
		zz = (int)(Math.random() *400 ) +100;
	}

	public int getZZ() {
		return zz;
	}
	
	public void testPipeEnd () {
		if ((posX+100) == 0) {
			posX = 1100;
			newRandomHole();
		}
	}
}
