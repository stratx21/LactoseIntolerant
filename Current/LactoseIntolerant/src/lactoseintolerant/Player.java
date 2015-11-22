/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author 0001058857
 */
public class Player extends PlayerStat{
    private PlayerCars cars=new PlayerCars();
    
    public Player(){ //temporary ::
        cars.CURRENT_TYPE=CAR_TYPE=0;
        currentImage=GraphicsAssets.getImage(2);
        
        health=100;//cars.getTopSpeed();
        ACCELERATION=10;//cars.getAcceleration();
        ORIGINAL_TOP_SPEED=TOP_SPEED=55;//cars.getTopSpeed();
        speed=30;
    }
    
    public Player(int t){
        cars.CURRENT_TYPE=CAR_TYPE=t;
        
        currentImage=GraphicsAssets.getImage(2);
        
        health=100;//cars.getTopSpeed();
        ACCELERATION=10;//cars.getAcceleration();
        ORIGINAL_TOP_SPEED=TOP_SPEED=55;//cars.getTopSpeed();
        speed=30;
    }
    
    
    /*
    * Logic flow for the Player class::
    *
    *@param Graphics p the graphics class used in the game's frame, used in the function to draw the vehicle.
    */
    public void draw(Graphics p){
        
        
        //draw actual car::
        if(angle==0)
            p.drawImage(currentImage,location[0],location[1],imageSize[0],imageSize[1],null);
        else
            p.drawImage(ImageUtils.rotateImage(currentImage,angle),location[0],location[1],imageSize[0],imageSize[1],null);
        
        
        
//        p.setColor(Color.pink);
//        p.fillRect(upperSpan.x,upperSpan.y,upperSpan.width,upperSpan.height);
//        p.setColor(Color.blue);
//        p.fillRect(lowerSpan.x,lowerSpan.y,lowerSpan.width,lowerSpan.height);
        
    }
    
    public void calculate(){
        
        TOP_SPEED=ORIGINAL_TOP_SPEED-angle/7;
        
//        System.out.println(TOP_SPEED);
        
        if(accelerating){ //speed in kilometers per hour
            if(speed<TOP_SPEED){
                speed+=ACCELERATION;
            }else if(speed>TOP_SPEED)
                speed=TOP_SPEED;
        } else if(!brakes){//not accelerating and is not applying brakes
            if(speed>0)
                speed-=noEffectDecrease;
            else speed=0;
        }
        
        if(brakes){
            if(speed>-1*TOP_SPEED/2)
                speed-=brakeDecrease;
        }
        
        if(//canTurnLeft&&
                turningLeft){ //other effecting observed elsewhere, partially keyPressed(KeyEvent).
            if(angle>ANGLE_MIN) // over min (do a regular turn)
                angle-=angleIncrement;
            else if(angle<ANGLE_MIN) //under min
                angle=ANGLE_MIN;
            currentTurnRate=(int)speed/12;//update turn rate
            location[0]-=currentTurnRate*(Math.abs(angle)/5);
        }else if(//canTurnRight&&
                turningRight){
            if(angle<ANGLE_MAX)
                angle+=angleIncrement;
            currentTurnRate=(int)speed/12;//update turn rate
            location[0]+=currentTurnRate*(Math.abs(angle)/5);
        }else if(shouldCheckStoppedTurning&&!colliding){ //and is not turning anyways
            if(angle<0)
                angle+=angleIncrement;
            else if(angle>0)
                angle-=angleIncrement;
            else shouldCheckStoppedTurning=false;
        }
        
        //flow for canAttack boolean::
        if(!canAttack){ //aka is in attack
            if(canAttackPing<50) //ping flow:: VV
                canAttackPing++;
            else if(canAttackPing==50){
                canAttackPing=0;
                canAttack=true;  
            }                    //end ping flow ^^
            
            if(!isDoneWithAttack){
                
            }
            
        } else if(attacking) { //canAttack is already assumed true :)
            //just starting attack::
            
            attacking=false; //after all attacking code has been run so that the player is not attacking again and the canAttack boolean flow may initialize.
        }
        
        updateCollisionRectangles(); //keep last
        
    }
    
    int divideBy=3,t;
    public int getMapDown(){
        downRemainder+=speed%7;
        if((t=downRemainder/divideBy)>0){
            downRemainder=downRemainder%divideBy;
            return (int)(speed/divideBy)+t;
        }
        
        return (int)(speed/divideBy);
            
    }
    
//    private void updateCollisionPolygon(Graphics p){
//        angle*=2;
//        
//        int tt;
//        if(angle!=0)
//            tt=1;
//        else tt=0;
//        int m=(int)Math.round(((Math.sin(Math.toRadians((180-angle)/2)))*(CAR_PIXELS_HORIZONTAL))/(Math.cos(Math.toRadians(a))))*tt;
//        double n=(180-angle)/2-a;
//        int dX=(int)Math.round(Math.cos(Math.toRadians(n))*m);
//        int dY=(int)Math.round(Math.sin(Math.toRadians(n))*m)-32*tt;
//        
//        if(angle<=0)
//            collisionSpan=new Polygon(new int[]{locationPixels[0]+originalPoints[0][0]+dX,locationPixels[0]+originalPoints[1][0]+dX,locationPixels[0]+originalPoints[2][0]-dX,locationPixels[0]+originalPoints[3][0]-dX},
//                                      new int[]{locationPixels[1]+originalPoints[0][1]-dY,locationPixels[1]+originalPoints[1][1]+dY,locationPixels[1]+originalPoints[2][1]+dY,locationPixels[1]+originalPoints[3][1]-dY},
//                                      4);
//        else //angle>0
//            collisionSpan=new Polygon(new int[]{locationPixels[0]+originalPoints[0][0]+dX,locationPixels[0]+originalPoints[1][0]+dX,locationPixels[0]+originalPoints[2][0]-dX,locationPixels[0]+originalPoints[3][0]-dX},
//                                      new int[]{locationPixels[1]+originalPoints[0][1]+dY,locationPixels[1]+originalPoints[1][1]-dY,locationPixels[1]+originalPoints[2][1]-dY,locationPixels[1]+originalPoints[3][1]+dY},
//                                      4);
//        p.setColor(Color.blue);
//        p.fillPolygon(collisionSpan);
//        
//        angle/=2;
//    }
    
    private void updateCollisionRectangles(){
        xInc=(int)(angle/5);
        upperSpan=ShapeUtils.getRectByPoint(location[0]+xInc+addForOriginRect[0],location[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
        lowerSpan=ShapeUtils.getRectByPoint(location[0]-xInc+addForOriginRect[0],location[1]+rectSize[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
    }
    
}
