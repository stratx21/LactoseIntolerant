/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 *
 * @author 0001058857
 */
public class GamePanel extends CPanel implements KeyListener{
    //hold off till needed for heldKeys::
    //public ArrayList<Character> heldKeys=new ArrayList<Character>();
    
    public Player player=new Player();
    
    public GamePanel(){
        
    }
    
    public void paintC(Graphics p){
        player.draw(p);
    }
    
    //GRAPHICS FUNCTIONS:: (to break up into parts)::
    //======================================================================VVVV
    
    

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(Character.toUpperCase(e.getKeyChar())){
            case 'W':
                player.accelerating=true;
                player.brakes=false;//if was applying brakes at the same time, then the acceleration overrides.
                break;
            case 'S':
                player.brakes=true;
                player.accelerating=false;//if was accelerating, then the brakes will override.
                break;
            case 'A':
                player.turningLeft=true;
                player.turningRight=false;//if was turning right, then starts to turn left instead.
                break;
            case 'D':
                player.turningRight=true;
                player.turningLeft=false;//if was turning left, then starts to turn right instead.
                break;
            case ' ':
                if(player.canAttack){
                    player.canAttack=false;
                }
                break;
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(Character.toUpperCase(e.getKeyChar())){
            case 'W':
                player.accelerating=false;
                break;
            case 'S':
                player.brakes=false;
                break;
            case 'A':
                player.turningLeft=false;
                break;
            case 'D':
                player.turningRight=false;
                break;
//            case ' ':
//                player.attacking=false;
//                break;
        }
    }
    
}
