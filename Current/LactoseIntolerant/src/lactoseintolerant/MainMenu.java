/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author 0001058857
 */
public class MainMenu extends Menu{
    
    public MainMenu(JFrame f){
        
        
        this.setLayout(null);
        
        this.add(new CButton(120,120,100,100,"start"){
            @Override
            public void clicked(){}
            @Override
            public void pressed(){}
            @Override
            public void released(){
                GameFlow g=new GameFlow(f); //initialize the main game flow
            }
            @Override
            public void entered(){}
            @Override
            public void exited(){}
        });
        
    }
    
}