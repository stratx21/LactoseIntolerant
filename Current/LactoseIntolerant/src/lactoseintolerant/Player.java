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
        TOP_SPEED=55;//cars.getTopSpeed();
    }
    
    public Player(int t){
        cars.CURRENT_TYPE=CAR_TYPE=t;
        
        currentImage=GraphicsAssets.getImage(2);
        
        health=100;//cars.getTopSpeed();
        ACCELERATION=10;//cars.getAcceleration();
        TOP_SPEED=55;//cars.getTopSpeed();
    }
    
    
    /*
    *guys dont bother reading thru all this it can get pretty messy but
    *ill try to add in some comments for reference, but it is mainly just
    *the logic flow for Player.
    *
    *@param Graphics p the graphics class used in the game's frame, used in the function to draw the vehicle.
    */
    public void draw(Graphics p){
        if(lastAngle!=angle)
            updateCollisionPolygon(p);
        
        lastAngle=angle;
        
        if(accelerating){ //speed in kilometers per hour
            if(speed<TOP_SPEED){
                speed+=ACCELERATION;
                System.out.println("going up");
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
        
        if(turningLeft){ //other effecting observed elsewhere, partially keyPressed(KeyEvent).
            if(angle>ANGLE_MIN)
                angle-=angleIncrement;
            currentTurnRate=(int)speed/12;//update turn rate
            distancePixels[0]-=currentTurnRate*(Math.abs(angle)/5);
        }else if(turningRight){
            if(angle<ANGLE_MAX)
                angle+=angleIncrement;
            currentTurnRate=(int)speed/12;//update turn rate
            distancePixels[0]+=currentTurnRate*(Math.abs(angle)/5);
        }else if(shouldCheckStoppedTurning){ //and is not turning anyways
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
        
        //draw actual car::
        p.drawImage(ImageUtils.rotateImage(currentImage,angle),distancePixels[0],distancePixels[1],imageSize[0],imageSize[1],null);
    
        
        //if(lastAngle!=angle)
            //updateCollisionPolygon(p);
        
        //lastAngle=angle;
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
    
    private void updateCollisionPolygon(Graphics p){
        angle*=2;
        
        int tt;
        if(angle!=0)
            tt=1;
        else tt=0;
        int m=(int)Math.round(((Math.sin(Math.toRadians((180-angle)/2)))*(carPixelsHorizontal))/(Math.cos(Math.toRadians(a))))*tt;
        double n=(180-angle)/2-a;
        int dX=(int)Math.round(Math.cos(Math.toRadians(n))*m);
        int dY=(int)Math.round(Math.sin(Math.toRadians(n))*m)-32*tt;
        
        if(angle<=0)
            collisionSpan=new Polygon(new int[]{distancePixels[0]+originalPoints[0][0]+dX,distancePixels[0]+originalPoints[1][0]+dX,distancePixels[0]+originalPoints[2][0]-dX,distancePixels[0]+originalPoints[3][0]-dX},
                                      new int[]{distancePixels[1]+originalPoints[0][1]-dY,distancePixels[1]+originalPoints[1][1]+dY,distancePixels[1]+originalPoints[2][1]+dY,distancePixels[1]+originalPoints[3][1]-dY},
                                      4);
        else //angle>0
            collisionSpan=new Polygon(new int[]{distancePixels[0]+originalPoints[0][0]+dX,distancePixels[0]+originalPoints[1][0]+dX,distancePixels[0]+originalPoints[2][0]-dX,distancePixels[0]+originalPoints[3][0]-dX},
                                      new int[]{distancePixels[1]+originalPoints[0][1]+dY,distancePixels[1]+originalPoints[1][1]-dY,distancePixels[1]+originalPoints[2][1]-dY,distancePixels[1]+originalPoints[3][1]+dY},
                                      4);
//        p.setColor(Color.blue);
//        p.fillPolygon(collisionSpan);
        
        angle/=2;
    }
    
}
