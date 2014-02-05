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
public class Saaennuste {

    private int ennusteenPituus;
    private String ennusteTiedosto;
    private int lumisateenMaara;

    public Saaennuste(String tiedostonNimi) {
        this.ennusteTiedosto = tiedostonNimi;
    }

    public void LueSaaennuste() {
        TiedostonLukija saaennusteenLukija = new TiedostonLukija(this.ennusteTiedosto);
        ArrayList<String> ennuste = saaennusteenLukija.LueTiedosto();
        if (ennuste.isEmpty()) {
            System.out.println("Ohjelmaa suoritettaessa tapahtui virhe. Ennustetta ei loytynyt.");
            System.exit(0);
        }
        if(!KirjaaEnnuste(ennuste.get(0),ennuste.get(1))){
            System.out.println("Ennustetiedosto on virheellinen");
            System.exit(0);
        }

    }

    public boolean KirjaaEnnuste(String pituus, String maara) {
        if (OnkoInteger(pituus) && OnkoInteger(maara)) {
            int pituusLuku = Integer.parseInt(pituus);
            int maaraLuku = Integer.parseInt(maara);
            if (OnkoSopivaLuku(pituusLuku) && OnkoSopivaLuku(maaraLuku) ) {
                this.ennusteenPituus = pituusLuku;
                 this.lumisateenMaara = maaraLuku;
                 return true;
            }
        }
        return false;
    }

    public int GetEnnusteenPituus() {
        return this.ennusteenPituus;
    }

    public int GetLumisateenMaara() {
        return this.lumisateenMaara;
    }

    public String AnnaSaaennuste() {

        return "Seuraavan " + this.ennusteenPituus + " sekunnin aikana tiedossa " + this.lumisateenMaara + " cm lunta.";
    }

    public boolean OnkoSopivaLuku(int luku) {
        if (luku <= 0) {
            return false;
        }
        return true;
    }

    public static boolean OnkoInteger(String teksti) {
        try {
            Integer.parseInt(teksti);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
