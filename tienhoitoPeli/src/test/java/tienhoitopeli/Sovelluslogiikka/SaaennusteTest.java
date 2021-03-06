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
public class SaaennusteTest {
    private Saaennuste ennuste;
    
    public SaaennusteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ennuste=new Saaennuste("src/main/java/tienhoitopeli/saaennusteet/saaennuste1.txt");
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void LueSaaennusteSaaOikeanEnnusteenPituuden() {
         this.ennuste.LueSaaennuste();
         assertEquals(1,this.ennuste.GetEnnusteenPituus());
     }
     @Test
     public void LueSaaennusteSaaOikeanLumisateeenMaaran() {
         this.ennuste.LueSaaennuste();
         assertEquals(10,this.ennuste.GetLumisateenMaara());
     }
     @Test
     public void OnkoPositiivinenPalauttaaOikenNollaSyotteella(){
         assertFalse(this.ennuste.OnkoPositiivinen(0));
     }
     @Test
     public void OnkoPositiivinenPalauttaaOikenNegatiivisellaSyotteella(){
         assertFalse(this.ennuste.OnkoPositiivinen(-3));
     }
     @Test
     public void OnkoPositiivinenPalauttaaOikenPositiivisellaSyotteella(){
         assertTrue(this.ennuste.OnkoPositiivinen(1));
     }
     @Test
     public void OnkoNollaTaiIsompiPalauttaaOikeinNollaSyotteelle(){
         assertTrue(this.ennuste.OnkoNollaTaiIsompi(0));
     }
     @Test
     public void OnkoNollaTaiIsompiPalauttaaOikeinNegatiiviselleSyotteelle(){
         assertFalse(this.ennuste.OnkoNollaTaiIsompi(-1));
     }
     @Test
     public void OnkoNollaTaiIsompiPalauttaaOikeinPositiiviselleSyotteelle(){
         assertTrue(this.ennuste.OnkoNollaTaiIsompi(1));
     }
     @Test
     public void OnkoIntegerPalauttaaOkeinNumeroSyotteelle(){
         assertTrue(this.ennuste.OnkoInteger("2"));
     }
     @Test
     public void OnkoIntegerPalauttaaOkeinHuonolleSyotteelle(){
         assertFalse(this.ennuste.OnkoInteger("e"));
     }
      @Test
     public void OnkoIntegerPalauttaaOkeinTyhjalleSyotteelle(){
         assertFalse(this.ennuste.OnkoInteger(""));
     }
}
