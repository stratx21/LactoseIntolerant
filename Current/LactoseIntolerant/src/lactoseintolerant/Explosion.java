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
    //center point of the explosion
    public Point center=new Point();
    
    public int radius=13,angle=0;
    public BufferedImage image=GraphicsAssets.getImage(25);
    public boolean expired=false;
    
    public int MAX_PING=55;
    
    public Rectangle damageSpan=new Rectangle();//used??
    
    /**
     * set up an explosion based on the original x and y coordinates and the 
     * maxPing, which is the point at which the explosion will expire and will
     * no longer exist
     * 
     * @param x x coordinate at which the explosion should start
     * @param y y coordinate at which the explosion should start
     * @param maxPing the point at which when the ping is equal to this then the
     *      explosion should expire
     */
    public Explosion(int x,int y,int maxPing){
        center.x=x;
        center.y=y;
        MAX_PING=maxPing;
    }
    
    /**
     * draw the explosion, and spin it around the point, increasing the angle
     * to spin it for each cycle that this function is called
     * 
     * @param p the Graphics instance from GamePanel used to draw the game's
     *      components
     * 
     * @param dy the distortion in the y direction caused by the player 
     *      increasing speed/containing a high speed or slowing down, moving
     *      all game objects up/down respectively 
     */
    public void draw(Graphics p,int dy){
        int t;
        p.drawImage(ImageUtils.rotateImage(image,angle), center.x-radius,center.y+dy-radius,radius*2,radius*2,null);  //radius/sqrt(2)
        
        angle+=35;
        radius+=2;
        
        expired=radius>MAX_PING;
    }
    
}
