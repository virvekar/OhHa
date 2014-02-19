/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;

/**
 *Olio, jonka tehtavana on sailyttaa saaennustetietoa ja jakaa sita eteenpain.
 * 
 * @author virvemaa
 */
public class Saaennuste {

    /**
     * int ennusteenPituus kertoo kuinka monen sekunnin ajalle ennuste on
     * int lumisateenMaara kertoo paljonko sataa yhteensa lunta
     * String ennusteTiedosto sisaltaa tiedostopolun, josta ennuste loytyy
     * int pituudenMuutoksenKeskihajonta kertoo ennusteen pituuden virheen keskihajonnan
     * int maaranMuutoksenKeskihajonta kertoo ennusteen maaran virheen keskihajonnan
     */
    private int ennusteenPituus;
    private String ennusteTiedosto;
    private int lumisateenMaara;
    private int pituudenMuutoksenKeskihajonta;
    private int maaranMuutoksenKeskihajonta;

    /**
     * 
     * @param tiedostonNimi polku, josta ennustetiedosto loytyy
     */
    public Saaennuste(String tiedostonNimi) {
        this.ennusteTiedosto = tiedostonNimi;
    }

    /**
     * Luo tiedostonLukija olion, jolle se delegoi tiedoston lukemisen
     * Jos tiedostoa ei loydy tai ennuste on virheellinen, ohjelma lopetetaan.
     */
    public void LueSaaennuste() {
        TiedostonLukija saaennusteenLukija = new TiedostonLukija(this.ennusteTiedosto);
        ArrayList<String> ennuste = saaennusteenLukija.LueTiedosto();
        if (ennuste.isEmpty()) {
            System.out.println("Ohjelmaa suoritettaessa tapahtui virhe. Ennustetta ei loytynyt.");
            System.exit(0);
        }
        if(!KirjaaEnnuste(ennuste.get(0),ennuste.get(1),ennuste.get(2),ennuste.get(3))){
            System.out.println("Ennustetiedosto on virheellinen");
            System.exit(0);
        }

    }

    /**
     * 
     * Tallentaa ennusteen, mikali syoteen molemmat arvot ovat positiivisia 
     * lukuja.
     * 
     * @param pituus tiedostosta luettu String, joka kertoo ennusteen pituuden
     * @param maara tiedostosta luettu String, joka kertoo sateen maaran
     * @param pituudenHajonta tiedostosta luettu String, joka kertoo ennusteen pituuden virheen keskihajonnan
     * @param maaranHajonta tiedostosta luettu String, joka kertoo ennusteen maaran virheen keskihajonnan
     * @return boolean, joka on true, mikali ennusteen kirjaaminen onnistui
     */
    public boolean KirjaaEnnuste(String pituus, String maara,String pituudenHajonta, String maaranHajonta) {
        if (OnkoInteger(pituus) && OnkoInteger(maara) && OnkoInteger(pituudenHajonta) && OnkoInteger(maaranHajonta)) {
            int pituusLuku = Integer.parseInt(pituus);
            int maaraLuku = Integer.parseInt(maara);
            int pituudenHajontaLuku=Integer.parseInt(pituudenHajonta);
            int maaranHajontaLuku=Integer.parseInt(maaranHajonta);
            if (OnkoPositiivinen(pituusLuku) && OnkoPositiivinen(maaraLuku) && 
                    OnkoNollaTaiIsompi(pituudenHajontaLuku) &&
                    OnkoNollaTaiIsompi(maaranHajontaLuku)) {
                this.ennusteenPituus = pituusLuku;
                 this.lumisateenMaara = maaraLuku;
                 this.pituudenMuutoksenKeskihajonta=pituudenHajontaLuku;
                 this.maaranMuutoksenKeskihajonta=maaranHajontaLuku;
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
    public int GetPituudenMuutoksenKeskihajonta(){
        return this.pituudenMuutoksenKeskihajonta;
    }
    public int GetMaaranMuutoksenKeskihajonta(){
        return this.maaranMuutoksenKeskihajonta;
    }

    /**
     * Antaa saaennusteen tekstimuodossa.
     * @return String, jossa ennuste on tekstimuodossa.
     */
    public String AnnaSaaennuste() {

        return "Seuraavan " + this.ennusteenPituus + " sekunnin aikana tiedossa " + this.lumisateenMaara + " cm lunta.\n" +
                "Sateen keston epavarmuus: "+ this.pituudenMuutoksenKeskihajonta+ "\n"+
                "Sateen maaran epavarmuus: "+this.maaranMuutoksenKeskihajonta;
    }

    /**
     * Kertoo onko luku positiivinen
     * @param luku luku joka halutaan tarkistaa
     * @return boolean, joka on true jos luku on positiivinen.
     */
    public boolean OnkoPositiivinen(int luku) {
        if (luku <= 0) {
            return false;
        }
        return true;
    }
    
    /**
     * Kertoo onko luku nolla tai isompi
     * @param luku
     * @return true jos luku on nolla tai isompi
     */
    public boolean OnkoNollaTaiIsompi(int luku) {
        if (luku < 0) {
            return false;
        }
        return true;
    }

    /**
     * Kertoo onko annettu String integer.
     * @param teksti String josta halutaan tarkistaa, onko se luku
     * @return boolean ,joka on true jos annettu String on luku
     */
    public static boolean OnkoInteger(String teksti) {
        try {
            Integer.parseInt(teksti);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
