/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author virvemaa
 */
public class Ruudukko {
    private HashMap lumikerrosKoordinaateissa;
    private int rivit;
    private int sarakkeet;
    
    public Ruudukko(HashMap lumikerros, int annetutRivit, int annetutSarakkeet){
        this.lumikerrosKoordinaateissa=lumikerros;
        this.rivit=annetutRivit;
        this.sarakkeet=annetutSarakkeet;
        
    }
    
    public void piirra(Graphics graphics){
        for(Object objektiKoordinaatit: this.lumikerrosKoordinaateissa.keySet()){
            ArrayList<Integer> koordinaatit=(ArrayList<Integer>) objektiKoordinaatit;
            
        }
    }
}
