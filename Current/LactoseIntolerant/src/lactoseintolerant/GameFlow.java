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
    private GamePanel gamePanel=null;
    private GaragePanel garagePanel=null;
    private JFrame frame;
    
    private int level=1;
    
    private final int frameRateMillisecondsInGame=20;
    
    public GameFlow(JFrame f,int level){ //initialize frame/panel/KeyListener relation aspects
        this.level=level;
        frame=f;
        
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        
        setUpGaragePanel();
        
        frame.setVisible(true);
        
        
        
        frame.requestFocus();
    }
    
    private void setUpGamePanel(){
//        frame.getContentPane().removeAll();
//        frame.getContentPane().repaint();
//        frame.add(gamePanel=new GamePanel(new int[]{frame.getWidth(),frame.getHeight()},level){
//            @Override
//            public void paintComponent(Graphics p){
//                gamePanel.paintC(p);
//                gamePanel.calcFlow();
//                
//                System.out.println("painting..");
//                
//                try{Thread.sleep(frameRateMillisecondsInGame);}
//                catch(Exception e){ErrorLogger.logError(e,"GameFlow.paintComponent");}
//                repaint();
//            }
//        });
//        frame.addKeyListener(gamePanel);
    }
    
    private void setUpGaragePanel(){
        frame.getContentPane().removeAll();
        frame.getContentPane().repaint();
        frame.add(garagePanel=new GaragePanel(frame,level));
        frame.repaint();
    }
    
    
    
    
    
}
