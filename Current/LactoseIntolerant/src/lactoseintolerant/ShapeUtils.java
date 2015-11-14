package lactoseintolerant;


import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Josh
 */
public class ShapeUtils {
    
    /** **** can be used for the Player/AI/Civilian vehicles to test if they 
     *  hit the median; there are two Polygons used there; will put here 
     *  (hopefully) if there are more that can/should be used by this function. 
     *  It will not work if there is a chance of two sides of two Polygons 
     *  running into each other without contact by the points.
     * 
     * @param one Polygon one to compare
     * @param two Polygon two to compare
     * @return boolean value if a point in either one is within the range of the other one
     */
    public static boolean polygonContainsPolygon(Polygon one,Polygon two){ //based on Point objects contained in the Polygon objects supplied in the arguments::
        for(int i=0;i<two.npoints;i++)
            if(one.contains(new Point(two.xpoints[i],two.ypoints[i])))
                return true;
        
        for(int i=0;i<one.npoints;i++)
            if(two.contains(new Point(one.xpoints[i],two.ypoints[i])))
                return true;
        
        return false;
    }
    
    /**
     *
     * @param px middle of Rectangle x point
     * @param py middle of Rectangle y point
     * @param xSize x length of Rectangle
     * @param ySize y length of Rectangle
     * @return a Rectangle with the length of xSize and height of ySize that starts at point (px,py)
     */
    public static Rectangle getRectByPoint(int px,int py,int xSize,int ySize){
        return new Rectangle(px+xSize/2,py+ySize/2,xSize,ySize);
    }
}
