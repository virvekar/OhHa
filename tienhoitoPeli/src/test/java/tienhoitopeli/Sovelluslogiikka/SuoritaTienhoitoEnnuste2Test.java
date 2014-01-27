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
public class SuoritaTienhoitoEnnuste2Test {

    private Lumikerros lumikerros;
    private ReitinLukija reitinLukija;
    private Kartta kartta;
    private Saaennuste ennuste;
    private Saa saa;
    private Auraaja auraaja;
    private TienhoidonSuorittaja tienhoidonSuorittaja;
    private ArrayList<Integer> koordinaattiPiste;

    public SuoritaTienhoitoEnnuste2Test() {
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
        ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste2.txt");
        ennuste.LueSaaennuste();
        saa = new Saa(ennuste);
        lumikerros = new Lumikerros(kartta);
        lumikerros.AlustaLumikerros();
        reitinLukija = new ReitinLukija(kartta, lumikerros);
        auraaja = new Auraaja(lumikerros, reitinLukija);
        tienhoidonSuorittaja = new TienhoidonSuorittaja(auraaja, saa, lumikerros);
        koordinaattiPiste=new ArrayList<Integer>();
        reitinLukija.KirjaaEnsimmainenPiste("1", "2");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void EkassaRuudussaOikeaMaaraLunta() {
        koordinaattiPiste.add(0);
        koordinaattiPiste.add(1);
        tienhoidonSuorittaja.SuoritaTienhoito();
        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        double lumiMaara=(double) kerros.get(koordinaattiPiste);
        boolean vastaus = lumiMaara > 7.99 && lumiMaara < 8.01;
        assertEquals(vastaus, true);

    }
     @Test
    public void TokassaRuudussaOikeaMaaraLunta() {
        koordinaattiPiste.add(1);
        koordinaattiPiste.add(1);
        tienhoidonSuorittaja.SuoritaTienhoito();
        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        double lumiMaara=(double) kerros.get(koordinaattiPiste);
        boolean vastaus = lumiMaara > 5.99 && lumiMaara < 6.01;
        assertEquals(vastaus, true);

    }
    @Test
    public void VikassaRuudussaOikeaMaaraLunta() {

        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("j");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("l");
        koordinaattiPiste.add(2);
        koordinaattiPiste.add(1);
        tienhoidonSuorittaja.SuoritaTienhoito();
        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        double lumiMaara=(double) kerros.get(koordinaattiPiste);
        boolean vastaus = lumiMaara > -0.01 && lumiMaara < 0.01;
        assertEquals(vastaus, true);
    }
     @Test
    public void AuraamattomassaRuudussaOikeaMaaraLunta() {
        koordinaattiPiste.add(4);
        koordinaattiPiste.add(1);
        tienhoidonSuorittaja.SuoritaTienhoito();
        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        double lumiMaara=(double) kerros.get(koordinaattiPiste);
        boolean vastaus = lumiMaara > 9.99 && lumiMaara < 10.01;
        assertEquals(vastaus, true);
    }
}
