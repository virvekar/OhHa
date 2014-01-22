package tienhoitopeliid.tienhoitopeli;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tienhoitopeliid.tienhoitopeli.Sovelluslogiikka.Kartta;
import tienhoitopeliid.tienhoitopeli.Sovelluslogiikka.Lumikerros;
import tienhoitopeliid.tienhoitopeli.Sovelluslogiikka.ReitinLukija;
import tienhoitopeliid.tienhoitopeli.Sovelluslogiikka.Saaennuste;

/**
 *
 * @author virvemaa
 */
public class ReitinLukijaTest {

    Kartta kartta;
    Saaennuste ennuste;
    Lumikerros lumikerros;
    ReitinLukija reitinLukija;
    ArrayList<Integer> piste;

    public ReitinLukijaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        kartta = new Kartta();
        kartta.LueKartta();
        ennuste = new Saaennuste();
        ennuste.LueSaaennuste();
        lumikerros = new Lumikerros(kartta, ennuste);
        lumikerros.AlustaLumikerros();
        reitinLukija = new ReitinLukija(kartta, lumikerros);
        piste = new ArrayList<Integer>();

    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void NegatiivinenVaakariviEiRajojenSisalla() {

        piste.add(-1);
        piste.add(0);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(false, vastaus);
    }

    @Test
    public void NegatiivinenPystyriviEiRajojenSisalla() {

        piste.add(0);
        piste.add(-1);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(false, vastaus);
    }

    @Test
    public void LiianSuuriVaakaRiviEiRajojenSisalla() {
        piste.add(5);
        piste.add(3);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(false, vastaus);
    }

    @Test
    public void LiianSuuriPystyRiviEiRajojenSisalla() {
        piste.add(3);
        piste.add(5);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(false, vastaus);
    }

    @Test
    public void OikeainPuoleisinRiviRajojenSisalla() {
        piste.add(4);
        piste.add(2);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(true, vastaus);
    }

    @Test
    public void AlinRiviRajojenSisalla() {
        piste.add(2);
        piste.add(4);
        boolean vastaus = this.reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(true, vastaus);
    }

    @Test
    public void RakennusPisteEiKelpaaReitille() {
        piste.add(0);
        piste.add(0);
        boolean vastaus = this.reitinLukija.TarkistaOnkoPisteKadulla(piste);
        assertEquals(false, vastaus);
    }

    @Test
    public void KatuPisteKelpaaReitille() {
        piste.add(0);
        piste.add(1);
        boolean vastaus = this.reitinLukija.TarkistaOnkoPisteKadulla(piste);
        assertEquals(true, vastaus);
    }

    @Test
    public void KunnonPisteLisataanReitille() {
        piste.add(0);
        piste.add(1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> reittiPiste = reitti.get(0);
        assertEquals(piste, reittiPiste);
    }

    @Test
    public void HuonoaPistettaEiLisataReitille() {
        piste.add(0);
        piste.add(0);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(0, reitti.size());
    }

    @Test
    public void LuoUusiKoordinaattiToimii() {
        int x = 1;
        int y = 2;
        ArrayList<Integer> piste1 = this.reitinLukija.LuoUusiKoordinaattiPiste(x, y);
        ArrayList<Integer> piste2 = new ArrayList<Integer>();
        piste2.add(x);
        piste2.add(y);
        assertEquals(piste2, piste1);

    }
    @Test 
    public void LiikutaYlosLiikuttaaYlos(){
        piste.add(1);
        piste.add(1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuYlos();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();
        ArrayList<Integer> uusiPiste=new ArrayList<Integer>();
        uusiPiste.add(0);
        uusiPiste.add(1);
        assertEquals(uusiPiste,reitti.get(1));
    }
    @Test 
    public void LiikutaYlosEiLiikutaYlosJosYlapuolellaRakennus(){
        piste.add(2);
        piste.add(0);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuYlos();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(1,reitti.size());
    }
     @Test 
    public void LiikutaYlosEiLiikutaYlosJosMeneeRajanYli(){
        piste.add(0);
        piste.add(1);
        boolean onnistuiko = this.reitinLukija.TarkistaPisteJaLisaaReitille(piste);
        reitinLukija.LiikuYlos();
        ArrayList<ArrayList<Integer>> reitti = this.reitinLukija.GetReitti();

        assertEquals(1,reitti.size());
    }
}
