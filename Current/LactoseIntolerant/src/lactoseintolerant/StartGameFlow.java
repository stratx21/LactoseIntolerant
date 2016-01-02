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
    public static int X_SIZE=0;
    public static  int Y_SIZE=0;
    
    public StartGameFlow(){  
        
        this.setIconImage(GraphicsAssets.getIcon());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(X_SIZE,Y_SIZE);
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
    
    
    public void setSIZE()
    { 
<<<<<<< HEAD
        X_SIZE=this.getWidth();
        Y_SIZE=this.getHeight();
        System.out.println(X_SIZE);
=======
        X_SIZE=1000;//this.getWidth();
        Y_SIZE=700;//this.getHeight();
        
        this.setSize(X_SIZE,Y_SIZE);
>>>>>>> 27c9901b9d4a8281e5ca93cdb3589d451c52b86a
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
