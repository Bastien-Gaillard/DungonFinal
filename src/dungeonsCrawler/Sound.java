package dungeonsCrawler;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
	public void jouerSon(String chemin) {
		try {
			File file = new File(chemin);
			AudioInputStream input=AudioSystem.getAudioInputStream(file);
			Clip clip=AudioSystem.getClip();
			clip.open(input);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	public void jouerSonLoop(String chemin) {
		try {
			File file = new File(chemin);
			AudioInputStream input=AudioSystem.getAudioInputStream(file);
			Clip clip=AudioSystem.getClip();
			clip.open(input);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

	}
}
