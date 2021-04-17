// FlappyFox2
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HighscoreHandler {

	Scanner s;
	FileWriter writer;
	public int hs;

	HighscoreHandler (File highscore) {

		try {
			s = new Scanner(highscore);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (s.hasNext()) {
			int hs = s.nextInt();	
			this.hs = hs;
		}


	}

	public void neuerHighscore (int neuHs, File highscore) {

		hs = neuHs;
		
		try {
			writer = new FileWriter(highscore);
			String neuHighscore = String.valueOf(neuHs);
			writer.write(neuHighscore);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int highscoreLesen () {
		return hs;
	}
	
	public void schliessen () {
		s.close();
	}

}
