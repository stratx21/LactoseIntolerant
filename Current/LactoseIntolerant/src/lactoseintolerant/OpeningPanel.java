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
    private int totalStates=2;//normal #s
    
    private ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();
    
    public OpeningPanel(){
        try{
            images.add(GraphicsAssets.getFirstImage());
            images.add(GraphicsAssets.getSecondImage());
        }catch(IOException e){ErrorLogger.logIOError(e,"OpeningPanel()");}
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,X_SIZE,Y_SIZE);
        if(currentState<totalStates){
            g.drawImage(images.get(currentState),0,100,X_SIZE,Y_SIZE-200,null);
            currentState++;
        }else if(currentState==totalStates)
            currentState++;
        else{
            GraphicsAssets.importImages();
            done=true;
        }
            
        if(!done&&currentState!=0)
        try{Thread.sleep(4000);
        }catch(Exception e){
            ErrorLogger.logError(e,"OpeningPanel");
        }
            
        repaint();
    }
    
}
