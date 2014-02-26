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
public class LumikerrosTest {

    private Kartta kartta;
    private Lumikerros lumikerros;
    private Saaennuste ennuste;
    private Saa saa;
    private ArrayList<Integer> koordinaatit;

    public LumikerrosTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kartta = new Kartta("src/main/java/tienhoitopeli/kartat/kartta1.txt");
        kartta.LueKartta();
        ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste1.txt");
        ennuste.LueSaaennuste();
        saa = new Saa(ennuste);
        lumikerros = new Lumikerros(kartta);
        lumikerros.AlustaLumikerros();

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void LumikerroksestaTuleeOikeanKokoinen() {

        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        assertEquals(5 * 5, kerros.size());
    }

    @Test
    public void LumikerrosRakennuksenPaallaOnNegatiivinen() {

        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit = this.LuoReittiKoordinaattiPiste(0, 0);
        double kerrosKoordinaateissa = (double) kerros.get(koordinaatit);
        boolean vastaus = kerrosKoordinaateissa < 0;
        assertEquals(vastaus, true);
    }

    @Test
    public void LumikerrosKadullaOnAluksiNolla() {

        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit = this.LuoReittiKoordinaattiPiste(0, 1);
        double kerrosKoordinaateissa = (double) kerros.get(koordinaatit);
        boolean vastaus = kerrosKoordinaateissa > -0.01 && kerrosKoordinaateissa < 0.01;
        assertEquals(vastaus, true);
    }

    @Test
    public void LisaaLuntaYhdenSekunninSateenVerranKadulleLisaaLunta() {
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit = this.LuoReittiKoordinaattiPiste(0, 1);
        double kerrosKoordinaateissa = (double) kerros.get(koordinaatit);
        boolean vastaus = kerrosKoordinaateissa > 0.01;
        assertEquals(vastaus, true);
    }

    @Test
    public void LisaaLuntaYhdenSekunninSateenVerranKadulleEiLisaaRakennuksenPaalle() {
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit=this.LuoReittiKoordinaattiPiste(0,0);
        double kerrosKoordinaateissa = (double) kerros.get(koordinaatit);
        boolean vastaus = kerrosKoordinaateissa < -0.01;
        assertEquals(vastaus, true);
    }

    @Test
    public void MerkittavanLumenKestoAlustusOikein() {
        HashMap merkittavanLumenKesto = lumikerros.GetMerkittavanLumenKestoKoordinaateissa();
        ArrayList<Integer> koordinaatit=this.LuoReittiKoordinaattiPiste(0,0);
        int kesto = (int) merkittavanLumenKesto.get(koordinaatit);
        assertEquals(kesto, 0);
    }

    @Test
    public void MerkittavanLumenKestoOikeinJosLuntaKauan() {
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
        this.PaivitaLumikerros3Kertaa();
        HashMap merkittavanLumenKesto = lumikerros.GetMerkittavanLumenKestoKoordinaateissa();
        ArrayList<Integer> koordinaatit=this.LuoReittiKoordinaattiPiste(2,1);
        int kesto = (int) merkittavanLumenKesto.get(koordinaatit);
        assertEquals(kesto, 3);
    }

    @Test
    public void MerkittavanLumenKestoOikeinJosLumiPoistetaan() {
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
        this.PaivitaLumikerros3Kertaa();
        ArrayList<Integer> koordinaatit=this.LuoReittiKoordinaattiPiste(2,1);
        lumikerros.PoistaLumikerros(koordinaatit);
        lumikerros.PaivitaMerkittavanLumenKesto();
        HashMap merkittavanLumenKesto = lumikerros.GetMerkittavanLumenKestoKoordinaateissa();
        int kesto = (int) merkittavanLumenKesto.get(koordinaatit);
        assertEquals(kesto, 0);
    }

    @Test
    public void MonessakoRuudussaOllutLuntaLiianKauanAntaaAluksiNolla() {
        assertEquals(lumikerros.MonessakoRuudussaOnOllutLuntaLiianKauanEikaOleImoitettu(), 0);
    }

    @Test
    public void MonessakoRuudussaOllutLuntaLiianKauanAntaaOikeanMaaran() {
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
        this.PaivitaLumikerros55Kertaa();
        assertEquals(lumikerros.MonessakoRuudussaOnOllutLuntaLiianKauanEikaOleImoitettu(), 16);
    }

    @Test
    public void MonessakoRuudussaOllutLuntaLiianKauanAntaaOikeanMaaranTokallaKerralla() {
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
        this.PaivitaLumikerros55Kertaa();
        lumikerros.MonessakoRuudussaOnOllutLuntaLiianKauanEikaOleImoitettu();
        assertEquals(lumikerros.MonessakoRuudussaOnOllutLuntaLiianKauanEikaOleImoitettu(), 0);
    }

    @Test
    public void MonessakoRuudussaOllutLuntaLiianKauanAntaaOikeanMaaranTokallaKerrallaKunAurattu() {
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
        this.PaivitaLumikerros55Kertaa();
        lumikerros.MonessakoRuudussaOnOllutLuntaLiianKauanEikaOleImoitettu();
        ArrayList<Integer> koordinaatit = this.LuoReittiKoordinaattiPiste(0, 1);
        lumikerros.PoistaLumikerros(koordinaatit);
        lumikerros.PaivitaMerkittavanLumenKesto();
        assertEquals(lumikerros.MonessakoRuudussaOnOllutLuntaLiianKauanEikaOleImoitettu(), 0);
    }

    @Test
    public void MonessakoRuudussaOllutLuntaLiianKauanAntaaOikeanMaaranTokallaKerrallaKunAurattuJaLuntaTullutLisaa() {
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
        this.PaivitaLumikerros55Kertaa();
        lumikerros.MonessakoRuudussaOnOllutLuntaLiianKauanEikaOleImoitettu();
        ArrayList<Integer> koordinaatit = this.LuoReittiKoordinaattiPiste(0, 1);
        lumikerros.PoistaLumikerros(koordinaatit);
        lumikerros.PaivitaMerkittavanLumenKesto();
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
        this.PaivitaLumikerros55Kertaa();
        assertEquals(lumikerros.MonessakoRuudussaOnOllutLuntaLiianKauanEikaOleImoitettu(), 1);
    }

    @Test
    public void MerkittavastaLumestaIlmoitettuJoskusOikeinJosEiIlmoituksia(){
        HashMap ilmoitukset=this.lumikerros.GetMerkittavastaLumestaIlmoitettuJoskus();
        ArrayList<Integer> koordinaatit=this.LuoReittiKoordinaattiPiste(0, 1);
        assertFalse((boolean)ilmoitukset.get(koordinaatit));
    }
    @Test
    public void MerkittavastaLumestaIlmoitettuJoskusOikeinJosIlmoitus(){
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
        this.PaivitaLumikerros55Kertaa();
        lumikerros.MonessakoRuudussaOnOllutLuntaLiianKauanEikaOleImoitettu();
        HashMap ilmoitukset=this.lumikerros.GetMerkittavastaLumestaIlmoitettuJoskus();
        ArrayList<Integer> koordinaatit=this.LuoReittiKoordinaattiPiste(0, 1);
        assertTrue((boolean)ilmoitukset.get(koordinaatit));
    }
    
    public void PaivitaLumikerros55Kertaa() {
        for (int i = 1; i < 55; i++) {
            lumikerros.PaivitaMerkittavanLumenKesto();
        }
    }
    public void PaivitaLumikerros3Kertaa() {
        for (int i = 1; i < 4; i++) {
            lumikerros.PaivitaMerkittavanLumenKesto();
        }
    }

    public ArrayList<Integer> LuoReittiKoordinaattiPiste(int rivi, int sarake) {
        ArrayList<Integer> koordinaatit = new ArrayList<Integer>();
        koordinaatit.add(rivi);
        koordinaatit.add(sarake);
        return koordinaatit;
    }

}
