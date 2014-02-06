/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *Olio, joka sailyttaa tietoa lumen maarasta kussakin pisteessa ja osaa
 * alustaa itsensa ja lisata ja poistaa lunta.
 * @author virvemaa
 */
public class Lumikerros {

    private Kartta kartta;
    private int sekunti;
    private HashMap lumikerrosKoordinaateissa;

    /**
     * 
     * @param annettuKartta Kartta olio joka tietaa kartan ArrayListin<String> oliona
     */
    public Lumikerros(Kartta annettuKartta) {
        this.kartta = annettuKartta;
        this.sekunti = 0;
        this.lumikerrosKoordinaateissa = new HashMap();
    }

    /**
     * Alustaa lumikerroksen
     * Lisaa HashMapiin jokaisen kartan pisteen avaimeksi siten, etta piste on kuvattu
     * ArrayListina jossa on kaksi lukua 
     * Ensimmainen luku kertoo rivin ylhaalta laskettuna ja toinen sarakkeet
     * vasemmalta laskettuna
     * Avaimet viittaavat double tyyppisiin lukuihin, jotka kertovat lumen maaran
     * Pisteissa joissa on rakennus lumen maara asetetaan -1,0:aan ja tiepisteissa
     * lumen maaraksi asetetaan 0,0.
     */
    public void AlustaLumikerros() {
        ArrayList<Integer> kartanKoko = this.kartta.KartanKoko();
        ArrayList<String> karttaPohja = this.kartta.GetKarttaPohja();
        int riviPiste;
        int riviNumero = 0;

        //Kay lapi kaikki rivit
        for (String rivi : karttaPohja) {

            //Kay lapi rivin jokaisen pisteen
            for (riviPiste = 0; riviPiste < kartanKoko.get(0); riviPiste++) {
                ArrayList<Integer> avainkoordinaatit = new ArrayList<Integer>();
                avainkoordinaatit.add(riviNumero);
                avainkoordinaatit.add(riviPiste);

                if (rivi.charAt(riviPiste) == 'x') {
                    this.lumikerrosKoordinaateissa.put(avainkoordinaatit, (double) -1.0);
                } else {
                    this.lumikerrosKoordinaateissa.put(avainkoordinaatit, (double) 0.0);
                }
            }
            riviNumero++;
        }
    }

    /**
     * Tulostaa lumikerroksen ruudulle.
     */
    public void TulostaLumikerros() {
        ArrayList<Integer> kartanKoko = this.kartta.KartanKoko();
        String tulosteRivi = "";
        for (int i = 0; i < kartanKoko.get(1); i++) {

            for (int j = 0; j < kartanKoko.get(0); j++) {
                ArrayList<Integer> koordinaatit = new ArrayList<Integer>();
                koordinaatit.add(i);
                koordinaatit.add(j);
                tulosteRivi = tulosteRivi.concat(this.lumikerrosKoordinaateissa.get(koordinaatit) + " ");
            }
            System.out.println(tulosteRivi);
            tulosteRivi = "";
        }

    }
/**
 * Laskee paljonko lunta sataa yhdessa sekunnissa ja lisaa sen veran
 * lunta kaikkiin tiepisteisiin.
 * @param sateenMaara satavan lumen kokonaismaara
 * @param sateenPituus kertoo kauanko sade kestaa
 */
    public void LisaaLuntaYhdenSekunninSateenVerran(int sateenMaara, int sateenPituus) {
        double sekunnissaSataa = ((double) sateenMaara) / ((double) sateenPituus);
        ArrayList<Integer> kartanKoko = this.kartta.KartanKoko();
        int rivi = 0;
        for (int i = 0; i < kartanKoko.get(1); i++) {

            for (int j = 0; j < kartanKoko.get(0); j++) {
                ArrayList<Integer> koordinaatit = new ArrayList<Integer>();
                koordinaatit.add(i);
                koordinaatit.add(j);
                double vanhaLumiMaara = (double) this.lumikerrosKoordinaateissa.get(koordinaatit);
                if (vanhaLumiMaara >= 0) {
                    double uusiLumiMaara = vanhaLumiMaara + sekunnissaSataa;
                    this.lumikerrosKoordinaateissa.put(koordinaatit, uusiLumiMaara);
                }
            }
        }

    }
    /**
     * Laskee monessako ruudussa on lunta.
     * @return lunta sisaltavien ruutujen maara
     */
    public int laskeRuudutJoissaOnLunta(){
        
        int ruutujenMaara=0;
        for (Object arvo:this.lumikerrosKoordinaateissa.values()){
            double lumenMaara=( double) arvo;
            if(lumenMaara>0.0001){
                ruutujenMaara++;
            }
        }
        return ruutujenMaara;
    }

    public HashMap GetLumikerrosKoordinaateissa() {
        return this.lumikerrosKoordinaateissa;
    }
    
    /**
     * Muuttaa lumen maaraksi 0 halutuissa koordinaateissa.
     * @param koordinaatit koorinaatit, joista lumi halutaan poistaa
     */
    public void PoistaLumikerros(ArrayList<Integer> koordinaatit){
        this.lumikerrosKoordinaateissa.put(koordinaatit, 0.0);
    }
}
