package tankgame;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AePlayWave extends Thread {

    private String filename;

    public AePlayWave(String wavfile) { // ¹¹ÔìÆ÷
        filename = wavfile;
    }

    @Override
    public void run() {
        File soundFile = new File(filename);
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundFile);
        } catch (UnsupportedAudioFileException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auLine = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auLine = (SourceDataLine) AudioSystem.getLine(info);
            auLine.open(format);
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        auLine.start();
        int nByteRead = 0;
        byte[] abData = new byte[512];
        try {
            while (nByteRead != -1) {

                nByteRead = audioInputStream.read(abData, 0, abData.length);

                if (nByteRead >= 0) {
                    auLine.write(abData, 0, nByteRead);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        } finally {
            auLine.drain();
            auLine.close();
        }

    }

}
