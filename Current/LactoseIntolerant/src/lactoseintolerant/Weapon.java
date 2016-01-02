/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author 0001058857
 */
public class Weapon {
    //types:: 0 - boost, 1 - auto gun, 2 - mines, 3 - missiles
    public int TYPE,
            LEVEL;
    
    public double speed=100,angle=0;
    
    /**
     * all Projectile objects in the game
     */
    public ArrayList<Projectile> projectiles=new ArrayList<Projectile>();
    
    /**
     * booleans used to tell if the weapon is attacking, and if the owner of
     * this Weapon is allowed to attack again, based on the pingWaitDelay
     */
    public boolean canAttack=true,isInAttack=false;
    
    /**
     * int used for the maximum value that the ping should hit for the owner
     * of this Weapon to be allowed to attack again. The ping is increased 
     * every paint/calculate cycle; once it reaches the pingWaitDelay the 
     * owner of the Weapon is allowed to attack again. 
     * 
     * pingWaitDelay depends on the type and level of the Weapon; it will
     * decrease for increased levels but will increase for certain types
     * of Weapons
     */
    public int pingWaitDelay=0;
    
    /**
     * if the Weapon type is a boost, this is the value that is added to the 
     * speed of the Weapon's owner when the boost is used
     */
    public double boostSpeed=30;
    
    /**
     * change the Weapon's values based on the arguments provided
     * 
     * @param t the type of Weapon
     * @param lv the level of the Weapon
     */
    public Weapon(int t,int lv){ 
        TYPE=t;
        LEVEL=lv;
        
        switch(TYPE){
            case 0:
                pingWaitDelay=250-15*lv;
                boostSpeed=lv*20;
                break;
            case 1:
                pingWaitDelay=3-lv;
                break;
            case 2:
                pingWaitDelay=70-lv*5;
                break;
            case 3:
                pingWaitDelay=125;
        }
    }
    
    
    
    
}
