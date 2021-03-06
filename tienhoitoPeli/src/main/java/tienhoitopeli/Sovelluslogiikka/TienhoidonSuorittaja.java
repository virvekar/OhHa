/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tienhoitopeli.Sovelluslogiikka;

/**
 * <p>
 *Olio, joka suorittaa aurauksen ja lumen lisaamisen delegoimalla ne
 * Auraaja oliolle ja Lumikerros oliolle ja laskee myos tienhoidosta 
 * syntyneet kulut kayttamalla Kululaskuri oliota.
 * <p>
 * @author Virve Karsisto
 */
public class TienhoidonSuorittaja {
    private Auraaja auraaja;
    private Saa saa;
    private Lumikerros lumikerros;
    private Kululaskuri kululaskuri;
    private ReitinLukija reitinlukija;

    /**
     * 
     * @param annettuAuraaja Auraaja olio, joka suorittaa lumen poiston
     * @param annettuSaa Saa olio, joka kertoo tulevan saan
     * @param annettuLumikerros Lumikerros olio, joka tietaa lumikerroksen eri koordinaateissa
     * @param reitinlukija reitinlukija olio, joka tietaa milloin auraus aloitetaan
     */
    public TienhoidonSuorittaja(Auraaja annettuAuraaja, Saa annettuSaa, Lumikerros annettuLumikerros,ReitinLukija reitinlukija){
        this.auraaja=annettuAuraaja;
        this.lumikerros=annettuLumikerros;
        this.saa=annettuSaa;
        this.kululaskuri=new Kululaskuri();
        this.reitinlukija=reitinlukija;

    }
    /**
     * <p>
     * Kutsuu vuorotellen auraajaa poistamaan lunta ja lumikerrosta lisaamaan lunta.
     * Auraus aloitetaan aloitusajan kertomana ajankohtana.
     * Auraaja auraa yhden reittipisteen yhdessa sekunnissa ja aina kun yksi
     * piste on aurattu lisataan lunta yhden sekunnin sateen verran.
     * <p>
     */
    public void SuoritaTienhoito(){

        int aurausAlkaa=this.reitinlukija.getAloitusAika();
        int aurauksenKesto=this.KerroAurauksenKesto();      
        int sateenKesto=this.saa.getSateenPituus();          
        int OperaationKesto=this.PalautaSuurempi(sateenKesto, aurauksenKesto);
        
        for (int aika=1; aika<=OperaationKesto; aika++){
            if(aika<=sateenKesto){
                lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
            }
            if(aika>=aurausAlkaa && aika<=aurauksenKesto){
                auraaja.AuraaSeuraavaPiste();             
            }
            lumikerros.PaivitaMerkittavanLumenKesto();
            this.laskeKulutLumestaJokaOnMaassaLiianKauan();
        }    
    }
    
    /**
     * Pyytaa kululaskuria laskemaan syntyneet kulut siten, etta lumisesta ruudusta
     * tulee aina 1000 e kuluja
     * @return kulujen maara
     */
    public int laskeKulutTonniPerKolari(){
        kululaskuri.LisaaKolariKulutTonniPerKolari(lumikerros.laskeRuudutJoissaOnLunta());
        kululaskuri.LisaaBensakulu(this.auraaja.AnnaAuraajanReitti().size());
        return kululaskuri.getKulujenMaara();
    }
    
    /**
     * Pyytaa kululaskuria laskemaan syntyneet kulut siten,etta kulujen maara
     * lumisesta ruudusta riippuu lumen maarasta
     * @return kulujen maara
     */
    public int laskeKulutLumenMaaranMukaan(){
        kululaskuri.LisaaKolariKulutLumenMaaranMukaan(lumikerros.GetLumikerrosKoordinaateissa());
        kululaskuri.LisaaBensakulu(this.auraaja.AnnaAuraajanReitti().size());
        return kululaskuri.getKulujenMaara();
    }
    
    /**
     * Laskee kulut jotka syntyvat siita etta ruudussa on lunta liian kauan
     * 
     */
    public void laskeKulutLumestaJokaOnMaassaLiianKauan(){
        kululaskuri.LisaaKolariKulutLumestaJokaOnMaassaLiianKauan(lumikerros.MonessakoRuudussaOnOllutLuntaLiianKauanEikaOleImoitettu());
        
    }
    
    
    /**
     * Palauttaa luvuista suuremman
     * @param luku1 verrattava luku1
     * @param luku2 verrattava luku2
     * @return suurempi luku
     */
    public int PalautaSuurempi(int luku1, int luku2){
        if(luku1<luku2){
            return luku2;
        }else{
            return luku1;
        }
    }
    public int getKulut(){
        return this.kululaskuri.getKulujenMaara();
    }
    
    /**
     * Kertoo aurauksen lopetushetken
     * @return aurauken lopetushetki
     */
    public int KerroAurauksenKesto(){
        int aurausAlkaa=this.reitinlukija.getAloitusAika();
        int aurauksenKesto=0;
        if(aurausAlkaa==1){
            aurauksenKesto=this.auraaja.AnnaAuraajanReitti().size();
        }else{
            aurauksenKesto=this.auraaja.AnnaAuraajanReitti().size()+aurausAlkaa-1;
        }
        return aurauksenKesto;
    }
}
