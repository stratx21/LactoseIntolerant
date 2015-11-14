/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author 0001058857
 */
public class MapInfo {
    public int TYPE=0;//type of terrain
    public int typeOne=0,typeTwo=0;
    public BufferedImage imageStraight=null,imageMedian=null,
            imageIntersection=null,imageOne=null,imageTwo=null;
    public int[] startLocationOne=new int[2];
    public int[] startLocationTwo=new int[2];
    
    public int STRAIGHT_LENGTH_PIXELS=2005;
    public int MEDIAN_LENGTH_PIXELS=4011;
    public int INTERSECTION_LENGTH_PIXELS=2005;
    public int HORIZONTAL_SIZE=1032;
    public int currentPixelLengthTwo=0,currentPixelLengthOne=0;
    
    
    
    public Rectangle leftSide=new Rectangle(132,0,8,2005);
    public Rectangle rightSide=new Rectangle(872,0,8,2005);
    
    public Polygon firstMedianLeft,firstMedianRight,secondMedianLeft,secondMedianRight;
    
}
