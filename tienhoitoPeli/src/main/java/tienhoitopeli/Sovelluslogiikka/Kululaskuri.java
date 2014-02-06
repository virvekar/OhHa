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
    public void LisaaKolariKulut(int kolareidenMaara) {
        if (kolareidenMaara > 0) {
            this.kulujenMaara += kolareidenMaara * 1000;
        }
    }

    public int getKulujenMaara() {
        return this.kulujenMaara;
    }
}
