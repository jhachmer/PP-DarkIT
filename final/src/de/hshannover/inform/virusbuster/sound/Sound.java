package de.hshannover.inform.virusbuster.sound;
import java.io.BufferedInputStream;
import java.io.InputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;/** * Class that provides sound playback *  * @author Jannes Hachmer * */
public class Sound {        /**     * Plays file located at given path (USE .wav!)     * (Creates new thread for every file)     * @param fileName Location of Soundfile     */
    public static synchronized void play(String fileName)
    {         
        new Thread(new Runnable() {
            public void run() {
                try {
                    InputStream is= getClass().getResourceAsStream(fileName);
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
                    AudioFormat format = inputStream.getFormat();
                    DataLine.Info info = new DataLine.Info(Clip.class, format);
                    Clip clip = (Clip)AudioSystem.getLine(info);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                    e.printStackTrace();
                }
            }
        }).start();
    }
}