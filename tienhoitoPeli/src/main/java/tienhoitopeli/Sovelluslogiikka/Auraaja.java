/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;

/**
 *
 * @author virvemaa
 */
public class Auraaja {

    private Lumikerros lumikerros;
    private ReitinLukija reitinLukija;
    private int reittiPisteNumero;

    public Auraaja(Lumikerros annettuLumikerros, ReitinLukija annettuReitinLukija) {
        this.lumikerros = annettuLumikerros;
        this.reitinLukija = annettuReitinLukija;
        this.reittiPisteNumero = 0;
    }

    public void AuraaSeuraavaPiste() {
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        if (reitti.size() >= reittiPisteNumero) {
            ArrayList<Integer> seuraavaPiste = reitti.get(this.reittiPisteNumero);
            this.lumikerros.PoistaLumikerros(seuraavaPiste);
            this.reittiPisteNumero++;
        }
    }

    public ArrayList<ArrayList<Integer>> AnnaAuraajanReitti() {
        return this.reitinLukija.GetReitti();
    }
    public int getReittiPisteNumero(){
        return this.reittiPisteNumero;
    }
}
