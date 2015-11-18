/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author 0001058857
 */
public class GamePanel extends CPanel implements KeyListener{
    private int civilianSpeed=30,remainder=0,playerScreenChange=0;
    
    public int[] FRAME_SIZE; //not used yet, may want to remove later!                                           <-----
    //hold off till needed for heldKeys::
    //public ArrayList<Character> heldKeys=new ArrayList<Character>();
    
    public Player player=new Player();
    
    public ArrayList<CivilianFlow> civilians=new ArrayList<CivilianFlow>();
    public void changeScreenLocationAllCivilians(int cy){
        for(int i=0;i<civilians.size();i++)
            civilians.get(i).locationPixels[1]+=cy;
    }
    
    public ArrayList<EnemyFlow> enemies=new ArrayList<EnemyFlow>();
    
    public MapManager map=new MapManager(0); //here and set to 0 for now
    
    public int mapMoveDown=0;
    
    public GamePanel(int[] size){
        AIPopulationCheck.start();
        FRAME_SIZE=size;
        
        //temporary to test the civilian::
        civilians.add(new CivilianFlow(0));
    }
    
    public void paintC(Graphics p){ //when mission ends, set player to null, and instantiate another one later with the constructor with one integer argument
        checkCollisions();
        mapDraw(p);
        playerDraw(p);
        civiliansDraw(p);
        drawSpeedBar(p);
        drawHealthBar(p);
    }
    
    
    
    //GRAPHICS FUNCTIONS:: (to break up into parts)::
    //======================================================================VVVV
    private void drawSpeedBar(Graphics p){
        // length of one color on top of the other  (green on gray) :: (player.speed/player.topSpeed[or whatever it was])*totalLength
        //make it pretty thin, and maybe top right or lower left bottom. Think of The Heist 2 style
    }
    
    private void drawHealthBar(Graphics p){
        
    }
    
    private void playerDraw(Graphics p){
        player.draw(p);
        
    }
    
    private void civiliansDraw(Graphics p){
        for(int i=0;i<civilians.size();i++)
            civilians.get(i).draw(p);
        
        changeScreenLocationAllCivilians(-1*(getHowFarGoesUpForCivilians()-playerScreenChange));
    }
    
    private void mapDraw(Graphics p){
        map. moveAllDown(playerScreenChange=player.getMapDown());
        map.draw(p);
    }
    
    private void checkCollisions(){ //note:: for Player/Map collisions it activates a boolean that disables turning; more will need to be added for the more aspects that are added that require the Player car to not be able to turn
        if(!checkPlayerMapCollisions()){
            player.canTurnRight=true;
            player.canTurnLeft=true;
        }
    }
    
    public final int RUNNING_INTO_SIDE_DAMAGE_NUM=5,HIT_MEDIAN_FRONT_ANGLE_CHANGE=20,HIT_MEDIAN_FROM_TOP_CHANGE=20,MEDIAN_ANGLE=5; //maybe change the physics later to having a velocity to the side to bounce off? The intensity is also somwhat represented by the angle though.
    public final double TURNING_AWAY_DAMAGE=0.3,ANYWAY_DAMAGE=0.4;
    private boolean checkPlayerMapCollisions(){
        boolean b=false;
        Rectangle r;
        Polygon p;
        //side collisions::
        if(player.lowerSpan.intersects(map.leftSide)||player.upperSpan.intersects(map.leftSide)){//left side/player collision::
            hittingLeftFromRight(MEDIAN_ANGLE);
            b=true;
        } else if(player.lowerSpan.intersects(map.rightSide)||player.upperSpan.intersects(map.rightSide)){//right side/player collsion::
            hittingRightFromLeft(-1*MEDIAN_ANGLE);
            b=true;
        }
        
        //median collisions::
        else if(map.rLeft1!=null||map.rLeft2!=null){
                if((map.rLeft1!=null&&(player.lowerSpan.intersects(r=map.rLeft1)||player.upperSpan.intersects(r)))
                    ||(map.rLeft2!=null&&(player.lowerSpan.intersects(r=map.rLeft2)||player.upperSpan.intersects(r)))){ 
                    //car hit left side of median
                    hittingRightFromLeft(-1*MEDIAN_ANGLE);
                    b=true;
                    
                }else if((map.rLeft1!=null&&(player.lowerSpan.intersects(r=map.rRight1)||player.upperSpan.intersects(r)))
                        ||(map.rLeft2!=null&&(player.lowerSpan.intersects(r=map.rRight2)||player.upperSpan.intersects(r)))){
                    //car hit right side of median
                    hittingLeftFromRight(MEDIAN_ANGLE);
                    b=true;
                    
                }else if((map.rLeft1!=null&&((p=map.upTri1).intersects(player.lowerSpan)||p.intersects(player.upperSpan)))
                        ||(map.rLeft2!=null&&((p=map.upTri2).intersects(player.lowerSpan)||p.intersects(player.upperSpan)))){
                    //car hit the upper triangular point of the median
                    if(player.locationPixels[0]+player.rectSize[0]>map.CENTER_OF_MEDIAN)
                        hittingLeftFromRight(HIT_MEDIAN_FROM_TOP_CHANGE);
                    else 
                        hittingRightFromLeft(HIT_MEDIAN_FROM_TOP_CHANGE);
                    
                    b=true;
                    
                }else if((map.rLeft1!=null&&((p=map.downTri1).intersects(player.lowerSpan)||p.intersects(player.upperSpan)))
                        ||(map.rLeft2!=null&&((p=map.downTri2).intersects(player.lowerSpan)||p.intersects(player.upperSpan)))){
                    //car hit the lower triangular point of the median
                    if(player.locationPixels[0]+player.rectSize[0]>map.CENTER_OF_MEDIAN)
                        hittingLeftFromRight(HIT_MEDIAN_FRONT_ANGLE_CHANGE);
                    else 
                        hittingRightFromLeft(HIT_MEDIAN_FRONT_ANGLE_CHANGE);
                    
                    b=true;
                }
        }
        
        
        
        
        return b;
    }
    
    private void hittingRightFromLeft(int an){
        player.canTurnRight=false;
        if(player.turningRight){
            player.health-=Math.abs(player.angle)/RUNNING_INTO_SIDE_DAMAGE_NUM+ANYWAY_DAMAGE;
            player.angle=an;
        }else
            player.health-=TURNING_AWAY_DAMAGE;
    }
    
    private void hittingLeftFromRight(int an){
        player.canTurnLeft=false;
        if(player.turningLeft){
            player.health-=Math.abs(player.angle)/RUNNING_INTO_SIDE_DAMAGE_NUM+ANYWAY_DAMAGE;
            player.angle=an;
        }else
            player.health-=TURNING_AWAY_DAMAGE;
    }
    
    int divideBy=3,te;
    private int getHowFarGoesUpForCivilians(){
        remainder+=civilianSpeed%7;
        if((te=remainder/divideBy)>0){
            remainder=remainder%divideBy;
            return (int)(civilianSpeed/divideBy)+te;
        }
        
        return (int)(civilianSpeed/divideBy);
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
    
    public Timer AIPopulationCheck=new Timer(7500, (ActionEvent e) -> {
        
    });
    
}
