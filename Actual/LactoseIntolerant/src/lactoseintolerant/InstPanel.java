/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author 0001058857
 */
public class InstPanel extends CPanel implements MouseListener{ //Instructions Panel
    public int PAGE=1;
    
    private ArrayList<Component> components=new ArrayList<Component>();
    public ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();
    public CListener done=null;
    
    private int currentIndex=0;
    
    
    public InstPanel(CListener l,int[] frmSize){
        FRAME_SIZE=StartGameFlow.FRAME_SIZE;
        done=l;
    }
    
    public void display(){
        
    }
    
    public void setComponents(){
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        //move to next image, check if it's at the last image.
        if(currentIndex<images.size()-1){
            currentIndex++;
        } else{
            done.actionPerformed();
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void paintComponent(java.awt.Graphics p){
        p.drawImage(images.get(currentIndex),0,0,FRAME_SIZE[0],FRAME_SIZE[1],null);
    }
    
}
