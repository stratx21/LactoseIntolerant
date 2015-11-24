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
    public boolean wipedOut=false,exploded=false;
    
    //keep the same between types::::              VVVVVVVVVV
    public int TYPE;
    public double ACCELERATION=5,TOP_SPEED=30,BRAKE_SPEED;
    
    public boolean turningLeft=false,turningRight=false,shouldCheckStoppedTurning=true;
        
    public boolean accelerating=false,brakes=false;
    
    public boolean attacking=false,canAttack=true,isDoneWithAttack=false;
    public int canAttackPing=0,attackReachPing=50;
    
    public double speed=30;
    //has no angle max/min values (will go straight unless hit by another vehicle; will not turn on its own.
    public double angle=0;//angle 0=straight up
    
    
    public double noEffectDecrease=3,brakeDecrease=5;
    public int currentTurnRate=0;
    
    public int angleIncrement=5;
    
    public float distanceTravelled=0;
    public int[] screenLocation={180,400};
    public int[] IMG_BLANK_SPACE=new int[2];
    
    public Polygon collisionSpan=new Polygon();
    public long locationPixelsTotal=400;
    
    public int SLOWING_DOWN_SPEED=4;
    
    //should change based on the car type::                         VVVVVVVV
    public double health;
    
    public Rectangle upperSpan=new Rectangle(),lowerSpan=new Rectangle();
    
    public Rectangle fullImageSpan;  //UPDATE!!! VV
    public double a;
    public int CAR_PIXELS_HORIZONTAL,CAR_PIXELS_VERTICAL; 
    public int ADD_FOR_HTNG_R_SIDE=45,ADD_FOR_HTNG_L_SIDE=23;
    public int ADD_FOR_LEFT_MEDIAN=45,ADD_FOR_RIGHT_MEDIAN=25;
    public int[] imageSize;
    public int[][] originalPoints;//for polygon
                                                       //^^^^^
    
    public BufferedImage currentImage=null;
    
    public boolean collidingWithPlayer=false,fromSidePlayer=false,
                    collidingWithMap=false,fromLeftMap=false;
    
    public int xInc=0;
    public int[] addForOriginRect=new int[]{7,-7};
    public int[] rectSize=new int[2];
    
    public boolean collidedMap=false;
    
    public int downRemainder=0;
    
    
    
//    public void updateCollisionPolygon(Graphics p){
//        angle*=2;
//        
//        int tt;
//        if(angle!=0)
//            tt=1;
//        else tt=0;
//        int m=(int)Math.round(((Math.sin(Math.toRadians((180-angle)/2)))*(CAR_PIXELS_HORIZONTAL))/(Math.cos(Math.toRadians(a))))*tt;
//        double n=(180-angle)/2-a;
//        int dX=(int)Math.round(Math.cos(Math.toRadians(n))*m);
//        int dY=(int)Math.round(Math.sin(Math.toRadians(n))*m)-32*tt;
//        
//        if(angle<=0)
//            collisionSpan=new Polygon(new int[]{screenLocation[0]+originalPoints[0][0]+dX,screenLocation[0]+originalPoints[1][0]+dX,screenLocation[0]+originalPoints[2][0]-dX,screenLocation[0]+originalPoints[3][0]-dX},
//                                      new int[]{screenLocation[1]+originalPoints[0][1]-dY,screenLocation[1]+originalPoints[1][1]+dY,screenLocation[1]+originalPoints[2][1]+dY,screenLocation[1]+originalPoints[3][1]-dY},
//                                      4);
//        else //angle>0
//            collisionSpan=new Polygon(new int[]{screenLocation[0]+originalPoints[0][0]+dX,screenLocation[0]+originalPoints[1][0]+dX,screenLocation[0]+originalPoints[2][0]-dX,screenLocation[0]+originalPoints[3][0]-dX},
//                                      new int[]{screenLocation[1]+originalPoints[0][1]+dY,screenLocation[1]+originalPoints[1][1]-dY,screenLocation[1]+originalPoints[2][1]-dY,screenLocation[1]+originalPoints[3][1]+dY},
//                                      4);
////        p.setColor(Color.blue);
////        p.fillPolygon(collisionSpan);
//        
//        angle/=2;
//    }
    
    
    
    
    
}
