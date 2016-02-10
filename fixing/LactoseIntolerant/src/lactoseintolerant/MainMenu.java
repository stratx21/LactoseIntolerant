/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 *
 * @author 0001058857
 */
public class MainMenu extends Menu{
//    private Rectangle start=new Rectangle(244,293,429,347);
//    private Rectangle nw=new Rectangle(462,293,646,346); //new game
//    private Rectangle options=new Rectangle(245,379,646,432);
//    private Rectangle exit=new Rectangle(245,469,646,523);
    public boolean wrongFilePrompt=false;
    
    private CButton wrongFileOK=null;
    
    private BufferedImage mainBackground=GraphicsAssets.getImage(27);
    
    int[] FRAME_SIZE = {StartGameFlow.FRAME_SIZE[0],StartGameFlow.FRAME_SIZE[1]};
    
    public MainMenu(JFrame f){
//        GameFlow g=new GameFlow(f);//comment out to not auto-go to game
        setUpMainMenu(f);
    }
    
    @Override
    public void paintComponent(Graphics p){
        FRAME_SIZE[0] = this.getWidth();
        FRAME_SIZE[1] = this.getHeight();
        
        if(!wrongFilePrompt){
            p.drawImage(mainBackground,0,0,FRAME_SIZE[0],FRAME_SIZE[1],null);
        }
        else{
            p.setColor(new Color(0,0,0,150));
            p.fillRect(0,0,FRAME_SIZE[0],FRAME_SIZE[1]);
            try{
                p.setColor(Color.white);
                p.setFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/Fonts/Square.ttf")).deriveFont(48f));
                p.drawString("File input invalid",350,350);
            } catch(Exception e){
                ErrorLogger.logError(e,"paintComponent(Graphics) - MainMenu");
            }
            
            //prompt OK for user knowing the file didn't work
            this.add(wrongFileOK=new CButton(450,500,100,67,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(65)),
                            new ImageIcon(GraphicsAssets.getImage(66))}
                ){
                    @Override
                    public void released(){
                        
                    }
            });
        }
    }
    
    private void setUpMainMenu(JFrame f){
        
        FRAME_SIZE[0] = StartGameFlow.FRAME_SIZE[0];
        FRAME_SIZE[1] = StartGameFlow.FRAME_SIZE[1];
        
        f.getContentPane().removeAll();
        f.getContentPane().repaint();
        
        this.setLayout(null);
        
        f.add(this);
        
        //start button::

        //this.add(new CButton((int)(FRAME_SIZE[0]*.2),(int)(FRAME_SIZE[1]*.2),(int)(FRAME_SIZE[0]*.937*1.65*.46*.5),(int)(FRAME_SIZE[1]*.237*.5),

        this.add(new CButton((int)(FRAME_SIZE[0]*.244),(int)(FRAME_SIZE[1]*.4105),
                (int)(FRAME_SIZE[0]*.185),(int)(FRAME_SIZE[1]*.075),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(34)),
                    new ImageIcon(GraphicsAssets.getImage(35)) }             ){
        @Override
        public void released(){
            try{
                Profile.open();
            } catch(Exception e){
                ErrorLogger.logError(e,"setUpMainMenu(JFrame) - MainMenu");
            }
            if(Profile.inputSaveFile!=null)
                new GaragePanel(f,1){
                    @Override
                    public void backToMainMenu(){
                        setUpMainMenu(f);
                    }
                };
        }
        @Override
        public void entered(){
            super.setIcon(icons[1]);
        }
        @Override
        public void exited(){
            super.setIcon(icons[0]);
        }
        });
        
        //new game button::
       // this.add(new CButton(462,293,184,54,
        this.add(new CButton((int)(int)(FRAME_SIZE[0]*.461),(int)(FRAME_SIZE[1]*.4105),
                (int)(FRAME_SIZE[0]*.185),(int)(FRAME_SIZE[1]*.075),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(30)),
                    new ImageIcon(GraphicsAssets.getImage(31)) }         ){
        @Override
        public void released(){
            GaragePanel g = new GaragePanel(f,1){
                @Override
                public void backToMainMenu(){
                    setUpMainMenu(f);
                }
            };
        }
        });
        
        //options button::
        //this.add(new CButton(245,379,401,52,
        this.add(new CButton((int)(int)(FRAME_SIZE[0]*.244),(int)(FRAME_SIZE[1]*.53),
                (int)(FRAME_SIZE[0]*.187*2.17),(int)(FRAME_SIZE[1]*.075),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(32)),
                    new ImageIcon(GraphicsAssets.getImage(33)) }           ){
        @Override
        public void released(){
            f.getContentPane().removeAll();
                        f.getContentPane().repaint();
                        f.add(new OptionsMenu(new CListener(){
                                @Override
                                public void actionPerformed(){
                                    setUpMainMenu(f);
                                }
                            },f));
                        f.repaint();
        }
        });
        
        //exit button::
       // this.add(new CButton(245,469,401,53,
        this.add(new CButton((int)(FRAME_SIZE[0]*.244),(int)(FRAME_SIZE[1]*.658),
                (int)(FRAME_SIZE[0]*.187*2.17),(int)(FRAME_SIZE[1]*.075),
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(28)),
                    new ImageIcon(GraphicsAssets.getImage(29)) }             ){
        @Override
        public void released(){
            System.exit(0);
        }
        @Override
        public void entered(){
            super.setIcon(icons[1]);
        }
        @Override
        public void exited(){
            super.setIcon(icons[0]);
        }
        });
        
    }
    
    
    
}