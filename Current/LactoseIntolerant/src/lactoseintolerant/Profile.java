/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author 0001058857
 */
public class Profile {
    
    //speed, health, boost, machine gun, mines, missiles
    public static boolean[][] upgrades=new boolean[3][6]; //make a 3d array for each car type added in
    
    public static final double[][] prices=new double[][]{
        {10000,15000,30000,45000,65000,85000},
        {15000,22500,10000,17500,20000,22500},
        {22500,27500,15000,22500,25000,32500}
    };
    
    public static int equipped=0; 
    
    public static File inputSaveFile=null;
    
    public static double money=1000000.00;
    
    public static void save(){
        JFileChooser sv=new JFileChooser();
        if(sv.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
        try{
            FileWriter save=new FileWriter(sv.getSelectedFile()+".txt");
            System.out.println(sv.getSelectedFile());//returns file name and the directory location
            //save.write......
            
            save.write(money+":");
            
            for(int i=0;i<3;i++){
                for(int j=0;j<6;j++){
                    save.write(upgrades[i][j]+":");
                }
            }
            
            save.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    }
    
    public static void open() throws Exception{
        JFileChooser fc=new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        if (fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
                inputSaveFile=fc.getSelectedFile();
            }
            
        String[] in=new Scanner(inputSaveFile).nextLine().split(":");
        
        for(int i=0;i<in.length;i++)
            System.out.println(in[i]);
        
        money=Double.parseDouble(in[0]);
        
        int c=1;
        for(int i=0;i<3;i++)
            for(int j=0;j<6;j++){
                upgrades[i][j]=Boolean.parseBoolean(in[c]);
                c++;
            }
    }
    
}
