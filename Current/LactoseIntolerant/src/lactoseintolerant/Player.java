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
    
    private PlayerCars cars=new PlayerCars();
    
    public int CAR_TYPE;
    public double ACCELERATION,TOP_SPEED,BRAKE_SPEED,ORIGINAL_TOP_SPEED,ORIGINAL_HEALTH;
    public boolean turningLeft=false,turningRight=false;
    
    public boolean collidingWithMap=false;// unused?????????????????????????????????????????????????????
    
    public boolean accelerating=false,brakes=false;
    
    public boolean hittingSideMap=false;
    
    public boolean attacking=false,canAttack=true,isDoneWithAttack=false;
    
    public int canAttackPing=0,attackReachPing=50;
    
    public boolean shouldCheckStoppedTurning=true;
    
    public double speedChange=0;
    
    public double speed=40;
    public double angle=25,ANGLE_MIN=-25,ANGLE_MAX=25,angleIncrement=5; //angle 0=straight up
    public double health,moneyHolding;
    public double noEffectDecrease=3,brakeDecrease=5;
    public int currentTurnRate=0,stoppedTurningTurnRate=1;
    public boolean canTurnRight=true,canTurnLeft=true;
    
    public float distanceTravelled=0;  //unused????????????????????????????????????????????????????  
    
    public int[] screenLocation={200,400};
    public int[] imageSize={76,93};
    //public Polygon collisionSpan=new Polygon();
    
    public long distancePixelsTotal=400;//unused?????????????????????????????????????????
    
    public int[] IMG_BLANK_SPACE=new int[]{22,11}; //how much blank space there is
    
    public int[][] originalPoints=new int[][]{{22,10},{53,10},{53,82},{22,82}};
    
//    public double a=Math.atan(Math.toRadians(45/16));
    
    public int CAR_PIXELS_HORIZONTAL=33,CAR_PIXELS_VERTICAL=72;
    
    public BufferedImage currentImage=null;
    
    public boolean collidedMap=false;
    
    public int downRemainder=0;
    
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
        switch(cars.CURRENT_TYPE=CAR_TYPE=t){ //original sudan that Gabe made the graphics for
            case 0:
                currentImage=GraphicsAssets.getImage(2);
                health=100;//cars.getTopSpeed();
                ACCELERATION=7;//cars.getAcceleration();
                ORIGINAL_TOP_SPEED=TOP_SPEED=55;//cars.getTopSpeed();
                speed=40;
                imageSize=new int[]{76,93};
                IMG_BLANK_SPACE=new int[]{22,11};
                originalPoints=new int[][]{{22,10},{53,10},{53,82},{22,82}};
                CAR_PIXELS_HORIZONTAL=33;
                rectSize=new int[]{31,36};
                addForOriginRect=new int[]{7,-7};
                ORIGINAL_HEALTH=100.0;
                break;
        }
        
        
        
        
    }
    
    
    /*
    * Draw the image::
    *
    *@param Graphics p the graphics class used in the game's frame, used in the function to draw the vehicle.
    */
    public void draw(Graphics p,int dY){
        
        
        //draw actual car::
        if(angle==0)
            p.drawImage(currentImage,screenLocation[0],screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        else
            p.drawImage(ImageUtils.rotateImage(currentImage,angle*(speed/TOP_SPEED)),screenLocation[0],screenLocation[1]+dY,imageSize[0],imageSize[1],null);
        
        
//        p.setColor(Color.pink);
//        p.fillRect(screenLocation[0],0,2,700);
        
//        p.setColor(Color.pink);
//        p.fillRect(upperSpan.x,upperSpan.y,upperSpan.width,upperSpan.height);
//        p.setColor(Color.blue);
//        p.fillRect(lowerSpan.x,lowerSpan.y,lowerSpan.width,lowerSpan.height);
        
    }
    
    /**
     *Logic flow for player::
     */
    public int calculate(int dY){
        
        TOP_SPEED=ORIGINAL_TOP_SPEED-angle/7;
        
//        System.out.println(TOP_SPEED);
        
        if(accelerating){ //speed in kilometers per hour
            speedChange=ACCELERATION;
            if(speed<TOP_SPEED){
                speed+=ACCELERATION;
            }else if(speed>TOP_SPEED){
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
                angle-=angleIncrement;
            else if(angle<ANGLE_MIN) //under min
                angle=ANGLE_MIN;
            currentTurnRate=(int)speed/12;//update turn rate
            screenLocation[0]-=currentTurnRate*(Math.abs(angle)/5);
        }else if(//canTurnRight&&
                turningRight&&speed!=0){
            if(angle<ANGLE_MAX)
                angle+=angleIncrement;
            currentTurnRate=(int)speed/12;//update turn rate
            screenLocation[0]+=currentTurnRate*(Math.abs(angle)/5);
        }else if(shouldCheckStoppedTurning&&!colliding){ //and is not turning anyways
            currentTurnRate=(int)speed/12;
            if(angle<0){
                if(angle<-1*angleIncrement)
                    angle+=angleIncrement;
                else
                    angle=0;
                
                screenLocation[0]+=stoppedTurningTurnRate*(angle/5);
            }else if(angle>0){
                if(angle>angleIncrement)
                    angle-=angleIncrement;
                else
                    angle=0;
                
                screenLocation[0]+=stoppedTurningTurnRate*(angle/5);
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
        
        updateCollisionRectangles(dY); //keep last
        
        return (int)speedChange;
        
    }
    
    int divideBy=3,t;
    public int getMapDown(){
        downRemainder+=speed%7;
        if((t=downRemainder/divideBy)>0){
            downRemainder=downRemainder%divideBy;
            return (int)(speed/divideBy)+t;
        }
        
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
    
    private void updateCollisionRectangles(int dY){
        xInc=(int)(angle/5);
        upperSpan=ShapeUtils.getRectByPoint(screenLocation[0]+xInc+addForOriginRect[0],dY+screenLocation[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
        lowerSpan=ShapeUtils.getRectByPoint(screenLocation[0]-xInc+addForOriginRect[0],dY+screenLocation[1]+rectSize[1]+addForOriginRect[1],rectSize[0],rectSize[1]);
    }
    
}
