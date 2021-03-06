/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Sailyttaa tietoa nykyisesta tasosta ja pelaajasta
 *
 * @author virvemaa
 */
public class PeliKerranOhjaaja {

    private String pelaaja;
    private int kentanNumero;
    private String tiedostoPolku;
    private HashMap lumikerrosKoordinaateissa;
    private int rivit;
    private int sarakkeet;
    private String ennuste;
    private ReitinLukija reitinLukija;
    private Lumikerros lumikerros;
    private Saa saa;
    private EnnatysListanPitaja ennatysListanPitaja;
    private int kenttienMaara;

    public PeliKerranOhjaaja() {
        this.kentanNumero = 1;
        this.pelaaja = "";
        this.tiedostoPolku = "src/main/java/tienhoitopeli/";
        this.kenttienMaara = 6;
    }

    /**
     *
     * @param nimi pelaajan nimi
     * @return true, jos nimen asettaminen onnistui
     */
    public boolean setPelaajanNimi(String nimi) {
        if (nimi.isEmpty()) {
            return false;
        } else {
            this.pelaaja = nimi;
            return true;
        }

    }

    public String getPelaajanNimi() {
        return this.pelaaja;
    }

    /**
     *
     * @return Kyseisen tason kartan tiedostopolku
     */
    public String getKartanPolku() {
        String polku = this.tiedostoPolku + "kartat/kartta" + this.kentanNumero + ".txt";
        return polku;
    }

    /**
     *
     * @return Kyseisen tason saaennusteen tiedostopolku
     */
    public String getSaaennusteenPolku() {
        String polku = this.tiedostoPolku + "saaennusteet/saaennuste" + this.kentanNumero + ".txt";
        return polku;
    }

    /**
     *
     * @return Kyseisen tason ennatyslistan tiedostopolku
     */
    public String getEnnatysListanPolku() {
        String polku = this.tiedostoPolku + "ennatyslistat/ennatyslista" + this.kentanNumero + ".txt";
        return polku;
    }

    /**
     * Vaihtaa kentan numeron seuraavaan.
     */
    public void MeneSeuraavaanKenttaan() {
        if (this.kentanNumero < this.kenttienMaara) {
            this.kentanNumero++;
        }
    }

    /**
     * Luo uutta tasoa varten tarvittavat oliot ja tallentaa ne.
     */
    public void AlustaTaso() {
        if (this.kentanNumero <= this.kenttienMaara) {
            Kartta kartta = new Kartta(this.getKartanPolku());
            kartta.LueKartta();

            ArrayList<Integer> kartanKoko = kartta.KartanKoko();

            Saaennuste uusiEnnuste = new Saaennuste(this.getSaaennusteenPolku());
            uusiEnnuste.LueSaaennuste();
            Saa uusiSaa = new Saa(uusiEnnuste);

            if (uusiEnnuste.GetPituudenMuutoksenKeskihajonta() > 0) {
                uusiSaa.ArvoSateenPituus(uusiEnnuste.GetPituudenMuutoksenKeskihajonta());
            }
            if (uusiEnnuste.GetMaaranMuutoksenKeskihajonta() > 0) {
                uusiSaa.ArvoSateenMaara(uusiEnnuste.GetMaaranMuutoksenKeskihajonta());
            }

            Lumikerros uusiLumikerros = new Lumikerros(kartta);
            uusiLumikerros.AlustaLumikerros();

            ReitinLukija uusiReitinLukija = new ReitinLukija(kartta, uusiLumikerros);

            EnnatysListanPitaja pitaja = new EnnatysListanPitaja(this.getEnnatysListanPolku());

            this.lumikerrosKoordinaateissa = uusiLumikerros.GetLumikerrosKoordinaateissa();
            this.rivit = kartanKoko.get(1);
            this.sarakkeet = kartanKoko.get(0);
            this.ennuste = uusiEnnuste.AnnaSaaennuste();
            this.reitinLukija = uusiReitinLukija;
            this.saa = uusiSaa;
            this.lumikerros = uusiLumikerros;
            this.ennatysListanPitaja = pitaja;
        }
    }

    public HashMap getLumikerrosKoordinaateissa() {
        return this.lumikerrosKoordinaateissa;
    }

    public int getRivit() {
        return this.rivit;
    }

    public int getSarakkeet() {
        return this.sarakkeet;
    }

    public String getEnnuste() {
        return this.ennuste;
    }

    public ReitinLukija getReitinLukija() {
        return this.reitinLukija;
    }

    public Saa getSaa() {
        return this.saa;
    }

    public Lumikerros getLumikerros() {
        return this.lumikerros;
    }

    public EnnatysListanPitaja getEnnatysListanPitaja() {
        return this.ennatysListanPitaja;
    }

    public int getKenttienMaara() {
        return this.kenttienMaara;
    }

    public int getKentanNumero() {
        return this.kentanNumero;
    }
}
