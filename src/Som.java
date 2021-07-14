import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Som extends Thread {
	private String audio;
	public Som() {}
	
	public Som(String audio) {
		this.audio = audio;
	}
	public void tocar() {
		FileInputStream fileInputStream;
		try {
			fileInputStream = new FileInputStream(audio);
			 Player player = new Player(fileInputStream);
			 player.play();
		} catch (FileNotFoundException | JavaLayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}
	}
		
	public void run() {
		while(true) {
			tocar();
		}
	}

}
