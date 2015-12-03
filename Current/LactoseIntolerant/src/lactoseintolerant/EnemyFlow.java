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
    
    public int[] toPlayerDifference=new int[2];
    
    public int ANGLE_MAX=25,ANGLE_MIN=-25,
            stoppedTurningTurnRate=1;
    
    public double slowedDownSpeed=25;
    
    public double speedChange;
    
    public boolean rightNextToSide=false;
    
    public int hitPlayerRightAndMapAdd=15,hitPlayerLeftAndMapAdd=25;
    
    public boolean hittingRightSideOfMap=false,contactWithPlayer=false;
    
    
    public EnemyFlow(int t){  //for harder AIs, increase noEffectDecrease and ACCELERATION   !! :)
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
                speed=TOP_SPEED=75;
                SLOWING_DOWN_SPEED=4;
                break;
                
        }
    }
    
    public void draw(Graphics p,int dY){
        if(angle==0)
            p.drawImage(currentImage,screenLocation[0],screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        else
            p.drawImage(ImageUtils.rotateImage(currentImage,angle),screenLocation[0],screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        
        
        
//        p.setColor(Color.pink);
//        p.fillRect(upperSpan.x,upperSpan.y,upperSpan.width,upperSpan.height);
//        p.setColor(Color.blue);
//        p.fillRect(lowerSpan.x,lowerSpan.y,lowerSpan.width,lowerSpan.height);
    }
    
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
            turningRightFlow();
        } else if(toPlayerDifference[0]+5<0){
            turningLeftFlow();
        } else{
            stopTurningFlow();
        }
        
        if(toPlayerDifference[1]-5>0){ //enemy is above the player - (literally by looking at screen, not by y values)
            slowDownFlow();
        } else{//enemy is below the player - (literally by looking at screen, not by y values)
            accelerateFlow();
        }
        
        
        
        
        updateSpanRectangles(dY);
    }
    
    private void accelerateFlow(){
        speedChange=ACCELERATION;
            if(speed<TOP_SPEED){
                speed+=ACCELERATION;
            }else if(speed>TOP_SPEED){
                speed=TOP_SPEED;
            }
    }
    
    private void slowDownFlow(){
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
    }
    
    private void turningRightFlow(){
        if(angle<ANGLE_MAX)
            angle+=angleIncrement;
        currentTurnRate=(int)speed/12;//update turn rate
        screenLocation[0]+=currentTurnRate*(Math.abs(angle)/5);
    }
    
    private void turningLeftFlow(){
        if(angle>ANGLE_MIN) // over min (do a regular turn)
           angle-=angleIncrement;
        else if(angle<ANGLE_MIN) //under min
            angle=ANGLE_MIN;
        currentTurnRate=(int)speed/12;//update turn rate
        screenLocation[0]-=currentTurnRate*(Math.abs(angle)/5);
    }
    
    private void stopTurningFlow(){
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
    
    private void updateSpanRectangles(int dY){
        xInc=(int)(angle/5);
        upperSpan=ShapeUtils.getRectByPoint(screenLocation[0]+xInc+addForOriginRect[0],dY+screenLocation[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
        lowerSpan=ShapeUtils.getRectByPoint(screenLocation[0]-xInc+addForOriginRect[0],dY+screenLocation[1]+rectSize[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
    }
    
}
