// FlappyFox2 :)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FlappyGUI extends JFrame implements Runnable {

	private JPanel contentPane;

	private Image dbImage;
	private Graphics dbg;

	//Die Farbe Hellblau für den Hintergrund erstellen
	public static final Color LIGHT_BLUE = new Color(51,204,255);

	private FlappySteuerung dieSteuerung;

	//Die Variablen für die Images erstellen
	BufferedImage playerImageNormal;
	BufferedImage playerImageSprung;
	BufferedImage pipeImage;
	BufferedImage pipeImageKopf;
	BufferedImage background;
	BufferedImage gameOverIMG;

	public static void main(String[] args) {
		Thread f = new Thread(new FlappyGUI());
		f.start();

	}

	public FlappyGUI() {

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				//Space wurde gedrückt
				case KeyEvent.VK_SPACE:
					dieSteuerung.pressedSpace();
					break;
				}
			}
		});

		//Das Feld zeichnen, in dem das Programm läuft
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true); //Fenster sichtbar machen

		//Images in die Variablen geben
		try {
			playerImageSprung = ImageIO.read(new File("img/FlappyFox80sprung2.png"));
			playerImageNormal = ImageIO.read(new File("img/FlappyFox80normal.png"));
			pipeImage		  = ImageIO.read(new File("img/hunderöhreUltimate.png"));
			pipeImageKopf	  = ImageIO.read(new File("img/hunderöhreUltimateKopf.png"));
			background		  = ImageIO.read(new File("img/background1.png"));
			gameOverIMG		  = ImageIO.read(new File("img/GameOver.png"));
		} catch (IOException e) {
		}

		dieSteuerung = new FlappySteuerung(this);

	}

	@Override
	public void run() {
		//Endlosschleife
		while (true) {
			dieSteuerung.run();
		}
	}

	@Override
	public void paint (Graphics g) {


		//Image für Double Buffer anlegen
		if (dbImage == null) {
			dbImage = createImage(this.getSize().width,
					this.getSize().height);
			dbg = dbImage.getGraphics();					  
		}

		//Hintergrund löschen
		dbg.setColor(Color.white);
		dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);
		//Rahmen zeichnen
		dbg.setColor(LIGHT_BLUE);
		dbg.fillRect(0,0,600,800);
		//Hintergrund
		dbg.drawImage(background, dieSteuerung.getPosBg1X(), 0, this);
		dbg.drawImage(background, dieSteuerung.getPosBg2X(), 0, this);
		//Spieler
		if (dieSteuerung.getHasToFlap() == true) {
			dbg.drawImage(playerImageSprung, dieSteuerung.getPosFoxX(), dieSteuerung.getPosFoxY(), this);
		} else {
			dbg.drawImage(playerImageNormal, dieSteuerung.getPosFoxX(), dieSteuerung.getPosFoxY(), this);
		}
		//Test Pipe 1
		dbg.drawImage(pipeImage, dieSteuerung.getPosPipe1X(), (dieSteuerung.getPipe1zz()+150), this);
		//Test Pipe 2
		dbg.drawImage(pipeImage, dieSteuerung.getPosPipe2X(), (dieSteuerung.getPipe2zz()+150), this);
		//Test Pipe 3
		dbg.drawImage(pipeImage, dieSteuerung.getPosPipe3X(), (dieSteuerung.getPipe3zz()+150), this);
		//Test Pipe Kopf 1
		dbg.drawImage(pipeImageKopf, dieSteuerung.getPosPipe1X(), (dieSteuerung.getPipe1zz()-800), this);
		//Test Pipe Kopf 2
		dbg.drawImage(pipeImageKopf, dieSteuerung.getPosPipe2X(), (dieSteuerung.getPipe2zz()-800), this);
		//Test Pipe Kopf 3
		dbg.drawImage(pipeImageKopf, dieSteuerung.getPosPipe3X(), (dieSteuerung.getPipe3zz()-800), this);		
		//Punkte
		dbg.setFont(new Font("", Font.BOLD, 20));
		dbg.setColor(Color.white);
		dbg.drawString("Punkte: "+dieSteuerung.getPunkte(), 17, 55);
		//Highscore
		dbg.drawString("Highscore: "+dieSteuerung.getHighscore(), 450, 55);
		//Press Space to Start
		if (dieSteuerung.getStart() == false) {
			dbg.drawString("Press space to", 150, 450);
			dbg.drawString("start / flap", 170, 475);
		}
		//Game Over
		if (dieSteuerung.getGameOver() == true) {
			dbg.drawImage(gameOverIMG, 100, 120, this);
		}
		//Nun Offscreen gezeichnetes Bild auf dem richtigen Bildschirm anzeigen
		g.drawImage(dbImage, 0, 0, this);
	}

}