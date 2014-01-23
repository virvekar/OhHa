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
public class KarttaTest {
    private Kartta kartta;
    
    public KarttaTest() {
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
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void LueKarttaTallettaaOikeanRiviMaaran() {
         this.kartta.LueKartta();
         ArrayList<String> karttaPohja=this.kartta.GetKarttaPohja();
         assertEquals(5,karttaPohja.size());
     
     }
      @Test
     public void LueKarttaTallettaaOikeanSarakeMaaran() {
         this.kartta.LueKartta();
         ArrayList<String> karttaPohja=this.kartta.GetKarttaPohja();
         assertEquals(5,karttaPohja.get(0).length());
     
     }
     @Test
     public void KartanKokoAntaaOikeanKoon(){
         this.kartta.LueKartta();
         ArrayList<Integer> kartanKoko=this.kartta.KartanKoko();
         ArrayList<Integer> koko=new ArrayList<Integer>();
         koko.add(5);
         koko.add(5);
         assertEquals(koko,kartanKoko);
     }
}
