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
public class LevelManager {
    public CListener done=null;
    
    //0-straight, 1-median, 2-intersection
    public byte[] levelInfo={0,0,1,0,2,0,1,0,2,0,1,0}; //testing level
    public int MAX_DISTANCE;
    public int currentIndex=-1;
    public int TYPE=0;
    public long objectiveTime=0;
    public int level=1;
    public String missionInfo="";    
    public LevelManager(int t,CListener c){
        done=c;
        switch(level=t){
            case 1:
                    TYPE=0;
                    objectiveTime=150000;
                    levelInfo=new byte[]{1,2,0,1,0,0,2,2,1,0,1,0,2,2,1};
                    break;
            case 2:
                    TYPE=1;
                    objectiveTime=159000;
                    levelInfo=new byte[]{0,2,2,1,2,0,0,2,1,0,1,2,0,2,1,0,0,2,0,1};
                    break;
            case 3:
                    TYPE=2;
                    objectiveTime=168000;
                    levelInfo=new byte[]{2,2,0,1,0,2,1,2,0,2,1,2,1,0,2,2,1,0,0,2,1,2,1,2,0};
                    break;
            case 4:
                    TYPE=0;
                    objectiveTime=177000;
                    levelInfo=new byte[]{0,1,2,2,2,0,0,1,0,0,2,0,1,2,2,1,0,1,0,0,0,2,0,0,1,2,1,0,2,2};
                    break;
            case 5:
                    TYPE=1;
                    objectiveTime=186000;
                    levelInfo=new byte[]{0,1,2,0,2,1,0,0,2,0,2,0,0,0,1,0,1,0,1,0,2,1,0,1,0,1,0,0,1,0,1,0,1,0,0};
                    break;
            case 6:
                    TYPE=3;
                    objectiveTime=195000;
                    levelInfo=new byte[]{1,0,2,1,0,2,1,2,0,0,0,1,2,2,2,2,1,0,0,0,0,2,2,2,1,2,1,0,2,1,2,1,0,1,0,2,0,2,0,2};
                    break;
            case 7:
                    TYPE=1;
                    objectiveTime=204000;
                    levelInfo=new byte[]{0,2,0,1,0,2,1,0,1,0,2,0,0,1,0,0,2,1,2,2,0,0,0,0,1,2,0,0,2,2,0,2,2,1,0,1,2,1,0,0,2,0,2,0,1};
                    break;
            case 8:
                    TYPE=3;
                    objectiveTime=213000;
                    levelInfo=new byte[]{2,2,2,0,0,1,2,2,2,1,0,2,2,2,2,2,0,0,2,1,0,0,0,0,1,0,1,0,0,2,1,0,0,0,1,0,1,0,1,0,2,0,0,0,1,0,2,1,2,2};
                    break;
            case 9:
                    TYPE=3;
                    objectiveTime=222000;
                    levelInfo=new byte[]{0,0,0,2,2,2,1,0,2,2,2,1,0,2,1,0,0,0,1,0,1,0,0,2,0,1,0,1,0,0,0,0,1,0,2,0,2,1,0,0,0,0,1,0,2,2,2,0,0,1,2,1,0,0,2};
                    break;
            case 10:
                    TYPE=3;
                    objectiveTime=231000;
                    levelInfo=new byte[]{1,0,1,2,2,0,2,1,2,0,2,1,2,1,2,0,2,1,0,0,0,1,0,0,0,2,2,0,1,2,0,0,0,1,0,1,0,0,1,0,1,0,0,0,1,2,2,1,2,2,1,2,0,2,1,0,2,0,1,2};
                    break;
            case 11:
                    TYPE=2;
                    objectiveTime=240000;
                    levelInfo=new byte[]{1,0,0,0,0,0,2,0,2,1,0,0,0,1,0,2,2,0,1,0,0,1,0,2,1,0,1,0,0,0,2,0,2,0,2,0,1,2,2,0,2,2,0,2,0,2,2,2,0,2,1,2,0,2,2,0,2,2,2,2,1,0,1,0,2};
                    break;
            case 12:
                    TYPE=2;
                    objectiveTime=249000;
                    levelInfo=new byte[]{1,2,0,0,1,2,0,2,2,2,2,1,0,0,1,0,1,0,1,2,2,1,0,1,0,1,0,1,0,1,2,2,0,0,0,2,0,2,0,2,1,0,0,0,1,0,2,2,0,1,2,1,0,1,0,2,1,2,0,1,0,2,2,2,0,1,0,1,2,2};
                    break;
            case 13:
                    TYPE=3;
                    objectiveTime=258000;
                    levelInfo=new byte[]{1,0,2,1,0,0,2,0,1,2,0,2,2,2,2,0,2,0,2,2,1,2,1,0,1,0,2,2,1,2,0,0,1,2,0,0,2,2,2,1,2,1,0,1,0,0,0,2,1,0,2,2,2,0,0,2,1,0,0,2,1,0,2,0,0,1,0,2,0,1,2,1,2,1,0};
                    break;
            case 14:
                    TYPE=2;
                    objectiveTime=267000;
                    levelInfo=new byte[]{1,0,0,0,1,0,1,0,0,0,2,2,0,1,0,1,0,0,2,0,0,1,0,1,0,2,1,2,0,2,0,1,0,1,0,0,1,0,1,0,2,1,0,0,0,2,0,1,2,1,0,0,0,0,0,1,0,0,1,0,2,2,0,1,0,2,0,0,0,1,0,1,0,1,2,1,2,1,0,1};
                    break;
            case 15:
                    TYPE=0;
                    objectiveTime=276000;
                    levelInfo=new byte[]{0,1,0,1,0,0,2,2,2,2,0,0,1,0,2,1,0,0,1,2,0,2,0,1,0,2,0,2,1,0,0,2,2,1,0,0,1,0,0,2,2,1,0,0,2,1,0,1,2,2,1,2,0,0,1,2,0,0,2,2,0,2,2,1,0,0,2,1,0,1,0,0,2,0,0,0,0,2,1,0,0,0,2,2,1};
                    break;
            case 16:
                    TYPE=0;
                    objectiveTime=285000;
                    levelInfo=new byte[]{0,0,0,2,2,2,2,1,0,1,2,2,2,1,0,1,2,1,2,0,0,0,1,0,1,0,1,0,0,2,2,1,0,0,1,2,0,2,2,2,2,0,0,1,0,1,0,0,0,1,2,0,2,1,0,1,2,1,0,0,0,0,1,0,2,0,1,0,1,0,2,0,1,0,1,0,2,2,1,2,1,0,0,2,2,2,1,2,1,0};
                    break;
            default:
                    System.err.println("Not within the available mission numbers");
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
