/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author 0001058857
 */
public class GameFlow { //main game flow
    private GamePanel gamePanel;
    private GaragePanel garagePanel=new GaragePanel();
    private JFrame frame;
    
    private int level=1;
    
    private int frameRateMilliseconds=40;
    
    public GameFlow(JFrame f){ //initialize frame/panel/KeyListener relation aspects
        frame=f;
        
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        
        frame.setVisible(false);
        frame.add(gamePanel=new GamePanel(new int[]{frame.getWidth(),frame.getHeight()},level){
            @Override
            public void paintComponent(Graphics p){
                gamePanel.paintC(p);
                
                try{Thread.sleep(frameRateMilliseconds);}
                catch(Exception e){ErrorLogger.logError(e,"GameFlow.paintComponent");}
                repaint();
            }
        });
        frame.setVisible(true);
        
        frame.addKeyListener(gamePanel);
        
        frame.requestFocus();
    }
    
    private void switchToGamePanel(){
        frame.setVisible(false);
        frame.remove(garagePanel);
        frame.add(gamePanel);
        frame.setVisible(true);
    }
    
    private void switchtoGaragePanel(){
        frame.setVisible(false);
        frame.remove(gamePanel);
        frame.add(garagePanel);
        frame.setVisible(true);
    }
    
    
    
    
    
}
