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
public class Profile {
    
    //speed, health, boost, machine gun, mines, missiles
    public static boolean[][] upgrades=new boolean[3][6]; //make a 3d array for each car type added in
    
    public static final double[][] prices=new double[][]{
        {10000,15000,30000,45000,65000,85000},
        {15000,22500,10000,17500,20000,22500},
        {22500,27500,15000,22500,25000,32500}
    };
    
    public static int equipped=0; 
    
    public static double money=1000000.00;
    
}
