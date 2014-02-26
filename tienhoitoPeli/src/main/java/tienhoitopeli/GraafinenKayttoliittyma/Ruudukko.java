/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *Piirtaa monta ruutu oliota
 *Kay lapi sijainnit, jotka on maaratty
 *avaimina lumikerrosKoordinaateissa HashMapissa
 *Vari riippuu lumen maarasta ruudussa
 * @author virvemaa
 */
public class Ruudukko {
    private HashMap lumikerrosKoordinaateissa;
    private HashMap merkittavastiLunta;
    private int rivit;
    private int sarakkeet;
    
    public Ruudukko(HashMap lumikerros, HashMap merkittavastiLunta,int annetutRivit, int annetutSarakkeet){
        this.lumikerrosKoordinaateissa=lumikerros;
        this.merkittavastiLunta=merkittavastiLunta;
        this.rivit=annetutRivit;
        this.sarakkeet=annetutSarakkeet;
        
    }
    
    public void Piirra(Graphics graphics){
        int ruudunKoko=400/this.rivit;
        for(Object objektiKoordinaatit: this.lumikerrosKoordinaateissa.keySet()){
            ArrayList<Integer> koordinaatit=(ArrayList<Integer>) objektiKoordinaatit;
            int y=koordinaatit.get(0)*ruudunKoko;
            int x=koordinaatit.get(1)*ruudunKoko;
            if((double)this.lumikerrosKoordinaateissa.get(koordinaatit)<-0.01){
                Ruutu ruutu=new Ruutu(x,y,ruudunKoko,Color.BLACK);
                ruutu.Piirra(graphics);
            }
            else if((double)this.lumikerrosKoordinaateissa.get(koordinaatit)<0.1){
                Ruutu ruutu=new Ruutu(x,y,ruudunKoko,Color.LIGHT_GRAY); 
                ruutu.Piirra(graphics);
            }else{
                Ruutu ruutu=new Ruutu(x,y,ruudunKoko,Color.WHITE); 
                ruutu.Piirra(graphics);
            }
            
            if((boolean)this.merkittavastiLunta.get(koordinaatit)){
                Ruutu ruutu=new Ruutu(x,y,ruudunKoko,Color.RED); 
                ruutu.Piirra(graphics);
            }
        }
    }

}
