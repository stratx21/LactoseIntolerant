/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author 0001058857
 */
public class MapManager {
    
    //note:: instead of having the two images, either make it so the player can't go backwards or make it an ArrayList of images.
    
    //0-straight, 1-median, 2-intersection
    LevelManager currentLevel=null;
    
    private boolean firstMedianExists=false,secondMedianExists=false;
    
    private int yDistort=0;
    
    public boolean drawSecond=false;
    
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
    
//    public boolean hitFromLeft=true;
    
    public CListener done=null;
    
    private int tempPixels,tD=0;
    
    /**
     *  Initialize the map
     * 
     * @param type type of terrain to put on the map
     * @param lv level (difficulty)
     */
    public MapManager(int type,int lv,CListener c){//starts on 0
        done=c;
        
        currentLevel=new LevelManager(lv,c);
        
        imageStraight=GraphicsAssets.getImage(10+(TYPE=type));//    V   x3
        imageMedian=GraphicsAssets.getImage(11+(type));
        imageIntersection=GraphicsAssets.getImage(12+(type)); //constant images^
        
        resetTypeOne();
        resetTypeTwo();
        
        //initialize:: 
        
        images.add(GraphicsAssets.getImage(10));
        yLoc.add(0);
        
        for(int i=0;i<currentLevel.levelInfo.length;i++){
            switch(currentLevel.levelInfo[i]){
                case 0://straight
                    images.add(GraphicsAssets.getImage(10));
                    tD-=STRAIGHT_LENGTH_PIXELS;
                    break;
                case 1://median
                    images.add(GraphicsAssets.getImage(11));
                    tD-=MEDIAN_LENGTH_PIXELS;
                    break;
                case 2://intersection
                    images.add(GraphicsAssets.getImage(12));
                    tD-=INTERSECTION_LENGTH_PIXELS;
                    break;
            }
            yLoc.add(tD);
        }
    }
    
    /**
     * Used for calculations that are located in GamePanel, which includes 
     * the main gameplay flow for relationships between the player, civilian, enemy, map, etc.
     *
     * @param d integer returned from the Player class that helps indicate how 
     * much the player speed is, influencing how much everything will move.
     */
    public void moveAllDown(int d){
        int a;
        for(int i=0;i<yLoc.size();i++){
            a=yLoc.get(i);
            yLoc.set(i,a+d);
        }
    }
    
    public int curIndex=0;

    /**
     * Process the local class-ranked calculations that occur regularly for the map::
     *
     */
    public void calculate(){
        //System.out.println("1: "+startLocationOne[1]+"  2: "+startLocationTwo[1]);
//        if(startLocationOne[1]>700){ //one expires
//            resetTypeOne();
//            startLocationOne[1]=startLocationTwo[1]-currentPixelLengthOne;//reset start
//        }
//        if(startLocationTwo[1]>700){ //two expires
//            resetTypeTwo();
//            startLocationTwo[1]=startLocationOne[1]-currentPixelLengthTwo;//reset start
//        }
//        
//        p.drawImage(imageOne,startLocationOne[0],startLocationOne[1],null);
//        
//        p.drawImage(imageTwo,startLocationTwo[0],startLocationTwo[1],null);
//        
//        updateMedianPolygons(p);
        if(curIndex==yLoc.size()-2){
            System.out.println("!!!!!!!!!!@@@@@@@@@@@@@@@@@@");
            done.actionPerformed();
        }
        
        
        boolean go=true;
        int temDis,//temporary distance (y)
                temp;//temp used after while ends
        while(go){
            if((temDis=yLoc.get(curIndex))>700){//image has expired
                curIndex++;
            } else if(temDis>-50){
                drawSecond=true;
                go=false;
            } else if(temDis<-1*STRAIGHT_LENGTH_PIXELS+400){ //picture is getting out of view of screen (is just going up beyond the lower boundary)
                curIndex--;
                go=false;
                drawSecond=true;
            } else go=false;
        }
        
        if(curIndex>0&&currentLevel.levelInfo[curIndex-1]==1){
//            medianSpan[0]=new Polygon(new int[]{505,      420,      420,    506,    592,    592      },
//                                      new int[]{temp+4000,temp+3948,temp+85,temp+34,temp+85,temp+3948},
//                                      6);
            temp=yLoc.get(curIndex-1)-MEDIAN_LENGTH_PIXELS;
//            System.out.println("oneee "+temp);
            
            
            //temp-=MEDIAN_LENGTH_PIXELS;
            
            rLeft1=new Rectangle(420,temp+88,85,3857);
            rRight1=new Rectangle(505,temp+88,85,3857);
            upTri1=new Polygon  (new int[]{420,      505,      592},
                                 new int[]{temp+85,  temp+34,  temp+85},
                                 3);
            downTri1=new Polygon(new int[]{420,      505,      592},
                                 new int[]{temp+3948,temp+4000,temp+3948},
                                 3);
            
            
        } else if(rLeft1!=null){
            rLeft1=null;
            rRight1=null;
            upTri1=null;
            downTri1=null;
        }
        
        if(drawSecond){
            if(currentLevel.levelInfo.length>curIndex&&currentLevel.levelInfo[curIndex]==1){
//                medianSpan[1]=new Polygon(new int[]{505,      420,      420,    506,    592,    592      },
//                                          new int[]{temp+4000,temp+3948,temp+85,temp+34,temp+85,temp+3948},
//                                          6);
                temp=yLoc.get(curIndex)-MEDIAN_LENGTH_PIXELS;
                
                //temp-=MEDIAN_LENGTH_PIXELS;
                
//                System.out.println("twoooo "+temp);
                
                rLeft2=new Rectangle(420,temp+88,85,3857);
                rRight2=new Rectangle(505,temp+88,85,3857);
                upTri2=new Polygon  (new int[]{420,      505,      592},
                                     new int[]{temp+85,  temp+34,  temp+85},
                                     3);
                downTri2=new Polygon(new int[]{420,      505,      592},
                                     new int[]{temp+3948,temp+4000,temp+3948},
                                     3);
                
//                p.setColor(Color.blue);
//                p.fillRect(rLeft2.x,rLeft2.y,rLeft2.width,rLeft2.height);
//                p.setColor(Color.red);
//                p.fillRect(rRight2.x,rRight2.y,rRight2.width,rRight2.height);
//                p.setColor(Color.pink);
//                p.fillPolygon(upTri2);
//                p.setColor(Color.yellow);
//                p.fillPolygon(downTri2);
                
                
            } else if(rLeft2!=null){
                rLeft2=null;
                rRight2=null;
                upTri2=null;
                downTri2=null;
            }
        }
        
//        if(rLeft1!=null){
//                p.setColor(Color.blue);
//                p.fillRect(rLeft1.x,rLeft1.y,rLeft1.width,rLeft1.height);
//                p.setColor(Color.red);
//                p.fillRect(rRight1.x,rRight1.y,rRight1.width,rRight1.height);
//                p.setColor(Color.pink);
//                p.fillPolygon(upTri1);
//                p.setColor(Color.yellow);
//                p.fillPolygon(downTri1);
//            }
        
    }
    
    /**
     * Draw the map image(s)
     *
     * @param p the Graphics class used to draw on the GamePanel.
     * @param dY the displacement that is added to the y direction for the 
     *  whole image based on the player speed. 
     */
    public void draw(Graphics p,int dY){
        p.drawImage(images.get(curIndex),Profile.xStart,yLoc.get(curIndex)+(yDistort=dY),null);
        if(drawSecond&&curIndex+1<images.size())
            p.drawImage(images.get(curIndex+1),Profile.xStart,yLoc.get(curIndex+1)+dY,null);
    }
    
    /**
     * reset the queue spot 1 so it can be used for the next type
     */
    private void resetTypeOne(){
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
    
    /**
     * reset the queue spot 2 so it can be used for the next type
     */
    private void resetTypeTwo(){
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
}
