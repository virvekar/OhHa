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
public class AuraajaTest {

    private Lumikerros lumikerros;
    private ReitinLukija reitinLukija;
    private Kartta kartta;
    private Saaennuste ennuste;
    private Saa saa;
    private Auraaja auraaja;

    public AuraajaTest() {
        kartta = new Kartta("src/main/java/tienhoitopeli/kartat/kartta1.txt");
        kartta.LueKartta();
        ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste1.txt");
        ennuste.LueSaaennuste();
        saa = new Saa(ennuste);
        lumikerros = new Lumikerros(kartta);
        lumikerros.AlustaLumikerros();
        lumikerros.LisaaLuntaYhdenSekunninSateenVerran(saa.getSateenMaara(), saa.getSateenPituus());
        reitinLukija = new ReitinLukija(kartta, lumikerros);
        reitinLukija.KirjaaEnsimmainenPiste("2", "2");
        reitinLukija.KirjaaReittiPisteJosKomentoKelpaa("k");
        
        auraaja = new Auraaja(lumikerros, reitinLukija);
        

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void AuraaSeuraavaPisteToimii() {
        auraaja.AuraaSeuraavaPiste();
        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit=this.LuoReittiPiste(1, 1);
        double kerrosKoordinaateissa=(double) kerros.get(koordinaatit);
        boolean vastaus=kerrosKoordinaateissa>-0.01 && kerrosKoordinaateissa<0.01;
        assertEquals(vastaus,true);

    }
    @Test
    public void AuraaSeuraavaPisteAuraaToisenkinPisteen(){
        auraaja.AuraaSeuraavaPiste();
        auraaja.AuraaSeuraavaPiste();
        HashMap kerros = lumikerros.GetLumikerrosKoordinaateissa();
        ArrayList<Integer> koordinaatit=this.LuoReittiPiste(2, 1);
        double kerrosKoordinaateissa=(double) kerros.get(koordinaatit);
        boolean vastaus=kerrosKoordinaateissa>-0.01 && kerrosKoordinaateissa<0.01;
        assertEquals(vastaus,true);
    }
    @Test
    public void AuraaSeuraavaPisteEiAuraaJosEiReittipistetta(){
        auraaja.AuraaSeuraavaPiste();
        auraaja.AuraaSeuraavaPiste();
        auraaja.AuraaSeuraavaPiste();
        assertEquals(2,auraaja.getReittiPisteNumero());
    }
    
    public ArrayList<Integer> LuoReittiPiste(int rivi, int sarake){
        ArrayList<Integer> koordinaatit=new ArrayList<Integer>();
        koordinaatit.add(rivi);
        koordinaatit.add(sarake);
        return koordinaatit;
    }
}
