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
    
    /** **function is, thus far, obsolete since the Player collision span will 
     * now be represented by two Rectangle objects 
     * (from a recent update in 11.2015)
     * 
     *  Can be used for the Player/AI/Civilian vehicles to test if they 
     *  hit the median; there are two Polygons used there; will put here 
     *  (hopefully) if there are more that can/should be used by this function. 
     * 
     *  It will not work if there is a chance of a side of one of the Polygon 
     *  objects being inside the other Polygon but the point is outside.
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
