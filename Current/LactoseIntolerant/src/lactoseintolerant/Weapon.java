/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lactoseintolerant;

import java.awt.Graphics;

/**
 *
 * @author 0001058857
 */
public class Weapon {
    //types:: 0 - boost, 1 - auto gun, 2 - mines, 3 - missiles
    public int TYPE,
            LEVEL;
    
    public boolean canAttack=true,isInAttack=false;
    public int canAttackPing=0,attackReachPing=50;
    
    private int fireSequence=0,totalFireSequence;
    
    public void draw(Graphics p){
        
    }
    
    public Weapon(int t,int lv){ 
        TYPE=t;
        LEVEL=lv;
    }
    
    
}
