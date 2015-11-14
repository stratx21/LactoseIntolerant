/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author 0001058857
 */
public class CivilianFlow extends AIFlow{
    
    public Rectangle outerSpan=new Rectangle();
    public boolean goingForward=true;
    
    /**
     *
     * @param t the type of Civilian car
     */
    public CivilianFlow(int t){
        switch(TYPE=t){
            case 0: //300x600
                a=Math.atan(Math.toRadians(45/16));
                CAR_PIXELS_HORIZONTAL=30;
                imageSize=new int[]{30,60};
                fullImageSpan=new Rectangle(30,60);
                currentImage=GraphicsAssets.getImage(22);
                originalPoints=new int[][]{{0,0},{30,0},{30,60},{0,60}};
                break;
        }
        
    }
    
    /**
     *
     * @param p Graphics instance used by the active CPanel GamePanel
     */
    public void draw(Graphics p){
        
        
        
        
        
        
        if(angle==0)
            p.drawImage(currentImage,locationPixels[0],locationPixels[1],imageSize[0],imageSize[1],null);
        else //aka angle is not 0
            p.drawImage(ImageUtils.rotateImage(currentImage,angle),locationPixels[0],locationPixels[1],imageSize[0],imageSize[1],null);
           
    }
    
    private void updateOuterSpanRect(Graphics p){
        
    }
    
}
