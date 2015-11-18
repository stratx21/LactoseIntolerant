/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author 0001058857
 */
public class MapInfo {
    public int TYPE=0;//type of terrain
    public int typeOne=0,typeTwo=0;
    public BufferedImage imageStraight=null,imageMedian=null,
            imageIntersection=null,imageOne=null,imageTwo=null;
    public int[] startLocation=new int[2];
    
    public final int 
            STRAIGHT_LENGTH_PIXELS=2005,
            MEDIAN_LENGTH_PIXELS=4011,
            INTERSECTION_LENGTH_PIXELS=2005,
            HORIZONTAL_SIZE=1032,
            CENTER_OF_MEDIAN=505;
    
    public int currentPixelLengthTwo=0,currentPixelLengthOne=0;
    
    
    public Polygon[] medianSpan=new Polygon[2];
    public ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();
    public ArrayList<Integer> yLoc=new ArrayList<Integer>();
    
    public Rectangle rLeft1,rLeft2,rRight1,rRight2;
    public Polygon upTri1,upTri2,downTri1,downTri2;
    
    
    
    public Rectangle leftSide=new Rectangle(132,0,8,2005);
    public Rectangle rightSide=new Rectangle(872,0,8,2005);
    
    public Polygon firstMedianLeft,firstMedianRight,secondMedianLeft,secondMedianRight;
    
}
