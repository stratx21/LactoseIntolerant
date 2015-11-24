/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

/**
 *
 * @author 0001058857
 */
public class LevelManager extends LevelInfo{
    //0-straight, 1-median, 2-intersection
    public byte[] levelInfo={0,0,1,0,2,0,1,0,2,0,1,0}; //testing level
    public int MAX_DISTANCE;
    public int currentIndex=-1;
    public int level=1;
    
    public LevelManager(int t){
        switch(level=t){
            case 1:
                levelInfo=new byte[]{0,0,1,0,2,0,1,0,2,0,1,0,0,1,0,0,0,1,0,1,0};
        }
    }
    
    public int getNextType(){
        int t=levelInfo[++currentIndex];
                System.out.println(t);
        return t;
    }
}
