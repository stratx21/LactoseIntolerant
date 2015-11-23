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
import java.awt.geom.Line2D;

/**
 *
 * @author 0001058857
 */
public class MapManager extends MapInfo{
    
    //note:: instead of having the two images, either make it so the player can't go backwards or make it an ArrayList of images.
    
    //0-straight, 1-median, 2-intersection
    LevelManager currentLevel=new LevelManager();
    
    private boolean firstMedianExists=false,secondMedianExists=false;
    
    private int yDistort=0;
    
    public boolean drawSecond=false;
    
//    public boolean hitFromLeft=true;
    
    
    
    private int tempPixels;
    
    public MapManager(int type){//starts on 0
        imageStraight=GraphicsAssets.getImage(10+(TYPE=type));//    V   x3
        imageMedian=GraphicsAssets.getImage(11+(TYPE));
        imageIntersection=GraphicsAssets.getImage(12+(TYPE)); //constant images^
        
        resetTypeOne();
        resetTypeTwo();
        
//        startLocationTwo[1]=startLocationOne[1]-currentPixelLengthTwo;
        
        initialize();
    }
    
    
    private int tD=0;
    private void initialize(){
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
    
    public void moveAllDown(int d){
        int a;
        for(int i=0;i<yLoc.size();i++){
            a=yLoc.get(i);
            yLoc.set(i,a+d);
        }
    }
    
    public int curIndex=0;
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
            if(currentLevel.levelInfo[curIndex]==1){
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
    
    public void draw(Graphics p,int dY){
        p.drawImage(images.get(curIndex),0,yLoc.get(curIndex)+(yDistort=dY),null);
        if(drawSecond)
            p.drawImage(images.get(curIndex+1),0,yLoc.get(curIndex+1)+dY,null);
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
//        if(firstMedianExists){
//            int x=startLocationOne[0],y=startLocationOne[1];
//            firstMedianLeft=new Polygon(new int[]{x+506, x+420, x+420,x+506},
//                                        new int[]{y+4000,y+3948,y+85, y+34},
//                                    4);
//            firstMedianRight=new Polygon(new int[]{x+506,x+592,x+592, x+506},
//                                         new int[]{y+34, y+85, y+3948,y+4000},
//                                        4);
//            p.setColor(Color.blue);
//            p.drawPolygon(firstMedianLeft);
//            p.drawPolygon(firstMedianRight);
//        } else if(firstMedianLeft!=null){
//            firstMedianLeft=null;
//            firstMedianRight=null;
//        }
//        
//        if(secondMedianExists){
//            int x=startLocationTwo[0],y=startLocationTwo[1];
//            secondMedianLeft=new Polygon(new int[]{x+506,x+592,x+592, x+506, x+420, x+420},
//                                    new int[]{y+34, y+85, y+3948,y+4000,y+3948,y+85},
//                                    4);
//            secondMedianRight=new Polygon(new int[]{x+506,x+592,x+592, x+506, x+420, x+420},
//                                    new int[]{y+34, y+85, y+3948,y+4000,y+3948,y+85},
//                                    4);
//            p.setColor(Color.blue);
//            p.drawPolygon(secondMedianLeft);
//            p.drawPolygon(secondMedianRight);
//        } else if(secondMedianLeft!=null){
//            secondMedianLeft=null;
//            secondMedianRight=null;
//        }
//        
//        
    }
    
//    public boolean medianContainsPolygon(Polygon one){ //based on Point objects contained in the Polygon objects supplied in the arguments::
//        if(firstMedianLeft!=null&&ShapeUtils.polygonContainsPolygon(firstMedianLeft,one)
//                ||secondMedianLeft!=null&&ShapeUtils.polygonContainsPolygon(secondMedianLeft,one)){
//            hitFromLeft=true;
//            return true;
//        } else if(firstMedianRight!=null&&ShapeUtils.polygonContainsPolygon(firstMedianRight,one)
//                ||secondMedianRight!=null&&ShapeUtils.polygonContainsPolygon(secondMedianRight,one)){
//            hitFromLeft=false;
//            return true;
//        }
//        
//        return false;
//    }
}
