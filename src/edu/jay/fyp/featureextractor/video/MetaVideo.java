package edu.jay.fyp.featureextractor.video;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JPanel;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaMeta;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;

public class MetaVideo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),
				"C:\\Program Files\\VideoLAN\\VLC");
		Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
		MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        Canvas c = new Canvas();
        c.setBackground(Color.black);
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(c, BorderLayout.CENTER);
        EmbeddedMediaPlayer mediaPlayer =mediaPlayerFactory.newEmbeddedMediaPlayer();
        mediaPlayer.setVideoSurface(mediaPlayerFactory.newVideoSurface(c));
        mediaPlayer.prepareMedia("D:\\fyp\\hollywood\\videoclips\\930f3282ec1cb9e4e407d869fd34b5bb.avi", ":start-time=30");
        MediaMeta metadata = mediaPlayer.getMediaMeta();
        System.out.println(metadata.getEncodedBy());
        System.out.println(metadata.getDate());
        
	}
}
