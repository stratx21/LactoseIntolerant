/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
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
    
    public ArrayList<Projectile> projectiles=new ArrayList<Projectile>();
    
    public boolean canAttack=true,isInAttack=false;
    
    public int pingWaitDelay=0;
    
    public double boostSpeed=30;
    
    public void draw(Graphics p){
        
    }
    
    public Weapon(int t,int lv){ 
        TYPE=t;
        LEVEL=lv;
        
        switch(TYPE){
            case 0:
                pingWaitDelay=250-15*lv;
                boostSpeed=lv*20;
                break;
            case 1:
                pingWaitDelay=20;
                break;
            case 2:
                pingWaitDelay=150;
                break;
            case 3:
                pingWaitDelay=200;
        }
    }
    
    public void fire(double angle){
        switch(TYPE){
            case 1:
                
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }
    
    
    
    
}
