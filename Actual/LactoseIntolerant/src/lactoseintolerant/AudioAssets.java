/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author 0001058857
 */
public class AudioAssets {
    
    public static void play(final String name){
        try{
            AudioInputStream audioIn=AudioSystem.getAudioInputStream(AudioAssets.class.getResource("src/Audio/"+name+".wav"));
            Clip clip=AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
            System.err.println("Error getting audio/playing:: "+e);
            ErrorLogger.logError(e,"AudioAssets.play(String)");
        }
    }
    
}
