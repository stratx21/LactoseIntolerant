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
    
    public Player(){
        cars.CURRENT_TYPE=CAR_TYPE=0;
        health=cars.getTopSpeed();
        ACCELERATION=cars.getAcceleration();
        TOP_SPEED=cars.getTopSpeed();
    }
    
    public void draw(Graphics p){
        if(accelerating){
            if(speed<TOP_SPEED){
                speed+=ACCELERATION;
                if(speed>TOP_SPEED)
                    speed=TOP_SPEED;
            }
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
        p.drawImage(GraphicsAssets.getImage(2),screenLocation[0],screenLocation[1],null);
    
    }
    
    
}
