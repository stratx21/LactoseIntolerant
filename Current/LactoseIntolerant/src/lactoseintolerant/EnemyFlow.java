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
    
    public boolean rightNextToSide=false;
    
    public boolean hittingRightSideOfMap=false,contactWithPlayer=false;
    
    
    public EnemyFlow(int t){
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
                speed=TOP_SPEED=50;
                SLOWING_DOWN_SPEED=4;
                break;
                
        }
    }
    
    public void draw(Graphics p,int dY){
        if(angle==0)
            p.drawImage(currentImage,screenLocation[0],screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        else
            p.drawImage(ImageUtils.rotateImage(currentImage,angle),screenLocation[0],screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        
        
        
        p.setColor(Color.pink);
        p.fillRect(upperSpan.x,upperSpan.y,upperSpan.width,upperSpan.height);
        p.setColor(Color.blue);
        p.fillRect(lowerSpan.x,lowerSpan.y,lowerSpan.width,lowerSpan.height);
    }
    
    public void calculate(int cY,int dY){
        screenLocation[1]+=cY;
        
        System.out.println(screenLocation[0]+", "+screenLocation[1]);
        
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
