/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author 0001058857
 */
public class Player{
    public int CAR_TYPE;
    public double ACCELERATION,TOP_SPEED,BRAKE_SPEED,ORIGINAL_TOP_SPEED,ORIGINAL_HEALTH;
    public boolean turningLeft=false,turningRight=false;
    
    public boolean collidingWithMap=false;
    
    public boolean collidingWithCiv=false;
    
    public boolean accelerating=false,brakes=false;
    
    public boolean hittingSideMap=false;
    
    public boolean attacking=false,canAttack=true,isDoneWithAttack=false;
    
    public int canAttackPing=0,attackReachPing=50;
    
    public boolean shouldCheckStoppedTurning=true;
    
    public double speedChange=0;
    
    public double speed=40;
    public double angle=25,ANGLE_MIN=-25,ANGLE_MAX=25,angleIncrement=5; //angle 0=straight up
    public double health;
    public double noEffectDecrease=3,brakeDecrease=5;
    public int currentTurnRate=0,stoppedTurningTurnRate=1;
    public boolean canTurnRight=true,canTurnLeft=true;
    
    public int[] screenLocation={200,400};
    public int[] imageSize={76,93};
    //public Polygon collisionSpan=new Polygon();
    
    public long distancePixelsTotal=400;
    
    public int[] IMG_BLANK_SPACE=new int[]{22,11}; //how much blank space there is
    
    public int[][] originalPoints=new int[][]{{22,10},{53,10},{53,82},{22,82}};
    
//    public double a=Math.atan(Math.toRadians(45/16));
    
    public int CAR_PIXELS_HORIZONTAL=33,CAR_PIXELS_VERTICAL=72;
    
    public BufferedImage currentImage=null;
    
    public boolean collidedMap=false;
    
    public boolean cantBeHitToRight=false,cantBeHitToLeft=false;
    
    public Rectangle upperSpan=new Rectangle(),lowerSpan=new Rectangle();
    public int[] rectSize=new int[]{31,36},
            addForOriginRect=new int[]{7,-7};//what is this variable? is it just something i used to figure it out once i looked
                                                //at the drawn rectangles in the game??
    
    
    public int xInc=0; //how much the x value should increase by for moving the upperSpan and lowerSpan Rectangle objects
    
    public boolean colliding=false;
    
    public Player(){ //temporary ::
        this(0);
    }
    
    public Player(int t){
        switch(CAR_TYPE=t){ //original sudan that Gabe made the graphics for
            case 0:
                currentImage=GraphicsAssets.getImage(2);
                health=100;//cars.getTopSpeed();
                ACCELERATION=5;//cars.getAcceleration();
                speed=ORIGINAL_TOP_SPEED=TOP_SPEED=75;//cars.getTopSpeed();
                imageSize=new int[]{76,93};
                IMG_BLANK_SPACE=new int[]{22,11};
                originalPoints=new int[][]{{22,10},{53,10},{53,82},{22,82}};
                CAR_PIXELS_HORIZONTAL=33;
                rectSize=new int[]{31,36};
                addForOriginRect=new int[]{7,-7};
                ORIGINAL_HEALTH=100.0;
                break;
        }
        
        setUpUpgradedStats();
        
        
    }
    
    private void setUpUpgradedStats(){
        if(Profile.upgrades[CAR_TYPE][2][0]){ //speed
            ORIGINAL_TOP_SPEED=TOP_SPEED+=21;
        } else if(Profile.upgrades[CAR_TYPE][1][0]){
            ORIGINAL_TOP_SPEED=TOP_SPEED+=14;
        } else if(Profile.upgrades[CAR_TYPE][0][0]){
            ORIGINAL_TOP_SPEED=TOP_SPEED+=7;
        }
        
        if(Profile.upgrades[CAR_TYPE][2][1]){ //armor
            health+=21;
        } else if(Profile.upgrades[CAR_TYPE][1][1]){
            health+=14;
        } else if(Profile.upgrades[CAR_TYPE][0][1]){
            health+=7;
        }
    }
    
    
    /**
    * Draw the image::
    *
    * @param p the graphics class used in the game's frame, used in the function to draw the vehicle.
    * 
    * @param dY displacement based on the player speeding up/slowing down and 
     * is only a component of graphical representation
    */
    public void draw(Graphics p,int dY){
        
        
        //draw actual car::
        if(angle==0)
            p.drawImage(currentImage,screenLocation[0]+Profile.xStart,screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        else
            p.drawImage(ImageUtils.rotateImage(currentImage,angle*(speed/TOP_SPEED)),screenLocation[0]+Profile.xStart,screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        
        
//        p.setColor(Color.pink);
//        p.fillRect(screenLocation[0],0,2,700);
        
//        p.setColor(Color.pink);
//        p.fillRect(upperSpan.x,upperSpan.y,upperSpan.width,upperSpan.height);
//        p.setColor(Color.blue);
//        p.fillRect(lowerSpan.x,lowerSpan.y,lowerSpan.width,lowerSpan.height);
        
    }
    
    /**
     * cycle flow for calculating the player location and other 
     * 
     * @param dY displacement based on the player speeding up/slowing down and 
     * is only a component of graphical representation
     */
    public int calculate(int dY){
        
        TOP_SPEED=ORIGINAL_TOP_SPEED-angle/7;
        
//        System.out.println(TOP_SPEED);
        
        if(accelerating){
            speedChange=ACCELERATION;
            if(speed<TOP_SPEED){
                speed+=ACCELERATION;
            }else if(speed>TOP_SPEED){
                if(TOP_SPEED>speed+5)
                    speed-=5;
                else
                    speed=TOP_SPEED;
            }
        } else if(!brakes){//not accelerating and is not applying brakes
            if(speed>0){
                if(speed>noEffectDecrease)
                    speed-=noEffectDecrease;
                else 
                    speed=0;
            } else if(speed<0){
                if(speed<-1*noEffectDecrease)
                    speed+=noEffectDecrease;
                else 
                    speed=0;
            }
            speedChange=0;
        }
        
        if(brakes){
            speedChange=-1*brakeDecrease;
            if(speed>-1*TOP_SPEED/2){
                speed+=speedChange;
            }
        }
        
        
        
        if(//canTurnLeft&&
                turningLeft&&speed!=0){ //other effecting observed elsewhere, partially keyPressed(KeyEvent).
            if(angle>ANGLE_MIN) // over min (do a regular turn)
                angle-=angleIncrement/2;
            else if(angle<ANGLE_MIN) //under min
                angle=ANGLE_MIN;
            currentTurnRate=(int)speed/12;//update turn rate
            screenLocation[0]-=currentTurnRate*(Math.abs(angle)/5)/2;
        }else if(//canTurnRight&&
                turningRight&&speed!=0){
            if(angle<ANGLE_MAX)
                angle+=angleIncrement/2;
            currentTurnRate=(int)speed/12;//update turn rate
            screenLocation[0]+=currentTurnRate*(Math.abs(angle)/5)/2;
        }else if(shouldCheckStoppedTurning&&!colliding){ //and is not turning anyways
            currentTurnRate=(int)speed/12;
            if(angle<0){
                if(angle<-1*angleIncrement/2)
                    angle+=angleIncrement/2;
                else
                    angle=0;
                
                screenLocation[0]+=stoppedTurningTurnRate*(angle/5)/2;
            }else if(angle>0){
                if(angle>angleIncrement/2)
                    angle-=angleIncrement/2;
                else
                    angle=0;
                
                screenLocation[0]+=stoppedTurningTurnRate*(angle/5)/2;
            }else shouldCheckStoppedTurning=false;
        }
        
        //flow for canAttack boolean::
        if(!canAttack){ //aka is in attack
            if(canAttackPing<50) //ping flow:: VV
                canAttackPing++;
            else if(canAttackPing==50){
                canAttackPing=0;
                canAttack=true;  
            }                    //end ping flow ^^
            
            if(!isDoneWithAttack){
                
            }
            
        } else if(attacking) { //canAttack is already assumed true :)
            //just starting attack::
            
            attacking=false; //after all attacking code has been run so that the player is not attacking again and the canAttack boolean flow may initialize.
        }
        
        
        xInc=(int)(angle/5);
        upperSpan=ShapeUtils.getRectByPoint(screenLocation[0]+xInc+addForOriginRect[0],dY+screenLocation[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
        lowerSpan=ShapeUtils.getRectByPoint(screenLocation[0]-xInc+addForOriginRect[0],dY+screenLocation[1]+rectSize[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
        
        return (int)speedChange;
        
    }
    
    
    //how much the speed should be divided by to get how far the map 
    //should be moved down.
    private int divideBy=3;
    
    /**
     * get how much the map should move down based on the player's speed
     * 
     * @return how much the map should move down as an integer value
     */
    public int getMapDown(){
        return (int)(speed/divideBy); 
    }
    
//    private void updateCollisionPolygon(Graphics p){
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
//            collisionSpan=new Polygon(new int[]{locationPixels[0]+originalPoints[0][0]+dX,locationPixels[0]+originalPoints[1][0]+dX,locationPixels[0]+originalPoints[2][0]-dX,locationPixels[0]+originalPoints[3][0]-dX},
//                                      new int[]{locationPixels[1]+originalPoints[0][1]-dY,locationPixels[1]+originalPoints[1][1]+dY,locationPixels[1]+originalPoints[2][1]+dY,locationPixels[1]+originalPoints[3][1]-dY},
//                                      4);
//        else //angle>0
//            collisionSpan=new Polygon(new int[]{locationPixels[0]+originalPoints[0][0]+dX,locationPixels[0]+originalPoints[1][0]+dX,locationPixels[0]+originalPoints[2][0]-dX,locationPixels[0]+originalPoints[3][0]-dX},
//                                      new int[]{locationPixels[1]+originalPoints[0][1]+dY,locationPixels[1]+originalPoints[1][1]-dY,locationPixels[1]+originalPoints[2][1]-dY,locationPixels[1]+originalPoints[3][1]+dY},
//                                      4);
//        p.setColor(Color.blue);
//        p.fillPolygon(collisionSpan);
//        
//        angle/=2;
//    }
    
}