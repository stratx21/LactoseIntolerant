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
    
    /**
     * first is the car type, out of:: 
     * getaway van, race car, armored van, tank
     * 
     * second is the level of the upgrade of the third (1-3) - this is before 
     * the third only for the sake of using x-y easy access for the upgrades
     * menu in GarageMenu.
     * 
     * third is the type of upgrade for that car::
     * speed, health, boost, machine gun, mines, missiles
     * 
     * 
     */
    public static boolean[][][] upgrades=new boolean[5][3][6]; //make a 3d array for each car type added in
    
    public static final double[][][] prices=new double[][][]{
        {//sudan
        {10000,15000,30000,45000,65000,85000},
        {15000,22500,10000,17500,20000,22500},
        {22500,27500,15000,22500,25000,32500}
        },
        
        {//getaway van
        {10000,15000,30000,45000,65000,85000},
        {15000,22500,10000,17500,20000,22500},
        {22500,27500,15000,22500,25000,32500}
        },
        
        {//race car
        {10000,15000,30000,45000,65000,85000},
        {15000,22500,10000,17500,20000,22500},
        {22500,27500,15000,22500,25000,32500}
        },
        
        {//armored van
        {10000,15000,30000,45000,65000,85000},
        {15000,22500,10000,17500,20000,22500},
        {22500,27500,15000,22500,25000,32500}
        },
        
        {//tank
        {10000,15000,30000,45000,65000,85000},
        {15000,22500,10000,17500,20000,22500},
        {22500,27500,15000,22500,25000,32500}
        },
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
            for(int k=0;k<5;k++)
                for(int i=0;i<3;i++){
                    for(int j=0;j<6;j++){
                        save.write(upgrades[k][i][j]+":");
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
        for(int k=0;k<5;k++)
            for(int i=0;i<3;i++)
                for(int j=0;j<6;j++){
                    upgrades[k][i][j]=Boolean.parseBoolean(in[c]);
                    c++;
                }
    }
    
}
