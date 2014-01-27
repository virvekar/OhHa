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
        this.laskuri.LisaaKolariKulut(3);
        assertEquals(3000,this.laskuri.getKulujenMaara());
    }
    @Test
    public void LaskeKolariKulutEiTeeMitaanJosSyoteNegatiivinen(){
        this.laskuri.LisaaKolariKulut(-4);
        assertEquals(0,this.laskuri.getKulujenMaara());
    }
    @Test
    public void LaskuriLaskeeYhteenOikein(){
        this.laskuri.LisaaBensakulu(4);
        this.laskuri.LisaaKolariKulut(2);
        assertEquals(2020,this.laskuri.getKulujenMaara());
    }
}
