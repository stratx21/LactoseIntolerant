/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

/**
 *
 * @author Josh
 */
public class StartGameFlow extends GameFrame{
    private CPanel currentPanel;
    
    
    public StartGameFlow(){
        
        
        
        //for full game and intro and stuff:: (also uncomment in MainMenu) ::
        currentPanel=new OpeningPanel();
        this.add(currentPanel);
        super.setVisible(true);//keep this statement always!!
        
//        while(!currentPanel.done){
//            try{Thread.sleep(100);}
//            catch(Exception e){ErrorLogger.logError(e,"GameFlow");}
//        }
        
        setNewPanelType(new MainMenu(this));
    }
    
    private void setNewPanelType(CPanel cp){    
        this.setVisible(false);
        this.remove(currentPanel);
        currentPanel=cp;
        this.add(currentPanel);
        this.setVisible(true);
    }
    
    
}
