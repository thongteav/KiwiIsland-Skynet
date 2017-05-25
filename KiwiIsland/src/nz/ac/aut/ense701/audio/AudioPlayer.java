/*
 * Title: AudioPlayer source code
 * Author: ForeignGuyMike
 * Date: 30/04/17
 * Code Version: 1.0
 * Availability: https://www.dropbox.com/s/fimwys7jk51umfk/Dragon%20Tale%20Tutorial%20P08.rar?dl=0
 */

package nz.ac.aut.ense701.audio;

import java.io.File;
import javax.sound.sampled.*;

public class AudioPlayer {

    private Clip clip;
    
    //Constructor loads inputted file and decodes the MP3 through imported libraries
    //since MP3 files are not supported in Java by default
    public AudioPlayer(File file) {

        try {
            AudioInputStream ais
                    = AudioSystem.getAudioInputStream(
                            file
                    );
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    baseFormat.getSampleRate(),
                    16,
                    baseFormat.getChannels(),
                    baseFormat.getChannels() * 2,
                    baseFormat.getSampleRate(),
                    false
            );
            AudioInputStream dais
                    = AudioSystem.getAudioInputStream(
                            decodeFormat, ais);
            clip = AudioSystem.getClip();
            clip.open(dais);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    //Plays decoded file. If the clip is already running, issues stop command
    public void play() {
        if (clip == null) {
            return;
        }
        stop();
        clip.setFramePosition(0);
        clip.start();
    }
    
    public void loopPlay() {
        long time = clip.getMicrosecondLength();
        while(!clip.isRunning()){
            this.play();
        }
    }

    public void stop() {
        if (clip.isRunning()) {
            clip.stop();
        }
    }

    public void close() {
        stop();
        clip.close();
    }

}
