/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;

/**
 *
 * @author Josh
 */
public class Projectile {
    public int TYPE,LEVEL;
    
    public double angle=0,speed=100,damage=20;
    
    public int[] screenLocation=new int[2];
    
    
    public Projectile(double angle,int type,int lv){
        TYPE=type;
        LEVEL=lv;
        this.angle=angle;
    }
    
    public void draw(Graphics p){
        
    }
    
    public void calc(){
        
    }
}
