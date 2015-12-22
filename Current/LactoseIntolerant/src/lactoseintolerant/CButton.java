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
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author 0001058857
 */
public class CButton extends JButton implements MouseListener{
    
    public int xIndex,yIndex;//when applicable 
    
    ImageIcon[] icons=null;
    
    public boolean pernamantSelect=false;
    
    ImageIcon disabledIcon=null;
    
    public boolean disabled=false,selected=false;
    
//    @Override
//    public void setIcon(Icon i){
//        System.out.println("changing icon");
//        super.setIcon(i);
//    }
    
    public CButton(int x,int y,int xs,int ys,String a){
        super.setBounds(x,y,xs,ys);
        if(a!=null)
            super.setText(a);
        this.addMouseListener(this);
    }
    
    public CButton(int x,int y,int xs,int ys){
        super.setBounds(x,y,xs,ys);
        this.addMouseListener(this);
    }
    
    public CButton(int x,int y,int xs,int ys,ImageIcon[] ic){
        icons=ic;
        
        this.setIcon(ic[0]);
        super.setBounds(x,y,xs,ys);
        this.addMouseListener(this);
        
        setContentAreaFilled(true);
    }
    
    public void disable(boolean dis){
        if(disabled=dis){
            this.setIcon(disabledIcon);
        } else{
            this.setIcon(icons[0]);
        }
    }
    
    public void setPernamantSelect(boolean c){
        if(pernamantSelect=c)
            this.setIcon(icons[1]);
        else if(!selected)
            this.setIcon(icons[0]);
        else 
            this.setIcon(icons[1]);
    }
    
//    public boolean getPernamantSelect(){
//        return pernamantSelect;
//    }
    
    public CButton(int x,int y,int xs,int ys,ImageIcon[] ic,ImageIcon dis,boolean border){
        icons=ic;
        disabledIcon=dis;
        
        this.setIcon(ic[0]);
        super.setBounds(x,y,xs,ys);
        this.addMouseListener(this);
        
        setContentAreaFilled(true);
        
        if(!border)
            setBorder(BorderFactory.createEmptyBorder());
    }
    
    public CButton(int x,int y,int xs,int ys,ImageIcon[] ic,boolean border){
        icons=ic;
        
        this.setIcon(ic[0]);
        super.setBounds(x,y,xs,ys);
        this.addMouseListener(this);
        
        setContentAreaFilled(true);
        
        if(!border)
            setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public void mouseClicked(MouseEvent e) {clicked();}

    @Override
    public void mousePressed(MouseEvent e) {pressed();}

    @Override
    public void mouseReleased(MouseEvent e) {
        super.setIcon(icons[0]);
        released();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!disabled&&!pernamantSelect){
            super.setIcon(icons[1]);
        }
        selected=true;
        entered();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!disabled&&!pernamantSelect){
            super.setIcon(icons[0]);
        }
        selected=false;
        exited();
    }
    
    public void clicked(){}
    public void pressed(){}
    public void released(){}
    public void entered(){}
    public void exited(){}
    
    
}
