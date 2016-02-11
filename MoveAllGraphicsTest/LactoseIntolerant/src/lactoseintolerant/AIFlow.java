/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author 0001058857
 */
public class AIFlow {
    
    //keep the same between types::::              VVVVVVVVVV
    public int TYPE;
    public double ACCELERATION=5,TOP_SPEED=30,BRAKE_SPEED;
    
    public boolean turningLeft=false,turningRight=false,shouldCheckStoppedTurning=true;
        
    public boolean accelerating=false,brakes=false;
    
    public double speed=30;
    //has no angle max/min values (will go straight unless hit by another vehicle; will not turn on its own.
    public double angle=0;//angle 0=straight up
    
    public double noEffectDecrease=3,brakeDecrease=5;
    public int currentTurnRate=0;
    
    public int angleIncrement=5;
    
    public int[] screenLocation={180,400};
    public int[] IMG_BLANK_SPACE=new int[2];
    
    public Polygon collisionSpan=new Polygon();
    public long locationPixelsTotal=400;
    
    public int SLOWING_DOWN_SPEED=4;
    
    //should change based on the car type::                         VVVVVVVV
    public double health=100;
    
    public Rectangle upperSpan=new Rectangle(),lowerSpan=new Rectangle();
    
    public Rectangle fullImageSpan;  //UPDATE!!! VV
    public double a;
    public int CAR_PIXELS_HORIZONTAL,CAR_PIXELS_VERTICAL; 
    public int ADD_FOR_HTNG_R_SIDE=45,ADD_FOR_HTNG_L_SIDE=25;
    public int ADD_FOR_LEFT_MEDIAN=45,ADD_FOR_RIGHT_MEDIAN=25;
    public int[] imageSize;
    public int[][] originalPoints;//for polygon
                                                       //^^^^^
    
    public BufferedImage currentImage=null;
    
    public boolean collidingWithPlayer=false,hitPlayerFromSide=false,
                    collidingWithMap=false,hitMapOnRightSideOfCar=false;
    
    public int xInc=0;
    public int[] addForOriginRect=new int[]{7,-7};
    public int[] rectSize=new int[2];
    
    public boolean collidedMap=false;
    
    public int downRemainder=0;
}
