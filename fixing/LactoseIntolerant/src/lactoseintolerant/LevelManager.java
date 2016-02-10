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
    public CListener done=null;
    
    //0-straight, 1-median, 2-intersection
    public byte[] levelInfo={0,0,1,0,2,0,1,0,2,0,1,0}; //testing level
    public int MAX_DISTANCE;
    public int currentIndex=-1;
    public long objectiveTime=0;
    public int level=1;
    public String missionInfo="";    
    public LevelManager(int t,CListener c){
        done=c;
        switch(level=t){
            case 1:
                objectiveTime=115000;
                levelInfo=new byte[]{0,0,1,0,2,0,1,0,2,0,1,0,0,1,0,0};
                break;
            case 2:
                levelInfo=new byte[]{0,0,1,1,0,1,0,0,1,2,1,0,1,2,1,0,1,2,1,1,2,1,0,1,2};
                break;
        }
    }
    
    public int getNextType(){
        int t=0;
        if(levelInfo.length==1+currentIndex++){
            System.out.println("reajfnjenfjenfjewnfjkenfef");
            done.actionPerformed();
        } else
            t=levelInfo[currentIndex];
        
        return t;
    }
}
