/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author 0001058857
 */
public class PlayerStat {
    public int CAR_TYPE;
    public double ACCELERATION,TOP_SPEED,BRAKE_SPEED;
    public boolean turningLeft=false,turningRight=false;
    
    public boolean accelerating=false,brakes=false;
    
    public boolean attacking=false,canAttack=true,isDoneWithAttack=false;
    public int canAttackPing=0,attackReachPing=50;
    
    public boolean shouldCheckStoppedTurning=true;
    
    public double speed=40;
    public double angle=25,ANGLE_MIN=-25,ANGLE_MAX=25,angleIncrement=5; //angle 0=straight up
    public double health,moneyHolding;
    
    public float distanceTravelled=0;
    public Rectangle displaySpan=new Rectangle(200,400,76,93);
    public Rectangle collisionSpan=new Rectangle();
    
    public BufferedImage currentImage=null;
    
    public int downRemainder=0;
    
    
    
    
}
