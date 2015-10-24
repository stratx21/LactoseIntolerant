/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
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
        
        health=cars.getTopSpeed();
        ACCELERATION=cars.getAcceleration();
        TOP_SPEED=cars.getTopSpeed();
    }
    
    public Player(int t){
        cars.CURRENT_TYPE=CAR_TYPE=t;
        //switch(t){//actually no dont use switch case, just add to the index of getImage for each since car images are right next to each other.
        currentImage=GraphicsAssets.getImage(2); 
        
        health=cars.getTopSpeed();
        ACCELERATION=cars.getAcceleration();
        TOP_SPEED=cars.getTopSpeed();
    }
    
    
    /*
    *guys dont bother reading thru all this it can get pretty messy but
    *ill try to add in some comments for reference, but it is mainly just
    *the logic flow for Player.
    
    *@param Graphics p the graphics class used in the game's frame, used in the function to draw the vehicle.
    */
    public void draw(Graphics p){
        if(accelerating){ //speed in kilometers per hour
            if(speed<TOP_SPEED){
                speed+=ACCELERATION;
                if(speed>TOP_SPEED)
                    speed=TOP_SPEED;
            }
        }
        
        if(turningLeft){ //other effecting observed elsewhere, partially keyPressed(KeyEvent).
            if(angle>ANGLE_MIN)
                angle-=angleIncrement;
            displaySpan.x-=Math.abs(angle)/2;
        }else if(turningRight){
            if(angle<ANGLE_MAX)
                angle+=angleIncrement;
            displaySpan.x+=Math.abs(angle)/2;
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
        p.drawImage(ImageUtils.rotateImage(currentImage,angle),displaySpan.x,displaySpan.y,displaySpan.width,displaySpan.height,null);
    
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
    
    
}
