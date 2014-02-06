/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

/**
 *Olio, joka laskee syntyneiden kulujen maaran
 * @author Virve Karsisto
 */
public class Kululaskuri {

    private int kulujenMaara;

    public Kululaskuri() {
        this.kulujenMaara = 0;
    }

    public void LisaaBensakulu(int ajetutRuudut) {
        if (ajetutRuudut > 0) {
            this.kulujenMaara += ajetutRuudut * 5;
        }

    }

    public void LisaaKolariKulut(int kolareidenMaara) {
        if (kolareidenMaara > 0) {
            this.kulujenMaara += kolareidenMaara * 1000;
        }
    }

    public int getKulujenMaara() {
        return this.kulujenMaara;
    }
}
