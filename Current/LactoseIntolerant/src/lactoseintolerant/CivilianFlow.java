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
public class CivilianFlow extends AIFlow{
    
    public Rectangle outerSpan=new Rectangle();
    public boolean goingForward=true;
    
    public boolean colliding=false;
    
    public boolean hittingRightSideOfMap=false;
    
    public boolean contactWithPlayer=false;
    
    public boolean rightNextToSide=false;
    
    public boolean shouldHeadLeft=false,shouldHeadRight=false;
    
    public int[] IMG_BLANK_SPACE_ACTUAL=new int[2],imageSizeActual=new int[2];
    
    public int CAR_PIXELS_HORIZONTAL_ACTUAL,CAR_PIXELS_VERTICAL_ACTUAL;
    
    public boolean hitAnotherCivilian=false,hitCivOnRight=false;
    
    public boolean collidingWithEnemy=false;
    
    /**
     *
     * @param t the type of Civilian car
     */
    public CivilianFlow(int t){//CAR_PIXELS_HORIZONTAL is actually wrong but got it working while it was wrong so compensated
        switch(TYPE=t){
            case 0: //race car! :)
                IMG_BLANK_SPACE=new int[]{50,30};
                IMG_BLANK_SPACE_ACTUAL=new int[]{30,18};
                CAR_PIXELS_HORIZONTAL=57;
                CAR_PIXELS_HORIZONTAL_ACTUAL=35;
                CAR_PIXELS_VERTICAL=74;
                CAR_PIXELS_VERTICAL_ACTUAL=74;
                imageSize=new int[]{90,99};
                imageSizeActual=new int[]{99,110};
                rectSize=new int[]{32,33};
                fullImageSpan=new Rectangle(30,60);
                currentImage=GraphicsAssets.getImage(22);
                originalPoints=new int[][]{{0,0},{30,0},{30,60},{0,60}};
                addForOriginRect=new int[]{10,1};
                speed=TOP_SPEED=40;
                SLOWING_DOWN_SPEED=4;
                
                break;
            case 1:
                IMG_BLANK_SPACE=new int[]{50,30};
                IMG_BLANK_SPACE_ACTUAL=new int[]{30,18};
                CAR_PIXELS_HORIZONTAL=57;
                CAR_PIXELS_HORIZONTAL_ACTUAL=35;
                CAR_PIXELS_VERTICAL=74;
                CAR_PIXELS_VERTICAL_ACTUAL=74;
                imageSize=new int[]{90,99};
                imageSizeActual=new int[]{99,110};
                rectSize=new int[]{32,33};
                fullImageSpan=new Rectangle(30,60);
                currentImage=GraphicsAssets.getImage(23);
                originalPoints=new int[][]{{0,0},{30,0},{30,60},{0,60}};
                addForOriginRect=new int[]{10,1};
                speed=TOP_SPEED=40;
                SLOWING_DOWN_SPEED=2;
                break;
        }
        
    }
    
    /**
     *
     * @param p Graphics instance used by the active CPanel GamePanel
     */
    public void draw(Graphics p,int dY){
        if(angle==0)
            p.drawImage(currentImage,screenLocation[0],screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        else //aka angle is not 0
            p.drawImage(ImageUtils.rotateImage(currentImage,angle),screenLocation[0],screenLocation[1]+dY,imageSize[0],imageSize[1],null);
           
        
//        p.setColor(Color.pink);
//        p.fillRect(upperSpan.x,upperSpan.y,upperSpan.width,upperSpan.height);
//        p.setColor(Color.blue);
//        p.fillRect(lowerSpan.x,lowerSpan.y,lowerSpan.width,lowerSpan.height);
    }
    
    /**
     * 
     * @param cy how much the vehicle moves up based on original movement, 
     * not to be confused with dY which is the displacement based on the player 
     * speeding up/slowing down and is only a component of graphical 
     * representation
     * 
     * @param dY displacement based on the player speeding up/slowing down and 
     * is only a component of graphical representation
     */
    public void calculate(int cy,int dY){
        screenLocation[1]+=cy;
        
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
        
        
        if(speed>TOP_SPEED)
            speed-=SLOWING_DOWN_SPEED;
        else if(speed<TOP_SPEED)
            speed+=ACCELERATION;
        
        updateSpanRectangles(dY);
    }
    
    private void updateSpanRectangles(int dY){
        xInc=(int)(angle/5);
        upperSpan=ShapeUtils.getRectByPoint(screenLocation[0]+xInc+addForOriginRect[0],dY+screenLocation[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
        lowerSpan=ShapeUtils.getRectByPoint(screenLocation[0]-xInc+addForOriginRect[0],dY+screenLocation[1]+rectSize[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
    }
    
}
