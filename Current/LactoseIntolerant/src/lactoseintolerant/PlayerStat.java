/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

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
    
    public double speed=0;
    public double angle=0; //straight up
    public double health;
    
    public float distanceTravelled=0;
    public int[] screenLocation=new int[2];
    
    
}
