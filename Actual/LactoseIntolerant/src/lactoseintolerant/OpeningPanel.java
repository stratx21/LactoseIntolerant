/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 0001058857
 */
public class OpeningPanel extends CPanel{
    private int currentState=0;//index #
    private int totalStates=3;//normal #s
    
    private int ping=1;
    
    private boolean imported=false;
    
    private ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();
    
    public OpeningPanel(){
        try{
            images.add(GraphicsAssets.getLogo());
            images.add(GraphicsAssets.getFirstImage());
            images.add(GraphicsAssets.getSecondImage());
        }catch(IOException e){ErrorLogger.logIOError(e,"OpeningPanel()");}
        FRAME_SIZE[0]=StartGameFlow.FRAME_SIZE[0];
        FRAME_SIZE[1]=StartGameFlow.FRAME_SIZE[1];
    }
    
    @Override
    public void paintComponent(Graphics g){
        
        
        g.setColor(Color.black);
        g.fillRect(0,0,FRAME_SIZE[0],FRAME_SIZE[1]);
        if(currentState<totalStates){
            g.drawImage(images.get(currentState),0,100,FRAME_SIZE[0],FRAME_SIZE[1]-200,null);
            if(ping%85==0)
                currentState++;
        }else if(currentState==totalStates)
                currentState++;
        else{
            done=true;
        }
           
        if(currentState==2&&!imported){
            GraphicsAssets.importImages();
//            System.out.println("aaaaaaaaaaa"); I FOUND IT!!! HA!
            imported=true;
        }
        
        if(currentState==2&&ping%14==0)
            AudioAssets.play("Sheep");
        else if(currentState==2&&ping%17==0)
            AudioAssets.play("JoshSheep");
        ping++;
        
        if(!done&&currentState!=totalStates)
        try{Thread.sleep(40);
        }catch(Exception e){
            ErrorLogger.logError(e,"OpeningPanel");
        }
            
        repaint();
    }
    
}
