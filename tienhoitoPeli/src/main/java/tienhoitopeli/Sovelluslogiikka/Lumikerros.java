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
/**
 * Kartta=Kartta olio joka tietaa kartan ArrayListin<String> oliona.
 * lumikerrosKoordinaateissa= HashMap jonka avaimena koordinaattipisteet ja arvoina
 * lumen maara kussakin pisteessa. 
 * merkittavanLumenKestoKoordinaateissa= HashMap  jonka avaimena koordinaattipisteet
 * ja arvoina tieto siita kauanko ruudussa on ollut merkittava maara lunta. 
 * merkittavastaLumestaIlmoitettu=HashMap jonka avaimena koordinaattipisteet ja 
 * arvoina true, jos ruudun liiasta lumesta on ilmoitettu ja false, jos ei ole imoitettu.
 * Kun lumi ruudusta aurataan, arvoksi muutetaan false.
 * merkittavastaLumestaIlmoitettuJoskus= sama kuin ylla, mutta auratessa arvoksi ei muuteta
 * false. Se siis kertoo missa ruuduissa on joskus ollut liikaa lunta pelin aikana.
 */
    private Kartta kartta;
    private HashMap lumikerrosKoordinaateissa;
    private HashMap merkittavanLumenKestoKoordinaateissa;
    private HashMap merkittavastaLumestaIlmoitettu;
    private HashMap merkittavastaLumestaIlmoitettuJoskus;
    

    /**
     * 
     * @param annettuKartta Kartta olio joka tietaa kartan ArrayListin<String> oliona
     */
    public Lumikerros(Kartta annettuKartta) {
        this.kartta = annettuKartta;
        this.lumikerrosKoordinaateissa = new HashMap();
        this.merkittavanLumenKestoKoordinaateissa=new HashMap();
        this.merkittavastaLumestaIlmoitettu=new HashMap();
        this.merkittavastaLumestaIlmoitettuJoskus=new HashMap();
    }

    /**
     * Alustaa lumikerroksen
     * Lisaa LumikerrosKoordinaateissa HashMapiin jokaisen kartan pisteen avaimeksi siten, etta piste on kuvattu
     * ArrayListina jossa on kaksi lukua. 
     * Ensimmainen luku kertoo rivin ylhaalta laskettuna ja toinen sarakkeet
     * vasemmalta laskettuna.
     * Avaimet viittaavat double tyyppisiin lukuihin, jotka kertovat lumen maaran.
     * Pisteissa joissa on rakennus lumen maara asetetaan -1,0:aan ja tiepisteissa
     * lumen maaraksi asetetaan 0,0.
     * Alustaa myos HashMapin merkittavanLumenKestoKoordinaateissa, jonka avaimet ovat
     * samat kuin edelleisessa mutta arvot ovat kaikki aluksi nolla.
     * Alustaa lisaksi HashMapin merkittavastaLumestaIlmoitettu, jonka avaimet ovat
     * samat kuin edellisessa mutta arvot aluksi false
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

                this.merkittavanLumenKestoKoordinaateissa.put(avainkoordinaatit, 0);
                this.merkittavastaLumestaIlmoitettu.put(avainkoordinaatit, false);
                this.merkittavastaLumestaIlmoitettuJoskus.put(avainkoordinaatit, false);
                
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
        for (Object lumenMaaraObjekti:this.lumikerrosKoordinaateissa.values()){
            double lumenMaara=(double) lumenMaaraObjekti;
            
            if(lumenMaara>0.0001){
                ruutujenMaara++;
                
            }
        }
        return ruutujenMaara;
    }

    public HashMap GetLumikerrosKoordinaateissa() {
        return this.lumikerrosKoordinaateissa;
    }
    
    public HashMap GetMerkittavanLumenKestoKoordinaateissa(){
        return this.merkittavanLumenKestoKoordinaateissa;
    }
    
    public HashMap GetMerkittavastaLumestaIlmoitettu(){
        return this.merkittavastaLumestaIlmoitettu;
    }
    public HashMap GetMerkittavastaLumestaIlmoitettuJoskus(){
        return this.merkittavastaLumestaIlmoitettuJoskus;
    }
    /**
     * Muuttaa lumen maaraksi 0 halutuissa koordinaateissa.
     * Jos kyseisen ruudun merkittavan lumen kestosta on ilmoitettu, asetetaan
     * merkittavastaLumestaIlmoiettu arvoksi false kyseisissa koordinaateissa.
     * @param koordinaatit koorinaatit, joista lumi halutaan poistaa
     */
    public void PoistaLumikerros(ArrayList<Integer> koordinaatit){
        this.lumikerrosKoordinaateissa.put(koordinaatit, 0.0);
        if((boolean)this.merkittavastaLumestaIlmoitettu.get(koordinaatit)){
            this.merkittavastaLumestaIlmoitettu.put(koordinaatit,false);
        }
        
    }
    
    /**
     * Lisaa HashMapin merkittavanLumenKestokoordinaateissa tietyn koordinaattiavaimen
     * viittaman arvon maaraa yhdella jos lumen maara koordinaateissa on enemman kuin 4.999.
     * Jos keston arvo on enemman kuin nolla mutta lunta ei ole pisteessa tarpeeksi, niin kesto asetetaan 
     * nollaan
     */
    public void PaivitaMerkittavanLumenKesto(){
        
        
        for (Object koordinaatit:this.merkittavanLumenKestoKoordinaateissa.keySet()){
            double lumenMaara=(double)this.lumikerrosKoordinaateissa.get(koordinaatit);
            int kesto=(int) this.merkittavanLumenKestoKoordinaateissa.get(koordinaatit);
            if(lumenMaara>4.999){
                kesto++;
                this.merkittavanLumenKestoKoordinaateissa.put(koordinaatit, kesto);
            }else if(kesto>0){
                this.merkittavanLumenKestoKoordinaateissa.put(koordinaatit, 0);
                
            }
        }
    }
    
    /**
     * Laskee monessako ruudussa on ollut yli 4.999 cm lunta kauemmin kuin 50 s.
     * Ei laske mukaan ruutuja joista on jo ilmoitettu aiemmin.
     * @return ruutujen maara joissa on ollut liian kauan lunta
     */
    public int MonessakoRuudussaOnOllutLuntaLiianKauanEikaOleImoitettu(){
        
        int ruutujenMaara=0;
        for (Object koordinaatit:this.lumikerrosKoordinaateissa.keySet()){
            int kesto=(int) this.merkittavanLumenKestoKoordinaateissa.get(koordinaatit);
             boolean onkoIlmoitettu=(boolean)this.merkittavastaLumestaIlmoitettu.get(koordinaatit);
            
            if(kesto>50 && !onkoIlmoitettu){
                ruutujenMaara++;
                this.merkittavastaLumestaIlmoitettu.put(koordinaatit, true);
                this.merkittavastaLumestaIlmoitettuJoskus.put(koordinaatit, true);
            }
        }
        
        return ruutujenMaara;
        
    }
}
