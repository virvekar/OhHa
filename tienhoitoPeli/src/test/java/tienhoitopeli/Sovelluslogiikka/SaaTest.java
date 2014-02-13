/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhoitopeli.Sovelluslogiikka;

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
public class SaaTest {

    private Saaennuste ennuste;
    private Saa saa;

    public SaaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ennuste = new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste2.txt");
        ennuste.LueSaaennuste();
        saa = new Saa(ennuste);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void ArvoSateenPituusEiTeeNegatiivistaSadetta() {

        boolean onkoOllutNegatiivinen = false;
        for (int i = 0; i < 50; i++) {
            this.saa.ArvoSateenPituus(3);
            int pituus = this.saa.getSateenPituus();
            if (pituus < 0) {
                onkoOllutNegatiivinen = true;
            }
        }
        assertTrue(!onkoOllutNegatiivinen);
    }

    @Test
    public void ArvoSateenMaaraEiTeeNegatiivistaSadetta() {
        boolean onkoOllutNegatiivinen = false;
        for (int i = 0; i < 50; i++) {
            this.saa.ArvoSateenMaara(3);
            int pituus = this.saa.getSateenMaara();
            if (pituus < 0) {
                onkoOllutNegatiivinen = true;
            }
        }
        assertTrue(!onkoOllutNegatiivinen);
    }

    @Test
    public void ArvoSateenPituusEiTeeMitaanNegatiivisellaSyotteella() {
        this.saa.ArvoSateenPituus(-2);
        assertEquals(this.ennuste.GetEnnusteenPituus(), this.saa.getSateenPituus());
    }

    @Test
    public void ArvoSateenPituusEiTeeMitaanNollaSyotteella() {
        this.saa.ArvoSateenPituus(0);
        assertEquals(this.ennuste.GetEnnusteenPituus(), this.saa.getSateenPituus());
    }

    @Test
    public void ArvoSateenMaaraEiTeeMitaanNegatiivisellaSyotteella() {
        this.saa.ArvoSateenPituus(-2);
        assertEquals(this.ennuste.GetLumisateenMaara(), this.saa.getSateenMaara());
    }

    @Test
    public void ArvoSateenMaaraEiTeeMitaanNollaSyotteella() {
        this.saa.ArvoSateenPituus(0);
        assertEquals(this.ennuste.GetLumisateenMaara(), this.saa.getSateenMaara());
    }

    @Test
    public void ArvoSateenMaaraMuuttaaMaaraa() {

        boolean onkoMuuttunut = false;
        for (int i = 0; i < 50; i++) {
            this.saa.ArvoSateenMaara(3);
            int maara = this.saa.getSateenMaara();
            if (maara != this.ennuste.GetLumisateenMaara()) {
                onkoMuuttunut = true;
            }
        }
        assertTrue(onkoMuuttunut);
    }

    @Test
    public void ArvoSateenPituusMuuttaaPituutta() {

        boolean onkoMuuttunut = false;
        for (int i = 0; i < 50; i++) {
            this.saa.ArvoSateenPituus(3);
            int pituus = this.saa.getSateenPituus();
            if (pituus != this.ennuste.GetEnnusteenPituus()) {
                onkoMuuttunut = true;
            }
        }
        assertTrue(onkoMuuttunut);
    }
}
