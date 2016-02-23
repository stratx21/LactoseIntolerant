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
    
    
    public static boolean soundEffectsOn=true,musicOn=true;
    
    public static boolean[] completedMissions=new boolean[16];
    
    /**
     * prices for each upgrade; each set includes the prices for the upgrades
     * menu for each type of car, an empty space between each set for type
     * of car
     */
    public static final double[][][] prices=new double[][][]{
        {//sudan
        {10000,15000,30000,    0,    0,    0},
        {15000,22500,20000,    0,    0,    0},
        {    0,    0,    0,    0,    0,    0}
        },
        
        {//getaway van
        {20000,25000,40000,55000,    0,    0},
        {25000,32500,30000,    0,    0,    0},
        {    0,    0,40000,    0,    0,    0}
        },
        
        {//race car
        {60000,40000,50000,69000,85000,    0},
        {    0,45000,35000,50000,45000,    0},
        {    0,55000,45000,    0,55000,    0}
        },
        
        {//armored van
        {65000,90000,100000,115000,120000,200000},
        {85000,110000,65000,80000, 95000,    0},
        {100000,   0,80000, 92000, 110000,   0}
        },
        
        {//tank
        {75000, 85000, 195000,220000,310000,500000},
        {85000, 100000,125000,175000,250000,350000},
        {100000,120000,150000,200000,275000,450000}
        },
    };
    
    public final static double[] teamPrices=new double[]{
        0.0,0.0,0.0,0.0,0.0,0.0
    };
    
    /**
     * contains prices of the 5 different types of cars; the first, the sudan, is zero
     * since the user already owns this car at the beginning of the game; This
     * is used more as a reference than as a Profile variable since it holds 
     * variables that are not influenced by input save data. 
     */
    public final static double[] carPrices=new double[]{
        0,100000,250000,600000,1000000
    };
    
    /**
     * a boolean for every type of car, this is variable to the actual Profile
     * of the user, telling if they have purchased the car yet.
     */
    public static boolean[] boughtCars=new boolean[]{
        true,false,false,false,false
    };
    
    /**
     * based on the resolution of the user's screen, this is how much space 
     * should be added from x 0 so that the game is in the center of the screen
     */
    public static int xStart=0;
    
    //file used to input the data from a save location
    public static File inputSaveFile=null;
    
    //money the user has; the value here, if not influenced by an import of data
    //by save file, is the default amount of money the user will start with in
    //a new game. 
    public static double money=30000.00;
    
    /**
     * save current progress in a save file, using the JFileChooser to let the
     * user identify a name and where to save it.
     */
    public static void save(){
        JFileChooser sv=new JFileChooser();
        if(sv.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
        try{
            try (FileWriter save = new FileWriter(sv.getSelectedFile()+".txt")) {
                System.out.println(sv.getSelectedFile());//returns file name and the directory location
                //save.write......
                
                save.write(money+":");
                for(int k=0;k<5;k++)
                    for(int i=0;i<3;i++){
                        for(int j=0;j<6;j++){
                            save.write(upgrades[k][i][j]+":");
                        }
                    }
                
                for(int i=0;i<5;i++)
                    save.write(boughtCars[i]+":");
                
                for(int i=0;i<completedMissions.length;i++)
                    save.write(completedMissions[i]+":");
            } //returns file name and the directory location
            //save.write......
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
    }
    
    /**
     * 
     * opens the save file specified by the user in the JFileChooser, and
     * imports the data from it to give the user their progress back. 
     * 
     * @throws Exception Exception thrown if the file is not found for the 
     * Scanner "in" uses to import the data from the save file specified by
     * the user through the JFileChooser
     */
    public static void open() throws Exception{
        JFileChooser fc=new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        if (fc.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
                inputSaveFile=fc.getSelectedFile();
            }
        if(inputSaveFile!=null){
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
            for(int i=0;i<5;i++){
                boughtCars[i]=Boolean.parseBoolean(in[c]);
                c++;
            }

            for(int i=0;i<completedMissions.length;i++){
                completedMissions[i]=Boolean.parseBoolean(in[c]);
                c++;
            }
        }
    }
    
}
