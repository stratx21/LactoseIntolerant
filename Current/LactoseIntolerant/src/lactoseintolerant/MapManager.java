/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;

/**
 *
 * @author 0001058857
 */
public class MapManager extends MapInfo{
    LevelManager currentLevel=new LevelManager();
    private boolean firstMedianExists=false,secondMedianExists=false;
    
    public boolean hitFromLeft=true;
    
    
    
    private int tempPixels;
    
    public MapManager(int type){//starts on 0
        imageStraight=GraphicsAssets.getImage(10+(TYPE=type));//    V   x3
        imageMedian=GraphicsAssets.getImage(11+(TYPE));
        imageIntersection=GraphicsAssets.getImage(12+(TYPE)); //constant images^
        
        resetTypeOne();
        resetTypeTwo();
        
        startLocationTwo[1]=startLocationOne[1]-currentPixelLengthTwo;
    }
    
    
    public void draw(Graphics p){
        //System.out.println("1: "+startLocationOne[1]+"  2: "+startLocationTwo[1]);
        if(startLocationOne[1]>700){ //one expires
            resetTypeOne();
            startLocationOne[1]=startLocationTwo[1]-currentPixelLengthOne;//reset start
        }
        if(startLocationTwo[1]>700){ //two expires
            resetTypeTwo();
            startLocationTwo[1]=startLocationOne[1]-currentPixelLengthTwo;//reset start
        }
        
        p.drawImage(imageOne,startLocationOne[0],startLocationOne[1],null);
        
        p.drawImage(imageTwo,startLocationTwo[0],startLocationTwo[1],null);
        
        updateMedianPolygons(p);
    }
        
    public void resetTypeOne(){
        switch(typeOne=currentLevel.getNextType()){
            case 0: currentPixelLengthOne=STRAIGHT_LENGTH_PIXELS;
                firstMedianExists=false;
                imageOne=imageStraight;
                break;
            case 1: currentPixelLengthOne=MEDIAN_LENGTH_PIXELS;
                firstMedianExists=true;
                imageOne=imageMedian;
                break;
            case 2: currentPixelLengthOne=INTERSECTION_LENGTH_PIXELS;
                firstMedianExists=false;
                imageOne=imageIntersection;
                break;
        }
    }
    
    public void resetTypeTwo(){
        switch(typeTwo=currentLevel.getNextType()){
            case 0: currentPixelLengthTwo=STRAIGHT_LENGTH_PIXELS;
                secondMedianExists=false;
                imageTwo=imageStraight;
                break;
            case 1: currentPixelLengthTwo=MEDIAN_LENGTH_PIXELS;
                secondMedianExists=true;
                imageTwo=imageMedian;
                break;
            case 2: currentPixelLengthTwo=INTERSECTION_LENGTH_PIXELS;
                secondMedianExists=false;
                imageTwo=imageIntersection;
                break;
        }
    }
    
    public void updateMedianPolygons(Graphics p){
        if(firstMedianExists){
            int x=startLocationOne[0],y=startLocationOne[1];
            firstMedianLeft=new Polygon(new int[]{x+506, x+420, x+420,x+506},
                                        new int[]{y+4000,y+3948,y+85, y+34},
                                    4);
            firstMedianRight=new Polygon(new int[]{x+506,x+592,x+592, x+506},
                                         new int[]{y+34, y+85, y+3948,y+4000},
                                        4);
            p.setColor(Color.blue);
            p.drawPolygon(firstMedianLeft);
            p.drawPolygon(firstMedianRight);
        } else if(firstMedianLeft!=null){
            firstMedianLeft=null;
            firstMedianRight=null;
        }
        
        if(secondMedianExists){
            int x=startLocationTwo[0],y=startLocationTwo[1];
            secondMedianLeft=new Polygon(new int[]{x+506,x+592,x+592, x+506, x+420, x+420},
                                    new int[]{y+34, y+85, y+3948,y+4000,y+3948,y+85},
                                    4);
            secondMedianRight=new Polygon(new int[]{x+506,x+592,x+592, x+506, x+420, x+420},
                                    new int[]{y+34, y+85, y+3948,y+4000,y+3948,y+85},
                                    4);
            p.setColor(Color.blue);
            p.drawPolygon(secondMedianLeft);
            p.drawPolygon(secondMedianRight);
        } else if(secondMedianLeft!=null){
            secondMedianLeft=null;
            secondMedianRight=null;
        }
        
        
    }
    
    public boolean medianContainsPolygon(Polygon one){ //based on Point objects contained in the Polygon objects supplied in the arguments::
        if(firstMedianLeft!=null&&ShapeUtils.polygonContainsPolygon(firstMedianLeft,one)
                ||secondMedianLeft!=null&&ShapeUtils.polygonContainsPolygon(secondMedianLeft,one)){
            hitFromLeft=true;
            return true;
        } else if(firstMedianRight!=null&&ShapeUtils.polygonContainsPolygon(firstMedianRight,one)
                ||secondMedianRight!=null&&ShapeUtils.polygonContainsPolygon(secondMedianRight,one)){
            hitFromLeft=false;
            return true;
        }
        
        return false;
    }
}
