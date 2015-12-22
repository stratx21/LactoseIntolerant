/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    
    private BufferedImage mainBackground=GraphicsAssets.getImage(27);
    
    public MainMenu(JFrame f){
//        GameFlow g=new GameFlow(f);//comment out to not auto-go to game
        setUpMainMenu(f);
    }
    
    @Override
    public void paintComponent(Graphics p){
        p.drawImage(mainBackground,0,0,null);
    }
    
    private void setUpMainMenu(JFrame f){
        System.out.println("setting up main menu...");
        
        f.getContentPane().removeAll();
        f.getContentPane().repaint();
        
        this.setLayout(null);
        
        f.add(this);
        
        //start button::
        this.add(new CButton(245,293,184,54,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(34)),
                    new ImageIcon(GraphicsAssets.getImage(35)) }             ){
        @Override
        public void released(){
            
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
        this.add(new CButton(462,293,184,54,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(30)),
                    new ImageIcon(GraphicsAssets.getImage(31)) }             ){
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
        this.add(new CButton(245,379,401,52,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(32)),
                    new ImageIcon(GraphicsAssets.getImage(33)) }             ){
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
        this.add(new CButton(245,469,401,53,
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