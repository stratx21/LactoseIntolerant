/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author 0001058857
 */
public class GamePanel extends CPanel implements KeyListener,Runnable{
    public int screenDistortY=0; //for if the player stops, then the screen goes up to follow then bounces back, etc.
    //^only used for drawing!!!!!!!!!!!!!!!!!!!!!!!
    public long time=0;
    
    public boolean paused=true, canUnpause=false;
    
    public boolean done=false;
    
    public long objectiveTime=10000;
    
    public final int[] laneStarts=new int[]{201,248,294,340,628,674,720,766};
    
    public ArrayList<Explosion> explosions=new ArrayList<Explosion>();
    
//    public Timer calcTimer=new Timer(40, (ActionEvent e) -> {
//        calcFlow();
//    });
    
    private int playerScreenChange=0;
    
    public int MAX_ENEMIES=0;
    
    //hold off till needed for heldKeys::
    //public ArrayList<Character> heldKeys=new ArrayList<Character>();
    
    /**
     *main Player object used throughout the code as the user
     */
    public Player player=new Player();
    
    public ArrayList<CivilianFlow> civilians=new ArrayList<CivilianFlow>();
    
    public ArrayList<EnemyFlow> enemies=new ArrayList<EnemyFlow>();
    
    public MapManager map=null; //here and set to 0 for now
    
    public int mapMoveDown=0;
    
    public int level=0;
    
    /**
     *
     * @param size size of JPanel, index 0 as the x size and 
     *      index 1 as the y size
     * @param lv difficulty of this round instantiated
     */
    public GamePanel(int[] size,int lv,CListener done){
        map=new MapManager(0,1,done);
        //calcTimer.start();
//        (new Thread(this)).start();
        AIPopCheck.start();
        FRAME_SIZE=size;
        level=lv;
        
        objectiveTime=map.currentLevel.objectiveTime;
        
        System.out.println("thru the GamePanel constructor");
    }
    
    /**
     * the function for drawing parts for the GamePanel. Calls the functions to
     * draw the panel
     * 
     * @param p Graphics class instance that is being used to draw on the panel
     */
    @Override
    public void paintComponent(Graphics p){
                paintC(p);
                calcFlow();
                
                System.out.println("painting!!!!!!!!(from GamePanel)");
                
                try{Thread.sleep(20);}
                catch(Exception e){ErrorLogger.logError(e,"GameFlow.paintComponent");}
                repaint();
    }
    
    /**
     * function called by the class that holds an instance of this class to 
     * paint the panel; this is a function call relay rather than the overriden
     * paintComponent(Graphics) function.
     * 
     * @param p Graphics class instance that is being used to draw on the panel
     */
    public void paintC(Graphics p){ //when mission ends, set player to null, and instantiate another one later with the constructor with one integer argument
        //affected by screenDistortY::
        mapDraw(p);
        player.draw(p,screenDistortY);
        civiliansDraw(p);
        enemiesDraw(p);
        explosionsDraw(p);
        
        //unaffected:: 
        drawSpeedBar(p);
        drawHealthBar(p);
        drawTimer(p);
    }
    
    
    /**
     * function that calls the other functions that calculate the different
     * parts of the game
     */
    public void calcFlow(){
        playerCalc();
        
        map.moveAllDown(playerScreenChange=player.getMapDown()/2);
        map.calculate();
        CivilianCalc();
        enemyCalc();
    }
    
    /**
     * function called to calculate the enemy statistics and what it affects
     */
    private void enemyCalc(){
        for(int i=0;i<enemies.size();i++){
            EnemyFlow e=enemies.get(i);
            if(e.health>0){
                e.toPlayerDifference[0]=player.screenLocation[0]-e.screenLocation[0];
                e.toPlayerDifference[1]=player.screenLocation[1]-e.screenLocation[1];
                
//                System.out.println(e.toPlayerDifference[0]+", "+e.toPlayerDifference[1]+" - "+e.speed);
                
                e.calculate(-1*(getHowFarGoesUpForAIs((int)e.speed)/2-playerScreenChange),screenDistortY);
                checkEnemyCollisions(e,i);
            } else{
                explosions.add(new Explosion(e.screenLocation[0]+e.imageSize[0],e.screenLocation[1]+e.imageSize[1]/2));
                enemies.remove(i);
                i--;
            }
        }
    }
    
    /**
     * function called to calculate the enemy statistics and whatever else the
     * civilians may affect
     */
    private void CivilianCalc(){
        for(int i=0;i<civilians.size();i++){
            CivilianFlow c=civilians.get(i);
            if(c.health>0){
                c.calculate(-1*(getHowFarGoesUpForAIs((int)c.speed)/2-playerScreenChange),screenDistortY);
                checkCivilianCollisions(c,i);
            }else{
                explosions.add(new Explosion(c.screenLocation[0]+c.imageSize[0]/2,c.screenLocation[1]+c.imageSize[1]/2));
                civilians.remove(i);
                i--;
            }
        }
    }
    
    //GRAPHICS FUNCTIONS:: (to break up into parts)::
    //======================================================================VVVV
    
    /////////////////////////////////////////////////////////////////////////
    //effects::::
    
    /**
     * draw/calculate the explosion
     * 
     * @param p Graphics class instance that is being used to draw on the panel
     */
    private void explosionsDraw(Graphics p){
        for(int i=0;i<explosions.size();i++){
            Explosion e;
            (e=explosions.get(i)).draw(p,screenDistortY);
            e.center.y+=playerScreenChange;
            if(e.expired){
                explosions.remove(i);
                i--;
            }
            
        }
    }
    
    /////////////////////////////////////////////////////////////////////////
    //game components:::::
    
    /**
     * draw the speed bar to represent the player's speed
     * 
     * @param p Graphics class instance that is being used to draw on the panel
     */
    private void drawSpeedBar(Graphics p){
        // length of one color on top of the other  (green on gray) :: (player.speed/player.topSpeed[or whatever it was])*totalLength
        //make it pretty thin, and maybe top right or lower left bottom. Think of The Heist 2 style
    }
    
    /**
     * draw the health bar to represent the player's health
     * 
     * @param p Graphics class instance that is being used to draw on the panel
     */
    private void drawHealthBar(Graphics p){
//        DecimalFormat dec = new DecimalFormat("0.00");
        int R=237-(int)(Math.abs(player.ORIGINAL_HEALTH-player.health)/4),
                G=28-(int)(Math.abs(player.ORIGINAL_HEALTH-player.health)/9),
                B=36-(int)(Math.abs(player.ORIGINAL_HEALTH-player.health)/9);
        if(R<0)
            R=0;
        else if(R>250)
            R=250;
        
        if(G<0)
            G=0;
        else if(G>250)
            G=250;
        
        if(B<0)
            B=0;
        else if(B>250)
            B=250;
        
        
        p.setColor(new Color(R,G,B));
        p.fillRect(10,10,(int)(player.health),15);
//        p.setColor(Color.gray);
//        p.setFont(new Font(Font.SERIF,Font.BOLD,20));
//        p.drawString(dec.format(player.health),60,10);
    }
    
    /**
     * draw the timer to show the player how much time they have been playing 
     * for so they can beat the objective time
     * 
     * @param p Graphics class instance that is being used to draw on the panel
     */
    private void drawTimer(Graphics p){
        try{
        p.setColor(Color.black);
        p.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/Fonts/straight.ttf")).deriveFont(18f));
        p.drawString(""+new DecimalFormat("000.000").format((time-3000)/1000.0f),10,75);
        } catch(Exception e){
            ErrorLogger.logError(e,"GamePanel.drawTimer(Graphics)");
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //player functions:::::
    
    /**
     * calculate the player statistics. This is to be called after the delay so
     * it is called every however many milliseconds the delay is
     * 
     */
    private void playerCalc(){
        player.hittingSideMap=checkPlayerMapCollisions();
        int t=player.calculate(screenDistortY);
        
        if(t==0)
            screenDistortY-=screenDistortY/10;
        else{
            if((screenDistortY<player.speed&&t>0)||(t<0&&screenDistortY>player.speed))
                screenDistortY+=t/3;
        }
    }
    
    /**
     * calculate the collisions between the player and the map, including the
     * flow and effects
     */
    private final int HIT_MEDIAN_FRONT_ANGLE_CHANGE=20,HIT_MEDIAN_FROM_TOP_CHANGE=20,MEDIAN_ANGLE=0;
    private boolean checkPlayerMapCollisions(){
        boolean b=false;
        Rectangle r;
        Polygon p;
        //side collisions::
        if(player.lowerSpan.intersects(map.leftSide)||player.upperSpan.intersects(map.leftSide)){//left side/player collision::
            hittingLeftFromRight(MEDIAN_ANGLE);
            player.screenLocation[0]=map.leftSide.x+map.leftSide.width-player.IMG_BLANK_SPACE[0];//132-10;
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
        
        
        if(b){
            player.colliding=false;
            player.health-=0.15;
        }
        
        return b;
    }
    
    /**
     * returns true if the player is to the right of the CivilianFlow instance
     * civ
     * 
     * @param civ the CivilianFlow instance to tell if the player is to the 
     * right of this CivilianFlow
     * 
     * @return returns true if the player is to the right proportionally to the
     * CivilianFlow instance civ
     */
    private boolean playerIsToRightOfCiv(CivilianFlow civ){
        return player.screenLocation[0]+player.imageSize[0]/2>=civ.screenLocation[0]+civ.imageSize[0]/2;
    }
    
    /**
     * old version of the player-civilian collisions 
     * 
     * @param civ 
     */
    @Deprecated
    private void aOLDcheckPlayerCivilianCollisions(CivilianFlow civ){
        if(player.upperSpan.intersects(civ.lowerSpan)
                ||player.upperSpan.intersects(civ.upperSpan)
                ||player.lowerSpan.intersects(civ.upperSpan)
                ||player.lowerSpan.intersects(civ.lowerSpan)){//civilian is hit
            civ.health-=3;
            player.health-=0.07;
            
            if(!civ.collidingWithPlayer){
                
                civ.collidingWithPlayer=true;
                
                if(player.screenLocation[1]+player.IMG_BLANK_SPACE[1]>civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]+civ.CAR_PIXELS_VERTICAL*(verticalDetectRatioPlayerOnTop)){
                    //player top is below 3/4 down of the civilian, and therefore the civilian should be pushed up and the angle changed
                    civ.hitPlayerFromSide=false;
                    if(!civ.collidingWithMap)
                        civ.angle=player.angle/2
                                -3*((player.screenLocation[0]+player.imageSize[0]/2)-(civ.screenLocation[0]+civ.imageSize[0]/2));
                    else
                        civ.angle=0;
                    
                    civ.speed+=2.5*(player.speed-civ.speed)+3;
                    player.speed*=3/5;
                    
                } else if(player.screenLocation[1]+player.IMG_BLANK_SPACE[1]+(verticalDetectRatioPlayerOnTop)*player.CAR_PIXELS_VERTICAL<civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]){
                    civ.hitPlayerFromSide=false;
                    swapVA(civ);
                }else{             //is from the side
                    civ.hitPlayerFromSide=true; 
                //was not hit from the bottom or the top; was the side::    VVVVVVVV
                    
                    if(!civ.collidingWithMap&&!civ.rightNextToSide){
                        civ.angle=player.angle*2;
                        player.angle/=-2;
                                
                        if(playerIsToRightOfCiv(civ)){ //player is to the right of the hit civ
                            if(player.screenLocation[0]+player.IMG_BLANK_SPACE[0]<civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0]+civ.CAR_PIXELS_HORIZONTAL)
                                civ.screenLocation[0]=player.screenLocation[0]+player.IMG_BLANK_SPACE[0]-civ.imageSize[0]-extraSpaceBetweenCarsOnHitRight; 
                        } else{  //player is to the left of the hit civ
                            if(player.screenLocation[0]+player.IMG_BLANK_SPACE[0]+player.CAR_PIXELS_HORIZONTAL>civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0])
                                civ.screenLocation[0]=player.screenLocation[0]+player.IMG_BLANK_SPACE[0]+player.CAR_PIXELS_HORIZONTAL+extraSpaceBetweenCarsOnHitLeft;
                        }
                    
                    } else{ //the civ is hitting the side of the map
                        player.angle*=-2;
                    }
            }                                                       // ^^^^^^^^
                
            
            } else{ //was previously colliding::
                if((playerIsToRightOfCiv(civ)||(civ.rightNextToSide&&!civ.hitMapOnRightSideOfCar))&&!civ.hittingRightSideOfMap){
                    player.screenLocation[0]=civ.screenLocation[0]+civ.imageSize[0]-civ.IMG_BLANK_SPACE[0]-player.IMG_BLANK_SPACE[0];
                    if(civ.rightNextToSide)
                        player.screenLocation[0]+=15;
                } else{
                    player.screenLocation[0]=civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0]+player.IMG_BLANK_SPACE[0]-player.imageSize[0]-leftExtraSpace;
                    if(civ.rightNextToSide)
                        player.screenLocation[0]-=5;
                }
            }
            
        } else {
            civ.collidingWithPlayer=false;
        }
        
    }
    
    /**
     * check the player-civilian collisions for each civilian
     * 
     * @return civ CivilianFlow instance used to tell if the civilian is in
     * collision with the player, and includes the flow for the cases
     */
    private double playerToCivAngleMultiple=3;
    private void checkPlayerCivilianCollisions(CivilianFlow civ){
        if(player.upperSpan.intersects(civ.upperSpan)
            ||player.upperSpan.intersects(civ.lowerSpan)
            ||player.lowerSpan.intersects(civ.upperSpan)
            ||player.lowerSpan.intersects(civ.lowerSpan)){
            
            if(!civ.collidingWithPlayer){//just started colliding
                civ.collidingWithPlayer=true;
                civ.health-=3;
                player.health-=0.07;
                if(player.screenLocation[1]+player.IMG_BLANK_SPACE[1]>civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]+civ.CAR_PIXELS_VERTICAL*(verticalDetectRatioPlayerOnBottom)){//player is below civ
                    //player top is below 3/4 down of the civilian, and therefore the civilian should be pushed up and the angle changed
                    civ.hitPlayerFromSide=false;
                    if(!civ.collidingWithMap)
                        civ.angle=player.angle/2
                                -3*((player.screenLocation[0]+player.imageSize[0]/2)-(civ.screenLocation[0]+civ.imageSize[0]/2));
                    else
                        civ.angle=0;
                    
                    civ.speed+=2.5*(player.speed-civ.speed)+3;
                    player.speed*=3/5;
                    
                } else if(player.screenLocation[1]+player.IMG_BLANK_SPACE[1]+(verticalDetectRatioPlayerOnTop)*player.CAR_PIXELS_VERTICAL<civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]){//player is above civ
                    civ.hitPlayerFromSide=false;
                    swapVA(civ);
                }else{
                if(playerIsToRightOfCiv(civ)){
                    if((civ.rightNextToSide)){
                        player.screenLocation[0]=civ.screenLocation[0]+civ.imageSize[0]-civ.IMG_BLANK_SPACE[0]-player.IMG_BLANK_SPACE[0];
                        player.screenLocation[0]+=15;
                        player.angle=15;
                    } else{
                    civ.screenLocation[0]=player.screenLocation[0]+player.IMG_BLANK_SPACE[0]+civ.IMG_BLANK_SPACE_ACTUAL[0]-civ.imageSizeActual[0]+(int)(player.angle/3)
                            //-(int)(player.speed/3)
                            ;
                    if(player.turningRight){//shouldnt be turning right, therefore the collision must be a glitch or was caused by a civilian
                        civ.speed=player.speed;
                        if(civ.angle>player.angle-3&&civ.angle<player.angle+3){
                            civ.angle=-25;
                            player.angle=25;
                        } else{
                        civ.angle=player.angle*playerToCivAngleMultiple;
                        
                        
                        if(civ.angle<-25)
                            player.angle=-10;
                        else
                            player.angle=5;
//                        player.angle*=-0.5;
                        }
                    } else{  //normal
                        civ.speed=player.speed;
                        if(civ.angle>player.angle-3&&civ.angle<player.angle+3){
                            civ.angle=-25;
                            player.angle=25;
                        } else{
                        civ.angle=player.angle*playerToCivAngleMultiple;
                        if(civ.angle<-25)
                            player.angle=10;
                        else
                            player.angle=-5;
//                        player.angle*=-0.5;
                        }
                    }
                    }
                } else{ //player is to the left of the civilian
                    if((civ.rightNextToSide)){
                    player.screenLocation[0]=civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0]+player.IMG_BLANK_SPACE[0]-player.imageSize[0]-leftExtraSpace;
                    player.screenLocation[0]-=5;
                    player.angle=-15;
                    } else{
                    civ.screenLocation[0]=player.screenLocation[0]+player.imageSize[0]-player.IMG_BLANK_SPACE[0]-civ.IMG_BLANK_SPACE_ACTUAL[0]+5+(int)(player.angle/3)
                            //+(int)(player.speed/3)
                            ;
                    if(player.turningLeft){//shouldnt be turning left
                        civ.speed=player.speed;
                        if(civ.angle>player.angle-3&&civ.angle<player.angle+3){
                            civ.angle=25;
                            player.angle=-25;
                        } else{
                        civ.angle=player.angle*playerToCivAngleMultiple;
                        
                        if(civ.angle<-25)
                            player.angle=10;
                        else
                            player.angle=-5;
//                        player.angle*=-0.5;
                        }
                        
                    } else{  //normal
                        civ.speed=player.speed;
                        if(civ.angle>player.angle-3&&civ.angle<player.angle+3){
                            civ.angle=25;
                            player.angle=-25;
                        } else{
                        civ.angle=player.angle*playerToCivAngleMultiple;
                        if(civ.angle<-25)
                            player.angle=-10;
                        else
                            player.angle=5;
//                        player.angle*=-0.5;
                        }
                    }
                    }
                }
                }
                
                
            } else{ //was already colliding
                if(player.screenLocation[1]+player.IMG_BLANK_SPACE[1]>civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]+civ.CAR_PIXELS_VERTICAL*(verticalDetectRatioPlayerOnBottom)){ //player below civ
                    civ.speed+=5;
                    player.speed-=10;
                } else if(player.screenLocation[1]+player.IMG_BLANK_SPACE[1]+(verticalDetectRatioPlayerOnTop)*player.CAR_PIXELS_VERTICAL<civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]){ //player above civ
                    civ.hitPlayerFromSide=false;
                    player.speed+=5;
                    civ.speed-=5;
                }else{ //was from the side::
                if((playerIsToRightOfCiv(civ)||(civ.rightNextToSide&&!civ.hitMapOnRightSideOfCar))&&!civ.hittingRightSideOfMap){
                    if((civ.rightNextToSide&&!civ.hitMapOnRightSideOfCar)){
                        player.screenLocation[0]=civ.screenLocation[0]+civ.imageSize[0]-civ.IMG_BLANK_SPACE[0]-player.IMG_BLANK_SPACE[0]+15;
//                        player.angle=15;
                        civ.angle=0;
                    } else{
                        civ.screenLocation[0]=player.screenLocation[0]+player.IMG_BLANK_SPACE[0]+civ.IMG_BLANK_SPACE_ACTUAL[0]-civ.imageSizeActual[0]+(int)(player.angle/3)
//                            //-(int)(player.speed/3)
                            ;
                    }
                    
                } else{
                    if((civ.rightNextToSide&&civ.hitMapOnRightSideOfCar)){
                    player.screenLocation[0]=civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0]+player.IMG_BLANK_SPACE[0]-player.imageSize[0]-leftExtraSpace;
//                    player.angle=-15;
                    civ.angle=0;
                    } else{
                        civ.screenLocation[0]=player.screenLocation[0]+player.imageSize[0]-player.IMG_BLANK_SPACE[0]-civ.IMG_BLANK_SPACE_ACTUAL[0]+(int)(player.angle/3)
//                      //+(int)(player.speed/3)
                        ;
                    }
                }
            }
            }
            
            
        } else civ.collidingWithPlayer=false;
    }
    
    /**
     * 
     * draw each CivilianFlow object
     * 
     * @param p Graphics class instance that is being used to draw on the panel
     */
    private void civiliansDraw(Graphics p){
        for (CivilianFlow civilian : civilians) {
//            CivilianFlow civ;
//            (civ=
            (civilian).draw(p, screenDistortY);
//            //black=player goes to right of civ
//            p.setColor(Color.black);
//            p.fillRect(civ.screenLocation[0]+civ.imageSize[0]-civ.IMG_BLANK_SPACE[0]-player.IMG_BLANK_SPACE[0],0,2,700);
//            //player goes to left of civ
//            p.setColor(Color.blue);
//            p.fillRect(civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0]+player.IMG_BLANK_SPACE[0]-player.imageSize[0]-leftExtraSpace,0,2,700);
        }
    }
    
    /**
     * 
     * draw each EnemyFlow object
     * 
     * @param p Graphics class instance that is being used to draw on the panel
     */
    private void enemiesDraw(Graphics p){
        for (EnemyFlow enemy : enemies) {
            enemy.draw(p, screenDistortY);
        }
    }
    
    /**
     * draw the map
     * 
     * @param p Graphics class instance that is being used to draw on the panel
     */
    private void mapDraw(Graphics p){
        map.draw(p,screenDistortY);
    }
    
    
    ///////////////////////////////////////////////////////////////////////////
    //civilian collisions::::
    private void checkCivilianCollisions(CivilianFlow civ,int i){
            civ.collidingWithMap=checkCivilianMapCollisions(civ);
            checkPlayerCivilianCollisions(civ);
            checkCivCivCollisions(civ,i);
            if(civ.rightNextToSide)
                civ.angle=0;
    }
    
    private boolean checkCivilianMapCollisions(CivilianFlow civ){
        boolean b=false;
        Rectangle r;
        Polygon p;
        //side collisions::
        if(civ.lowerSpan.intersects(map.leftSide)||civ.upperSpan.intersects(map.leftSide)){//left side/civ collision::
            civ.angle*=-1/2;
            civ.screenLocation[0]=map.leftSide.x+map.leftSide.width-civ.IMG_BLANK_SPACE[0]+civ.ADD_FOR_HTNG_L_SIDE;//132-10;
            civ.hitMapOnRightSideOfCar=false;
            civ.rightNextToSide=true;
            b=true;
        } else if(civ.lowerSpan.intersects(map.rightSide)||civ.upperSpan.intersects(map.rightSide)){//right side/civ collsion::
            civ.hitMapOnRightSideOfCar=true;
            civ.angle*=-1/2;
            civ.screenLocation[0]=map.rightSide.x-civ.IMG_BLANK_SPACE[0]-civ.CAR_PIXELS_HORIZONTAL+civ.ADD_FOR_HTNG_R_SIDE;
            civ.rightNextToSide=true;
            civ.hittingRightSideOfMap=true;
            b=true;
        }
        
        //median collisions::
        else if(map.rLeft1!=null||map.rLeft2!=null){
                if((map.rLeft1!=null&&((p=map.upTri1).intersects(civ.lowerSpan)||p.intersects(civ.upperSpan)))
                        ||(map.rLeft2!=null&&((p=map.upTri2).intersects(civ.lowerSpan)||p.intersects(civ.upperSpan)))){
                    //car hit the upper triangular point of the median
                    civ.angle=HIT_MEDIAN_FROM_TOP_CHANGE;
                    b=true;
                }else if((map.rLeft1!=null&&((p=map.downTri1).intersects(civ.lowerSpan)||p.intersects(civ.upperSpan)))
                        ||(map.rLeft2!=null&&((p=map.downTri2).intersects(civ.lowerSpan)||p.intersects(civ.upperSpan)))){
                    //car hit the lower triangular point of the median
                    if(civ.screenLocation[0]+civ.rectSize[0]>map.CENTER_OF_MEDIAN){//right part
                        civ.angle=HIT_MEDIAN_FRONT_ANGLE_CHANGE;
                        civ.screenLocation[0]+=5;
                        civ.speed=5;
                    }else {                                                             //left part
                        civ.angle=-1*HIT_MEDIAN_FRONT_ANGLE_CHANGE;
                        civ.screenLocation[0]-=5;
                        civ.speed=5;
                    }
                    b=true;
                    
                }else if((map.rLeft1!=null&&(civ.lowerSpan.intersects(r=map.rLeft1)||civ.upperSpan.intersects(r)))
                    ||(map.rLeft2!=null&&(civ.lowerSpan.intersects(r=map.rLeft2)||civ.upperSpan.intersects(r)))){ 
                    //car hit left side of median
                    civ.angle=-1*MEDIAN_ANGLE;
                    civ.screenLocation[0]=420-civ.IMG_BLANK_SPACE[0]-civ.CAR_PIXELS_HORIZONTAL+civ.ADD_FOR_LEFT_MEDIAN;
                    b=true;
                    civ.hitMapOnRightSideOfCar=false;
                    
                }else if((map.rLeft1!=null&&(civ.lowerSpan.intersects(r=map.rRight1)||civ.upperSpan.intersects(r)))
                        ||(map.rLeft2!=null&&(civ.lowerSpan.intersects(r=map.rRight2)||civ.upperSpan.intersects(r)))){
                    //car hit right side of median
                    civ.angle=MEDIAN_ANGLE;
                    civ.screenLocation[0]=590-civ.IMG_BLANK_SPACE[0]+civ.ADD_FOR_RIGHT_MEDIAN;
                    b=true;
                    civ.hitMapOnRightSideOfCar=true;
                    
                }
        }
        if(b)
            civ.health-=0.8;
        
        return b;
    }
    
    private void OLDcheckCivCivCollisions(CivilianFlow civ,int ind){
        for(int j=ind+1;j<civilians.size();j++){
                CivilianFlow testing=civilians.get(j);
                civ.health-=3;
                testing.health-=0.07;
                if(testing.screenLocation[1]+testing.IMG_BLANK_SPACE[1]>civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]+civ.CAR_PIXELS_VERTICAL*(verticalDetectRatioPlayerOnBottom)){//testing is below civ
                    //testing top is below 3/4 down of the civilian, and therefore the civilian should be pushed up and the angle changed
                    if(!civ.collidingWithMap)
                        civ.angle=testing.angle/2
                                -3*((testing.screenLocation[0]+testing.imageSize[0]/2)-(civ.screenLocation[0]+civ.imageSize[0]/2));
                    else
                        civ.angle=0;
                    
                    civ.speed+=2.5*(testing.speed-civ.speed)+3;
                    testing.speed*=3/5;
                    
                } else if(testing.screenLocation[1]+testing.IMG_BLANK_SPACE[1]+(verticalDetectRatioPlayerOnTop)*testing.CAR_PIXELS_VERTICAL<civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]){//testing is above civ
                    swapVA(civ);
                }else{
                if(civIsToRightOfCiv(testing,civ)){
                    if((civ.rightNextToSide)){
                        testing.screenLocation[0]=civ.screenLocation[0]+civ.imageSize[0]-civ.IMG_BLANK_SPACE[0]-testing.IMG_BLANK_SPACE[0];
                        testing.screenLocation[0]+=15;
                        testing.angle=15;
                    } else{
                    civ.screenLocation[0]=testing.screenLocation[0]+testing.IMG_BLANK_SPACE[0]+civ.IMG_BLANK_SPACE_ACTUAL[0]-civ.imageSizeActual[0]+(int)(testing.angle/3)
                            //-(int)(testing.speed/3)
                            ;
                    if(testing.turningRight){//shouldnt be turning right, therefore the collision must be a glitch or was caused by a civilian
                        civ.speed=testing.speed;
                        if(civ.angle>testing.angle-3&&civ.angle<testing.angle+3){
                            civ.angle=-25;
                            testing.angle=25;
                        } else{
                        civ.angle=testing.angle*1.5;
                        
                        
                        if(civ.angle<-25)
                            testing.angle=-10;
                        else
                            testing.angle=5;
//                        testing.angle*=-0.5;
                        }
                    } else{  //normal
                        civ.speed=testing.speed;
                        if(civ.angle>testing.angle-3&&civ.angle<testing.angle+3){
                            civ.angle=-25;
                            testing.angle=25;
                        } else{
                        civ.angle=testing.angle*1.5;
                        if(civ.angle<-25)
                            testing.angle=10;
                        else
                            testing.angle=-5;
//                        testing.angle*=-0.5;
                        }
                    }
                    }
                } else{ //testing is to the left of the civilian
                    if((civ.rightNextToSide)){
                    testing.screenLocation[0]=civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0]+testing.IMG_BLANK_SPACE[0]-testing.imageSize[0]-leftExtraSpace;
                    testing.screenLocation[0]-=5;
                    testing.angle=-15;
                    } else{
                    civ.screenLocation[0]=testing.screenLocation[0]+testing.imageSize[0]-testing.IMG_BLANK_SPACE[0]-civ.IMG_BLANK_SPACE_ACTUAL[0]+5+(int)(testing.angle/3)
                            //+(int)(testing.speed/3)
                            ;
                    if(testing.turningLeft){//shouldnt be turning left
                        civ.speed=testing.speed;
                        if(civ.angle>testing.angle-3&&civ.angle<testing.angle+3){
                            civ.angle=25;
                            testing.angle=-25;
                        } else{
                        civ.angle=testing.angle*1.5;
                        
                        if(civ.angle<-25)
                            testing.angle=10;
                        else
                            testing.angle=-5;
//                        testing.angle*=-0.5;
                        }
                        
                    } else{  //normal
                        civ.speed=testing.speed;
                        if(civ.angle>testing.angle-3&&civ.angle<testing.angle+3){
                            civ.angle=25;
                            testing.angle=-25;
                        } else{
                        civ.angle=testing.angle*1.5;
                        if(civ.angle<-25)
                            testing.angle=-10;
                        else
                            testing.angle=5;
//                        testing.angle*=-0.5;
                        }
                    }
                    }
                }
                }
        }
    }
    
    private final double topCivCivRatio=0.5,bottomCivCivRatio=0.7;
    private void checkCivCivCollisions(CivilianFlow civ,int ind){
        for(int j=ind+1;j<civilians.size();j++){
                CivilianFlow testing=civilians.get(j);
                
            if(testing.upperSpan.intersects(civ.lowerSpan)
                ||testing.upperSpan.intersects(civ.upperSpan)
                ||testing.lowerSpan.intersects(civ.upperSpan)
                ||testing.lowerSpan.intersects(civ.lowerSpan)){//civilian is hit
            civ.health-=3.5;
            testing.health-=3.5;
                if(!civ.collidingWithPlayer){
                
//                civ.collidingWithPlayer=true;
                
                if(testing.screenLocation[1]+testing.IMG_BLANK_SPACE_ACTUAL[1]>civ.screenLocation[1]+civ.IMG_BLANK_SPACE_ACTUAL[1]+civ.CAR_PIXELS_VERTICAL_ACTUAL*(bottomCivCivRatio)){
                    //testing top is below 3/4 down of the civilian, and therefore the civilian should be pushed up and the angle changed
//                    civ.hitPlayerFromSide=false;
                    System.out.println("car hit vertical 1");
                    if(!civ.collidingWithMap)
                        civ.angle=testing.angle/2
                                -3*((testing.screenLocation[0]+testing.imageSize[0]/2)-(civ.screenLocation[0]+civ.imageSize[0]/2));
                    else
                        civ.angle=0;
                    
                    civ.speed+=(testing.speed-civ.speed)+3;
                    testing.speed*=3/5;
                    
                } else if(testing.screenLocation[1]+testing.IMG_BLANK_SPACE[1]+(verticalDetectRatioPlayerOnTop)*testing.CAR_PIXELS_VERTICAL<civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]+10){
//                    civ.hitPlayerFromSide=false;
                    System.out.println("car hit vertical 2");
                    swapVA(civ,testing);
                }else{             //is from the side
//                    civ.hitPlayerFromSide=true; 
                //was not hit from the bottom or the top; was the side::    VVVVVVVV
                    
                    System.out.println("from side");
                    
                    if(!civ.collidingWithMap&&!civ.rightNextToSide){
                        civ.angle=testing.angle*2;
                        testing.angle/=-2;
                                
                        if(civIsToRightOfCiv(testing,civ)){ //testing is to the right of the hit civ
                            if(testing.screenLocation[0]+testing.IMG_BLANK_SPACE[0]<civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0]+civ.CAR_PIXELS_HORIZONTAL)
                                civ.screenLocation[0]=testing.screenLocation[0]+testing.IMG_BLANK_SPACE[0]-civ.imageSize[0]-extraSpaceBetweenCarsOnHitRight; 
                        } else{  //testing is to the left of the hit civ
                            if(testing.screenLocation[0]+testing.IMG_BLANK_SPACE[0]+testing.CAR_PIXELS_HORIZONTAL>civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0])
                                civ.screenLocation[0]=testing.screenLocation[0]+testing.IMG_BLANK_SPACE_ACTUAL[0]+testing.CAR_PIXELS_HORIZONTAL_ACTUAL-civ.IMG_BLANK_SPACE_ACTUAL[0]+5;
                        }
                    
                    } else{ //the civ is hitting the side of the map
                        testing.angle*=-2;
                    }
            }                                                       // ^^^^^^^^
                
            
            } else{ //was previously colliding::
                    
                if(testing.screenLocation[1]+testing.IMG_BLANK_SPACE[1]>civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]+civ.CAR_PIXELS_VERTICAL*(verticalDetectRatioPlayerOnTop)){
                     //testing below civ
                    civ.speed+=5;
                    testing.speed-=5;
                }else if(testing.screenLocation[1]+testing.IMG_BLANK_SPACE[1]+(verticalDetectRatioPlayerOnTop)*testing.CAR_PIXELS_VERTICAL<civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]){
                    //testing above civ
                    civ.speed-=5;
                    testing.speed+=5;
                }else if((civIsToRightOfCiv(testing,civ)||(civ.rightNextToSide&&!civ.hitMapOnRightSideOfCar))&&!civ.hittingRightSideOfMap){
                    testing.screenLocation[0]=civ.screenLocation[0]+civ.imageSize[0]-civ.IMG_BLANK_SPACE[0]-testing.IMG_BLANK_SPACE[0];
                    if(civ.rightNextToSide)
                        testing.screenLocation[0]+=15;
                } else{
                    testing.screenLocation[0]=civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0]+testing.IMG_BLANK_SPACE[0]-testing.imageSize[0]-leftExtraSpace;
                    if(civ.rightNextToSide)
                        testing.screenLocation[0]-=5;
                }
            }
            
        } else {}
                
                
                
                
        }
    }
    
    private final double verticalDetectRatioPlayerOnTop=14.0/16,verticalDetectRatioPlayerOnBottom=10.0/16;
    private final int extraSpaceBetweenCarsOnHitLeft=4,extraSpaceBetweenCarsOnHitRight=4,leftExtraSpace=22;
    
    ///////////////////////////////////////////////////////////////////////////
    //enemy collisions::::
    private void checkEnemyCollisions(EnemyFlow enemy,int index){
        checkPlayerEnemyCollisions(enemy);
        checkMapEnemyCollisions(enemy);
        checkEnemyCivCollisions(enemy);
    }
    
    private void checkEnemyCivCollisions(EnemyFlow enemy){
        for(int i=0;i<civilians.size();i++){
            CivilianFlow civ=civilians.get(i);
            
            if(enemy.upperSpan.intersects(civ.lowerSpan)
                ||enemy.upperSpan.intersects(civ.upperSpan)
                ||enemy.lowerSpan.intersects(civ.upperSpan)
                ||enemy.lowerSpan.intersects(civ.lowerSpan)){//civilian is hit
            civ.health-=3;
            enemy.health-=0.07;
            
            if(!civ.collidingWithPlayer){
                
                civ.collidingWithPlayer=true;
                
                if(enemy.screenLocation[1]+enemy.IMG_BLANK_SPACE[1]>civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]+civ.CAR_PIXELS_VERTICAL*(verticalDetectRatioPlayerOnTop)){
                    //enemy top is below 3/4 down of the civilian, and therefore the civilian should be pushed up and the angle changed
                    civ.hitPlayerFromSide=false;
                    if(!civ.collidingWithMap)
                        civ.angle=enemy.angle/2
                                -3*((enemy.screenLocation[0]+enemy.imageSize[0]/2)-(civ.screenLocation[0]+civ.imageSize[0]/2));
                    else
                        civ.angle=0;
                    
                    civ.speed+=2.5*(enemy.speed-civ.speed)+3;
                    enemy.speed*=3/5;
                    
                } else if(enemy.screenLocation[1]+enemy.IMG_BLANK_SPACE[1]+(verticalDetectRatioPlayerOnTop)*enemy.CAR_PIXELS_VERTICAL<civ.screenLocation[1]+civ.IMG_BLANK_SPACE[1]){
                    civ.hitPlayerFromSide=false;
                    swapVA(enemy,civ);
                }else{             //is from the side
                    civ.hitPlayerFromSide=true; 
                //was not hit from the bottom or the top; was the side::    VVVVVVVV
                    
                    if(!civ.collidingWithMap&&!civ.rightNextToSide){
                        civ.angle=enemy.angle*2;
                        enemy.angle/=-2;
                                
                        if(enemyIsToRightOfCiv(enemy,civ)){ //enemy is to the right of the hit civ
                            if(enemy.screenLocation[0]+enemy.IMG_BLANK_SPACE[0]<civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0]+civ.CAR_PIXELS_HORIZONTAL)
                                civ.screenLocation[0]=enemy.screenLocation[0]+enemy.IMG_BLANK_SPACE[0]-civ.imageSize[0]-extraSpaceBetweenCarsOnHitRight; 
                        } else{  //enemy is to the left of the hit civ
                            if(enemy.screenLocation[0]+enemy.IMG_BLANK_SPACE[0]+enemy.CAR_PIXELS_HORIZONTAL>civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0])
                                civ.screenLocation[0]=enemy.screenLocation[0]+enemy.IMG_BLANK_SPACE[0]+enemy.CAR_PIXELS_HORIZONTAL+extraSpaceBetweenCarsOnHitLeft;
                        }
                    
                    } else{ //the civ is hitting the side of the map
                        enemy.angle*=-2;
                    }
            }                                                       // ^^^^^^^^
                
            
            } else{ //was previously colliding::
                if((enemyIsToRightOfCiv(enemy,civ)||(civ.rightNextToSide&&!civ.hitMapOnRightSideOfCar))&&!civ.hittingRightSideOfMap){
                    enemy.screenLocation[0]=civ.screenLocation[0]+civ.imageSize[0]-civ.IMG_BLANK_SPACE[0]-enemy.IMG_BLANK_SPACE[0];
                    if(civ.rightNextToSide)
                        enemy.screenLocation[0]+=15;
                } else{
                    enemy.screenLocation[0]=civ.screenLocation[0]+civ.IMG_BLANK_SPACE[0]+enemy.IMG_BLANK_SPACE[0]-enemy.imageSize[0]-leftExtraSpace;
                    if(civ.rightNextToSide)
                        enemy.screenLocation[0]-=5;
                }
            }
            
        } else {
            civ.collidingWithPlayer=false;
        }
        
            
        }
    }
    
    private void checkPlayerEnemyCollisions(EnemyFlow enemy){
        if(player.upperSpan.intersects(enemy.lowerSpan)
                ||player.upperSpan.intersects(enemy.upperSpan)
                ||player.lowerSpan.intersects(enemy.upperSpan)
                ||player.lowerSpan.intersects(enemy.lowerSpan)){//civilian is hit
            enemy.health-=3;
            player.health-=0.07;
            
            if(!enemy.collidingWithPlayer){
                
                enemy.collidingWithPlayer=true;
                
                if(player.screenLocation[1]+player.IMG_BLANK_SPACE[1]>enemy.screenLocation[1]+enemy.IMG_BLANK_SPACE[1]+enemy.CAR_PIXELS_VERTICAL*(verticalDetectRatioPlayerOnTop)){
                    //player top is below 3/4 down of the civilian, and therefore the civilian should be pushed up and the angle changed
                    enemy.hitPlayerFromSide=false;
                    if(!enemy.collidingWithMap)
                        enemy.angle=player.angle/2
                                -3*((player.screenLocation[0]+player.imageSize[0]/2)-(enemy.screenLocation[0]+enemy.imageSize[0]/2));
                    else
                        enemy.angle=0;
                    
                    enemy.speed+=2.5*(player.speed-enemy.speed)+3;
                    player.speed*=3/5;
                    
                } else if(player.screenLocation[1]+player.IMG_BLANK_SPACE[1]+(verticalDetectRatioPlayerOnTop)*player.CAR_PIXELS_VERTICAL<enemy.screenLocation[1]+enemy.IMG_BLANK_SPACE[1]){
                    enemy.hitPlayerFromSide=false;
                    swapVA(enemy);
                }else{             //is from the side
                    enemy.hitPlayerFromSide=true; 
                //was not hit from the bottom or the top; was the side::    VVVVVVVV
                    
                    if(!enemy.collidingWithMap&&!enemy.rightNextToSide){
                        enemy.angle=player.angle*2;
                        player.angle/=-2;
                                
                        if(playerIsToRightOfEnemy(enemy)){ //player is to the right of the hit civ
                            if(player.screenLocation[0]+player.IMG_BLANK_SPACE[0]<enemy.screenLocation[0]+enemy.IMG_BLANK_SPACE[0]+enemy.CAR_PIXELS_HORIZONTAL)
                                enemy.screenLocation[0]=player.screenLocation[0]+player.IMG_BLANK_SPACE[0]-enemy.imageSize[0]-extraSpaceBetweenCarsOnHitRight; 
                        } else{  //player is to the left of the hit civ
                            if(player.screenLocation[0]+player.IMG_BLANK_SPACE[0]+player.CAR_PIXELS_HORIZONTAL>enemy.screenLocation[0]+enemy.IMG_BLANK_SPACE[0])
                                enemy.screenLocation[0]=player.screenLocation[0]+player.IMG_BLANK_SPACE[0]+player.CAR_PIXELS_HORIZONTAL+extraSpaceBetweenCarsOnHitLeft;
                        }
                    
                    } else{ //the civ is hitting the side of the map
                        player.angle*=-2;
                    }
            }                                                       // ^^^^^^^^
                
            
            } else{ //was previously colliding::
                if((playerIsToRightOfEnemy(enemy)||(enemy.rightNextToSide&&!enemy.hitMapOnRightSideOfCar))&&!enemy.hittingRightSideOfMap){
                    player.screenLocation[0]=enemy.screenLocation[0]+enemy.imageSize[0]-enemy.IMG_BLANK_SPACE[0]-player.IMG_BLANK_SPACE[0]-enemy.hitPlayerRightAndMapAdd;
                    if(enemy.rightNextToSide)
                        player.screenLocation[0]+=15;
                } else{
                    player.screenLocation[0]=enemy.screenLocation[0]+enemy.IMG_BLANK_SPACE[0]+player.IMG_BLANK_SPACE[0]-player.imageSize[0]-leftExtraSpace+enemy.hitPlayerLeftAndMapAdd;
                    if(enemy.rightNextToSide)
                        player.screenLocation[0]-=5;
                }
            }
            
        } else {
            enemy.collidingWithPlayer=false;
        }
        
    }
    
    private boolean checkMapEnemyCollisions(EnemyFlow enemy){
        boolean b=false;
        Rectangle r;
        Polygon p;
        //side collisions::
        if(enemy.lowerSpan.intersects(map.leftSide)||enemy.upperSpan.intersects(map.leftSide)){//left side/enemy collision::
            enemy.angle*=-1/2;
            enemy.screenLocation[0]=map.leftSide.x+map.leftSide.width-enemy.IMG_BLANK_SPACE[0];
            enemy.hitMapOnRightSideOfCar=false;
            enemy.rightNextToSide=true;
            b=true;
        } else if(enemy.lowerSpan.intersects(map.rightSide)||enemy.upperSpan.intersects(map.rightSide)){//right side/enemy collsion::
            enemy.hitMapOnRightSideOfCar=true;
            enemy.angle*=-1/2;
            enemy.screenLocation[0]=map.rightSide.x-enemy.IMG_BLANK_SPACE[0]-enemy.CAR_PIXELS_HORIZONTAL/*+enemy.ADD_FOR_HTNG_R_SIDE*/;
            enemy.rightNextToSide=true;
            enemy.hittingRightSideOfMap=true;
            b=true;
        }
        
        //median collisions::
        else if(map.rLeft1!=null||map.rLeft2!=null){
                if((map.rLeft1!=null&&((p=map.upTri1).intersects(enemy.lowerSpan)||p.intersects(enemy.upperSpan)))
                        ||(map.rLeft2!=null&&((p=map.upTri2).intersects(enemy.lowerSpan)||p.intersects(enemy.upperSpan)))){
                    //car hit the upper triangular point of the median
                    enemy.angle=HIT_MEDIAN_FROM_TOP_CHANGE;
                    b=true;
                }else if((map.rLeft1!=null&&((p=map.downTri1).intersects(enemy.lowerSpan)||p.intersects(enemy.upperSpan)))
                        ||(map.rLeft2!=null&&((p=map.downTri2).intersects(enemy.lowerSpan)||p.intersects(enemy.upperSpan)))){
                    //car hit the lower triangular point of the median
                    if(enemy.screenLocation[0]+enemy.rectSize[0]>map.CENTER_OF_MEDIAN){//right part
                        enemy.angle=HIT_MEDIAN_FRONT_ANGLE_CHANGE;
                        enemy.screenLocation[0]+=5;
                        enemy.speed=5;
                    }else {                                                             //left part
                        enemy.angle=-1*HIT_MEDIAN_FRONT_ANGLE_CHANGE;
                        enemy.screenLocation[0]-=5;
                        enemy.speed=5;
                    }
                    b=true;
                    
                }else if((map.rLeft1!=null&&(enemy.lowerSpan.intersects(r=map.rLeft1)||enemy.upperSpan.intersects(r)))
                    ||(map.rLeft2!=null&&(enemy.lowerSpan.intersects(r=map.rLeft2)||enemy.upperSpan.intersects(r)))){ 
                    //car hit left side of median
                    enemy.angle=-1*MEDIAN_ANGLE;
                    enemy.screenLocation[0]=420-enemy.IMG_BLANK_SPACE[0]-enemy.CAR_PIXELS_HORIZONTAL/*+enemy.ADD_FOR_LEFT_MEDIAN*/;
                    b=true;
                    enemy.hitMapOnRightSideOfCar=false;
                    
                }else if((map.rLeft1!=null&&(enemy.lowerSpan.intersects(r=map.rRight1)||enemy.upperSpan.intersects(r)))
                        ||(map.rLeft2!=null&&(enemy.lowerSpan.intersects(r=map.rRight2)||enemy.upperSpan.intersects(r)))){
                    //car hit right side of median
                    enemy.angle=MEDIAN_ANGLE;
                    enemy.screenLocation[0]=590-enemy.IMG_BLANK_SPACE[0]/*+enemy.ADD_FOR_RIGHT_MEDIAN*/;
                    b=true;
                    enemy.hitMapOnRightSideOfCar=true;
                    
                }
        }
        if(b)
            enemy.health-=0.8;
        
        return b;
    }
    
    private void swapVA(CivilianFlow civ){
        double t1=civ.angle,t2=civ.speed;
            civ.angle=player.angle;
            civ.speed=player.speed;
            
            player.angle=t1;
            player.speed=t2;
    }
    
    private void swapVA(CivilianFlow civ1,CivilianFlow civ2){
        double t1=civ1.angle,t2=civ1.speed;
            civ1.angle=civ2.angle;
            civ1.speed=civ2.speed;
            
            civ2.angle=t1;
            civ2.speed=t2;
    }
    
    private void swapVA(EnemyFlow a){
        double t1=a.angle,t2=a.speed;
            a.angle=player.angle;
            a.speed=player.speed;
            
            player.angle=t1;
            player.speed=t2;
    }
    
    private void swapVA(EnemyFlow a,CivilianFlow c){
        double t1=a.angle,t2=a.speed;
            a.angle=c.angle;
            a.speed=c.speed;
            
            c.angle=t1;
            c.speed=t2;
    }
    
    
    
    /**
     * returns true if civ1 is to the right of civ2
     * 
     * @param civ1 CivilianFlow that is used to see if it's to the right of civ2 to return true
     * @param civ2 CivilianFlow that is used to see if it's to the left of civ1 to return true
    */
    private boolean civIsToRightOfCiv(CivilianFlow civ1,CivilianFlow civ2){
        return civ1.screenLocation[0]+civ1.imageSize[0]/2>=civ2.screenLocation[0]+civ2.imageSize[0]/2;
    }
    
    private boolean playerIsToRightOfEnemy(EnemyFlow e){
        return player.screenLocation[0]+player.imageSize[0]/2>=e.screenLocation[0]+e.imageSize[0]/2;
    }
    
    private boolean enemyIsToRightOfCiv(EnemyFlow enemy,CivilianFlow civ){
        return enemy.screenLocation[0]+enemy.imageSize[0]/2>=civ.screenLocation[0]+civ.imageSize[0]/2;
    }
    
    
    
    private void hittingRightFromLeft(int an){
        player.canTurnRight=false;
        player.angle=an;
    }
    
    private void hittingLeftFromRight(int an){
        player.canTurnLeft=false;
        player.angle=an;
    }
    
    int divideBy=3,te;
    private int getHowFarGoesUpForAIs(int sp){
        return (int)(sp/divideBy);
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
    
    public int MAX_CIVILIANS=15;
    private int shouldExpireCheckPing=0;

    /**
     *
     */
    public Timer AIPopCheck=new Timer(450, (ActionEvent e) -> {
        if(!paused){
        if(shouldExpireCheckPing==3){
            civExpireCheck();
            enemyExpireCheck();
            shouldExpireCheckPing=0;
        }
        
        if(civilians.size()<MAX_CIVILIANS){
            spawnCivilian();
        }
        
        
        if(enemies.size()<MAX_ENEMIES){
            spawnEnemy();
        }
        
        shouldExpireCheckPing++;
        }
    });
    
    private void enemyExpireCheck(){
        int a;
        for(int i=0;i<enemies.size();i++)
            if((a=(enemies.get(i).screenLocation[1]))>1200||a<-650){//it should expire
                enemies.remove(i);
                i--;
            }    
    }
    
    private void civExpireCheck(){
        int a;
        for(int i=0;i<civilians.size();i++)
            if((a=(civilians.get(i).screenLocation[1]))>1000||a<-400){//it should expire
                civilians.remove(i);
                i--;
            }    
    }
    
    private void spawnCivilian(){
          CivilianFlow toAdd=new CivilianFlow((int)(Math.random()*2));
        
            toAdd.screenLocation[0]=laneStarts[(int)(Math.random()*8)]-toAdd.IMG_BLANK_SPACE[0]+28;
            
            if(player.speed>30){//then spawn from top
                toAdd.screenLocation[1]=-200;
            } else { //then spawn from bottom
                toAdd.screenLocation[1]=850;
            }
            
            civilians.add(toAdd);
    }
    
    private void spawnEnemy(){
        EnemyFlow toAdd=new EnemyFlow(0);
        toAdd.screenLocation[0]=laneStarts[(int)(Math.random()*8)]-toAdd.IMG_BLANK_SPACE[0]+28;
        
        if((int)(Math.random()*2)==0)
            toAdd.screenLocation[1]=-200;
        else
            toAdd.screenLocation[1]=850;
        
        enemies.add(toAdd);
        
    }

    public final int calcDelay=20;
    @Override
    public void run() {
        calcFlow();
        try{Thread.sleep(calcDelay);}
        catch(Exception e){ErrorLogger.logError(e,"run");}
        run();
    }
    
}