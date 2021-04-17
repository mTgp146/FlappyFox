// FlappyFox2
import java.io.File;

public class FlappySteuerung {

	private Fox derFox;
	private HighscoreHandler derHighscoreHandler;
	File highscoreFile = new File("highscore.txt");
	private Pipe diePipe1;
	private Pipe diePipe2;
	private Pipe diePipe3;
	private Hintergrund derHintergrund1;
	private Hintergrund derHintergrund2;
	private FlappyGUI dieGUI;

	int posFoxX = 180;
	int groeﬂeFoxY = 43;
	int groeﬂeFoxX = 90;

	int radiusKreis = 50;
	double radiusKreisDouble = 50;

	boolean start = false;
	boolean gameOver = false;

	boolean pressSpace = false;
	boolean hasToFlap = false;
	int timerS = 0;

	int timerP = 0;

	String punkte = "";
	int pts = 0;

	String highscore = "";
	int hs = 0;

	public FlappySteuerung(FlappyGUI dieGui) {
		this.dieGUI = dieGui;
		derFox = new Fox(380);
		diePipe1 = new Pipe(600);
		diePipe2 = new Pipe(1000);
		diePipe3 = new Pipe(1400);
		derHighscoreHandler = new HighscoreHandler(highscoreFile);
		derHintergrund1 = new Hintergrund(0);
		derHintergrund2 = new Hintergrund(900);

		hs = derHighscoreHandler.highscoreLesen();
		highscore = String.valueOf(hs);

		pts = derFox.getPunkte();
		punkte = String.valueOf(pts);

	}

	public void run() {

		if ((start == true)&&(gameOver == false)) {

			testIfFoxHitsPipe();

			if ((pressSpace == true)||(hasToFlap == true)) {
				if (pressSpace == true) {
					hasToFlap = true;
				}
				pressSpace = false;
				derFox.flap();
				timerS++;
				if (timerS == 20) {
					hasToFlap = false;
					timerS = 0;
				}
			} else {			
				derFox.gravity();
			}

			diePipe1.newPosX();
			diePipe2.newPosX();
			diePipe3.newPosX();
			derHintergrund1.updatePosBgX();
			derHintergrund2.updatePosBgX();

			derHintergrund1.testIfBgEnd();
			derHintergrund2.testIfBgEnd();

			diePipe1.testPipeEnd();
			diePipe2.testPipeEnd();
			diePipe3.testPipeEnd();

			if ((diePipe1.getPosX() == 80) || (diePipe2.getPosX() == 80) || (diePipe3.getPosX() == 80)) {
				derFox.erhˆhePunkte();
			}

			pts = derFox.getPunkte();
			punkte = String.valueOf(pts);

		}

		if (gameOver == true) {
			if (derHighscoreHandler.highscoreLesen() < derFox.getPunkte()) {
				derHighscoreHandler.neuerHighscore(derFox.getPunkte(), highscoreFile);;
				hs = derHighscoreHandler.highscoreLesen();
				highscore = String.valueOf(hs);
			}
			reset();
		}

		dieGUI.repaint();
		
		try {
			Thread.sleep(12);
		} catch (Exception e) {
			e.printStackTrace();
		}	

	}

	public void pressedSpace() {
		timerS = 0;
		pressSpace = true;
		start = true;
	}

	public int getPosFoxY () {
		return derFox.getPosY();
	}

	public int getPosFoxX () {
		return posFoxX;
	}
	
	public int getPosPipe1X () {
		return diePipe1.getPosX();
	}

	public int getPosPipe2X () {
		return diePipe2.getPosX();
	}

	public int getPosPipe3X () {
		return diePipe3.getPosX();
	}

	public int getHighscore () {
		return derHighscoreHandler.highscoreLesen();
	}

	public int getPunkte () {
		return derFox.getPunkte();
	}

	public int getPosBg1X () {
		return derHintergrund1.getPosBgX();
	}

	public int getPosBg2X () {
		return derHintergrund2.getPosBgX();
	}

	public int getPipe1zz() {
		return diePipe1.getZZ();
	}

	public int getPipe2zz() {
		return diePipe2.getZZ();
	}

	public int getPipe3zz() {
		return diePipe3.getZZ();
	}

	private void testIfFoxHitsPipe () {

		// Kollision 1 (Fuchs als Rechteck, Rˆhre als Rechteck)
		/*
		if (((posFoxX+90 >= dieSteuerung.getPosPipe1X())&&(posFoxX <= (dieSteuerung.getPosPipe1X() +100))&&((dieSteuerung.getPosFoxY() <= zz1)||((dieSteuerung.getPosFoxY()+43) >= (zz1+150)))) ||
			((posFoxX+90 >= dieSteuerung.getPosPipe2X())&&(posFoxX <= (dieSteuerung.getPosPipe2X() +100))&&((dieSteuerung.getPosFoxY() <= zz2)||((dieSteuerung.getPosFoxY()+43) >= (zz2+150)))) ||
			((posFoxX+90 >= dieSteuerung.getPosPipe3X())&&(posFoxX <= (dieSteuerung.getPosPipe3X() +100))&&((dieSteuerung.getPosFoxY() <= zz3)||((dieSteuerung.getPosFoxY()+43) >= (zz3+150))))) {

			gameOver = true;
		}
		 */

		// Kollision 2 (Fuchs als Rechteck, Rˆhre als Rechteck)
		/*
		if ((((posFoxX+140)>(dieSteuerung.getPosPipe1X()+50))&&((posFoxX-50)<(dieSteuerung.getPosPipe1X()+50))&&(((dieSteuerung.getPosFoxY()-50)<(zz1-50))||((dieSteuerung.getPosFoxY()+93)>(zz1+200))))||
			(((posFoxX+140)>(dieSteuerung.getPosPipe2X()+50))&&((posFoxX-50)<(dieSteuerung.getPosPipe2X()+50))&&(((dieSteuerung.getPosFoxY()-50)<(zz2-50))||((dieSteuerung.getPosFoxY()+93)>(zz2+200))))||
			(((posFoxX+140)>(dieSteuerung.getPosPipe3X()+50))&&((posFoxX-50)<(dieSteuerung.getPosPipe3X()+50))&&(((dieSteuerung.getPosFoxY()-50)<(zz3-50))||((dieSteuerung.getPosFoxY()+93)>(zz3+200))))) {

			gameOver = true;
		}
		 */

		// Kollision 3 (Fuchs als Rechteck, Rˆhre als Kreis)
		/*
		 double vektorCP1 = Math.sqrt(Math.pow((zz1+200)-(dieSteuerung.getPosFoxY()+groeﬂeFoxY), 2) + Math.pow((dieSteuerung.getPosPipe1X()+radiusKreis)-(posFoxX), 2));
		 double vektorDP1 = Math.sqrt(Math.pow((zz1+200)-(dieSteuerung.getPosFoxY()+groeﬂeFoxY), 2) + Math.pow((dieSteuerung.getPosPipe1X()+radiusKreis)-(posFoxX+groeﬂeFoxX), 2));
		 double vektorAS1 = Math.sqrt(Math.pow((zz1- 50)-(dieSteuerung.getPosFoxY()), 2) + Math.pow((dieSteuerung.getPosPipe1X()+radiusKreis)-(posFoxX), 2));
		 double vektorBS1 = Math.sqrt(Math.pow((zz1- 50)-(dieSteuerung.getPosFoxY()), 2) + Math.pow((dieSteuerung.getPosPipe1X()+radiusKreis)-(posFoxX+groeﬂeFoxX), 2));

		 double vektorCP2 = Math.sqrt(Math.pow((zz2+200)-(dieSteuerung.getPosFoxY()+groeﬂeFoxY), 2) + Math.pow((dieSteuerung.getPosPipe2X()+radiusKreis)-(posFoxX), 2));
		 double vektorDP2 = Math.sqrt(Math.pow((zz2+200)-(dieSteuerung.getPosFoxY()+groeﬂeFoxY), 2) + Math.pow((dieSteuerung.getPosPipe2X()+radiusKreis)-(posFoxX+groeﬂeFoxX), 2));
		 double vektorAS2 = Math.sqrt(Math.pow((zz2- 50)-(dieSteuerung.getPosFoxY()), 2) + Math.pow((dieSteuerung.getPosPipe2X()+radiusKreis)-(posFoxX), 2));
		 double vektorBS2 = Math.sqrt(Math.pow((zz2- 50)-(dieSteuerung.getPosFoxY()), 2) + Math.pow((dieSteuerung.getPosPipe2X()+radiusKreis)-(posFoxX+groeﬂeFoxX), 2));

		 double vektorCP3 = Math.sqrt(Math.pow((zz3+200)-(dieSteuerung.getPosFoxY()+groeﬂeFoxY), 2) + Math.pow((dieSteuerung.getPosPipe3X()+radiusKreis)-(posFoxX), 2));
		 double vektorDP3 = Math.sqrt(Math.pow((zz3+200)-(dieSteuerung.getPosFoxY()+groeﬂeFoxY), 2) + Math.pow((dieSteuerung.getPosPipe3X()+radiusKreis)-(posFoxX+groeﬂeFoxX), 2));
		 double vektorAS3 = Math.sqrt(Math.pow((zz3- 50)-(dieSteuerung.getPosFoxY()), 2) + Math.pow((dieSteuerung.getPosPipe3X()+radiusKreis)-(posFoxX), 2));
		 double vektorBS3 = Math.sqrt(Math.pow((zz3- 50)-(dieSteuerung.getPosFoxY()), 2) + Math.pow((dieSteuerung.getPosPipe3X()+radiusKreis)-(posFoxX+groeﬂeFoxX), 2));

		 if ( (((vektorCP1<radiusKreisDouble)||(vektorDP1<radiusKreisDouble)||(vektorAS1<radiusKreisDouble)||(vektorBS1<radiusKreisDouble)||((dieSteuerung.getPosFoxY())<(zz1-radiusKreis))||((dieSteuerung.getPosFoxY()+groeﬂeFoxY)>(zz1+radiusKreis+150)))&&((posFoxX+groeﬂeFoxX)>dieSteuerung.getPosPipe1X())&&((posFoxX)<(dieSteuerung.getPosPipe1X()+100)))||		
			  (((vektorCP2<radiusKreisDouble)||(vektorDP2<radiusKreisDouble)||(vektorAS2<radiusKreisDouble)||(vektorBS2<radiusKreisDouble)||((dieSteuerung.getPosFoxY())<(zz2-radiusKreis))||((dieSteuerung.getPosFoxY()+groeﬂeFoxY)>(zz2+radiusKreis+150)))&&((posFoxX+groeﬂeFoxX)>dieSteuerung.getPosPipe2X())&&((posFoxX)<(dieSteuerung.getPosPipe2X()+100)))||
			  (((vektorCP3<radiusKreisDouble)||(vektorDP3<radiusKreisDouble)||(vektorAS3<radiusKreisDouble)||(vektorBS3<radiusKreisDouble)||((dieSteuerung.getPosFoxY())<(zz3-radiusKreis))||((dieSteuerung.getPosFoxY()+groeﬂeFoxY)>(zz3+radiusKreis+150)))&&((posFoxX+groeﬂeFoxX)>dieSteuerung.getPosPipe3X())&&((posFoxX)<(dieSteuerung.getPosPipe3X()+100)))) {
			 gameOver = true;
		 }
		 */

		//Kollision 4 ()

		double vektorAS1 = Math.sqrt(Math.pow((diePipe1.getZZ()- 50)-(derFox.getPosY() +  0), 2) + Math.pow((diePipe1.getPosX()+radiusKreis)-(posFoxX + 73), 2));
		double vektorBS1 = Math.sqrt(Math.pow((diePipe1.getZZ()- 50)-(derFox.getPosY() +  6), 2) + Math.pow((diePipe1.getPosX()+radiusKreis)-(posFoxX + 77), 2));
		double vektorCS1 = Math.sqrt(Math.pow((diePipe1.getZZ()- 50)-(derFox.getPosY() + 29), 2) + Math.pow((diePipe1.getPosX()+radiusKreis)-(posFoxX + 89), 2));
		double vektorDP1 = Math.sqrt(Math.pow((diePipe1.getZZ()+200)-(derFox.getPosY() + 42), 2) + Math.pow((diePipe1.getPosX()+radiusKreis)-(posFoxX + 89), 2));
		double vektorEP1 = Math.sqrt(Math.pow((diePipe1.getZZ()+200)-(derFox.getPosY() + 49), 2) + Math.pow((diePipe1.getPosX()+radiusKreis)-(posFoxX + 49), 2));
		double vektorFP1 = Math.sqrt(Math.pow((diePipe1.getZZ()+200)-(derFox.getPosY() + 10), 2) + Math.pow((diePipe1.getPosX()+radiusKreis)-(posFoxX + 10), 2));
		double vektorGS1 = Math.sqrt(Math.pow((diePipe1.getZZ()- 50)-(derFox.getPosY() + 24), 2) + Math.pow((diePipe1.getPosX()+radiusKreis)-(posFoxX + 10), 2));
		double vektorHS1 = Math.sqrt(Math.pow((diePipe1.getZZ()- 50)-(derFox.getPosY() + 14), 2) + Math.pow((diePipe1.getPosX()+radiusKreis)-(posFoxX + 25), 2));
		double vektorIS1 = Math.sqrt(Math.pow((diePipe1.getZZ()- 50)-(derFox.getPosY() +  9), 2) + Math.pow((diePipe1.getPosX()+radiusKreis)-(posFoxX + 49), 2));
		double vektorJS1 = Math.sqrt(Math.pow((diePipe1.getZZ()- 50)-(derFox.getPosY() +  0), 2) + Math.pow((diePipe1.getPosX()+radiusKreis)-(posFoxX + 63), 2));

		double vektorAS2 = Math.sqrt(Math.pow((diePipe2.getZZ()- 50)-(derFox.getPosY() +  0), 2) + Math.pow((diePipe2.getPosX()+radiusKreis)-(posFoxX + 73), 2));
		double vektorBS2 = Math.sqrt(Math.pow((diePipe2.getZZ()- 50)-(derFox.getPosY() +  6), 2) + Math.pow((diePipe2.getPosX()+radiusKreis)-(posFoxX + 77), 2));
		double vektorCS2 = Math.sqrt(Math.pow((diePipe2.getZZ()- 50)-(derFox.getPosY() + 29), 2) + Math.pow((diePipe2.getPosX()+radiusKreis)-(posFoxX + 89), 2));
		double vektorDP2 = Math.sqrt(Math.pow((diePipe2.getZZ()+200)-(derFox.getPosY() + 42), 2) + Math.pow((diePipe2.getPosX()+radiusKreis)-(posFoxX + 89), 2));
		double vektorEP2 = Math.sqrt(Math.pow((diePipe2.getZZ()+200)-(derFox.getPosY() + 49), 2) + Math.pow((diePipe2.getPosX()+radiusKreis)-(posFoxX + 49), 2));
		double vektorFP2 = Math.sqrt(Math.pow((diePipe2.getZZ()+200)-(derFox.getPosY() + 10), 2) + Math.pow((diePipe2.getPosX()+radiusKreis)-(posFoxX + 10), 2));
		double vektorGS2 = Math.sqrt(Math.pow((diePipe2.getZZ()- 50)-(derFox.getPosY() + 24), 2) + Math.pow((diePipe2.getPosX()+radiusKreis)-(posFoxX + 10), 2));
		double vektorHS2 = Math.sqrt(Math.pow((diePipe2.getZZ()- 50)-(derFox.getPosY() + 14), 2) + Math.pow((diePipe2.getPosX()+radiusKreis)-(posFoxX + 25), 2));
		double vektorIS2 = Math.sqrt(Math.pow((diePipe2.getZZ()- 50)-(derFox.getPosY() +  9), 2) + Math.pow((diePipe2.getPosX()+radiusKreis)-(posFoxX + 49), 2));
		double vektorJS2 = Math.sqrt(Math.pow((diePipe2.getZZ()- 50)-(derFox.getPosY() +  0), 2) + Math.pow((diePipe2.getPosX()+radiusKreis)-(posFoxX + 63), 2));

		double vektorAS3 = Math.sqrt(Math.pow((diePipe3.getZZ()- 50)-(derFox.getPosY() +  0), 2) + Math.pow((diePipe3.getPosX()+radiusKreis)-(posFoxX + 73), 2));
		double vektorBS3 = Math.sqrt(Math.pow((diePipe3.getZZ()- 50)-(derFox.getPosY() +  6), 2) + Math.pow((diePipe3.getPosX()+radiusKreis)-(posFoxX + 77), 2));
		double vektorCS3 = Math.sqrt(Math.pow((diePipe3.getZZ()- 50)-(derFox.getPosY() + 29), 2) + Math.pow((diePipe3.getPosX()+radiusKreis)-(posFoxX + 89), 2));
		double vektorDP3 = Math.sqrt(Math.pow((diePipe3.getZZ()+200)-(derFox.getPosY() + 42), 2) + Math.pow((diePipe3.getPosX()+radiusKreis)-(posFoxX + 89), 2));
		double vektorEP3 = Math.sqrt(Math.pow((diePipe3.getZZ()+200)-(derFox.getPosY() + 49), 2) + Math.pow((diePipe3.getPosX()+radiusKreis)-(posFoxX + 49), 2));
		double vektorFP3 = Math.sqrt(Math.pow((diePipe3.getZZ()+200)-(derFox.getPosY() + 10), 2) + Math.pow((diePipe3.getPosX()+radiusKreis)-(posFoxX + 10), 2));
		double vektorGS3 = Math.sqrt(Math.pow((diePipe3.getZZ()- 50)-(derFox.getPosY() + 24), 2) + Math.pow((diePipe3.getPosX()+radiusKreis)-(posFoxX + 10), 2));
		double vektorHS3 = Math.sqrt(Math.pow((diePipe3.getZZ()- 50)-(derFox.getPosY() + 14), 2) + Math.pow((diePipe3.getPosX()+radiusKreis)-(posFoxX + 25), 2));
		double vektorIS3 = Math.sqrt(Math.pow((diePipe3.getZZ()- 50)-(derFox.getPosY() +  9), 2) + Math.pow((diePipe3.getPosX()+radiusKreis)-(posFoxX + 49), 2));
		double vektorJS3 = Math.sqrt(Math.pow((diePipe3.getZZ()- 50)-(derFox.getPosY() +  0), 2) + Math.pow((diePipe3.getPosX()+radiusKreis)-(posFoxX + 63), 2));

		if ((((vektorAS1<radiusKreisDouble)||(vektorBS1<radiusKreisDouble)||(vektorCS1<radiusKreisDouble)||(vektorDP1<radiusKreisDouble)||(vektorEP1<radiusKreisDouble)||(vektorFP1<radiusKreisDouble)||(vektorGS1<radiusKreisDouble)||(vektorHS1<radiusKreisDouble)||(vektorIS1<radiusKreisDouble)||(vektorJS1<radiusKreisDouble)||((derFox.getPosY())<(diePipe1.getZZ()-radiusKreis))||((derFox.getPosY()+groeﬂeFoxY)>(diePipe1.getZZ()+radiusKreis+150)))&&((posFoxX+groeﬂeFoxX)>diePipe1.getPosX())&&((posFoxX)<(diePipe1.getPosX()+100)))||		
				(((vektorAS2<radiusKreisDouble)||(vektorBS2<radiusKreisDouble)||(vektorCS2<radiusKreisDouble)||(vektorDP2<radiusKreisDouble)||(vektorEP2<radiusKreisDouble)||(vektorFP2<radiusKreisDouble)||(vektorGS2<radiusKreisDouble)||(vektorHS2<radiusKreisDouble)||(vektorIS2<radiusKreisDouble)||(vektorJS2<radiusKreisDouble)||((derFox.getPosY())<(diePipe2.getZZ()-radiusKreis))||((derFox.getPosY()+groeﬂeFoxY)>(diePipe2.getZZ()+radiusKreis+150)))&&((posFoxX+groeﬂeFoxX)>diePipe2.getPosX())&&((posFoxX)<(diePipe2.getPosX()+100)))||
				(((vektorAS3<radiusKreisDouble)||(vektorBS3<radiusKreisDouble)||(vektorCS3<radiusKreisDouble)||(vektorDP3<radiusKreisDouble)||(vektorEP3<radiusKreisDouble)||(vektorFP3<radiusKreisDouble)||(vektorGS3<radiusKreisDouble)||(vektorHS3<radiusKreisDouble)||(vektorIS3<radiusKreisDouble)||(vektorJS3<radiusKreisDouble)||((derFox.getPosY())<(diePipe3.getZZ()-radiusKreis))||((derFox.getPosY()+groeﬂeFoxY)>(diePipe3.getZZ()+radiusKreis+150)))&&((posFoxX+groeﬂeFoxX)>diePipe3.getPosX())&&((posFoxX)<(diePipe3.getPosX()+100)))) {
			gameOver = true;
		}

	}

	private void reset () {
		if (pressSpace == true) {
			diePipe1.setPosX(600);
			diePipe2.setPosX(1000);
			diePipe3.setPosX(1400);
			derFox.setPosY(380);
			gameOver = false;
			pressSpace = false;
			start = false;
			derFox.resetPunkte();
		}
	}

	public boolean getHasToFlap () {
		return hasToFlap;
	}

	public boolean getStart () {
		return start;
	}

	public boolean getGameOver () {
		return gameOver;
	
	}

}