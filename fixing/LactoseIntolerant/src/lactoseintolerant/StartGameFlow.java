/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author Josh
 */
public class StartGameFlow extends JFrame{
    private CPanel currentPanel;
    private Button button;
    public static int[] FRAME_SIZE=new int[2];
    
    public StartGameFlow(){  
        
        this.setIconImage(GraphicsAssets.getIcon());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(FRAME_SIZE[0],FRAME_SIZE[1]);
        this.setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setSIZE();
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);
        
        //for full game and intro and stuff:: (also uncomment in MainMenu) ::
        currentPanel=new OpeningPanel();
        this.add(currentPanel);
        super.setVisible(true);//keep this statement always!!
        
        while(!currentPanel.done){
            try{Thread.sleep(200);}
            catch(Exception e){ErrorLogger.logError(e,"StartGameFlow");}
        }
        
        setNewPanelType(new MainMenu(this));
       
        
    }
    
    
    public void setSIZE(){ 

        FRAME_SIZE[0]=this.getWidth();
        FRAME_SIZE[1]=this.getHeight();

        this.setSize(FRAME_SIZE[0],FRAME_SIZE[1]);

        //TODO make game scale dinamicly
    }
    
    private void setNewPanelType(CPanel cp){    
//        this.setVisible(false);
        this.remove(currentPanel);
        currentPanel=cp;
        this.add(currentPanel);
        this.setVisible(true);
    }
    
    
}
