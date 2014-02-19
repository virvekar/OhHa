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
public class SuoritaTienhoitoTest {

    private Lumikerros lumikerros;
    private ReitinLukija reitinLukija;
    private Kartta kartta;
    private Saaennuste ennuste;
    private Saa saa;
    private Auraaja auraaja;
    private TienhoidonSuorittaja tienhoidonSuorittaja;
    private ArrayList<ArrayList<Integer>> koordinaattiLista;

    public SuoritaTienhoitoTest() {
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
        reitinLukija = new ReitinLukija(kartta, lumikerros);
        
        auraaja = new Auraaja(lumikerros, reitinLukija);
        tienhoidonSuorittaja = new TienhoidonSuorittaja(auraaja, saa, lumikerros,reitinLukija);
        koordinaattiLista = new ArrayList<ArrayList<Integer>>();

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void SuoritaTienhoitoAuraa() {
        reitinLukija.KirjaaEnsimmainenPiste("1", "2");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        tienhoidonSuorittaja.SuoritaTienhoito();
        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        for (int i = 0; i <= 3; i++) {
            ArrayList<Integer> koordinaattiPiste = new ArrayList<Integer>();
            koordinaattiPiste.add(i);
            koordinaattiPiste.add(1);
            koordinaattiLista.add(koordinaattiPiste);
        }
        double lumiMaara = 0.0;
        for (int j = 0; j < 3; j++) {
            lumiMaara += (double) kerros.get(koordinaattiLista.get(j));
        }
        boolean vastaus = lumiMaara > -0.01 && lumiMaara < 0.01;
        assertEquals(vastaus, true);

    }
    @Test
    public void SuoritaTienhoitoEiAuraaJosEiReittia(){
        tienhoidonSuorittaja.SuoritaTienhoito();
        int lumiRuudut=lumikerros.laskeRuudutJoissaOnLunta();
        assertEquals(16,lumiRuudut);
    }

    @Test
    public void LaskeKulutToimii() {
        reitinLukija.KirjaaEnsimmainenPiste("1", "2");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        tienhoidonSuorittaja.SuoritaTienhoito();
        int kulut = tienhoidonSuorittaja.laskeKulutTonniPerKolari();
        assertEquals(15 + 13000, kulut);
    }
    
    @Test
    public void LaskeKulutLumenMaaranMukaanToimii(){
        reitinLukija.KirjaaEnsimmainenPiste("1", "2");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        tienhoidonSuorittaja.SuoritaTienhoito();
        int kulut=tienhoidonSuorittaja.laskeKulutLumenMaaranMukaan();
        assertEquals(15 + 500*13, kulut);
    }
    
    @Test
    public void LaskeKulutLumestaJokaOnMaassaLiianKauanToimii(){
        reitinLukija.LisaaAloitusAika("51");
        reitinLukija.KirjaaEnsimmainenPiste("1", "2");
        tienhoidonSuorittaja.SuoritaTienhoito();
        int kulut=tienhoidonSuorittaja.getKulut();
        assertEquals(kulut,200*15);
    }

    @Test
    public void LaskeKulutAntaaBensakulutJosKaikkiKadutAurattu() {
        reitinLukija.KirjaaEnsimmainenPiste("1", "2");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("j");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("l");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("l");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("l");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("i");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("i");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("l");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("j");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("j");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("j");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("j");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("l");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("i");    
        tienhoidonSuorittaja.SuoritaTienhoito();
        int kulut=tienhoidonSuorittaja.laskeKulutTonniPerKolari();
        assertEquals(20*5, kulut);
    }

    @Test
    public void PalautaSuurempiToimii() {
        int luku = tienhoidonSuorittaja.PalautaSuurempi(3, 5);
        assertEquals(5, luku);
    }
    
    
    
}
