/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Point;


/**
 *
 * @author Josh
 */
public class Triangle extends java.awt.Polygon{
    public Triangle(Point one,Point two,Point three){
        this.addPoint(one.x,one.y);
        this.addPoint(two.x,two.y);
        this.addPoint(three.x,three.y);
    }
    
}
