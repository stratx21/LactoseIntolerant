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
    
    public boolean contactWithPlayer=false;
    
    /**
     *
     * @param t the type of Civilian car
     */
    public CivilianFlow(int t){
        switch(TYPE=t){
            case 0: //race car! :)
                IMG_BLANK_SPACE=new int[]{50,30};
                CAR_PIXELS_HORIZONTAL=57;
                imageSize=new int[]{90,99};
                rectSize=new int[]{32,33};
                fullImageSpan=new Rectangle(30,60);
                currentImage=GraphicsAssets.getImage(22);
                originalPoints=new int[][]{{0,0},{30,0},{30,60},{0,60}};
                addForOriginRect=new int[]{10,1};
                
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
    
    public void calculate(int cy,int dY){
        screenLocation[1]+=cy;
        
        if(angle!=0&&!contactWithPlayer){
            angle-=angle/5;
            if(angle<0){
//                angle+=angleIncrement;
                screenLocation[0]-=currentTurnRate*(Math.abs(angle)/5);
            }else if(angle>0){
//                angle-=angleIncrement;
                screenLocation[0]+=currentTurnRate*(Math.abs(angle)/5);
            }
        }
        
        updateSpanRectangles(dY);
    }
    
    private void updateSpanRectangles(int dY){
        xInc=(int)(angle/5);
        upperSpan=ShapeUtils.getRectByPoint(screenLocation[0]+xInc+addForOriginRect[0],dY+screenLocation[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
        lowerSpan=ShapeUtils.getRectByPoint(screenLocation[0]-xInc+addForOriginRect[0],dY+screenLocation[1]+rectSize[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
    }
    
}
