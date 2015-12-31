/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Josh
 */
public class StartGameFlow extends JFrame{
    private CPanel currentPanel;
    
    public final int X_SIZE=1000;
    public final int Y_SIZE=700;
    
    public StartGameFlow(){  
        this.setIconImage(GraphicsAssets.getIcon());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setSize(X_SIZE,Y_SIZE);
        this.setVisible(true);
        
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
    
    private void setNewPanelType(CPanel cp){    
//        this.setVisible(false);
        this.remove(currentPanel);
        currentPanel=cp;
        this.add(currentPanel);
        this.setVisible(true);
    }
    
    
}
