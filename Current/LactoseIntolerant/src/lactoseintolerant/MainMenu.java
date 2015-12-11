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
public class MainMenu extends Menu implements MouseListener{
    private Rectangle start=new Rectangle(244,293,429,347);
    private Rectangle nw=new Rectangle(462,293,646,346); //new game
    private Rectangle options=new Rectangle(245,379,646,432);
    private Rectangle exit=new Rectangle(245,469,646,523);
    
    private BufferedImage mainBackground=GraphicsAssets.getImage(27);
    
    public MainMenu(JFrame f){
//        GameFlow g=new GameFlow(f);//comment out to not auto-go to game
        
        
        this.setLayout(null);
        
        this.addMouseListener(this);
        
        f.add(this);
        
        //start button::
        this.add(new CButton(245,293,184,54,
                new ImageIcon[]{new ImageIcon(GraphicsAssets.getImage(34)),
                    new ImageIcon(GraphicsAssets.getImage(35)) }             ){
        @Override
        public void released(){
            GameFlow g=new GameFlow(f); //initialize the main game flow
        }
        @Override
        public void entered(){
            
            setIc(null);
            setIc(icons[1]);
            rmv(this);
            addd(this);
            super.repaint();
            
        }
        @Override
        public void exited(){
//            super.setIcon(icons[0]);
//            invalidate();
//            rpnt();
            System.out.println("exited-----");
            
        }
        });
        
    }
    
    public void rpnt(){
        repaint();
    }
    
    public void rmv(java.awt.Component c){
        this.remove(c);
    }
    
    public void addd(java.awt.Component c){
        this.add(c);
    }
    
    @Override
    public void paintComponent(Graphics p){
        p.drawImage(mainBackground,0,0,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("entered");
        if(start.contains(new Point(e.getX(),e.getY()))){
            
        } else if(nw.contains(new Point(e.getX(),e.getY()))){
            
        } else if(options.contains(new Point(e.getX(),e.getY()))){
            
        } else if(exit.contains(new Point(e.getX(),e.getY()))){
            
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    
    
    
}