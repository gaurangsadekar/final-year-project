package edu.jay.fyp.featureextractor.audio;

import java.io.File;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import edu.jay.fyp.featureextractor.database.MetadataMatcher;

public class AudioExtractor {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setLocation(100, 100);
		frame.setSize(1050, 600);

		JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(null);
		File file = fc.getSelectedFile();
		String filePath = file.getAbsolutePath();
		AudioMetaGenerator generator = new AudioMetaGenerator();
		Map<String, String> metaData = generator.getMetaData(filePath);
		MetadataMatcher metadataMatcher = new MetadataMatcher();
		String desiredPath = metadataMatcher.getAudioPath(metaData);
		AudioPlayer player = new AudioPlayer();
		player.playSong(desiredPath);
	}

}
