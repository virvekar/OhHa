/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;

/**
 *Olio, jonka tehtava on poistaa lumikerros seuraavasta reittipisteesta
 * @author Virve Karsisto
 */
public class Auraaja {

    private Lumikerros lumikerros;
    private ReitinLukija reitinLukija;
    private int reittiPisteNumero;

    /**
     * 
     * @param annettuLumikerros Lumikerros olio, joka tietaa lumen maaran eri koordinaateissa
     * @param annettuReitinLukija Reitinlukija olio, joka tietaa kuljettavan reitin
     */
    public Auraaja(Lumikerros annettuLumikerros, ReitinLukija annettuReitinLukija) {
        this.lumikerros = annettuLumikerros;
        this.reitinLukija = annettuReitinLukija;
        this.reittiPisteNumero = 0;
    }

    /**
     * Kysyy seuraavan reittipisteen reitinlukijalta ja pyytaa lumikerrosta
     * poistamaan siita lumen
     * Reittipistenumeron kertoo monennessako ruudussa mennaan.
     */
    public void AuraaSeuraavaPiste() {
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        if (reitti.size() > reittiPisteNumero) {
            ArrayList<Integer> seuraavaPiste = reitti.get(this.reittiPisteNumero);
            this.lumikerros.PoistaLumikerros(seuraavaPiste);
            this.reittiPisteNumero++;
        }
    }

    /**
     * Antaa auraajan reitin koordinaaatit
     * @return ArrayList, johon on tallennettu koordinaattipisteet reitilla
     */
    public ArrayList<ArrayList<Integer>> AnnaAuraajanReitti() {
        return this.reitinLukija.GetReitti();
    }
    public int getReittiPisteNumero(){
        return this.reittiPisteNumero;
    }
}
