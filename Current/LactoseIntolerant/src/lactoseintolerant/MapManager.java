/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;

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
        
    }
    
    
    public void draw(Graphics p){
        System.out.println("1: "+startLocationOne[1]+"  2: "+startLocationTwo[1]);
        if(startLocationOne[1]>700){ //one expires
            startLocationOne[1]=startLocationTwo[1]-currentPixelLengthTwo;//reset start
            resetTypeOne();
            System.out.println("one just passed");
        }
        if(startLocationTwo[1]>700){ //two expires
            startLocationTwo[1]=startLocationOne[1]-currentPixelLengthOne;//reset start
            resetTypeTwo();
            System.out.println("two just passed");
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
                imageOne=imageStraight;
                break;
            case 2: currentPixelLengthOne=INTERSECTION_LENGTH_PIXELS;
                imageOne=imageStraight;
                break;
        }
    }
    
    public void resetTypeTwo(){
        switch(typeTwo=currentLevel.getNextType()){
            case 0: currentPixelLengthTwo=STRAIGHT_LENGTH_PIXELS;
                imageTwo=imageStraight;
                break;
            case 1: currentPixelLengthTwo=MEDIAN_LENGTH_PIXELS;
                imageTwo=imageStraight;
                break;
            case 2: currentPixelLengthTwo=INTERSECTION_LENGTH_PIXELS;
                imageTwo=imageStraight;
                break;
        }
    }
}
