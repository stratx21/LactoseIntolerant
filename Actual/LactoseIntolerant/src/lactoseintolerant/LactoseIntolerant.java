/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import javax.swing.JFrame;

/**
 *
 * @author 0001058857
 */
public class LactoseIntolerant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LactoseIntolerant.class.getResource("/Fonts/AA_typewriter.ttf");
        JFrame.setDefaultLookAndFeelDecorated(true);
        new StartGameFlow();
    }
    
    //to go to menu and everything, toggle commented parts in StartGameFlow.
    //^also remove the first line in the main, GraphicsAssets.importImages() for running the full game.
    
    /**
     * other stuff to do!!!::
     * - in Player, make it so when stopped the car cannot turn at all. Modify max and min turn rate based on speed?
     */
    
}
