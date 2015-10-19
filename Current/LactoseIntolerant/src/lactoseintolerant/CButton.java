/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author 0001058857
 */
public class CButton extends JButton implements MouseListener{
    
    public ArrayList<BufferedImage> icons=new ArrayList<BufferedImage>();
    
    public CButton(int x,int y,int xs,int ys,String a){
        super.setBounds(x,y,xs,ys);
        if(a!=null)
            super.setText(a);
        this.addMouseListener(this);
    }
    
    public CButton(int x,int y,int xs,int ys){
        super.setBounds(x,y,xs,ys);
    }

    @Override
    public void mouseClicked(MouseEvent e) {clicked();}

    @Override
    public void mousePressed(MouseEvent e) {pressed();}

    @Override
    public void mouseReleased(MouseEvent e) {released();}

    @Override
    public void mouseEntered(MouseEvent e) {entered();}

    @Override
    public void mouseExited(MouseEvent e) {exited();}
    
    public void clicked(){}
    public void pressed(){}
    public void released(){}
    public void entered(){}
    public void exited(){}
    
    
}
