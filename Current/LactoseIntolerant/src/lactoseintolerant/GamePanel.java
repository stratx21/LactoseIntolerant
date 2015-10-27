/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author 0001058857
 */
public class GamePanel extends CPanel implements KeyListener{
    //hold off till needed for heldKeys::
    //public ArrayList<Character> heldKeys=new ArrayList<Character>();
    
    public Player player=new Player();
    public MapManager map=new MapManager(0); //here and set to 0 for now
    
    public int mapMoveDown=0;
    
    public GamePanel(){
        
    }
    
    public void paintC(Graphics p){ //when mission ends, set player to null, and instantiate another one later with the constructor with one integer argument
        checkPlayerMapCollisions();
        mapDraw(p);
        playerDraw(p);
    }
    
    
    
    //GRAPHICS FUNCTIONS:: (to break up into parts)::
    //======================================================================VVVV
    private void drawSpeedBar(Graphics p){
        // length of one color on top of the other  (green on gray) :: (player.speed/player.topSpeed[or whatever it was])*totalLength
        //make it pretty thin, and maybe top right or lower left bottom. Think of The Heist 2 style
    }
    
    private void playerDraw(Graphics p){
        player.draw(p);
        
    }
    
    private void checkPlayerMapCollisions(){
        
    }
    
    private void mapDraw(Graphics p){
        int t;
        map.startLocationOne[1]+=(t=player.getMapDown());
        map.startLocationTwo[1]+=t;
        player.distancePixelsTotal+=t;
        map.draw(p);
    }
    
    
    
    
    //KEY LISTENER RESOURCES:: 
    //=====================================================================VVVVV
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
                player.shouldCheckStoppedTurning=true;
                break;
            case 'D':
                player.turningRight=false;
                player.shouldCheckStoppedTurning=true;
                break;
//            case ' ':
//                player.attacking=false;
//                break;
        }
    }
    
}
