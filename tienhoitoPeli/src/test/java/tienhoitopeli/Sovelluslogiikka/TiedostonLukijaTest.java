/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

import java.util.ArrayList;
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
public class TiedostonLukijaTest {

    public TiedostonLukijaTest() {
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
    public void LueTiedostoLukeeOikeanKokoisenTiedoston() {
        TiedostonLukija tiedostonLukija = new TiedostonLukija("src/main/java/tienhoitopeli/kartat/kartta1.txt");
        ArrayList<String> tiedosto=tiedostonLukija.LueTiedosto();
        assertEquals(5,tiedosto.size());
    }
    @Test
    public void LueTiedostoPalauttaaTyhjanJosTiedostoaEiLoydy(){
        TiedostonLukija tiedostonLukija = new TiedostonLukija("src/main/java/tienhoitopeli/kartat/kartta.txt");
        ArrayList<String> tiedosto=tiedostonLukija.LueTiedosto();
        assertEquals(0,tiedosto.size());
    }
}
