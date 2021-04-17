// FlappyFox2
public class Fox {

	private int posY;
	private int punkte;
	
	int g;
	int endgeschwindigeit;
	int geschwindigkeitY;
	
	
	public Fox(int y) {
		g = 2;
		endgeschwindigeit = 100;
		geschwindigkeitY = 0;
		punkte = 0;
		posY = y;
	}

	public void flap () {
		geschwindigkeitY = 0;
		posY = posY - 4;
	}

	public void gravity () {

		geschwindigkeitY = geschwindigkeitY + g;
		if (geschwindigkeitY > endgeschwindigeit)
		{
			geschwindigkeitY = endgeschwindigeit;
		}
		posY = posY + (geschwindigkeitY/10);
	}

	public int getPosY () {
		return posY;
	}

	public void setPosY (int y) {
		posY = y;
	}

	public void erhöhePunkte () {
		punkte++;
	}

	public int getPunkte () {
		return punkte;
	}

	public void resetPunkte () {
		punkte = 0;
	}
}

















