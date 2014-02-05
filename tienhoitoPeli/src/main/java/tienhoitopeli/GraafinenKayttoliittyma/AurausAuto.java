/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.GraafinenKayttoliittyma;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author virvemaa
 */
public class AurausAuto {

    private Ruutu ruutu;
    


    public AurausAuto(int rivi, int sarake, int koko) {

        this.ruutu = new Ruutu(sarake, rivi, koko, Color.ORANGE);
    }

    public void Piirra(Graphics graphics) {
        this.ruutu.Piirra(graphics);
    }

    public void Siirry(int xSiirtyma, int ySiirtyma) {
        this.ruutu.Siirry(xSiirtyma, ySiirtyma);
    }
    public void MuutaPaikkaa(int xPaikka, int yPaikka){
        this.ruutu.MuutaPaikkaa(xPaikka, yPaikka);
    }
    public int getYKoko(){
        return this.ruutu.getKoko();
    }
}
