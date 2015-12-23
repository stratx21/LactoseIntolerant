/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Josh
 */
public class Projectile {
    public int TYPE,LEVEL;
    
    public double angle=0,speed=100,damage=20;
    
    public BufferedImage image=null;
    
    public Rectangle span=new Rectangle();
    
    public int[] screenLocation=new int[2];
    
    
    public Projectile(double angle,int type,int lv,int xC,int yC){
        TYPE=type;
        LEVEL=lv;
        this.angle=angle;
        
        screenLocation[0]=span.x=xC;
        screenLocation[1]=span.y=yC;
    }
    
    public void draw(Graphics p,int dY){
        
    }
    
    public void calc(int cY,int dY){
        screenLocation[1]+=cY;
        
        screenLocation[0]+=Math.sin(angle)*speed/3;
        
    }
    
    private void updateSpanRectangle(int dy){
        
    }
}
