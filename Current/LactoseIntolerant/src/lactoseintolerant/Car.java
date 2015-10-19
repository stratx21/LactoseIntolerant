/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Josh Holland
 */
public class Car { //aka car types
    public static int NUMBER_OF_TYPES;
    public static int CURRENT_TYPE;
    public static double[][] spec; //[index where car is found]
                            //[number shows different specs
                                // 0-total health
                                // 1-speed (m/s)
                                // 2-acceleration (m/s^2)
                                // 3-turn rate(how fast it can turn)
                                // 4-brake speed
    
    
    public Car(){
        
        
    }
    
    public static double getOriginalHealth(){
        return spec[CURRENT_TYPE][0];
    }
    
    public static double getTopSpeed(){
        return spec[CURRENT_TYPE][1];
    }
    
    public static double getAcceleration(){
        return spec[CURRENT_TYPE][2];
    }
    
    public static double getTurnRate(){
        return spec[CURRENT_TYPE][3];
    }
    
    public static double getBrakeSpeed(){
        return spec[CURRENT_TYPE][4];
    }
    
    
}
