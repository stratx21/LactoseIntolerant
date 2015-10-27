/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
        updateCollisionPolygon(p);
        
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
        
        
    }
    
}
