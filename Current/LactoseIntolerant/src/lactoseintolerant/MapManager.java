/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author 0001058857
 */
public class MapManager extends MapInfo{
    LevelManager currentLevel=new LevelManager();
    
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
    }
        
    public void resetTypeOne(){
        switch(typeOne=currentLevel.getNextType()){
            case 0: currentPixelLengthOne=STRAIGHT_LENGTH_PIXELS;
                imageOne=imageStraight;
                break;
            case 1: currentPixelLengthOne=MEDIAN_LENGTH_PIXELS;
                imageOne=imageMedian;
                break;
            case 2: currentPixelLengthOne=INTERSECTION_LENGTH_PIXELS;
                imageOne=imageIntersection;
                break;
        }
    }
    
    public void resetTypeTwo(){
        switch(typeTwo=currentLevel.getNextType()){
            case 0: currentPixelLengthTwo=STRAIGHT_LENGTH_PIXELS;
                imageTwo=imageStraight;
                break;
            case 1: currentPixelLengthTwo=MEDIAN_LENGTH_PIXELS;
                imageTwo=imageMedian;
                break;
            case 2: currentPixelLengthTwo=INTERSECTION_LENGTH_PIXELS;
                imageTwo=imageIntersection;
                break;
        }
    }
    
    public boolean checkIfHitsMap(Rectangle r){ //to edit
        return false;
    }
}
