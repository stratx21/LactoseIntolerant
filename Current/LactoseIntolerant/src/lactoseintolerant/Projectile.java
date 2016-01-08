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
    //type and level of the projectile
    public int TYPE,LEVEL;
    
    public double angle=0,speed=100,damage=20;
    
    public BufferedImage image=null;
    
    /**
     * used for the mine upgrade; 2 images alternate so it appears to flash
     */
    public BufferedImage[] imageGroup=null;
    
    /**
     * Rectangle object used for collisions with other game objects
     */
    public Rectangle span=new Rectangle();
    
    //x and y coordinates of the Projectile
    public int[] screenLocation=new int[2];// < -- might just use span.x and span.y ?
    
    //size of the image
    public int size[]=null;
    
    /**
     * uses the data given by the arguments to form a Projectile object based
     * on what has been fired by the Weapon
     * 
     * @param angle the angle at which the vehicle was at when it fired this
     *      Projectile
     * @param type the type of Projectile this is 
     *      (bullet/shot, mine, or missile)
     * @param lv the level - the higher the level, the higher the damage and 
     *      the lower the delay until another Projectile can be fired
     * @param xC the x coordinate at which the Projectile should start
     * @param yC the y coordinate at which the Projectile should start
     */
    public Projectile(double angle,int type,int lv,int xC,int yC){
        switch(TYPE=type){
            case 1:
                damage=4+5*lv;
                speed=125;
                size=new int[]{9*StartGameFlow.FRAME_SIZE[0]/1000,8*StartGameFlow.FRAME_SIZE[1]/1000};
                break;
            case 2:
                damage=50+lv*10;
                speed=0;
                imageGroup=new BufferedImage[]{GraphicsAssets.getImage(70),GraphicsAssets.getImage(68)};
                size=new int[]{25*StartGameFlow.FRAME_SIZE[0]/1000,25*StartGameFlow.FRAME_SIZE[1]/1000};
                break;
            case 3:
                speed=150+50*lv;
                damage=100+30*lv;
                size=new int[]{30*StartGameFlow.FRAME_SIZE[0]/1000,46*StartGameFlow.FRAME_SIZE[1]/1000};
                break;
        }
        
        image=GraphicsAssets.getImage(66+TYPE);
        
        LEVEL=lv;
        this.angle=angle;
        
        screenLocation[0]=span.x=xC;
        screenLocation[1]=span.y=yC;
    }
    
    /**
     * used for the mine; increases by 1 every regular run through the 
     * draw(Graphics,int) function to change the image for the mine to "flash"
     */
    private int ping=0;
    
    /**
     * draw the graphical interpretation of the Projectile using the Graphics
     * from the GamePanel
     * 
     * @param p instance of the Graphics class from GamePanel. It is used to 
     *      draw the Projectile in the game
     * @param dY the distortion in the y direction caused by the player 
     *      speeding up or slowing down; all moves up or down on the screen
     *      based on this value
     */
    public void draw(Graphics p,int dY){
            
        if(imageGroup!=null){
            image=imageGroup[ping%2];
            ping++;
        }
        
        p.drawImage(image,screenLocation[0]+Profile.xStart,screenLocation[1],size[0],size[1],null);
    }
    
    /**
     * calculate the new location and other properties of the Projectile
     * 
     * @param cY number calculated outside of the Projectile class that is
     *      determined in GamePanel. It is how much the Projectile y location
     *      on the screen should increase based on the Projectile speed and how 
     *      much the other components are moving based on the player's speed
     * @param dY distortion in the y direction caused by the player speeding 
     *      up or slowing down
     */
    public void calc(int cY,int dY){
        screenLocation[1]+=cY;
        
//        screenLocation[0]+=Math.sin(Math.toRadians(angle))*speed/3;
        span=new Rectangle(screenLocation[0],screenLocation[1]+dY,size[0],size[1]);
    }
}
