/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author 0001058857
 */
public class GameFrame extends JFrame{
    public final int X_SIZE=1000;
    public final int Y_SIZE=700;
    
    public GameFrame(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setSize(X_SIZE,Y_SIZE);
        this.setVisible(true);
    }
}
