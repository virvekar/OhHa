/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

import java.util.HashMap;

/**
 *Olio, joka laskee syntyneiden kulujen maaran
 * @author Virve Karsisto
 */
public class Kululaskuri {

    private int kulujenMaara;

    public Kululaskuri() {
        this.kulujenMaara = 0;
    }

    /**
     * Laskee bensakulut ja tallentaa ne
     * @param ajetutRuudut ajettujen ruutujen maara
     */
    public void LisaaBensakulu(int ajetutRuudut) {
        if (ajetutRuudut > 0) {
            this.kulujenMaara += ajetutRuudut * 5;
        }

    }
/**
 * Laskee kolarikulut ja tallentaa ne
 * @param kolareidenMaara niiden ruutujen maara joissa on lunta eli joissa synttyy kolareita
 */
    public void LisaaKolariKulutTonniPerKolari(int kolareidenMaara) {
        if (kolareidenMaara > 0) {
            this.kulujenMaara += kolareidenMaara * 1000;
        }
    }
/**
 * Laskee kolarikulut perustuen lumen maaraa ja tallentaa kulut
 * @param lumikerrosKoordinaateissa HashMap jossa avaimena koordinaattipiste ja arvona lumen maara
 */    
    public void LisaaKolariKulutLumenMaaranMukaan(HashMap lumikerrosKoordinaateissa){
        for (Object lumi:lumikerrosKoordinaateissa.values()){
            double lumimaara= (double) lumi;
            if(lumimaara>0.001){
                if(lumimaara<20.001){
                    this.kulujenMaara+=((int)lumimaara)*50;
                }else{
                    this.kulujenMaara+=1500;
                }
            }
        }
    }
    
    /**
     * Laskee kulut jotka ovat seurausta siita etta yhdessa ruudussa on ollut
     * liikaa lunta liian kauan
     * @param ruutujenMaara monessako ruudussa on ollut lunta liian kauan
     */
    public void LisaaKolariKulutLumestaJokaOnMaassaLiianKauan(int ruutujenMaara){
        if(ruutujenMaara>0){
            this.kulujenMaara+=200*ruutujenMaara;
        }
    }

    public int getKulujenMaara() {
        return this.kulujenMaara;
    }
}
