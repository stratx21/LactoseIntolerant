/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Josh
 */
public class Explosion {
    public Point center=new Point();
    public int radius=13,angle=0;
    public BufferedImage image=GraphicsAssets.getImage(25);
    public boolean expired=false;
    public Rectangle damageSpan=new Rectangle();
    
    public Explosion(int x,int y){
        center.x=x;
        center.y=y;
    }
    
    public void draw(Graphics p,int dy){
        int t;
        p.drawImage(ImageUtils.rotateImage(image,angle), center.x-radius,center.y+dy-radius,radius*2,radius*2,null);  //radius/sqrt(2)
        
        angle+=35;
        radius+=2;
        
        expired=radius>55;
    }
    
}
