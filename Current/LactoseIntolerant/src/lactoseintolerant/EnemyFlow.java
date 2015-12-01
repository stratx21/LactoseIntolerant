/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;

/**
 *
 * @author 0001058857
 */
public class EnemyFlow extends AIFlow{
    
    public EnemyFlow(int t){
        switch(TYPE=t){
            case 0:
                break;
                
        }
    }
    
    public void draw(Graphics p,int dY){
        if(angle==0)
            p.drawImage(currentImage,screenLocation[0],screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        else
            p.drawImage(ImageUtils.rotateImage(currentImage,angle),screenLocation[0],screenLocation[1]+dY,imageSize[0],imageSize[1],null);
    }
    
    public void calculate(int dY){
        
        
        updateSpanRectangles(dY);
    }
    
    private void updateSpanRectangles(int dY){
        xInc=(int)(angle/5);
        upperSpan=ShapeUtils.getRectByPoint(screenLocation[0]+xInc+addForOriginRect[0],dY+screenLocation[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
        lowerSpan=ShapeUtils.getRectByPoint(screenLocation[0]-xInc+addForOriginRect[0],dY+screenLocation[1]+rectSize[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
    }
    
}
