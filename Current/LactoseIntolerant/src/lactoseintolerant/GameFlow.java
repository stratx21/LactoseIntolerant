/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import javax.swing.JPanel;

/**
 *
 * @author 0001058857
 */
public class GameFlow extends GameFrame{
    private GamePanel gamePanel;
    
    public GameFlow(){
        
        this.setVisible(false);
        gamePanel=new GamePanel();
        this.add(gamePanel);
        this.setVisible(true);
        
        this.addKeyListener(gamePanel);
    }
    
}
