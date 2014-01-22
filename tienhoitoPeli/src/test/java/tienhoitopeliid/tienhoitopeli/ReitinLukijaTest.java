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
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    public void NegatiivinenPisteEiRajojenSisalla() {
        Kartta kartta = new Kartta();
        kartta.LueKartta();
        Saaennuste ennuste = new Saaennuste();
        ennuste.LueSaaennuste();
        Lumikerros lumikerros = new Lumikerros(kartta, ennuste);
        ReitinLukija reitinLukija= new ReitinLukija(kartta, lumikerros);
        
        ArrayList<Integer> piste = new ArrayList<Integer>();
        piste.add(1);
        piste.add(1);
        boolean vastaus=reitinLukija.TarkistaOnkoRajojenSisalla(piste);
        assertEquals(false,vastaus);

    }
}
