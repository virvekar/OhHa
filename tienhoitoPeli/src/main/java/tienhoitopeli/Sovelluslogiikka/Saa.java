/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

import java.util.Random;

/**
 * Olio, jonka tehtavana on luoda todellinen saa ennusteen perusteella
 *
 * @author Virve Karsisto
 *
 */
public class Saa {

    private Saaennuste ennuste;
    private int sateenPituus;
    private int sateenMaara;

    private Random saaArpoja;

    /**
     *
     * @param annettuEnnuste Saaennuste,joka annetaan konstruktorille
     */
    public Saa(Saaennuste annettuEnnuste) {
        this.ennuste = annettuEnnuste;
        this.sateenMaara = ennuste.GetLumisateenMaara();
        this.sateenPituus = ennuste.GetEnnusteenPituus();
        this.saaArpoja = new Random();
    }

    /**
     * Arpoo sateen pituuden kayttaen gaussista jakaumaa, jonka keskiarvo on
     * ennustettu pituus ja hajonta annetaan parametrina.
     *
     * @param keskihajonta muutoksen keskihajonta
     */
    public void ArvoSateenPituus(int keskihajonta) {
        if (keskihajonta > 0) {
            int ennustettuPituus = this.ennuste.GetEnnusteenPituus();
            int muutos = (int) Math.round(this.saaArpoja.nextGaussian() * keskihajonta);
            int uusiPituus = ennustettuPituus + muutos;
            if (uusiPituus < 1) {
                this.sateenPituus = 1;
            } else {
                this.sateenPituus = uusiPituus;
            }
        }
    }

    /**
     * Arpoo satavan lumen maaran kayttaen gaussista jakaumaa, jonka keskiarvo
     * on ennustettu maara ja hajonta annetaan parametrinia
     *
     * @param keskihajonta muutoksen keskihajonta
     */
    public void ArvoSateenMaara(int keskihajonta) {
        if (keskihajonta > 0) {
            int ennustettuMaara = this.ennuste.GetLumisateenMaara();
            int muutos = (int) Math.round(this.saaArpoja.nextGaussian() * keskihajonta);
            int uusiMaara = ennustettuMaara + muutos;
            if (uusiMaara < 0) {
                this.sateenMaara = 0;
            } else {
                this.sateenMaara = uusiMaara;
            }
        }
    }

    public int getSateenPituus() {
        return this.sateenPituus;
    }

    public int getSateenMaara() {
        return this.sateenMaara;
    }

}
