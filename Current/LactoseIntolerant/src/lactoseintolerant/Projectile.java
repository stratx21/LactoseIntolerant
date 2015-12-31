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
    public BufferedImage[] imageGroup=null;
    
    public Rectangle span=new Rectangle();
    
    public int[] screenLocation=new int[2];
    
    public int size[]=null;
    
    
    public Projectile(double angle,int type,int lv,int xC,int yC){
        switch(TYPE=type){
            case 1:
                damage=4+5*lv;
                speed=125;
                size=new int[]{9,8};
                break;
            case 2:
                damage=50+lv*10;
                speed=0;
                imageGroup=new BufferedImage[]{GraphicsAssets.getImage(70),GraphicsAssets.getImage(68)};
                size=new int[]{25,25};
                break;
            case 3:
                speed=150+50*lv;
                damage=100+30*lv;
                break;
        }
        
        image=GraphicsAssets.getImage(66+TYPE);
        
        LEVEL=lv;
        this.angle=angle;
        
        screenLocation[0]=span.x=xC;
        screenLocation[1]=span.y=yC;
    }
    
    private int ping=0;
    
    public void draw(Graphics p,int dY){
            
        if(imageGroup!=null){
            image=imageGroup[ping%2];
            ping++;
        }
        
        p.drawImage(image,screenLocation[0],screenLocation[1],null);
    }
    
    public void calc(int cY,int dY){
        screenLocation[1]+=cY;
        
//        screenLocation[0]+=Math.sin(Math.toRadians(angle))*speed/3;
        updateSpanRectangle(dY);
    }
    
    private void updateSpanRectangle(int dY){
        span=new Rectangle(screenLocation[0],screenLocation[1]+dY,size[0],size[1]);
    }
}
