/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author virvemaa
 */
public class KululaskuriTest {

    private Kululaskuri laskuri;

    public KululaskuriTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        this.laskuri = new Kululaskuri();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void LaskeBensaKulutLaskeeOikein() {
        this.laskuri.LisaaBensakulu(4);
        assertEquals(20, this.laskuri.getKulujenMaara());
    }
    @Test
    public void LaskeBensaKulutEiTeeMitaanJosSyoteNegatiivinen(){
        this.laskuri.LisaaBensakulu(-4);
        assertEquals(0,this.laskuri.getKulujenMaara());
    }
    @Test
    public void LaskeKolariKulutLaskeeOikein(){
        this.laskuri.LisaaKolariKulutTonniPerKolari(3);
        assertEquals(3000,this.laskuri.getKulujenMaara());
    }
    @Test
    public void LaskeKolariKulutEiTeeMitaanJosSyoteNegatiivinen(){
        this.laskuri.LisaaKolariKulutTonniPerKolari(-4);
        assertEquals(0,this.laskuri.getKulujenMaara());
    }
    @Test
    public void LaskuriLaskeeYhteenOikein(){
        this.laskuri.LisaaBensakulu(4);
        this.laskuri.LisaaKolariKulutTonniPerKolari(2);
        assertEquals(2020,this.laskuri.getKulujenMaara());
    }
    @Test
    public void LisaaKolariKulutLumenMaaranMukaanJosEiLuntaEiKuluja(){
        HashMap lumikerrosKoordinaateissa=this.AnnaLumikerros();
        this.laskuri.LisaaKolariKulutLumenMaaranMukaan(lumikerrosKoordinaateissa);
        assertEquals(0,this.laskuri.getKulujenMaara());
    }
    @Test
    public void LisaaKolariKulutLumenMaaranMukaanJos2cmLuntaKulutOikein(){
        HashMap lumikerrosKoordinaateissa=this.AnnaLumikerros();
        ArrayList<Integer> piste=this.LuoKoordinaattiPiste(2, 0);
        lumikerrosKoordinaateissa.put(piste, 2.0);
        this.laskuri.LisaaKolariKulutLumenMaaranMukaan(lumikerrosKoordinaateissa);
        assertEquals(100,this.laskuri.getKulujenMaara());
    }
    @Test
    public void LisaaKolariKulutLumenMaaranMukaanJos20cmLuntaKulutOikein(){
        HashMap lumikerrosKoordinaateissa=this.AnnaLumikerros();
        ArrayList<Integer> piste=this.LuoKoordinaattiPiste(2, 0);
        lumikerrosKoordinaateissa.put(piste, 20.0);
        this.laskuri.LisaaKolariKulutLumenMaaranMukaan(lumikerrosKoordinaateissa);
        assertEquals(1000,this.laskuri.getKulujenMaara());
    }
    @Test
    public void LisaaKolariKulutLumenMaaranMukaanJos21cmLuntaKulutOikein(){
        HashMap lumikerrosKoordinaateissa=this.AnnaLumikerros();
        ArrayList<Integer> piste=this.LuoKoordinaattiPiste(2, 0);
        lumikerrosKoordinaateissa.put(piste, 21.0);
        this.laskuri.LisaaKolariKulutLumenMaaranMukaan(lumikerrosKoordinaateissa);
        assertEquals(1500,this.laskuri.getKulujenMaara());
    }
    
    @Test
    public void LisaaKolariKulutLumenMaaranMukaanJos2cmLuntaMonessaRuudussaKulutOikein(){
        HashMap lumikerrosKoordinaateissa=this.AnnaLumikerros();
        ArrayList<Integer> piste1=this.LuoKoordinaattiPiste(2, 0);
        lumikerrosKoordinaateissa.put(piste1, 2.0);
        ArrayList<Integer> piste2=this.LuoKoordinaattiPiste(2, 1);
        lumikerrosKoordinaateissa.put(piste2, 2.0);
        this.laskuri.LisaaKolariKulutLumenMaaranMukaan(lumikerrosKoordinaateissa);
        assertEquals(200,this.laskuri.getKulujenMaara());
    }
    @Test
    public void LisaaKolariKulutLumenMaaranMukaanJosLuntaMonessaRuudussaKulutOikein(){
        HashMap lumikerrosKoordinaateissa=this.AnnaLumikerros();
        ArrayList<Integer> piste1=this.LuoKoordinaattiPiste(2, 0);
        lumikerrosKoordinaateissa.put(piste1, 2.0);
        ArrayList<Integer> piste2=this.LuoKoordinaattiPiste(2, 1);
        lumikerrosKoordinaateissa.put(piste2, 21.0);
        this.laskuri.LisaaKolariKulutLumenMaaranMukaan(lumikerrosKoordinaateissa);
        assertEquals(1600,this.laskuri.getKulujenMaara());
    }
    
    
    public HashMap AnnaLumikerros(){
        Kartta kartta = new Kartta("src/main/java/tienhoitopeli/kartat/kartta1.txt");
        kartta.LueKartta();
        Lumikerros uusiLumikerros = new Lumikerros(kartta);
        uusiLumikerros.AlustaLumikerros();
        return uusiLumikerros.GetLumikerrosKoordinaateissa();
    }
    public ArrayList<Integer> LuoKoordinaattiPiste(int rivi,int sarake){
        ArrayList<Integer> koordinaattiPiste=new ArrayList<Integer>();
        koordinaattiPiste.add(rivi);
        koordinaattiPiste.add(sarake);
        return koordinaattiPiste;
    }
}
