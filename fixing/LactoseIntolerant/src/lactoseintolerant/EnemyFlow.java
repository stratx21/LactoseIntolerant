/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author 0001058857
 */
public class EnemyFlow extends AIFlow{
    /**
     * how far the player is from this enemy
     */
    public int[] toPlayerDifference=new int[2];
    
    public int ANGLE_MAX=25,ANGLE_MIN=-25,
            stoppedTurningTurnRate=1;
    
    public double slowedDownSpeed=25;
    
    public double speedChange;
    
    public boolean rightNextToSide=false;
    
    public int hitPlayerRightAndMapAdd=15,hitPlayerLeftAndMapAdd=25;
    
    public boolean hittingRightSideOfMap=false,contactWithPlayer=false;
    
    /**
     * set up the enemy based on what type it is
     * 
     * @param t the type of Enemy
     */
    public EnemyFlow(int t){
        noEffectDecrease=1;
        ACCELERATION=2;
        switch(TYPE=t){
            case 0:
                IMG_BLANK_SPACE=new int[]{30,19};
                CAR_PIXELS_HORIZONTAL=33;
                CAR_PIXELS_VERTICAL=69;
                imageSize=new int[]{93,107};
                rectSize=new int[]{33,34};
                fullImageSpan=new Rectangle(30,60);
                currentImage=GraphicsAssets.getImage(26);
                originalPoints=new int[][]{{0,0},{30,0},{30,60},{0,60}};
                addForOriginRect=new int[]{14,2};
                speed=TOP_SPEED=90;
                SLOWING_DOWN_SPEED=4;
                break;
                
        }
    }
    
    /**
     * draw the graphical representation of the enemy
     * 
     * @param p the instance of the Graphics class from GamePanel. It is used to
     *      draw the enemy here but also used to draw other game components
     *      elsewhere
     * @param dY the distortion in the y direction caused by the player 
     *      increasing speed/containing a high speed or slowing down, moving
     *      all game objects up/down respectively 
     */
    public void draw(Graphics p,int dY){
        if(angle==0)
            p.drawImage(currentImage,screenLocation[0]+Profile.xStart,screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        else
            p.drawImage(ImageUtils.rotateImage(currentImage,angle),screenLocation[0]+Profile.xStart,screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        
        
        
//        p.setColor(Color.pink);
//        p.fillRect(upperSpan.x,upperSpan.y,upperSpan.width,upperSpan.height);
//        p.setColor(Color.blue);
//        p.fillRect(lowerSpan.x,lowerSpan.y,lowerSpan.width,lowerSpan.height);
    }
    
    /**
     * calculate the new location and other aspects of the enemy that may or 
     * may not affect this location, such as speed, braking, turning, etc. Also
     * update the Rectangle object used to calculate collisions in GamePanel
     * 
     * @param cY how much the vehicle moves up based on original movement, 
     * not to be confused with dY which is the displacement based on the player 
     * speeding up/slowing down and is only a component of graphical 
     * representation
     * 
     * @param dY displacement based on the player speeding up/slowing down and 
     * is only a component of graphical representation
     */
    public void calculate(int cY,int dY){
        screenLocation[1]+=cY;
        
//        System.out.println(screenLocation[0]+", "+screenLocation[1]);
        
        if(angle!=0&&!contactWithPlayer){
            screenLocation[0]+=angle/5;
            if(angle<0){
                if(angle<-5)
                    angle+=5;
                else angle=0;
//                angle+=angleIncrement;
                screenLocation[0]-=currentTurnRate*(Math.abs(angle)/5);
            }else if(angle>0){
                if(angle>5)
                    angle-=5;
                else angle=0;
                
//                angle-=angleIncrement;
                screenLocation[0]+=currentTurnRate*(Math.abs(angle)/5);
            }
        }
        
        
        //relation to player stuff::
        //(run out by function calls just in case the Enemy AI gets a difficulty upgrade later)
        if(toPlayerDifference[0]-5>0){//should go to the right; player is to the right of the enemy
            if(angle<ANGLE_MAX)
            angle+=angleIncrement;
            currentTurnRate=(int)speed/12;//update turn rate
            screenLocation[0]+=currentTurnRate*(Math.abs(angle)/5);
        } else if(toPlayerDifference[0]+5<0){
            if(angle>ANGLE_MIN) // over min (do a regular turn)
                angle-=angleIncrement;
            else if(angle<ANGLE_MIN) //under min
                angle=ANGLE_MIN;
            currentTurnRate=(int)speed/12;//update turn rate
            screenLocation[0]-=currentTurnRate*(Math.abs(angle)/5);
        } else{
            currentTurnRate=(int)speed/12;
            if(angle<0){
                if(angle<-1*angleIncrement)
                    angle+=angleIncrement;
                else
                    angle=0;
                
                screenLocation[0]+=stoppedTurningTurnRate*(angle/5);
            }else if(angle>0){
                if(angle>angleIncrement)
                    angle-=angleIncrement;
                else
                    angle=0;
                
                screenLocation[0]+=stoppedTurningTurnRate*(angle/5);
            } //else shouldCheckStoppedTurning=false;
        }
        
        if(toPlayerDifference[1]-5>0){ //enemy is above the player - (literally by looking at screen, not by y values)
            if(speed>slowedDownSpeed){
                if(speed>noEffectDecrease){
                    if(speed>slowedDownSpeed+20)
                        speed-=noEffectDecrease*2;
                    else
                        speed-=noEffectDecrease;
                } else 
                    speed=0;
        } else if(speed<slowedDownSpeed){
                if(speed<-1*noEffectDecrease)
                    speed+=noEffectDecrease;
                else 
                    speed=0;
            }
            speedChange=0;
        } else{//enemy is below the player - (literally by looking at screen, not by y values)
            speedChange=ACCELERATION;
            if(speed<TOP_SPEED){
                speed+=ACCELERATION;
            }else if(speed>TOP_SPEED){
                speed=TOP_SPEED;
            }
        }
        
        xInc=(int)(angle/5);
        upperSpan=ShapeUtils.getRectByPoint(screenLocation[0]+xInc+addForOriginRect[0],dY+screenLocation[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
        lowerSpan=ShapeUtils.getRectByPoint(screenLocation[0]-xInc+addForOriginRect[0],dY+screenLocation[1]+rectSize[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
    }
    
}
