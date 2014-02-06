/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Olio, jonka tehtavana on sailyttaa tietoa ajoneuvon reitista ja lisata
 * reitille pisteita.
 *
 * @author virvemaa
 */
public class ReitinLukija {

    private ArrayList<ArrayList<Integer>> reitti;
    private Kartta kartta;
    private Lumikerros lumikerros;
    private int aloitusAika;

    /**
     *
     * @param annettuKartta Kartta olio joka tietaa kartan koon
     * @param annettuLumikerros Lumikerros olio josta selvitetaan tiepisteet
     */
    public ReitinLukija(Kartta annettuKartta, Lumikerros annettuLumikerros) {
        this.kartta = annettuKartta;
        this.lumikerros = annettuLumikerros;
        this.reitti = new ArrayList<ArrayList<Integer>>();
        this.aloitusAika=1;

    }

    /**
     * Tarkistaa ovatko annetut Stringit lukuja ja osuvatkone kartan tie
     * pisteisiin Lisaa ruudun reitille jos se kelpaa.
     *
     * @param aloitusRivi Rivi jolta auraus aloitetaan
     * @param aloitusSarake Sarake jolta auraus aloitetaan
     * @return boolean joka on true mikali pisteen kirjaaminen onnistui
     */
    public boolean KirjaaEnsimmainenPiste(String aloitusRivi, String aloitusSarake) {
        if (aloitusRivi != null && aloitusSarake != null && !aloitusRivi.isEmpty() && !aloitusSarake.isEmpty()) {
            if (OnkoInteger(aloitusRivi) && OnkoInteger(aloitusSarake)) {
                int aRivi = Integer.parseInt(aloitusRivi) - 1;
                int aSarake = Integer.parseInt(aloitusSarake) - 1;
                ArrayList<Integer> reittiPiste = this.LuoUusiKoordinaattiPiste(aRivi, aSarake);

                boolean onnistuiko = this.TarkistaPisteJaLisaaReitille(reittiPiste);

                return onnistuiko;
            }
        }
        System.out.println("Tuntematon komento");
        return false;
    }

    /**
     * Tekstikayttoliittymaa varten Tarkistaa loytyyko komento ja suorittaa sen
     *
     * @param komento kertoo mihin suuntaan tahdotaan liikkua
     * @return true, jos annetaan lopetuskomento
     */
    public boolean KirjaaReittiPisteJosKomentoKelpaa(String komento) {

        boolean lopetuskomentoAnnettu = false;

        if (komento != null && !komento.isEmpty()) {
            lopetuskomentoAnnettu = this.SuoritaKomentoJosSeLoytyy(komento);
            return lopetuskomentoAnnettu;
        } else {
            System.out.println("Tuntematon komento");
            return false;
        }
    }

    /**
     * Tarkistaa ettei piste mene kartan ulkopuolelle
     *
     * @param piste koordiaatit jotka halutaan tarkistaa
     * @return true, jos piste on rajojen sisalla
     */
    public boolean TarkistaOnkoRajojenSisalla(ArrayList<Integer> piste) {
        ArrayList<Integer> kartanKoko = this.kartta.KartanKoko();

        //meneeko pisteen rivi yli kartan rajan
        if (piste.get(0) > kartanKoko.get(1) - 1 || piste.get(0) < 0) {
            return false;
        }
        //meneeko pisteen sarake yli kartan rajan
        if (piste.get(1) > kartanKoko.get(0) - 1 || piste.get(1) < 0) {
            return false;
        }

        return true;
    }

    /**
     * Tarkistaa etta piste ei ole rakennuksen paalla.
     *
     * @param piste tarkistettava piste
     * @return true, jos piste on kadulla
     */
    public boolean TarkistaOnkoPisteKadulla(ArrayList<Integer> piste) {
        HashMap lumikerrosKoordinaateissa = this.lumikerros.GetLumikerrosKoordinaateissa();
        if ((double) lumikerrosKoordinaateissa.get(piste) < 0) {
            return false;
        }

        return true;
    }

    /**
     * Tarkistaa onko piste sopiva ja lisaa sen reitille jos se kelpaa.
     *
     * @param uusiPiste tarkistettava piste
     * @return palauttaa true, jos piste kelpaa ja se lisattiin reitille
     */
    public boolean TarkistaPisteJaLisaaReitille(ArrayList<Integer> uusiPiste) {

        if (this.TarkistaOnkoRajojenSisalla(uusiPiste)) {
            if (this.TarkistaOnkoPisteKadulla(uusiPiste)) {
                reitti.add(uusiPiste);
                return true;
            }
        }
        return false;
    }

    /**
     * Luo uuden ArrayListin, jossa on kaksi lukua, mika toimii
     * koordinaattipisteena
     *
     * @param rivi luotavan koordinaattipisteen y arvo
     * @param sarake luotavan koordinaattipisteen x arvo
     * @return ArrayList<Integer> jossa rivi ja sarake
     */
    public ArrayList<Integer> LuoUusiKoordinaattiPiste(int rivi, int sarake) {
        ArrayList<Integer> uusiPiste = new ArrayList<Integer>();
        uusiPiste.add(rivi);
        uusiPiste.add(sarake);
        return uusiPiste;
    }

    /**
     * Tekstikayttoliittymaa varten Suorittaa liikkumiskomennon jos se loytyy
     *
     * @param komento suoritettava komento
     * @return true, jos annetaan lopetuskomento
     */
    public boolean SuoritaKomentoJosSeLoytyy(String komento) {
        if (komento != null && !komento.isEmpty()) {

            char kirjain = komento.charAt(0);
            if (kirjain == 'p') {
                return true;
            } else {
                if (kirjain == 'i') {
                    this.LiikuYlos();

                } else if (kirjain == 'k') {
                    this.LiikuAlas();

                } else if (kirjain == 'j') {
                    this.LiikuVasemmalle();

                } else if (kirjain == 'l') {
                    this.LiikuOikealle();

                } else {
                    System.out.println("Tuntematon komento");

                }
                return false;
            }
        }
        System.out.println("Tuntematon komento");
        return false;
    }

    /**
     * Luo uuden koordinaatti pisteen jonka y arvo on yksi vahemman kuin
     * edellisen reittipisteen y-arvo ja lisaa sen reitille jos se kelpaa
     *
     * @return true,jos liikutus onnistui
     */
    public boolean LiikuYlos() {
        int rivi = reitti.get(reitti.size() - 1).get(0) - 1;
        int sarake = reitti.get(reitti.size() - 1).get(1);
        ArrayList<Integer> uusiPiste = this.LuoUusiKoordinaattiPiste(rivi, sarake);
        return this.TarkistaPisteJaLisaaReitille(uusiPiste);
    }

    /**
     * Luo uuden koordinaatti pisteen jonka y arvo on yksi enemman kuin
     * edellisen reittipisteen y-arvo ja lisaa sen reitille jos se kelpaa
     *
     * @return true,jos liikutus onnistui
     */
    public boolean LiikuAlas() {

        int rivi = reitti.get(reitti.size() - 1).get(0) + 1;
        int sarake = reitti.get(reitti.size() - 1).get(1);
        ArrayList<Integer> uusiPiste = this.LuoUusiKoordinaattiPiste(rivi, sarake);
        return this.TarkistaPisteJaLisaaReitille(uusiPiste);
    }

    /**
     * Luo uuden koordinaatti pisteen jonka x arvo on yksi vahemman kuin
     * edellisen reittipisteen x-arvo ja lisaa sen reitille jos se kelpaa
     *
     * @return true,jos liikutus onnistui
     */
    public boolean LiikuVasemmalle() {
        int rivi = reitti.get(reitti.size() - 1).get(0);
        int sarake = reitti.get(reitti.size() - 1).get(1) - 1;
        ArrayList<Integer> uusiPiste = this.LuoUusiKoordinaattiPiste(rivi, sarake);
        return this.TarkistaPisteJaLisaaReitille(uusiPiste);
    }

    /**
     * Luo uuden koordinaatti pisteen jonka x arvo on yksi enemman kuin
     * edellisen reittipisteen x-arvo ja lisaa sen reitille jos se kelpaa
     *
     * @return true,jos liikutus onnistui
     */
    public boolean LiikuOikealle() {
        int rivi = reitti.get(reitti.size() - 1).get(0);
        int sarake = reitti.get(reitti.size() - 1).get(1) + 1;
        ArrayList<Integer> uusiPiste = this.LuoUusiKoordinaattiPiste(rivi, sarake);
        return this.TarkistaPisteJaLisaaReitille(uusiPiste);
    }

    public ArrayList<ArrayList<Integer>> GetReitti() {
        return this.reitti;
    }
/**
 * Tarkistaa voiko annetun stringin muuttaa integeriksi
 * @param teksti String josta halutaan tarkistaa, onko se integer
 * @return true, jos teksti on integer
 */
    public static boolean OnkoInteger(String teksti) {
        try {
            Integer.parseInt(teksti);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public boolean LisaaAloitusAika(String aika){
        if(aika != null && OnkoInteger(aika)){
            this.aloitusAika=Integer.parseInt(aika);
            return true;
        }
        return false;
        
    }
    public int getAloitusAika(){
        return this.aloitusAika;
    }
}
