/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.Sovelluslogiikka;

/**
 *
 * @author virvemaa
 */
public class Kululaskuri {
    private int kulujenMaara;
    
    public Kululaskuri(){
        this.kulujenMaara=0;
    }
    public void laskeBensakulu(int ajetutRuudut){
        this.kulujenMaara+=ajetutRuudut*5;
    }
    public void lisaaKolariKulut(int kolareidenMaara){
        this.kulujenMaara+=kolareidenMaara*1000;
    }
    public int getKulujenMaara(){
        return this.kulujenMaara;
    }
}
