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
public class GamePanel extends CPanel implements KeyListener,Runnable{
    public int screenDistortY=0; //for if the player stops, then the screen goes up to follow then bounces back, etc.
    
//    public Timer calcTimer=new Timer(40, (ActionEvent e) -> {
//        calcFlow();
//    });
    
    private int civilianSpeed=30,remainder=0,playerScreenChange=0;
    
    public int[] FRAME_SIZE; //not used yet, may want to remove later!                                           <-----
    //hold off till needed for heldKeys::
    //public ArrayList<Character> heldKeys=new ArrayList<Character>();
    
    public Player player=new Player();
    
    public ArrayList<CivilianFlow> civilians=new ArrayList<CivilianFlow>();
    public void changeScreenLocationAllCivilians(int cy){
        for(int i=0;i<civilians.size();i++){
            civilians.get(i).calculate(cy,screenDistortY);
        }
    }
    
    public ArrayList<EnemyFlow> enemies=new ArrayList<EnemyFlow>();
    
    public MapManager map=new MapManager(0); //here and set to 0 for now
    
    public int mapMoveDown=0;
    
    public GamePanel(int[] size){
        //calcTimer.start();
        (new Thread(this)).start();
        AIPopulationCheck.start();
        FRAME_SIZE=size;
        
        //temporary to test the civilian::
        civilians.add(new CivilianFlow(0));
    }
    
    public void paintC(Graphics p){ //when mission ends, set player to null, and instantiate another one later with the constructor with one integer argument
        //affected by screenDistortY::
        mapDraw(p);
        player.draw(p,screenDistortY);
        civiliansDraw(p);
        
        //unaffected:: 
        drawSpeedBar(p);
        drawHealthBar(p);
    }
    
    private void calcFlow(){
        playerCalc();
        map.moveAllDown(playerScreenChange=player.getMapDown());
        map.calculate();
        changeScreenLocationAllCivilians(-1*(getHowFarGoesUpForCivilians()-playerScreenChange));
        checkCollisions();
    }
    
    
    
    //GRAPHICS FUNCTIONS:: (to break up into parts)::
    //======================================================================VVVV
    private void drawSpeedBar(Graphics p){
        // length of one color on top of the other  (green on gray) :: (player.speed/player.topSpeed[or whatever it was])*totalLength
        //make it pretty thin, and maybe top right or lower left bottom. Think of The Heist 2 style
    }
    
    private void drawHealthBar(Graphics p){
        
    }
    
    private void playerCalc(){
        int t=player.calculate(screenDistortY);
        
        if(t==0)
            screenDistortY-=screenDistortY/10;
        else{
            if((screenDistortY<player.speed&&t>0)||(t<0&&screenDistortY>player.speed))
                screenDistortY+=t/3;
        }
    }
    
    private void civiliansDraw(Graphics p){
        for(int i=0;i<civilians.size();i++)
            civilians.get(i).draw(p,screenDistortY);
    }
    
    private void mapDraw(Graphics p){
        map.draw(p,screenDistortY);
    }
    
    private void checkCollisions(){ //note:: for Player/Map collisions it activates a boolean that disables turning; more will need to be added for the more aspects that are added that require the Player car to not be able to turn
        checkPlayerMapCollisions();
        checkPlayerCivilianCollisions();
    }
    
    private final int RUNNING_INTO_SIDE_DAMAGE_NUM=5,HIT_MEDIAN_FRONT_ANGLE_CHANGE=20,HIT_MEDIAN_FROM_TOP_CHANGE=20,MEDIAN_ANGLE=0; //maybe change the physics later to having a velocity to the side to bounce off? The intensity is also somwhat represented by the angle though.
    private final double TURNING_AWAY_DAMAGE=0.3,ANYWAY_DAMAGE=0.4;
    private void checkPlayerMapCollisions(){
        boolean b=false;
        Rectangle r;
        Polygon p;
        //side collisions::
        if(player.lowerSpan.intersects(map.leftSide)||player.upperSpan.intersects(map.leftSide)){//left side/player collision::
            hittingLeftFromRight(MEDIAN_ANGLE);
            player.screenLocation[0]=132-10;
            b=true;
        } else if(player.lowerSpan.intersects(map.rightSide)||player.upperSpan.intersects(map.rightSide)){//right side/player collsion::
            hittingRightFromLeft(-1*MEDIAN_ANGLE);
            player.screenLocation[0]=872-player.IMG_BLANK_SPACE[0]-player.CAR_PIXELS_HORIZONTAL;
            b=true;
        }
        
        //median collisions::
        else if(map.rLeft1!=null||map.rLeft2!=null){
                if((map.rLeft1!=null&&((p=map.upTri1).intersects(player.lowerSpan)||p.intersects(player.upperSpan)))
                        ||(map.rLeft2!=null&&((p=map.upTri2).intersects(player.lowerSpan)||p.intersects(player.upperSpan)))){
                    //car hit the upper triangular point of the median
                    if(player.screenLocation[0]+player.rectSize[0]>map.CENTER_OF_MEDIAN)
                        hittingLeftFromRight(HIT_MEDIAN_FROM_TOP_CHANGE);
                    else 
                        hittingRightFromLeft(HIT_MEDIAN_FROM_TOP_CHANGE);
                    
                    b=true;
                    
                }else if((map.rLeft1!=null&&((p=map.downTri1).intersects(player.lowerSpan)||p.intersects(player.upperSpan)))
                        ||(map.rLeft2!=null&&((p=map.downTri2).intersects(player.lowerSpan)||p.intersects(player.upperSpan)))){
                    //car hit the lower triangular point of the median
                    if(player.screenLocation[0]+player.rectSize[0]>map.CENTER_OF_MEDIAN){//right part
                        hittingLeftFromRight(HIT_MEDIAN_FRONT_ANGLE_CHANGE);
                        player.screenLocation[0]+=5;
                        player.speed=5;
                    }else {                                                             //left part
                        hittingRightFromLeft(-1*HIT_MEDIAN_FRONT_ANGLE_CHANGE);
                        player.screenLocation[0]-=5;
                        player.speed=5;
                    }
                    
                    b=true;
                }else if((map.rLeft1!=null&&(player.lowerSpan.intersects(r=map.rLeft1)||player.upperSpan.intersects(r)))
                    ||(map.rLeft2!=null&&(player.lowerSpan.intersects(r=map.rLeft2)||player.upperSpan.intersects(r)))){ 
                    //car hit left side of median
                    hittingRightFromLeft(-1*MEDIAN_ANGLE);
                    player.screenLocation[0]=420-player.IMG_BLANK_SPACE[0]-player.CAR_PIXELS_HORIZONTAL;
                    b=true;
                    
                }else if((map.rLeft1!=null&&(player.lowerSpan.intersects(r=map.rRight1)||player.upperSpan.intersects(r)))
                        ||(map.rLeft2!=null&&(player.lowerSpan.intersects(r=map.rRight2)||player.upperSpan.intersects(r)))){
                    //car hit right side of median
                    hittingLeftFromRight(MEDIAN_ANGLE);
                    player.screenLocation[0]=590-player.IMG_BLANK_SPACE[0];
                    b=true;
                    
                }
        }
        
        
        if(!b){
            player.canTurnRight=true;
            player.canTurnLeft=true;
        } else player.colliding=false;
    }
    
    private final double REAR_ENDED_DAMAGE=1.5;
    private void checkPlayerCivilianCollisions(){
        for(int i=0;i<civilians.size();i++){
            CivilianFlow civ=civilians.get(i);
            System.out.println("check"+player.angle+civ.contactWithPlayer);
            
            //ordered in this way for effects
            if(player.upperSpan.intersects(civ.upperSpan)){
                System.out.println("top hit top");
                //upper player hit upper civilian
                if(player.screenLocation[0]+player.IMG_BLANK_SPACE[0]<civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0]){
                    //upper hit upper, player on left civilian on right
                    civ.angle=player.angle+player.speed/5;
                } else{
                    //upper hit upper, player on right civilian on left
                    civ.angle=player.angle-player.speed/5;
                }
            } else if(player.lowerSpan.intersects(civ.upperSpan)){
                //civilian ran into the back of the player
                
            } else if(player.upperSpan.intersects(civ.lowerSpan)){
                //upper player hit lower civilian
                
            } else if(player.lowerSpan.intersects(civ.lowerSpan)){
                if(player.screenLocation[0]<civ.screenLocation[0]){
                    //lower hit lower, player on left civilian on right
                } else{
                    //lower hit lower, player on right civilian on left
                }
            } else if(civ.contactWithPlayer){
                civ.contactWithPlayer=false;
            }
        }
    }
    
    private void hittingRightFromLeft(int an){
        player.canTurnRight=false;
        if(player.turningRight){
            player.health-=Math.abs(player.angle)/RUNNING_INTO_SIDE_DAMAGE_NUM+ANYWAY_DAMAGE;
            
        }else
            player.health-=TURNING_AWAY_DAMAGE;
        player.angle=an;
    }
    
    private void hittingLeftFromRight(int an){
        player.canTurnLeft=false;
        if(player.turningLeft){
            player.health-=Math.abs(player.angle)/RUNNING_INTO_SIDE_DAMAGE_NUM+ANYWAY_DAMAGE;
            
        }else
            player.health-=TURNING_AWAY_DAMAGE;
        player.angle=an;
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

    public int calcDelay=40;
    @Override
    public void run() {
        calcFlow();
        try{Thread.sleep(calcDelay);}
        catch(Exception e){ErrorLogger.logError(e,"run");}
        run();
    }
    
}
